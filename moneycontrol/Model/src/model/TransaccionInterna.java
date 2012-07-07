package model;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries( { @NamedQuery(name = "TransaccionInterna.findAll", query = "select o from TransaccionInterna o") })
@Table(name = "TRANSACCION_INTERNA")
@IdClass(TransaccionInternaPK.class)
public class TransaccionInterna implements Serializable {
    @Id
    @Column(name = "CUENTA_IDCUENTA", nullable = false, insertable = false, updatable = false)
    private BigDecimal cuentaIdcuenta;
    @Id
    @Column(name = "TRANSACCION_IDTRANSACCION", nullable = false, insertable = false, updatable = false)
    private BigDecimal transaccionIdtransaccion;
    @ManyToOne
    @JoinColumn(name = "CUENTA_IDCUENTA")
    private Cuenta cuenta;
    @ManyToOne
    @JoinColumn(name = "TRANSACCION_IDTRANSACCION")
    private Transaccion transaccion;

    public TransaccionInterna() {
    }

    public TransaccionInterna(Cuenta cuenta, Transaccion transaccion) {
        this.cuenta = cuenta;
        this.transaccion = transaccion;
    }

    public BigDecimal getCuentaIdcuenta() {
        return cuentaIdcuenta;
    }

    public void setCuentaIdcuenta(BigDecimal cuentaIdcuenta) {
        this.cuentaIdcuenta = cuentaIdcuenta;
    }

    public BigDecimal getTransaccionIdtransaccion() {
        return transaccionIdtransaccion;
    }

    public void setTransaccionIdtransaccion(BigDecimal transaccionIdtransaccion) {
        this.transaccionIdtransaccion = transaccionIdtransaccion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        if (cuenta != null) {
            this.cuentaIdcuenta = cuenta.getIdcuenta();
        }
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
        if (transaccion != null) {
            this.transaccionIdtransaccion = transaccion.getIdtransaccion();
        }
    }
}
