/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.control;

import br.com.fox.control.exceptions.NonexistentEntityException;
import br.com.fox.db.SinalRouter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Users;
import br.com.fox.db.Cliente;

/**
 *
 * @author Dvr
 */
public class SinalRouterJpaController implements Serializable {

    public SinalRouterJpaController() {
    }

    public SinalRouterJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jCameraViewPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SinalRouter sinalRouter) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users username = sinalRouter.getUsername();
            if (username != null) {
                username = em.getReference(username.getClass(), username.getUsername());
                sinalRouter.setUsername(username);
            }
            Cliente codCli = sinalRouter.getCodCli();
            if (codCli != null) {
                codCli = em.getReference(codCli.getClass(), codCli.getIdcliente());
                sinalRouter.setCodCli(codCli);
            }
            em.persist(sinalRouter);
            if (username != null) {
                username.getSinalRouterList().add(sinalRouter);
                username = em.merge(username);
            }
            if (codCli != null) {
                codCli.getSinalRouterList().add(sinalRouter);
                codCli = em.merge(codCli);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SinalRouter sinalRouter) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SinalRouter persistentSinalRouter = em.find(SinalRouter.class, sinalRouter.getIdsinalRouter());
            Users usernameOld = persistentSinalRouter.getUsername();
            Users usernameNew = sinalRouter.getUsername();
            Cliente codCliOld = persistentSinalRouter.getCodCli();
            Cliente codCliNew = sinalRouter.getCodCli();
            if (usernameNew != null) {
                usernameNew = em.getReference(usernameNew.getClass(), usernameNew.getUsername());
                sinalRouter.setUsername(usernameNew);
            }
            if (codCliNew != null) {
                codCliNew = em.getReference(codCliNew.getClass(), codCliNew.getIdcliente());
                sinalRouter.setCodCli(codCliNew);
            }
            sinalRouter = em.merge(sinalRouter);
            if (usernameOld != null && !usernameOld.equals(usernameNew)) {
                usernameOld.getSinalRouterList().remove(sinalRouter);
                usernameOld = em.merge(usernameOld);
            }
            if (usernameNew != null && !usernameNew.equals(usernameOld)) {
                usernameNew.getSinalRouterList().add(sinalRouter);
                usernameNew = em.merge(usernameNew);
            }
            if (codCliOld != null && !codCliOld.equals(codCliNew)) {
                codCliOld.getSinalRouterList().remove(sinalRouter);
                codCliOld = em.merge(codCliOld);
            }
            if (codCliNew != null && !codCliNew.equals(codCliOld)) {
                codCliNew.getSinalRouterList().add(sinalRouter);
                codCliNew = em.merge(codCliNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sinalRouter.getIdsinalRouter();
                if (findSinalRouter(id) == null) {
                    throw new NonexistentEntityException("The sinalRouter with id " + id + " no longer exists.");
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
            SinalRouter sinalRouter;
            try {
                sinalRouter = em.getReference(SinalRouter.class, id);
                sinalRouter.getIdsinalRouter();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sinalRouter with id " + id + " no longer exists.", enfe);
            }
            Users username = sinalRouter.getUsername();
            if (username != null) {
                username.getSinalRouterList().remove(sinalRouter);
                username = em.merge(username);
            }
            Cliente codCli = sinalRouter.getCodCli();
            if (codCli != null) {
                codCli.getSinalRouterList().remove(sinalRouter);
                codCli = em.merge(codCli);
            }
            em.remove(sinalRouter);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SinalRouter> findSinalRouterEntities() {
        return findSinalRouterEntities(true, -1, -1);
    }

    public List<SinalRouter> findSinalRouterEntities(int maxResults, int firstResult) {
        return findSinalRouterEntities(false, maxResults, firstResult);
    }

    private List<SinalRouter> findSinalRouterEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SinalRouter.class));
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

    public SinalRouter findSinalRouter(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SinalRouter.class, id);
        } finally {
            em.close();
        }
    }

    public int getSinalRouterCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SinalRouter> rt = cq.from(SinalRouter.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
