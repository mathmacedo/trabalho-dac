package com.tads.dac.converter;

import com.tads.dac.beans.Cidade;
import com.tads.dac.facade.CidadeFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="cidadeConverter", forClass=Cidade.class)
public class CidadeConverter implements Converter {
    @Override
    public Object getAsObject (FacesContext context, UIComponent component,
            String value){
        return CidadeFacade.getCidadeById(Integer.parseInt(value));
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value){
        return Integer.toString(((Cidade)value).getId());
    }
}
