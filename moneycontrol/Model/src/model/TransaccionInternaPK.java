package model;

import java.io.Serializable;

import java.math.BigDecimal;

public class TransaccionInternaPK implements Serializable {
    private BigDecimal cuentaIdcuenta;
    private BigDecimal transaccionIdtransaccion;

    public TransaccionInternaPK() {
    }

    public TransaccionInternaPK(BigDecimal cuentaIdcuenta, BigDecimal transaccionIdtransaccion) {
        this.cuentaIdcuenta = cuentaIdcuenta;
        this.transaccionIdtransaccion = transaccionIdtransaccion;
    }

    public boolean equals(Object other) {
        if (other instanceof TransaccionInternaPK) {
            final TransaccionInternaPK otherTransaccionInternaPK = (TransaccionInternaPK)other;
            final boolean areEqual =
                (otherTransaccionInternaPK.cuentaIdcuenta.equals(cuentaIdcuenta) && otherTransaccionInternaPK.transaccionIdtransaccion.equals(transaccionIdtransaccion));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
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
}
