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
import com.ifrs.restinga.restinga_servicos.classes.Usuario;
import com.ifrs.restinga.restinga_servicos.jwt.JWTSecretKey;

import com.ifrs.restinga.restinga_servicos.jwt.JWTToken;
import com.ifrs.restinga.restinga_servicos.utils.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import org.json.JSONObject;

/**
 *
 * @author not275ssd
 */
//http://localhost:8080/entry-point/restingaservicos/usuario
@Path("/entry-point/restingaservicos/usuario")
public class UsuarioEntry extends EntryPoint{
    
    public UsuarioEntry() {
        super(UsuarioEntry.class,
                Usuario.class,
                "/entry-point/restingaservicos/usuario");
    }

    @GET
    @Path("/nojwt")
    public Response echo(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
 
    @GET
    @Path("/jwt")
    @JWTToken
    public Response echoWithJWTToken(@QueryParam("message") String message) {
        return Response.ok().entity(message == null ? "no message" : message).build();
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Usuario usuario) {
        
        String token = null;
       // getLOGGER().info(secretKey.getClass());
        try {
 
            if(usuario != null){
              
                Consulta consulta = new Consulta(0,
                            "email",
                            Consulta.LogicaEnum.EQUALS,
                            usuario.getEmail());
                
                consulta.adicionaArgs(Consulta.CondicaoEnum.AND, 
                        "senha", 
                        Consulta.LogicaEnum.EQUALS, 
                        usuario.getSenha());
        
                getLOGGER().info("CONSULTA: " + consulta.getConsultaName(false));
                
                if(genericDAO.getDAO(consulta) != null){
                    
                    getLOGGER().info("gerando issue");
                    try{
                        token = issueToken(usuario.getEmail());
                    }
                    catch(UnsupportedEncodingException ex){
                         getLOGGER().warn(ex.getMessage());
                    }
                    
                    getLOGGER().info("TOKEN: " + token);
                    
                }
                
            }
            
            JSONObject obj = new JSONObject();
            obj.put("token", token);
            
            return Response.ok(obj.toString()).header(AUTHORIZATION, "Bearer " + token).build();
 
        } 
        catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
        
    }
 
    private String issueToken(String login) throws UnsupportedEncodingException {
//        
//        String Token = Jwts.builder()
//                .setSubject(login)
//                .setIssuer("RestingaServicos")
//                .setIssuedAt(new Date())
//                .setExpiration(Utils.General.getDataExpira())
//                .signWith(SignatureAlgorithm.HS512, JWTSecretKey.JWTSecretKeyManager.GetSecretKey().getBytes("UTF-8"))
//                .compact();
        String Token = Jwts.builder()
                        .setSubject(login)
                        .signWith(SignatureAlgorithm.HS512, JWTSecretKey.JWTSecretKeyManager.GetSecretKey().getBytes("UTF-8"))
                        .compact();

        return Token;
        
    }
    
    @Produces("application/json")
    protected Response updateUsuario(Usuario usuario){
        
        try{

            if(usuario != null){
                
                genericDAO.UpdateDAO(usuario);
                
                return Response.status(Response.Status.OK)
                    .type("text/html")
                    .entity(Response.Status.OK
                            + " : ".concat("Senha atualizada com sucesso!")).build();
                
            }
            else{
                throw new IllegalArgumentException("Conta do usuário n?o encontrada!");
            }
        
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("text/html")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
        
        }
        
    }
    
}
