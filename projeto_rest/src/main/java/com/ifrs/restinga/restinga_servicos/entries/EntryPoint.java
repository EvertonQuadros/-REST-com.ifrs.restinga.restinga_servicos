/* 
 * Copyright 2017 restingaservicoscontato@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Authors: Éverton Quadros do Couto (eqdcouto@restinga.ifrs.edu.br)
 *          Rodrigo Sebben           (rsebben@restinga.ifrs.edu.br)
 *          Natalia Schnechel        (nsschenechel@restinga.ifrs.edu.br)
 * 
 * Created: 03/04/2017
 */
package com.ifrs.restinga.restinga_servicos.entries;

import com.ifrs.restinga.restinga_servicos.classes.Entidade;

import com.ifrs.restinga.restinga_servicos.db.GenericDAO;

import com.ifrs.restinga.restinga_servicos.utils.Utils;

import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.configuration.PropertiesConfiguration;

import org.apache.log4j.Logger;

/**
 *
 * @author not275ssd
 * @param <T>
 */
//Endereço (localhost): http://localhost:8080/entry-point/restingaservicos
//@Path("/entry-point/restingaservicos") 
public class EntryPoint<T extends Entidade> {
    
    private final Logger LOGGER;
    private final String url;
    private static final PropertiesConfiguration CONFIG 
            = Utils.Configurations.getConfiguration("config.properties");
    protected GenericDAO genericDAO;
    private Class<T> tipo;
   
    /**
     *
     * @param tipo
     * @param subTipo
     * @param url
     */
    public EntryPoint(Class<T> tipo, Class<T> subTipo, String url){
        
        this.LOGGER = Logger.getLogger(tipo);
            this.url = url;
                this.genericDAO = new GenericDAO(LOGGER,subTipo);
                    this.tipo = subTipo;
                    
    }
    
    public EntryPoint(){
        
        this.LOGGER = Logger.getLogger(EntryPoint.class);
            this.url = CONFIG.getString("jersey.main");
            
    }
    
    public Logger getLOGGER() {
        return LOGGER;
    }

    public String getUrl() {
        return url;
    }
    
    public GenericDAO getGenericDAO() {
        return genericDAO;
    }
    
    /**
     *
     * @return
     * @throws java.net.URISyntaxException
     */
    @GET
    @Produces("text/html")
    public Response telaApresentacao() throws URISyntaxException {
        
        //adicionar aqui o link da home do projeto web em LUA.
        LOGGER.debug("Cliente executando o método telaApresentacao().");
        return Response.temporaryRedirect(new java.net.URI("http://jersey.java.net")).build();

    }  

    @GET
    @Path("/get/{param}")
    @Produces("application/json")
    public Response get(@PathParam("param") int id){

        getLOGGER().debug("Cliente executando metodo (get(int id))");
        getLOGGER().debug("retornando resultados:");
       
        try {
            return Response.ok(genericDAO.getDAO(id).toString()).build();
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("text/html")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
        
        }
        
    }
    
    @GET
    @Path("/list")
    @Produces("application/json")
    public Response list(){
        
        getLOGGER().debug("Cliente executando metodo (list())");
        getLOGGER().debug("retornando resultados (limit 1000):");
       
        try {
            return Response.ok(genericDAO.listDAO(1000).toString()).build();
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("text/html")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
        
        }
        
    }
    
    @POST
    @Path("/insert")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insert(String entidade){
        
        getLOGGER().info("Cliente executando metodo insert(String entidade)");
        
        try{
            
            if(entidade != null){
                
                try{
                    genericDAO.InsertDAO(Utils.JSON.convertFromJSONString(entidade, tipo));
                }
                catch(Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            
            }
            else{
                throw new IllegalArgumentException("Entidade invalida!");
            }
            
        }
        catch(IllegalArgumentException ex){
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("application/json")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
            
        }

        return Response.ok(Response.Status.ACCEPTED)
                    .type("application/json")
                    .entity(tipo.getSimpleName()
                            .concat(" : Cadastrada(o) com sucesso!")).build();
    
    }
      
    @PUT
    @Path("/update")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String entidade){
        
        getLOGGER().debug("Cliente executando metodo update(String entidade)");
        
         try{
            
            if(entidade != null){
                
                try{
                    genericDAO.UpdateDAO(Utils.JSON.convertFromJSONString(entidade, tipo));
                }
                catch(Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            
            }
            else{
                throw new IllegalArgumentException("Entidade invalida!");
            }
            
        }
        catch(IllegalArgumentException ex){
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("application/json")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
            
        }

        return Response.ok(Response.Status.ACCEPTED)
                    .type("application/json")
                    .entity(tipo.getSimpleName()
                            .concat(" : Atualizada(o) com sucesso!")).build();
        
    }
  
    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(String entidade){
        
        getLOGGER().debug("Cliente executando metodo delete(String entidade)");
        
         try{
            
            if(entidade != null){
                
                try{
                    genericDAO.DeleteDAO(Utils.JSON.convertFromJSONString(entidade, tipo));
                }
                catch(Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            
            }
            else{
                throw new IllegalArgumentException("Entidade invalida!");
            }
            
        }
        catch(IllegalArgumentException ex){
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("application/json")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
            
        }

        return Response.ok(Response.Status.ACCEPTED)
                    .type("application/json")
                    .entity(tipo.getSimpleName()
                            .concat(" : Removida(o) com sucesso!")).build();
        
    }
    
}
