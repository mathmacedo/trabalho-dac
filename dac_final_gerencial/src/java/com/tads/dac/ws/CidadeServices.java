/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.dac.ws;

import com.tads.dac.beans.Cidade;
import com.tads.dac.facade.CidadeFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author tacid
 */
@Path("/cidade")
public class CidadeServices {

    @Context
    private UriInfo context;

    public CidadeServices() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Cidade getCidade() {
        return CidadeFacade.getCidadeById(1);
    }
}
