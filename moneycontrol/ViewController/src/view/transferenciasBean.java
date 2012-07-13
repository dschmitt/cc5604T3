package view;

import java.math.BigDecimal;

import java.util.Date;

import java.util.List;

import javax.faces.context.FacesContext;

import model.Categoria;
import model.Cuenta;
import model.SessionEJB;

import model.Transaccion;
import model.TransaccionInterna;
import model.Usuario;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

public class transferenciasBean {
    private RichInputDate fecha;
    private RichInputNumberSpinbox monto;
    private RichSelectOneChoice tipo;
    private RichSelectOneChoice cuenta;
    private RichSelectOneChoice categoria;
    private RichInputDate limite;
    private RichOutputText estado;
    private RichSelectOneChoice usuario;

    public transferenciasBean() {
    }

    public void setFecha(RichInputDate fecha) {
        this.fecha = fecha;
    }

    public RichInputDate getFecha() {
        return fecha;
    }

    public void setMonto(RichInputNumberSpinbox monto) {
        this.monto = monto;
    }

    public RichInputNumberSpinbox getMonto() {
        return monto;
    }

    public void setTipo(RichSelectOneChoice tipo) {
        this.tipo = tipo;
    }

    public RichSelectOneChoice getTipo() {
        return tipo;
    }

    public void setCuenta(RichSelectOneChoice cuenta) {
        this.cuenta = cuenta;
    }

    public RichSelectOneChoice getCuenta() {
        return cuenta;
    }

    public void setCategoria(RichSelectOneChoice categoria) {
        this.categoria = categoria;
    }

    public RichSelectOneChoice getCategoria() {
        return categoria;
    }

    public void setLimite(RichInputDate limite) {
        this.limite = limite;
    }

    public RichInputDate getLimite() {
        return limite;
    }

    public void setEstado(RichOutputText estado) {
        this.estado = estado;
    }

    public RichOutputText getEstado() {
        return estado;
    }

    public void setUsuario(RichSelectOneChoice usuario) {
        this.usuario = usuario;
    }

    public RichSelectOneChoice getUsuario() {
        return usuario;
    }

    public String transferir() {
        this.estado.setValue("");
        Date f = (Date) this.fecha.getValue();
        BigDecimal m = new BigDecimal(this.monto.getValue().toString());
        String t = this.tipo.getValue().toString();
        BigDecimal cu = new BigDecimal(this.cuenta.getValue().toString());
        BigDecimal ca = new BigDecimal(this.categoria.getValue().toString());
        String u = this.usuario.getValue().toString();
        
        SessionEJB bd = ModelAccess.getSessionEJB();
        List<Cuenta> aux_cu = bd.getCuentaFindByID(cu);
        List<Categoria> aux_ca = bd.getCategoriaFindByID(ca);
        if(aux_cu.isEmpty()){
            estado.setValue("No se puede transferir porque usted no ha creado una cuenta");
            return null;
        }
        if(aux_ca.isEmpty()){
            estado.setValue("No se puede transferir porque usted no ha creado una categoría");
            return null;
        }
        Cuenta cu_orig = aux_cu.get(0);
        Categoria ca_orig = aux_ca.get(0);

        aux_cu = bd.getCuentaFindByUsuario(u);
        aux_ca = bd.getCategoriaFindByUsuario(u);
        if(aux_cu.isEmpty()){
            estado.setValue("No se puede transferir al usuario de destino porque no ha creado una cuenta");
            return null;
        }
        if(aux_ca.isEmpty()){
            estado.setValue("No se puede transferir al usuario de destino porque no ha creado una categoría");
            return null;
        }
        Cuenta cu_dest = aux_cu.get(0);
        Categoria ca_dest = aux_ca.get(0);
        
        String t2;
        if(t.equals("INGRESO")) t2 = "GASTO";
        else if(t.equals("GASTO")) t2 = "INGRESO";
        else{
            t2 = "PRESTAMO";
            m = m.negate();
        }
        
        Transaccion t_orig = new Transaccion();
        t_orig.setCategoria(ca_orig);
        t_orig.setCuenta(cu_orig);
        t_orig.setFecha(f);
        t_orig.setMonto(m);
        t_orig.setTipo(t);
        Transaccion t_dest = new Transaccion();
        t_dest.setCategoria(ca_dest);
        t_dest.setCuenta(cu_dest);
        t_dest.setFecha(f);
        t_dest.setMonto(m.negate());
        t_dest.setTipo(t2);
        
        //t_orig.addTransaccionInterna(ti_orig);
        //t_dest.addTransaccionInterna(ti_dest);
        bd.persistTransaccion(t_orig);
        bd.persistTransaccion(t_dest);
        
        Date ahora = new Date();
        BigDecimal ingreso = bd.getTransaccionFindIngresos(cu_orig.getIdcuenta(), ahora).get(0);
        BigDecimal gasto = bd.getTransaccionFindGastos(cu_orig.getIdcuenta(), ahora).get(0);
        BigDecimal prestamo = bd.getTransaccionFindPrestamos(cu_orig.getIdcuenta(), ahora).get(0);
        if(ingreso == null) ingreso = new BigDecimal(0);
        if(gasto == null) gasto = new BigDecimal(0);
        if(prestamo == null) prestamo = new BigDecimal(0);
        BigDecimal aux = ingreso.subtract(gasto).add(prestamo);
        cu_orig.setSaldo(aux);
        ingreso = bd.getTransaccionFindIngresos(cu_dest.getIdcuenta(), ahora).get(0);
        gasto = bd.getTransaccionFindGastos(cu_dest.getIdcuenta(), ahora).get(0);
        prestamo = bd.getTransaccionFindPrestamos(cu_dest.getIdcuenta(), ahora).get(0);
        if(ingreso == null) ingreso = new BigDecimal(0);
        if(gasto == null) gasto = new BigDecimal(0);
        if(prestamo == null) prestamo = new BigDecimal(0);
        aux = ingreso.subtract(gasto).add(prestamo);
        cu_dest.setSaldo(aux);
        bd.mergeCuenta(cu_orig);
        bd.mergeCuenta(cu_dest);
        
        /*
        TransaccionInterna ti_orig = new TransaccionInterna(cu_dest, t_orig);
        TransaccionInterna ti_dest = new TransaccionInterna(cu_orig, t_dest);
        bd.persistTransaccionInterna(ti_orig);
        bd.persistTransaccionInterna(ti_dest);
        */
        
        return "gototransacciones";
    }
}
