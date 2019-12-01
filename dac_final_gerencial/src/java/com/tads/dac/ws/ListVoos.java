package com.tads.dac.ws;

import com.tads.dac.beans.Voo;
import com.tads.dac.facade.VooFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/voo")
public class ListVoos {

    @Context
    private UriInfo context;

    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
    public List<Voo> listVoos() {
        return VooFacade.listVoos();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
    @Path("{id}")
    public Voo getVooById(@PathParam("id") int id) {
        return VooFacade.getVooById(id);
    }
}
