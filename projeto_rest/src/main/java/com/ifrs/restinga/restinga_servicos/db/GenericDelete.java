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
public class GenericDelete<T extends Entidade> extends GenericDAO<T> {
    
    public GenericDelete(Logger LOGGER, Class<T> tipo) {
        super(LOGGER, tipo);
    }
    
    public void delete(T entidade) throws Exception{
        
        LOGGER.debug("Executando o metodo delete(T Entidade)"); 

        try {
            
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.getReference(tipo, entidade.getId()));
            entityManager.getTransaction().commit();
   
        } 
        catch (Exception e) {
            
            LOGGER.error("SQLEXCEPTION: " + e.getMessage());
            entityManager.getTransaction().rollback();
            throw new Exception(e.getMessage());
            
        }
        
    }
    
}
