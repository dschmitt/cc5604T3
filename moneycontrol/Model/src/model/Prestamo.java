package model;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries( { @NamedQuery(name = "Prestamo.findAll", query = "select o from Prestamo o") })
public class Prestamo implements Serializable {
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_LIMITE", nullable = false)
    private Date fechaLimite;
    @Id
    @Column(name = "TRANSACCION_IDTRANSACCION", nullable = false, insertable = false, updatable = false)
    private BigDecimal transaccionIdtransaccion;
    @ManyToOne
    @JoinColumn(name = "TRANSACCION_IDTRANSACCION")
    private Transaccion transaccion;

    public Prestamo() {
    }

    public Prestamo(Date fechaLimite, Transaccion transaccion) {
        this.fechaLimite = fechaLimite;
        this.transaccion = transaccion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public BigDecimal getTransaccionIdtransaccion() {
        return transaccionIdtransaccion;
    }

    public void setTransaccionIdtransaccion(BigDecimal transaccionIdtransaccion) {
        this.transaccionIdtransaccion = transaccionIdtransaccion;
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
