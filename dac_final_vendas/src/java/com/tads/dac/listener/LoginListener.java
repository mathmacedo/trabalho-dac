package com.tads.dac.listener;

import com.tads.dac.managedBeans.SessionBean;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoginListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext context = pe.getFacesContext();
        if ("/index.xhtml".equals(context.getViewRoot().getViewId())) return;
        if ("/cadastrar_cliente.xhtml".equals(context.getViewRoot().getViewId()))
            return;
        
        SessionBean s = context.getApplication()
            .evaluateExpressionGet(context, "#{sessionBean}", SessionBean.class);
        
        if (s.getId() <= 0){
            NavigationHandler handler = context.getApplication()
                    .getNavigationHandler();
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Você deve estar logado para acessar essa página.", null));

            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);
            handler.handleNavigation(context, null, "index?faces-redirect=true");
            
            context.renderResponse();
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {}

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
