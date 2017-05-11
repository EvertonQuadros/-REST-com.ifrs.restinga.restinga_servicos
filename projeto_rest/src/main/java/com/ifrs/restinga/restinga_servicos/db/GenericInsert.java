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

import com.ifrs.restinga.restinga_servicos.classes.Entidade;

import org.apache.log4j.Logger;

/**
 *
 * @author not275ssd
 * @param <T>
 */
public class GenericInsert <T extends Entidade> extends GenericDAO<T> {

    protected GenericInsert(Logger LOGGER, Class<T> tipo) {    
        super(LOGGER, tipo);
    }
    
    public void insert(T entidade) throws Exception{
        
        LOGGER.debug("Executando o metodo insert(T Entidade)"); 

        try {
            
            entityManager.getTransaction().begin();
            entityManager.persist(entidade);
            entityManager.getTransaction().commit();
   
        } 
        catch (Exception e) {
            
            LOGGER.error("SQLEXCEPTION: " + e.getMessage());
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
            
        }
        
    }
    
}
