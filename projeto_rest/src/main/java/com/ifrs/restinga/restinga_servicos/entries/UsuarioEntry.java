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

import com.ifrs.restinga.restinga_servicos.classes.Usuario;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

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
