/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.ifrs.restinga.restinga_servicos.db;

import com.ifrs.restinga.restinga_servicos.classes.Entidade;

import org.apache.log4j.Logger;

/**
 *
 * @author not275ssd
 * @param <T>
 */
public class GenericUpdate <T extends Entidade> extends GenericDAO<T> {
    
    protected GenericUpdate(Logger LOGGER, Class<T> tipo) {    
        super(LOGGER, tipo);
    }
    
    public void update(T entidade) throws Exception{
        
        LOGGER.debug("Executando o metodo update(T Entidade)"); 

        try {
            
            entityManager.getTransaction().begin();
            entityManager.merge(entidade);
            entityManager.getTransaction().commit();
   
        } 
        catch (Exception e) {
            
            LOGGER.error("SQLEXCEPTION: " + e.getMessage());
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
            
        }
        
    }
    
}
