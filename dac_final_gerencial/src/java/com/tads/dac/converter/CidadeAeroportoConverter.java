package com.tads.dac.converter;

import com.tads.dac.beans.CidadeAeroporto;
import com.tads.dac.facade.CidadeAeroportoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="aeroportoConverter", forClass=CidadeAeroporto.class)
public class CidadeAeroportoConverter implements Converter {
    @Override
    public Object getAsObject (FacesContext context, UIComponent component,
            String value){
        return CidadeAeroportoFacade.getCidadeAeroportoById(Integer.parseInt(value));
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value){
        return Integer.toString(((CidadeAeroporto)value).getId());
    }
}
