/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.dac.ws;

import com.tads.dac.beans.Endereco;
import com.tads.dac.facade.EnderecoFacade;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/endereco")
public class EnderecoService {

    @Context
    private UriInfo context;

    public EnderecoService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public List<Endereco> getEnderecoList() {
        return EnderecoFacade.listEnderecos();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("inserir")
    public int insertEndereco(Endereco e) {
        return EnderecoFacade.insertEndereco(e);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Path("{id}")
    public Endereco getEnderecoById(@PathParam("id") int id) {
        return EnderecoFacade.getEnderecoById(id);
    }
}
