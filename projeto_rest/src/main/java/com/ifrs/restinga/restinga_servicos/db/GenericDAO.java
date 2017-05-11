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

import com.ifrs.restinga.restinga_servicos.utils.EntityManagerUtil;

import java.lang.reflect.ParameterizedType;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author not275ssd
 * @param <T>
 */
public class GenericDAO<T extends Entidade>{
    
    protected static Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected final Logger LOGGER;
    protected final Class<T> tipo;
    protected final EntityManager entityManager 
            = EntityManagerUtil.getEntityManager();
    protected StringBuilder argumento = null;
    protected Query query = null;
    
    public GenericDAO(Logger LOGGER, Class<T> tipo){
      
        this.LOGGER = LOGGER;
        this.tipo = tipo;
        
    }

    /**
     * Método que retorna o tipo de classe na qual a nossa classe 
     * foi instanciada.
     * @return Tipo da Classe.
     */
    private Class<T> getT(){
        
        ParameterizedType parameterizedType = (ParameterizedType) getClass()
                .getGenericSuperclass();

        return (Class<T>) parameterizedType.getActualTypeArguments()[0]; 
        
    }
    
    protected Class<T> getTipo(){
        return tipo;
    }

    public JSONObject getDAO(int id) throws Exception{
        return new GenericGet(LOGGER,tipo).get(id);
    }
    
    public JSONObject getDAO(Consulta consulta) throws Exception{
        return new GenericGet(LOGGER,tipo).get(consulta);
    }
    
    public Entidade getObjectDAO(Consulta consulta) throws Exception{
        return new GenericGet(LOGGER,tipo).getObject(consulta);
    }
    
    public JSONArray listDAO(int limit) throws Exception{
        return new GenericList(LOGGER,tipo).list(limit);
    }
    
    public JSONArray listDAO(Consulta consulta) throws Exception{
        return new GenericList(LOGGER,tipo).list(consulta);
    }
    
    public void InsertDAO(T entidade) throws Exception{
        new GenericInsert(LOGGER,tipo).insert(entidade);
    }
    
    public void UpdateDAO(T entidade) throws Exception{
        new GenericUpdate(LOGGER,tipo).update(entidade);
    }

}
