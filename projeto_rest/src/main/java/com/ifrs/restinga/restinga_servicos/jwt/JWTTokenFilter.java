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
package com.ifrs.restinga.restinga_servicos.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.io.IOException;
import java.io.Serializable;

import java.security.Key;

import javax.annotation.Priority;

import javax.ws.rs.Priorities;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

/**
 *
 * @author not275ssd
 */

@Provider
@JWTToken
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenFilter implements ContainerRequestFilter, Serializable {   
   
    private final static Logger LOGGER = Logger.getLogger(JWTTokenFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
 
        // Get the HTTP Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
 
        if(authorizationHeader != null){
        
            // Extract the token from the HTTP Authorization header
           // String token = authorizationHeader.substring("Bearer".length()).trim();
            String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDaWNsYW5ob0BnbWFpbC5jb20ifQ.sadDRslJP7obalqoqumFrtCSj9CxPYx4rm6AxYgAzKa7wXEr547AlGZRGUonLeaB5g6WQ-Sy2Mf-gk6bU5Vbag";
            try {

                LOGGER.info(authorizationHeader);
                Jwts.parser().setSigningKey(JWTSecretKey.JWTSecretKeyManager.GetSecretKey()).parseClaimsJws(token);
                LOGGER.warn("-------------> valid token : " + token);

            } 
            catch (ExpiredJwtException 
                    | MalformedJwtException 
                    | SignatureException 
                    | UnsupportedJwtException 
                    | IllegalArgumentException e) {

                LOGGER.warn("------------->".concat(e.getMessage()));
                LOGGER.warn("-------------> invalid token : " + token);
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

            }
        
        }
        else{
            
            LOGGER.error("HTTP Header: Authorization Header not found.");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            
        }
        
    }
    
}
