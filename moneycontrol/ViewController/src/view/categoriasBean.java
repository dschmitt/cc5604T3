package view;

import java.util.List;

import java.util.Map;

import javax.faces.context.FacesContext;

import model.Categoria;
import model.SessionEJB;

import model.Usuario;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class categoriasBean {
    private RichInputText nombre;

    public categoriasBean() {
    }

    public void setNombre(RichInputText nombre) {
        this.nombre = nombre;
    }

    public RichInputText getNombre() {
        return nombre;
    }

    public String agregar() {
        SessionEJB bd = ModelAccess.getSessionEJB();
        Categoria c = new Categoria();
        c.setNombre(this.nombre.getValue().toString());
        String aux = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario").toString();
        List<Usuario> lista = bd.getUsuarioFindByNombre(aux);
        if(lista.isEmpty()){
            return null;
        }
        Usuario u = lista.get(0);
        c.setUsuario(u);
        bd.persistCategoria(c);
        return "gotocategorias";
    }
}
