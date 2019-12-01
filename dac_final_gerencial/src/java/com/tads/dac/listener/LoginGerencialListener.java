package com.tads.dac.listener;

import com.tads.dac.managedBeans.SessionBean;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LoginGerencialListener implements PhaseListener{

    @Override
    public void afterPhase(PhaseEvent pe) {
        FacesContext context = pe.getFacesContext();
        String current = context.getViewRoot().getViewId();
        SessionBean s = context.getApplication()
            .evaluateExpressionGet(context, "#{sessionBean}", SessionBean.class);
        NavigationHandler handler = context.getApplication()
                    .getNavigationHandler();
        
        if (current.equals("/index.xhtml")){
            if (s.getId() <= 0) return;
            else {
                if (s.getTipo() == 'F'){
                    handler.handleNavigation(context, null, "menu_funcionario.xhtml");
                    context.renderResponse();
                }
                else if (s.getTipo() == 'G'){
                    handler.handleNavigation(context, null, "menu_gerente.xhtml");
                    context.renderResponse();
                }
            }
        }
        
        if (s.getId() <= 0 || (s.getTipo() != 'F' && s.getTipo() != 'G')){            
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_FATAL,
                    "Você não tem permissão para acessar essa página. Por favor, faça login.", null));

            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);
            
            handler.handleNavigation(context, null, "index?faces-redirect=true");
            
            context.renderResponse();
        }
        
        else{
            if (
                    s.getTipo() == 'G' && (
                    current.equals("/consultar_assentos.xhtml") ||
                    current.equals("/consultar_voos.xhtml") ||
                    current.equals("/efetuar_checkin.xhtml") ||
                    current.equals("/menu_funcionario.xhtml")))
            {
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Você não tem permisão para acessar essa página.", null));

                FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);

                handler.handleNavigation(context, null, "index?faces-redirect=true");

                context.renderResponse();
            }
            else if (s.getTipo() == 'F' && (
                    current.equals("/menu_gerente.xhtml") ||
                    current.contains("manter") ||
                    current.contains("pesquisar") ||
                    current.contains("relatorio")))
            {
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_FATAL,
                        "Você não tem permissão para acessar essa página.", null));

                FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);

                handler.handleNavigation(context, null, "index?faces-redirect=true");

                context.renderResponse();
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent pe) {}

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
