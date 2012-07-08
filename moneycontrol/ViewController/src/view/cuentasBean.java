package view;

import java.math.BigDecimal;

import java.util.List;

import javax.faces.context.FacesContext;

import model.Categoria;
import model.Cuenta;
import model.SessionEJB;

import model.Usuario;

import oracle.adf.view.rich.component.rich.input.RichInputText;

public class cuentasBean {
    private RichInputText nombre;
    private RichInputText comentario;

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
}
