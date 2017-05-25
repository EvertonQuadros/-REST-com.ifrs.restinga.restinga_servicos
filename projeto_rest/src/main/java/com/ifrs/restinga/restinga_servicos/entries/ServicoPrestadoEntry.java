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

import com.ifrs.restinga.restinga_servicos.classes.Consulta;
import com.ifrs.restinga.restinga_servicos.classes.Servico_prestado;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

/**
 *
 * @author not275ssd
 */
//http://localhost:8080/entry-point/restingaservicos/servicoprestado
@Path("/entry-point/restingaservicos/servicoprestado")
public class ServicoPrestadoEntry extends EntryPoint{
    
    public ServicoPrestadoEntry() {
        super(ServicoPrestadoEntry.class,
                Servico_prestado.class,
                "/entry-point/restingaservicos/servicoprestado");
    }
    
    @GET
    @Path("/list/pessoacontratada/{param}")
    @Produces("application/json")
    public Response listServicoPrestado(@PathParam("param") int id){
        
        getLOGGER().debug("Cliente executando metodo (listServicoPrestado(int id))");
        getLOGGER().debug("retornando resultados:");

        Consulta consulta = new Consulta(0,
                            "pessoa_contratada_id",
                            Consulta.LogicaEnum.EQUALS,
                            id);
        
        getLOGGER().info("CONSULTA: " + consulta.getConsultaName(false));
   
        try {
            return Response.ok(genericDAO.listDAO(consulta).toString()).build();
           
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        
        }
        
    }
  
    @GET
    @Path("/list/servicospessoa/{param}")
    @Produces("application/json")
    public Response listServicoPrestadoPorData(@PathParam("param") int id){
        
        getLOGGER().debug("Cliente executando metodo (listServicoPrestadoPorData(int id))");
        getLOGGER().debug("retornando resultados:");
        
        Consulta consulta = new Consulta(5,
                            "pessoa_contratada_id",
                            Consulta.LogicaEnum.EQUALS,
                            id);
        
        consulta.setOrderBy("data_termino");
        
        getLOGGER().info("CONSULTA: " + consulta.getConsultaName(false));
        
        try {
            return Response.ok(genericDAO.listDAO(consulta).toString()).build();
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        
        }
        
    }
    
}