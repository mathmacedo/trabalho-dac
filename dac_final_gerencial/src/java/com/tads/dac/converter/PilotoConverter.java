package com.tads.dac.converter;

import com.tads.dac.beans.Piloto;
import com.tads.dac.facade.PilotoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="pilotoConverter", forClass=Piloto.class)
public class PilotoConverter implements Converter {
    @Override
    public Object getAsObject (FacesContext context, UIComponent component,
            String value){
        return PilotoFacade.getPilotoById(Integer.parseInt(value));
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value){
        return Integer.toString(((Piloto)value).getId());
    }
}
