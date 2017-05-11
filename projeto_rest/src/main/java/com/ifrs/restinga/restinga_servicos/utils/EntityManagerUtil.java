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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 *
 * @author not275ssd
 */
public class EntityManagerUtil {
    
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private final static Logger LOGGER = Logger.getLogger(EntityManagerUtil.class);

        static {

            try {
                
                LOGGER.debug("Inicializando objeto ENTITY_MANAGER_FACTORY.");
                ENTITY_MANAGER_FACTORY 
                        = Persistence.createEntityManagerFactory("com.ifrs.restinga.restinga_servicos_projeto_rest_serverPU");
                
            } 
            catch (Throwable ex) {

                LOGGER.error("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);

            }

        }

    public static EntityManager getEntityManager() {
        
        LOGGER.debug("Acessando o método getEntityManager():");
        return ENTITY_MANAGER_FACTORY.createEntityManager();
        
    }
  
}
    
