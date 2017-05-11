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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifrs.restinga.restinga_servicos.classes.Consulta;
import com.ifrs.restinga.restinga_servicos.classes.Email;
import com.ifrs.restinga.restinga_servicos.classes.Usuario;
import com.ifrs.restinga.restinga_servicos.utils.EmailHelper;

import java.time.Instant;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

/**
 *
 * @author not275ssd
 */
//http://localhost:8080/entry-point/restingaservicos/email
@Path("/entry-point/restingaservicos/email")
public class EmailEntry extends EntryPoint{
    
    public EmailEntry() {
        super(EmailEntry.class
                ,Email.class
                ,"/entry-point/restingaservicos/email");
    }
    
    @POST
    @Path("/reset")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(Usuario usuario){
       
        try{
        
            if(usuario != null){

                Email email = new Email();
                email.setConfirmacao(false);
                email.setConta(usuario);
                
                Instant instant = new Date().toInstant().plusSeconds(86400);
                Date data = Date.from(instant);
                
                email.setData_expira(data);
                email.setToken(UUID.randomUUID().toString());

                try{

                    genericDAO.InsertDAO(email);

                    EmailHelper.Email.enviaEmail(usuario.getEmail(), 
                    "Troca de Senha:", 
                    "Você ativou a troca da senha.\n Para alterar a "
                    .concat("senha, acesse o caminho: (url da entrypoint + token) = token: " //adicionar aqui o link mais tarde do servidor para acesso direto.
                            .concat(email.getToken())
                            .concat("\n Se nao requisitou a troca da senha ignore este email!")
                            .concat("\nNAO RESPONDA ESTA MENSAGEM!")));

                }
                catch(Exception ex){
                    throw new IllegalArgumentException(ex.getMessage());
                }
            
            }
            else{
                throw new IllegalArgumentException("Usuario invalido!");
            }
            
        }
        catch(IllegalArgumentException ex){
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("application/json")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
            
        }

        return Response.ok(Response.Status.OK)
                    .type("application/json")
                    .entity(Response.Status.OK 
                            + " : ".concat("Email enviado com sucesso para "
                                   .concat(usuario.getEmail()))).build();
        
    }
    
    @GET
    @Path("/validate/{param}/{param2}")
    @Produces("application/json")
    public Response validateToken(@PathParam("param") String token, 
                                  @PathParam("param2") String pass){
        
        getLOGGER().debug("Cliente executando metodo (validateToken(String token))");
        getLOGGER().debug("retornando resultados:");

        Consulta consulta = new Consulta(0,
                            "token",
                            Consulta.LogicaEnum.EQUALS,
                            token);
        
        consulta.adicionaArgs(Consulta.CondicaoEnum.AND, 
                "data_expira", 
                Consulta.LogicaEnum.GREATER_EQ, 
                new Date());
        
        consulta.adicionaArgs(Consulta.CondicaoEnum.AND, 
                "confirmacao", 
                Consulta.LogicaEnum.EQUALS
                , false);
        
        getLOGGER().info("CONSULTA: " + consulta.getConsultaName(false));
   
        try {
            
            if(pass.length() < 10){
                throw new IllegalArgumentException("A senha muito curta, digite pelo menos 10 caracteres.");
            }
            
            //provisório.
            //JSONObject obj = genericDAO.getDAO(consulta);
            Email email = (Email)genericDAO.getObjectDAO(consulta);
            
           // ObjectMapper mapper = new ObjectMapper();
           // Email email = mapper.readValue(obj.toString(), Email.class); //erro aqui
            
            email.setConfirmacao(true); 
            genericDAO.UpdateDAO(email);
            
            email.getConta().setSenha(pass);
            
            return new UsuarioEntry().updateUsuario(email.getConta());
            
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("text/html")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
        
        }
        
    }
    
    @Override
    public Response list(){
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
    @Override
    public Response get(int id){
        return Response.status(Response.Status.FORBIDDEN).build();
    }
    
}
