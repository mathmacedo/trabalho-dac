package com.tads.dac.converter;

import com.tads.dac.beans.Estado;
import com.tads.dac.facade.EstadoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="estadoConverter", forClass=Estado.class)
public class EstadoConverter implements Converter {
    @Override
    public Object getAsObject (FacesContext context, UIComponent component,
            String value){
        return EstadoFacade.getEstadoBySigla(value);
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value){
        return ((Estado)value).getSigla();
    }
}
