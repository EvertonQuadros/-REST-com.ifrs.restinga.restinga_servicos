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

import com.ifrs.restinga.restinga_servicos.classes.Consulta;
import com.ifrs.restinga.restinga_servicos.classes.Entidade;

import java.util.List;

import org.apache.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author not275ssd
 * @param <T>
 */
public class GenericList<T extends Entidade> extends GenericDAO<T> {

    protected GenericList(Logger LOGGER, Class<T> tipo) {    
        super(LOGGER, tipo);
        
        argumento = new StringBuilder();
        argumento.append("from ")
                 .append(tipo.getSimpleName())
                 .append(" c");
        
    }
  
    protected JSONArray list(int limit) throws Exception{
 
        JSONArray jsonObjetos = null;

        if(query == null){
            query = entityManager.createQuery(argumento.toString());
        }
        
        try {
        
            entityManager.getTransaction().begin();
            
            List<T> entidades;
            
            if(limit > 0){
                entidades = query.setMaxResults(limit).getResultList();
            }
            else{
                entidades = query.getResultList();
            }
            
            entityManager.getTransaction().commit();
           
            jsonObjetos = new JSONArray(entidades);
            
        } 
        catch (Exception e) {
            
            LOGGER.error("SQLEXCEPTION: " + e.getMessage());
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
            
        }

        jsonObjetos.put(listWithCount(jsonObjetos.length())); 
  
        return jsonObjetos;
   
    }
   
    protected JSONArray list(Consulta consulta) throws Exception{

        query = entityManager.createQuery(argumento.toString()
                                       .concat(consulta.getConsultaName(true)));
        
        for(int i = 1 ; i < consulta.getPos(); i++){
            query.setParameter(i, consulta.getArgumentoByIndex(i-1));    
        }

        return list(consulta.getLimit());
   
    }
    
    private JSONObject listWithCount(int size) throws Exception{
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("count", size);
        
        return jsonObj;
        
    }
    
}
