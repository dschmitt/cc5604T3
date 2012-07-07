package model;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "SessionEJB", mappedName = "moneycontrol-Model-SessionEJB")
public class SessionEJBBean implements SessionEJB, SessionEJBLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public SessionEJBBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public TransaccionInterna persistTransaccionInterna(TransaccionInterna transaccionInterna) {
        em.persist(transaccionInterna);
        return transaccionInterna;
    }

    public TransaccionInterna mergeTransaccionInterna(TransaccionInterna transaccionInterna) {
        return em.merge(transaccionInterna);
    }

    public void removeTransaccionInterna(TransaccionInterna transaccionInterna) {
        transaccionInterna =
                em.find(TransaccionInterna.class, new TransaccionInternaPK(transaccionInterna.getCuentaIdcuenta(),
                                                                           transaccionInterna.getTransaccionIdtransaccion()));
        em.remove(transaccionInterna);
    }

    /** <code>select o from TransaccionInterna o</code> */
    public List<TransaccionInterna> getTransaccionInternaFindAll() {
        return em.createNamedQuery("TransaccionInterna.findAll").getResultList();
    }

    public Categoria persistCategoria(Categoria categoria) {
        em.persist(categoria);
        return categoria;
    }

    public Categoria mergeCategoria(Categoria categoria) {
        return em.merge(categoria);
    }

    public void removeCategoria(Categoria categoria) {
        categoria = em.find(Categoria.class, categoria.getIdcategoria());
        em.remove(categoria);
    }

    /** <code>select o from Categoria o</code> */
    public List<Categoria> getCategoriaFindAll() {
        return em.createNamedQuery("Categoria.findAll").getResultList();
    }

    public Usuario persistUsuario(Usuario usuario) {
        em.persist(usuario);
        return usuario;
    }

    public Usuario mergeUsuario(Usuario usuario) {
        return em.merge(usuario);
    }

    public void removeUsuario(Usuario usuario) {
        usuario = em.find(Usuario.class, usuario.getNombre());
        em.remove(usuario);
    }

    /** <code>select o from Usuario o</code> */
    public List<Usuario> getUsuarioFindAll() {
        return em.createNamedQuery("Usuario.findAll").getResultList();
    }

    /** <code>select o from Usuario o where o.nombre = :nombre</code> */
    public List<Usuario> getUsuarioFindByNombre(String nombre) {
        return em.createNamedQuery("Usuario.findByNombre").setParameter("nombre", nombre).getResultList();
    }

    /** <code>select o from Usuario o where o.nombre = :nombre and o.contraseña = :clave</code> */
    public List<Usuario> getUsuarioLogin(String nombre, String clave) {
        return em.createNamedQuery("Usuario.login").setParameter("nombre", nombre).setParameter("clave",
                                                                                                clave).getResultList();
    }

    public Prestamo persistPrestamo(Prestamo prestamo) {
        em.persist(prestamo);
        return prestamo;
    }

    public Prestamo mergePrestamo(Prestamo prestamo) {
        return em.merge(prestamo);
    }

    public void removePrestamo(Prestamo prestamo) {
        prestamo = em.find(Prestamo.class, prestamo.getTransaccionIdtransaccion());
        em.remove(prestamo);
    }

    /** <code>select o from Prestamo o</code> */
    public List<Prestamo> getPrestamoFindAll() {
        return em.createNamedQuery("Prestamo.findAll").getResultList();
    }

    public Transaccion persistTransaccion(Transaccion transaccion) {
        em.persist(transaccion);
        return transaccion;
    }

    public Transaccion mergeTransaccion(Transaccion transaccion) {
        return em.merge(transaccion);
    }

    public void removeTransaccion(Transaccion transaccion) {
        transaccion = em.find(Transaccion.class, transaccion.getIdtransaccion());
        em.remove(transaccion);
    }

    /** <code>select o from Transaccion o</code> */
    public List<Transaccion> getTransaccionFindAll() {
        return em.createNamedQuery("Transaccion.findAll").getResultList();
    }

    public Cuenta persistCuenta(Cuenta cuenta) {
        em.persist(cuenta);
        return cuenta;
    }

    public Cuenta mergeCuenta(Cuenta cuenta) {
        return em.merge(cuenta);
    }

    public void removeCuenta(Cuenta cuenta) {
        cuenta = em.find(Cuenta.class, cuenta.getIdcuenta());
        em.remove(cuenta);
    }

    /** <code>select o from Cuenta o</code> */
    public List<Cuenta> getCuentaFindAll() {
        return em.createNamedQuery("Cuenta.findAll").getResultList();
    }
}
