/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.dac.ws;

import com.tads.dac.facade.EstadoFacade;
import com.tads.dac.beans.Estado;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("/estado")
public class EstadoServices {

    @Context
    private UriInfo context;

    public EstadoServices() {
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Estado> getEstados() {
        return EstadoFacade.listEstados();
    }
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("{sigla}")
    public Estado getEstadoBySigla(@PathParam("sigla") String sigla) {
        return EstadoFacade.getEstadoBySigla(sigla);
    }
}
