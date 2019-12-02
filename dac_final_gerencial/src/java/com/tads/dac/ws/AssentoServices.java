package com.tads.dac.ws;

import com.tads.dac.beans.Assento;
import com.tads.dac.facade.AssentoFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/assentos")
public class AssentoServices {

    @Context
    private UriInfo context;

    public AssentoServices() {}

    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Path("{id}")
    public List<Assento> getAssentosByVoo(@PathParam("id") int id) {
        return AssentoFacade.getAssentosByVoo(id);
    }
}
