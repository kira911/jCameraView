/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.control;

import br.com.fox.control.exceptions.NonexistentEntityException;
import br.com.fox.db.Contato;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Cliente;

/**
 *
 * @author Dvr
 */
public class ContatoJpaController implements Serializable {

    public ContatoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Contato contato) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idcliente = contato.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                contato.setIdcliente(idcliente);
            }
            em.persist(contato);
            if (idcliente != null) {
                idcliente.getContatoList().add(contato);
                idcliente = em.merge(idcliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contato contato) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contato persistentContato = em.find(Contato.class, contato.getIdcontatos());
            Cliente idclienteOld = persistentContato.getIdcliente();
            Cliente idclienteNew = contato.getIdcliente();
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                contato.setIdcliente(idclienteNew);
            }
            contato = em.merge(contato);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getContatoList().remove(contato);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getContatoList().add(contato);
                idclienteNew = em.merge(idclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contato.getIdcontatos();
                if (findContato(id) == null) {
                    throw new NonexistentEntityException("The contato with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contato contato;
            try {
                contato = em.getReference(Contato.class, id);
                contato.getIdcontatos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contato with id " + id + " no longer exists.", enfe);
            }
            Cliente idcliente = contato.getIdcliente();
            if (idcliente != null) {
                idcliente.getContatoList().remove(contato);
                idcliente = em.merge(idcliente);
            }
            em.remove(contato);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contato> findContatoEntities() {
        return findContatoEntities(true, -1, -1);
    }

    public List<Contato> findContatoEntities(int maxResults, int firstResult) {
        return findContatoEntities(false, maxResults, firstResult);
    }

    private List<Contato> findContatoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contato.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Contato findContato(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contato.class, id);
        } finally {
            em.close();
        }
    }

    public int getContatoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contato> rt = cq.from(Contato.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
