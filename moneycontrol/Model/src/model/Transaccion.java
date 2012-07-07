package model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "Transaccion.findAll", query = "select o from Transaccion o") })
public class Transaccion implements Serializable {
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;
    @Id
    @Column(nullable = false)
    private BigDecimal idtransaccion;
    @Column(nullable = false)
    private BigDecimal monto;
    @Column(nullable = false, length = 8)
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "CUENTA_IDCUENTA")
    private Cuenta cuenta;
    @ManyToOne
    @JoinColumn(name = "CATEGORIA_IDCATEGORIA")
    private Categoria categoria;
    @OneToMany(mappedBy = "transaccion")
    private List<TransaccionInterna> transaccionInternaList;
    @OneToMany(mappedBy = "transaccion")
    private List<Prestamo> prestamoList;

    public Transaccion() {
    }

    public Transaccion(Categoria categoria, Cuenta cuenta, Date fecha, BigDecimal idtransaccion, BigDecimal monto,
                       String tipo) {
        this.categoria = categoria;
        this.cuenta = cuenta;
        this.fecha = fecha;
        this.idtransaccion = idtransaccion;
        this.monto = monto;
        this.tipo = tipo;
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getIdtransaccion() {
        return idtransaccion;
    }

    public void setIdtransaccion(BigDecimal idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<TransaccionInterna> getTransaccionInternaList() {
        return transaccionInternaList;
    }

    public void setTransaccionInternaList(List<TransaccionInterna> transaccionInternaList) {
        this.transaccionInternaList = transaccionInternaList;
    }

    public TransaccionInterna addTransaccionInterna(TransaccionInterna transaccionInterna) {
        getTransaccionInternaList().add(transaccionInterna);
        transaccionInterna.setTransaccion(this);
        return transaccionInterna;
    }

    public TransaccionInterna removeTransaccionInterna(TransaccionInterna transaccionInterna) {
        getTransaccionInternaList().remove(transaccionInterna);
        transaccionInterna.setTransaccion(null);
        return transaccionInterna;
    }

    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    public Prestamo addPrestamo(Prestamo prestamo) {
        getPrestamoList().add(prestamo);
        prestamo.setTransaccion(this);
        return prestamo;
    }

    public Prestamo removePrestamo(Prestamo prestamo) {
        getPrestamoList().remove(prestamo);
        prestamo.setTransaccion(null);
        return prestamo;
    }
}
