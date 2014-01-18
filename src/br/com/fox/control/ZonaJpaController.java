/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.control;

import br.com.fox.control.exceptions.NonexistentEntityException;
import br.com.fox.db.Zona;
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
public class ZonaJpaController implements Serializable {

    public ZonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public ZonaJpaController() {
        
    }    
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jCameraViewPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Zona zona) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idcliente = zona.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                zona.setIdcliente(idcliente);
            }
            em.persist(zona);
            if (idcliente != null) {
                idcliente.getZonaList().add(zona);
                idcliente = em.merge(idcliente);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Zona zona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Zona persistentZona = em.find(Zona.class, zona.getIdzonas());
            Cliente idclienteOld = persistentZona.getIdcliente();
            Cliente idclienteNew = zona.getIdcliente();
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                zona.setIdcliente(idclienteNew);
            }
            zona = em.merge(zona);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getZonaList().remove(zona);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getZonaList().add(zona);
                idclienteNew = em.merge(idclienteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = zona.getIdzonas();
                if (findZona(id) == null) {
                    throw new NonexistentEntityException("The zona with id " + id + " no longer exists.");
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
            Zona zona;
            try {
                zona = em.getReference(Zona.class, id);
                zona.getIdzonas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The zona with id " + id + " no longer exists.", enfe);
            }
            Cliente idcliente = zona.getIdcliente();
            if (idcliente != null) {
                idcliente.getZonaList().remove(zona);
                idcliente = em.merge(idcliente);
            }
            em.remove(zona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Zona> findZonaEntities() {
        return findZonaEntities(true, -1, -1);
    }

    public List<Zona> findZonaEntities(int maxResults, int firstResult) {
        return findZonaEntities(false, maxResults, firstResult);
    }

    private List<Zona> findZonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Zona.class));
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

    public Zona findZona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Zona.class, id);
        } finally {
            em.close();
        }
    }

    public int getZonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Zona> rt = cq.from(Zona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Zona> getCameras(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT z FROM Zona z WHERE "
                    + "z.idcliente.idcliente = '" + cliente.getIdcliente() + "' and z.camera <> 0");

            return (List<Zona>) q.getResultList();
        } finally {
            em.close();
        }
    }        
    
}
