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
package com.ifrs.restinga.restinga_servicos.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

import org.apache.log4j.Logger;

import org.h2.tools.RunScript;

/**
 *
 * @author not275ssd
 */
public class DBConnection {
    
    private final static Logger LOGGER = Logger.getLogger(DBConnection.class);
    private static Connection dbConnection;
    private static PropertiesConfiguration config;
    
    private static Connection getDBConnection(){

        LOGGER.debug("Executando o metodo getDBConnection()");
        
        dbConnection = null;
        
        try {
            
            Class.forName(config.getString("database.driver"));
            dbConnection = DriverManager.getConnection(config.getString("database.connection"), 
                                                       config.getString("database.user"), 
                                                       config.getString("database.password"));
         
        } 
        catch (ClassNotFoundException | SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
  
        return dbConnection;
        
    }
    
    public static void startDB(PropertiesConfiguration config){
        
        LOGGER.debug("Executando o metodo startDB(PropertiesConfiguration config)");
        
        FileReader reader = null;
        boolean onError = false;
        DBConnection.config = config;
        getDBConnection();
        
        String dir = System.getProperty("user.dir")
            .concat(config.getString("database.sqlpath"));
        
        List<Object> scripts = config.getList("database.scripts");
          
        File arq = new File(System.getProperty(("user.dir"))
            .concat(config.getString("database.local")));
 
        if(!arq.exists() 
                && !arq.isDirectory() 
                    || arq.length() <= 10000000) {
            
            LOGGER.warn("ARQUIVO DE BANCO DE DADOS NAO ENCONTRADO, OU PARECE ESTAR VAZIO.");
            LOGGER.warn("GERANDO UM NOVO BANCO DE DADOS... AGUARDE...");
        
            try {
                
                for (Object caminho : scripts) {
                 
                    reader = new FileReader(dir.concat(caminho.toString()));
                    LOGGER.debug("Gerando arquivo sql: " + caminho);
                    RunScript.execute(dbConnection, reader);
                    LOGGER.debug("Gerado com sucesso!");
       
                }
                
            } 
            catch (SQLException | FileNotFoundException ex) {
                
                LOGGER.fatal(ex.getMessage());
                onError = !onError;

            }            
            finally {
            
                try {
                    
                    dbConnection.close();
                    
                    if(reader != null){
                        reader.close();
                    }
                    
                } 
                catch (SQLException | IOException ex) {
                    
                    LOGGER.fatal(ex.getMessage());
                    
                    if(!onError){
                        onError = !onError;
                    }
                    
                }

                if(onError){
                    
                    arq.delete();
                    System.exit(1);
                    
                }
                
            }
            
        }
        else{
            LOGGER.info("Banco de dados encontrado, prosseguindo programa...");
        }

    }
    
}
