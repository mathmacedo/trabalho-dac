package com.tads.dac.managedBeans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "checkinBean")
@RequestScoped
public class CheckinBean {
    private String checkin;
    
    public CheckinBean() {}
    
    public String getCheckin(){ return this.checkin; }
    public void setCheckin(String s){ this.checkin = s; }
    
    public void efetuarCheckin() {
        FacesContext.getCurrentInstance().addMessage(null, new
        FacesMessage(FacesMessage.SEVERITY_INFO, "Checkin realizado!", null));
    }
}
