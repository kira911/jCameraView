/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.control;

import br.com.fox.control.exceptions.NonexistentEntityException;
import br.com.fox.db.Cliente;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import br.com.fox.db.Contato;
import java.util.ArrayList;
import java.util.List;
import br.com.fox.db.Usuario;
import br.com.fox.db.Zona;

/**
 *
 * @author Dvr
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("jCameraViewPU");

    public ClienteJpaController() {
        
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getContatoList() == null) {
            cliente.setContatoList(new ArrayList<Contato>());
        }
        if (cliente.getUsuarioList() == null) {
            cliente.setUsuarioList(new ArrayList<Usuario>());
        }
        if (cliente.getZonaList() == null) {
            cliente.setZonaList(new ArrayList<Zona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Contato> attachedContatoList = new ArrayList<Contato>();
            for (Contato contatoListContatoToAttach : cliente.getContatoList()) {
                contatoListContatoToAttach = em.getReference(contatoListContatoToAttach.getClass(), contatoListContatoToAttach.getIdcontatos());
                attachedContatoList.add(contatoListContatoToAttach);
            }
            cliente.setContatoList(attachedContatoList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : cliente.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdusuarios());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            cliente.setUsuarioList(attachedUsuarioList);
            List<Zona> attachedZonaList = new ArrayList<Zona>();
            for (Zona zonaListZonaToAttach : cliente.getZonaList()) {
                zonaListZonaToAttach = em.getReference(zonaListZonaToAttach.getClass(), zonaListZonaToAttach.getIdzonas());
                attachedZonaList.add(zonaListZonaToAttach);
            }
            cliente.setZonaList(attachedZonaList);
            em.persist(cliente);
            for (Contato contatoListContato : cliente.getContatoList()) {
                Cliente oldIdclienteOfContatoListContato = contatoListContato.getIdcliente();
                contatoListContato.setIdcliente(cliente);
                contatoListContato = em.merge(contatoListContato);
                if (oldIdclienteOfContatoListContato != null) {
                    oldIdclienteOfContatoListContato.getContatoList().remove(contatoListContato);
                    oldIdclienteOfContatoListContato = em.merge(oldIdclienteOfContatoListContato);
                }
            }
            for (Usuario usuarioListUsuario : cliente.getUsuarioList()) {
                Cliente oldIdclienteOfUsuarioListUsuario = usuarioListUsuario.getIdcliente();
                usuarioListUsuario.setIdcliente(cliente);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldIdclienteOfUsuarioListUsuario != null) {
                    oldIdclienteOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldIdclienteOfUsuarioListUsuario = em.merge(oldIdclienteOfUsuarioListUsuario);
                }
            }
            for (Zona zonaListZona : cliente.getZonaList()) {
                Cliente oldIdclienteOfZonaListZona = zonaListZona.getIdcliente();
                zonaListZona.setIdcliente(cliente);
                zonaListZona = em.merge(zonaListZona);
                if (oldIdclienteOfZonaListZona != null) {
                    oldIdclienteOfZonaListZona.getZonaList().remove(zonaListZona);
                    oldIdclienteOfZonaListZona = em.merge(oldIdclienteOfZonaListZona);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            List<Contato> contatoListOld = persistentCliente.getContatoList();
            List<Contato> contatoListNew = cliente.getContatoList();
            List<Usuario> usuarioListOld = persistentCliente.getUsuarioList();
            List<Usuario> usuarioListNew = cliente.getUsuarioList();
            List<Zona> zonaListOld = persistentCliente.getZonaList();
            List<Zona> zonaListNew = cliente.getZonaList();
            List<Contato> attachedContatoListNew = new ArrayList<Contato>();
            for (Contato contatoListNewContatoToAttach : contatoListNew) {
                contatoListNewContatoToAttach = em.getReference(contatoListNewContatoToAttach.getClass(), contatoListNewContatoToAttach.getIdcontatos());
                attachedContatoListNew.add(contatoListNewContatoToAttach);
            }
            contatoListNew = attachedContatoListNew;
            cliente.setContatoList(contatoListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdusuarios());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            cliente.setUsuarioList(usuarioListNew);
            List<Zona> attachedZonaListNew = new ArrayList<Zona>();
            for (Zona zonaListNewZonaToAttach : zonaListNew) {
                zonaListNewZonaToAttach = em.getReference(zonaListNewZonaToAttach.getClass(), zonaListNewZonaToAttach.getIdzonas());
                attachedZonaListNew.add(zonaListNewZonaToAttach);
            }
            zonaListNew = attachedZonaListNew;
            cliente.setZonaList(zonaListNew);
            cliente = em.merge(cliente);
            for (Contato contatoListOldContato : contatoListOld) {
                if (!contatoListNew.contains(contatoListOldContato)) {
                    contatoListOldContato.setIdcliente(null);
                    contatoListOldContato = em.merge(contatoListOldContato);
                }
            }
            for (Contato contatoListNewContato : contatoListNew) {
                if (!contatoListOld.contains(contatoListNewContato)) {
                    Cliente oldIdclienteOfContatoListNewContato = contatoListNewContato.getIdcliente();
                    contatoListNewContato.setIdcliente(cliente);
                    contatoListNewContato = em.merge(contatoListNewContato);
                    if (oldIdclienteOfContatoListNewContato != null && !oldIdclienteOfContatoListNewContato.equals(cliente)) {
                        oldIdclienteOfContatoListNewContato.getContatoList().remove(contatoListNewContato);
                        oldIdclienteOfContatoListNewContato = em.merge(oldIdclienteOfContatoListNewContato);
                    }
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    usuarioListOldUsuario.setIdcliente(null);
                    usuarioListOldUsuario = em.merge(usuarioListOldUsuario);
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Cliente oldIdclienteOfUsuarioListNewUsuario = usuarioListNewUsuario.getIdcliente();
                    usuarioListNewUsuario.setIdcliente(cliente);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldIdclienteOfUsuarioListNewUsuario != null && !oldIdclienteOfUsuarioListNewUsuario.equals(cliente)) {
                        oldIdclienteOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldIdclienteOfUsuarioListNewUsuario = em.merge(oldIdclienteOfUsuarioListNewUsuario);
                    }
                }
            }
            for (Zona zonaListOldZona : zonaListOld) {
                if (!zonaListNew.contains(zonaListOldZona)) {
                    zonaListOldZona.setIdcliente(null);
                    zonaListOldZona = em.merge(zonaListOldZona);
                }
            }
            for (Zona zonaListNewZona : zonaListNew) {
                if (!zonaListOld.contains(zonaListNewZona)) {
                    Cliente oldIdclienteOfZonaListNewZona = zonaListNewZona.getIdcliente();
                    zonaListNewZona.setIdcliente(cliente);
                    zonaListNewZona = em.merge(zonaListNewZona);
                    if (oldIdclienteOfZonaListNewZona != null && !oldIdclienteOfZonaListNewZona.equals(cliente)) {
                        oldIdclienteOfZonaListNewZona.getZonaList().remove(zonaListNewZona);
                        oldIdclienteOfZonaListNewZona = em.merge(oldIdclienteOfZonaListNewZona);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Contato> contatoList = cliente.getContatoList();
            for (Contato contatoListContato : contatoList) {
                contatoListContato.setIdcliente(null);
                contatoListContato = em.merge(contatoListContato);
            }
            List<Usuario> usuarioList = cliente.getUsuarioList();
            for (Usuario usuarioListUsuario : usuarioList) {
                usuarioListUsuario.setIdcliente(null);
                usuarioListUsuario = em.merge(usuarioListUsuario);
            }
            List<Zona> zonaList = cliente.getZonaList();
            for (Zona zonaListZona : zonaList) {
                zonaListZona.setIdcliente(null);
                zonaListZona = em.merge(zonaListZona);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    

    
}
