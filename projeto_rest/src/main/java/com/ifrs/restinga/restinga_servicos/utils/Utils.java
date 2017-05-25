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
package com.ifrs.restinga.restinga_servicos.utils;

import com.google.gson.Gson;

import com.ifrs.restinga.restinga_servicos.classes.Entidade;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.ResultSet;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import org.apache.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import org.reflections.Reflections;

/**
 *
 * @author not275ssd
 */
public class Utils {
    
    private static final Logger LOGGER = Logger.getLogger(Utils.class);
    
    public static class General {
       
        private static final String SEPARATOR = File.separator;
        private static final long TIMEADJ = 24*60*60*1000;
           
        public static Date getDataExpira(){
            return new Date (new Date().getTime() + TIMEADJ);
        }
        
        public static String getResourcePath(){
               
            StringBuilder path = new StringBuilder();
            
            path.append(System.getProperty("user.dir"))
                .append(SEPARATOR)
                .append("src")
                .append(SEPARATOR)
                .append("main")
                .append(SEPARATOR)
                .append("resources")
                .append(SEPARATOR);

            return path.toString();
            
        }
        
        public static String mostraStackTrace(Exception e) {

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            
            return sw.toString();

        }
        
    }
    
    public static class Validator {
        
        private static Pattern pattern;
	private static Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		.concat("[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	public static boolean validaEmail(final String email) {

            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            
            return matcher.matches();

	}
        
    }
    
    public static class JSON {
        
        public static JSONArray convertToJSONArray(ResultSet resultSet) throws Exception{
        
            JSONArray jsonArray = new JSONArray();

            while (resultSet.next()) {

                JSONObject jsonObj = new JSONObject();

                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    jsonObj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                            .toLowerCase(), resultSet.getObject(i + 1));
                }

                jsonArray.put(jsonObj);

            }

            return jsonArray;
        
        }
        
        public static JSONObject convertToJSON(ResultSet resultSet) throws Exception{
            
            JSONObject jsonObj = new JSONObject();
            
            if(resultSet.next()){
                
                for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                    jsonObj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                            .toLowerCase(), resultSet.getObject(i + 1));
                }
   
            }
            
            return jsonObj;
            
        }
        
        public static Entidade convertFromJSONString(String json, Class<? extends Entidade> tipo){
            return new Gson().fromJson(json, tipo);
        }
        
    }
    
    public static class Configurations {
        
        private static PropertiesConfiguration config;
        
        public static PropertiesConfiguration getConfiguration(String propertiesFile){
            
            LOGGER.debug("Executando metodo getConfiguration()");
            
            try{
                
                config = new PropertiesConfiguration();
                config.load(Utils.General.getResourcePath().concat(propertiesFile));
                
            }
            catch(ConfigurationException ex){
                
                LOGGER.fatal(ex.getMessage());
                System.exit(1);
                
            }
                  
            return config;
            
        }
        
        public static void setConfiguration(String propertiesFile, 
                                            HashMap<String,String> valores){

            try{
            
                config = new PropertiesConfiguration(Utils.General.getResourcePath()
                        .concat(propertiesFile));
                
                valores.entrySet().forEach((valor) -> {
                    config.setProperty(String.valueOf(valor.getKey()), valor.getValue());
                });
                
                config.save();
            
            }
            catch(ConfigurationException ex){
                
                LOGGER.fatal(ex.getMessage());
                System.exit(1);
                
            }
            
        }
        
    }
    
    public static class Reflection{
        
        private final static Logger LOGGER = Logger.getLogger(Reflection.class);
        
        public static Entidade getInstance(Entidade entidade){
     
            Reflections reflections = new Reflections();
            Set<Class<? extends Entidade>> subTypes = reflections.getSubTypesOf(Entidade.class);

            for(Class<? extends Entidade> sub : subTypes){
               
                Entidade entity = null;
                
                try {
                    
                    entity = sub.newInstance();
                    entity = entity.getClass().cast(entidade);
                    
                } 
                catch (InstantiationException | IllegalAccessException ex) {
                    LOGGER.error(ex.getMessage());
                }
                
                if(entity != null){
                    
                    LOGGER.debug(entity.getClass().getSimpleName());
                    LOGGER.debug("--->"+entidade.getClass().getSimpleName());

                    if(entity.getClass().isInstance(entidade)){
                        return entity.getClass().cast(entidade);
                    }
  
                }
                
            }
     
            return null;
            
        }
  
    }
    
}
