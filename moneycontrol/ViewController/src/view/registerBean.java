package view;

import model.SessionEJB;

import model.Usuario;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;

public class registerBean {
    private RichInputText nombre;
    private RichInputText nombreComp;
    private RichInputText correo;
    private RichInputText clave;
    private RichSelectBooleanCheckbox admin;

    public registerBean() {
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

    public String registrar() {
        SessionEJB bd = ModelAccess.getSessionEJB();
        Usuario u = new Usuario(
                        "0",
                        this.clave.getValue().toString(),
                        this.correo.getValue().toString(),
                        this.nombre.getValue().toString(),
                        this.nombreComp.getValue().toString()
                        );
        bd.persistUsuario(u);
        return "success";
    }

    public void setAdmin(RichSelectBooleanCheckbox admin) {
        this.admin = admin;
    }

    public RichSelectBooleanCheckbox getAdmin() {
        return admin;
    }

    public String registrarAdmin() {
        SessionEJB bd = ModelAccess.getSessionEJB();
        Usuario u = new Usuario(
                        this.admin.isSelected() ? "1" : "0",
                        this.clave.getValue().toString(),
                        this.correo.getValue().toString(),
                        this.nombre.getValue().toString(),
                        this.nombreComp.getValue().toString()
                        );
        bd.persistUsuario(u);
        return "success";
    }
}
