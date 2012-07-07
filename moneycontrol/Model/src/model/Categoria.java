package model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries( { @NamedQuery(name = "Categoria.findAll", query = "select o from Categoria o") })
public class Categoria implements Serializable {
    @Id
    @Column(nullable = false)
    private BigDecimal idcategoria;
    @Column(nullable = false, length = 30)
    private String nombre;
    @OneToMany(mappedBy = "categoria")
    private List<Transaccion> transaccionList;
    @ManyToOne
    @JoinColumn(name = "USUARIO_NOMBRE")
    private Usuario usuario;

    public Categoria() {
    }

    public Categoria(BigDecimal idcategoria, String nombre, Usuario usuario) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public BigDecimal getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(BigDecimal idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    public Transaccion addTransaccion(Transaccion transaccion) {
        getTransaccionList().add(transaccion);
        transaccion.setCategoria(this);
        return transaccion;
    }

    public Transaccion removeTransaccion(Transaccion transaccion) {
        getTransaccionList().remove(transaccion);
        transaccion.setCategoria(null);
        return transaccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
