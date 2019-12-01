package com.tads.dac.listener;

import com.tads.dac.managedBeans.SessionBean;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class VerificarLoginGerente implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext context = pe.getFacesContext();
        if ("/index.xhtml".equals(context.getViewRoot().getViewId())) return;
        
        SessionBean s = context.getApplication()
            .evaluateExpressionGet(context, "#{s}", SessionBean.class);
        
        if (s.getId() < 0){
            NavigationHandler handler = context.getApplication()
                    .getNavigationHandler();
            
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
