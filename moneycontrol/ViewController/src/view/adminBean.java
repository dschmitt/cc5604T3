package view;

import java.util.List;

import model.SessionEJB;

import model.Usuario;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

public class adminBean {
    private RichInputText nombre;
    private RichInputText nombreComp;
    private RichInputText correo;
    private RichInputText clave;
    private RichInputText nuevaClave;
    private RichOutputLabel estado;

    public adminBean() {
    }

    public void setNombre(RichInputText nombre) {
        this.nombre = nombre;
    }

    public RichInputText getNombre() {
        return nombre;
    }

    public void setNombreComp(RichInputText nombreComp) {
        this.nombreComp = nombreComp;
    }

    public RichInputText getNombreComp() {
        return nombreComp;
    }

    public void setCorreo(RichInputText correo) {
        this.correo = correo;
    }

    public RichInputText getCorreo() {
        return correo;
    }

    public void setClave(RichInputText clave) {
        this.clave = clave;
    }

    public RichInputText getClave() {
        return clave;
    }

    public void setNuevaClave(RichInputText nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public RichInputText getNuevaClave() {
        return nuevaClave;
    }

    public void setEstado(RichOutputLabel estado) {
        this.estado = estado;
    }

    public RichOutputLabel getEstado() {
        return estado;
    }

    public String actualizar() {
        this.estado.setValue("");
        String c = this.clave.getValue().toString();
        SessionEJB bd = ModelAccess.getSessionEJB();
        List<Usuario> lista = bd.getUsuarioFindByNombre(this.nombre.getValue().toString());
        if(lista.isEmpty()){
            this.estado.setValue("Este usuario no está registrado");
            return null;
        }
        Usuario u = lista.get(0);
        if(!u.getContraseña().equals(c)){
            this.estado.setValue("Contraseña Incorrecta");
            return null;
        }
        u.setNombreCompleto(this.nombreComp.getValue().toString());
        u.setCorreo(this.correo.getValue().toString());
        u.setContraseña(this.nuevaClave.getValue().toString());
        bd.mergeUsuario(u);
        return "gotomain";
    }
}
