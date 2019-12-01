package com.tads.dac.ws;

import com.tads.dac.facade.EstadoFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/listvoos")
public class ListVoos {

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{sigla}")
    public Object getEstadoBySisla(@PathParam("sigla") String sigla) {
        return EstadoFacade.getEstadoBySigla(sigla);
    }
}
