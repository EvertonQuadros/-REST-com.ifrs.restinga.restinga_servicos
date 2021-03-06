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
 * Authors: �verton Quadros do Couto (eqdcouto@restinga.ifrs.edu.br)
 *          Rodrigo Sebben           (rsebben@restinga.ifrs.edu.br)
 *          Natalia Schnechel        (nsschenechel@restinga.ifrs.edu.br)
 * 
 * Created: 03/04/2017
 */
package com.ifrs.restinga.restinga_servicos.entries;

import com.ifrs.restinga.restinga_servicos.classes.Consulta;
import com.ifrs.restinga.restinga_servicos.classes.Pessoa_profissao;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author not275ssd
 */
//http://localhost:8080/entry-point/restingaservicos/pessoaprofissao
@Path("/entry-point/restingaservicos/pessoaprofissao")
public class PessoaProfissaoEntry extends EntryPoint{
    
    public PessoaProfissaoEntry() {
        super(PessoaProfissaoEntry.class
                ,Pessoa_profissao.class
                ,"/entry-point/restingaservicos/pessoaprofissao");
    }
    
    @GET
    @Path("/list/profissao/{param}")
    @Produces("application/json")
    public Response listPessoaProfissionalByProfissao(@PathParam("param") int id){

        getLOGGER().debug("Cliente executando metodo (listPessoaProfissionalByProfissao(int id))");
        getLOGGER().debug("retornando resultados:");

        Consulta consulta = new Consulta(1000,
                            "profissao_id",
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
    @Path("/list/ficha/{param}")
    @Produces("application/json")
    public Response listPessoaProfissionalByProfissaoPrincipal(@PathParam("param") int id){

        getLOGGER().debug("Cliente executando metodo (listPessoaProfissionalByProfissaoPrincipal(int id))");
        getLOGGER().debug("retornando resultados:");
       
        Consulta consulta = new Consulta(0,
                            "flg_principal",
                            Consulta.LogicaEnum.EQUALS,
                            true);
        
        consulta.adicionaArgs(Consulta.CondicaoEnum.AND, 
                "profissao_id", 
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
    
}
