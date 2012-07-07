package view;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import model.SessionEJB;

import model.Usuario;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

public class loginBean {
    private RichInputText nombre;
    private RichInputText clave;
    private RichOutputLabel estado;

    public loginBean() {
    }

    public void setNombre(RichInputText nombre) {
        this.nombre = nombre;
    }

    public RichInputText getNombre() {
        return nombre;
    }

    public void setClave(RichInputText clave) {
        this.clave = clave;
    }

    public RichInputText getClave() {
        return clave;
    }

    public void setEstado(RichOutputLabel estado) {
        this.estado = estado;
    }

    public RichOutputLabel getEstado() {
        return estado;
    }
    
    public Usuario getCliente(){
        Usuario u = null;
        String aux = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario").toString();
        if(aux != null){
            SessionEJB bd = ModelAccess.getSessionEJB();
            List<Usuario> lista = bd.getUsuarioFindByNombre(aux);
            if(!lista.isEmpty()) u = lista.get(0);
        }
        return u;
    }

    public String entrar() {
        this.estado.setValue("");
        SessionEJB bd = ModelAccess.getSessionEJB();
        String n = this.nombre.getValue().toString();
        String c = this.clave.getValue().toString();
        List<Usuario> aux = bd.getUsuarioLogin(n,c);
        if(aux.isEmpty()){
            this.estado.setValue("Usuario o Contraseña Incorrecta");
            return null;
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",n);
        return "gotomain";
    }

    public Object logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        return "gotologin";
    }
}
