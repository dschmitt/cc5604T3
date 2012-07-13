package model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
@NamedQuery(name = "Cuenta.findAll", query = "select o from Cuenta o"),
@NamedQuery(name = "Cuenta.findByID", query = "select o from Cuenta o where o.idcuenta = :id"),
@NamedQuery(name = "Cuenta.findByUsuario", query = "select o from Cuenta o where o.usuario.nombre = :nombre")
})
public class Cuenta implements Serializable {
    private String comentario;
    @Id
    @Column(nullable = false)
    private BigDecimal idcuenta;
    @Column(nullable = false, length = 30)
    private String nombre;
    @Column(nullable = false)
    private BigDecimal saldo;
    @OneToMany(mappedBy = "cuenta")
    private List<Transaccion> transaccionList;
    @OneToMany(mappedBy = "cuenta", cascade=CascadeType.ALL)
    private List<TransaccionInterna> transaccionInternaList = new ArrayList<TransaccionInterna>();
    @ManyToOne
    @JoinColumn(name = "USUARIO_NOMBRE")
    private Usuario usuario;

    public Cuenta() {
    }

    public Cuenta(String comentario, BigDecimal idcuenta, String nombre, BigDecimal saldo, Usuario usuario) {
        this.comentario = comentario;
        this.idcuenta = idcuenta;
        this.nombre = nombre;
        this.saldo = saldo;
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public BigDecimal getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(BigDecimal idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }


    public List<Transaccion> getTransaccionList() {
        return transaccionList;
    }

    public void setTransaccionList(List<Transaccion> transaccionList) {
        this.transaccionList = transaccionList;
    }

    public Transaccion addTransaccion(Transaccion transaccion) {
        getTransaccionList().add(transaccion);
        transaccion.setCuenta(this);
        return transaccion;
    }

    public Transaccion removeTransaccion(Transaccion transaccion) {
        getTransaccionList().remove(transaccion);
        transaccion.setCuenta(null);
        return transaccion;
    }

    public List<TransaccionInterna> getTransaccionInternaList() {
        return transaccionInternaList;
    }

    public void setTransaccionInternaList(List<TransaccionInterna> transaccionInternaList) {
        this.transaccionInternaList = transaccionInternaList;
    }

    public TransaccionInterna addTransaccionInterna(TransaccionInterna transaccionInterna) {
        getTransaccionInternaList().add(transaccionInterna);
        transaccionInterna.setCuenta(this);
        return transaccionInterna;
    }

    public TransaccionInterna removeTransaccionInterna(TransaccionInterna transaccionInterna) {
        getTransaccionInternaList().remove(transaccionInterna);
        transaccionInterna.setCuenta(null);
        return transaccionInterna;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
