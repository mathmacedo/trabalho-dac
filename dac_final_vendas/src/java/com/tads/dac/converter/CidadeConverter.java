package com.tads.dac.converter;

import com.tads.dac.beans.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@FacesConverter(value="cidadeConverter", forClass=Cidade.class)
public class CidadeConverter implements Converter {
    @Override
    public Object getAsObject (FacesContext context, UIComponent component,
            String value){
        Client c = ClientBuilder.newClient();
        return c
                .target("http://localhost:8080/dac_final_gerencial/webresources/cidade/" + value)
                .request(MediaType.APPLICATION_JSON)
                .get(Cidade.class);
        //return CidadeFacade.getCidadeById(Integer.parseInt(value));
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value){
        return Integer.toString(((Cidade)value).getId());
    }
}
