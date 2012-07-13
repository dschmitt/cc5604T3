package view;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.input.RichInputDate;

public class transaccionesBean {
    private RichInputDate antes;
    private RichInputDate despues;

    public transaccionesBean() {
    }

    public void setAntes(RichInputDate antes) {
        this.antes = antes;
    }

    public RichInputDate getAntes() {
        return antes;
    }

    public void setDespues(RichInputDate despues) {
        this.despues = despues;
    }

    public RichInputDate getDespues() {
        return despues;
    }
}
