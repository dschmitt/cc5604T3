package model;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface SessionEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    TransaccionInterna persistTransaccionInterna(TransaccionInterna transaccionInterna);

    TransaccionInterna mergeTransaccionInterna(TransaccionInterna transaccionInterna);

    void removeTransaccionInterna(TransaccionInterna transaccionInterna);

    List<TransaccionInterna> getTransaccionInternaFindAll();

    Categoria persistCategoria(Categoria categoria);

    Categoria mergeCategoria(Categoria categoria);

    void removeCategoria(Categoria categoria);

    List<Categoria> getCategoriaFindAll();

    Usuario persistUsuario(Usuario usuario);

    Usuario mergeUsuario(Usuario usuario);

    void removeUsuario(Usuario usuario);

    List<Usuario> getUsuarioFindAll();

    List<Usuario> getUsuarioFindByNombre(String nombre);

    List<Usuario> getUsuarioLogin(String nombre, String clave);

    Prestamo persistPrestamo(Prestamo prestamo);

    Prestamo mergePrestamo(Prestamo prestamo);

    void removePrestamo(Prestamo prestamo);

    List<Prestamo> getPrestamoFindAll();

    Transaccion persistTransaccion(Transaccion transaccion);

    Transaccion mergeTransaccion(Transaccion transaccion);

    void removeTransaccion(Transaccion transaccion);

    List<Transaccion> getTransaccionFindAll();

    Cuenta persistCuenta(Cuenta cuenta);

    Cuenta mergeCuenta(Cuenta cuenta);

    void removeCuenta(Cuenta cuenta);

    List<Cuenta> getCuentaFindAll();
}
