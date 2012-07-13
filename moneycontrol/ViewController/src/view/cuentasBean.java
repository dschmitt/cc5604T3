package view;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import model.Categoria;
import model.Cuenta;
import model.SessionEJB;

import model.Usuario;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

public class cuentasBean {
    private RichInputText nombre;
    private RichInputText comentario;
    private RichSelectOneChoice selectCuenta;
    private RichInputDate inputFecha;
    private RichOutputText resultado;

    public cuentasBean() {
    }

    public void setNombre(RichInputText nombre) {
        this.nombre = nombre;
    }

    public RichInputText getNombre() {
        return nombre;
    }

    public void setComentario(RichInputText comentario) {
        this.comentario = comentario;
    }

    public RichInputText getComentario() {
        return comentario;
    }

    public String agregar() {
        SessionEJB bd = ModelAccess.getSessionEJB();
        Cuenta c = new Cuenta();
        c.setNombre(this.nombre.getValue().toString());
        c.setSaldo(new BigDecimal(0));
        c.setComentario(this.comentario.getValue().toString());
        String aux = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario").toString();
        List<Usuario> lista = bd.getUsuarioFindByNombre(aux);
        if(lista.isEmpty()){
            return null;
        }
        Usuario u = lista.get(0);
        c.setUsuario(u);
        bd.persistCuenta(c);
        return "gotocuentas";
    }

    public void setSelectCuenta(RichSelectOneChoice selectCuenta) {
        this.selectCuenta = selectCuenta;
    }

    public RichSelectOneChoice getSelectCuenta() {
        return selectCuenta;
    }

    public void setInputFecha(RichInputDate inputFecha) {
        this.inputFecha = inputFecha;
    }

    public RichInputDate getInputFecha() {
        return inputFecha;
    }

    public void setResultado(RichOutputText resultado) {
        this.resultado = resultado;
    }

    public RichOutputText getResultado() {
        return resultado;
    }

    public void buscarSaldo(ActionEvent actionEvent) {
        BigDecimal cuenta = new BigDecimal(this.selectCuenta.getValue().toString());
        Date fecha = (Date) this.inputFecha.getValue();
        SessionEJB bd = ModelAccess.getSessionEJB();
        BigDecimal ingreso = bd.getTransaccionFindIngresos(cuenta, fecha).get(0);
        BigDecimal gasto = bd.getTransaccionFindGastos(cuenta, fecha).get(0);
        BigDecimal prestamo = bd.getTransaccionFindPrestamos(cuenta, fecha).get(0);
        if(ingreso == null) ingreso = new BigDecimal(0);
        if(gasto == null) gasto = new BigDecimal(0);
        if(prestamo == null) prestamo = new BigDecimal(0);
        BigDecimal aux = ingreso.subtract(gasto).add(prestamo);
        resultado.setValue("Saldo = $" + aux.toString());
    }
}
