package com.tads.dac.converter;

import com.tads.dac.beans.Estado;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@FacesConverter(value="estadoConverter", forClass=Estado.class)
public class EstadoConverter implements Converter {
    @Override
    public Object getAsObject (FacesContext context, UIComponent component,
            String value){
        Client c = ClientBuilder.newClient();
        return c
                .target("http://localhost:8080/dac_final_gerencial/webresources/estado/" + value)
                .request(MediaType.APPLICATION_JSON)
                .get(Estado.class);
        //return EstadoFacade.getEstadoBySigla(value);
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value){
        return ((Estado)value).getSigla();
    }
}
