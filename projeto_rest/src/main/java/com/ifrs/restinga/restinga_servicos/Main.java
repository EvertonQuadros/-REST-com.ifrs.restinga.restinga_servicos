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
package com.ifrs.restinga.restinga_servicos;

import com.ifrs.restinga.restinga_servicos.db.DBConnection;

import com.ifrs.restinga.restinga_servicos.jwt.JWTSecretKey;

import com.ifrs.restinga.restinga_servicos.utils.Utils;

import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

import org.eclipse.jetty.server.Server;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import org.apache.log4j.Logger;


public class Main {
    
    private final static Logger LOGGER = Logger.getLogger(Main.class);
    private static final PropertiesConfiguration CONFIG 
            = Utils.Configurations.getConfiguration("config.properties");
    
    public static void main(String[] args) throws Exception {
        
        JWTSecretKey.JWTSecretKeyManager.GenerateSecretKey();
        
        LOGGER.debug("Instanciando contexto...");
        ServletContextHandler context 
                = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        LOGGER.debug("Instanciando servidor...");
        Server jettyServer = new Server(CONFIG.getInt("jersey.port"));
        jettyServer.setHandler(context);
        
        ServletHolder jerseyServlet
                = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        
        jerseyServlet.setInitOrder(0);
        
        LOGGER.debug("Instanciando banco de dados...");
        DBConnection.startDB(CONFIG);

        LOGGER.debug("Definindo parametros de inicializacao:");
        List<Object> classes = CONFIG.getList("rest.classes");
        
        StringBuilder entrypoints = new StringBuilder();
        
        classes.forEach((classe) -> {
            entrypoints.append(classe);
        });
        
        LOGGER.debug(entrypoints.toString());
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames" ,entrypoints.toString());
        //context.addFilter(JWTTokenFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        try {

            LOGGER.debug("Iniciando servidor rest jetty...");
            jettyServer.start();
            jettyServer.join();

        } 
        finally {
            jettyServer.destroy();
        }

    }

}
