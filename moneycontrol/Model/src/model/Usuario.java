package model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
@NamedQuery(name = "Usuario.findAll", query = "select o from Usuario o"),
@NamedQuery(name = "Usuario.findByNombre", query = "select o from Usuario o where o.nombre = :nombre"),
@NamedQuery(name = "Usuario.login", query = "select o from Usuario o where o.nombre = :nombre and o.contraseña = :clave")
})
public class Usuario implements Serializable {
    @Column(nullable = false)
    private String admin;
    @Column(nullable = false, length = 30)
    private String contraseña;
    @Column(nullable = false, length = 50)
    private String correo;
    @Id
    @Column(nullable = false, length = 30)
    private String nombre;
    @Column(name = "NOMBRE_COMPLETO", nullable = false, length = 100)
    private String nombreCompleto;
    @OneToMany(mappedBy = "usuario")
    private List<Categoria> categoriaList;
    @OneToMany(mappedBy = "usuario")
    private List<Cuenta> cuentaList;

    public Usuario() {
    }

    public Usuario(String admin, String contraseña, String correo, String nombre, String nombreCompleto) {
        this.admin = admin;
        this.contraseña = contraseña;
        this.correo = correo;
        this.nombre = nombre;
        this.nombreCompleto = nombreCompleto;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public Categoria addCategoria(Categoria categoria) {
        getCategoriaList().add(categoria);
        categoria.setUsuario(this);
        return categoria;
    }

    public Categoria removeCategoria(Categoria categoria) {
        getCategoriaList().remove(categoria);
        categoria.setUsuario(null);
        return categoria;
    }

    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
    }

    public Cuenta addCuenta(Cuenta cuenta) {
        getCuentaList().add(cuenta);
        cuenta.setUsuario(this);
        return cuenta;
    }

    public Cuenta removeCuenta(Cuenta cuenta) {
        getCuentaList().remove(cuenta);
        cuenta.setUsuario(null);
        return cuenta;
    }
}
