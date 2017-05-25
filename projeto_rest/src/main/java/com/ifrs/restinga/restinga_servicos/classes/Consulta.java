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
package com.ifrs.restinga.restinga_servicos.classes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author not275ssd
 */
public class Consulta {
   
    private final int limit;
    private String orderBy = null;
    private String groupBy = null;
    private int pos = 1;
    private final Map<Argumento,String> args = new LinkedHashMap<>();
   
    public Consulta(int limit, String campo, LogicaEnum logica, Object valor){
        
        this.limit = limit;
        args.put(new Argumento(campo,logica,valor), null);
    
    }
    
    public void adicionaArgs(CondicaoEnum prefixo, String campo, LogicaEnum logica,  Object valor){
        args.put(new Argumento(campo,logica,valor), prefixo.getCondicao());
    }
    
    public int getLimit(){
        return limit;
    }
    
    public Map getArgumentos(){
        return args;
    }
    
    public String getOrderBy(){
        return orderBy;
    }
    
    public void setOrderBy(String campo){
        orderBy = campo;
    }
    
    public String getGroupBy(){
        return groupBy;
    }
    
    public void setGroupBy(String campo){
        groupBy = campo;
    }
    
    public int getPos(){
        return pos;
    }
    
    public String getConsultaName(boolean buildQuery){
        
        pos = 1;
        
        StringBuilder consulta = new StringBuilder();
        consulta.append(" Where ");
        
        args.entrySet().forEach((entry) -> {
            
            if (entry.getValue() != null) {
                consulta.append(entry.getValue());
            }
            
            Argumento argumento = (Argumento) entry.getKey();
            
            consulta.append(argumento.getCampo())
                    .append(" ")
                    .append(argumento.getLogica())
                    .append(" ");
            
            if (buildQuery){
                consulta.append(" ?")
                        .append(pos++);
            }
            else{
                consulta.append(argumento.getValor());
            }

        });
        
        if(orderBy != null){
            consulta.append(" order by ")
                    .append(orderBy);
        }
        else if(groupBy != null){
            consulta.append(" group by ")
                    .append(groupBy);
        }
        
        return consulta.toString();
        
    }
   
    public Object getArgumentoByIndex(int index){
        return ((Argumento)(args.keySet().toArray())[index]).getValor();
    }
    
    /**
    * Tipo de enumeradores que representam as operaç?es condicionais em uma pesquisa:
    * AND (Operacao E),
    * OR (Operacao OU),
    * NOT (Operacao NAO (NEGAÇAO)).
    */
    public enum CondicaoEnum {
       AND(" AND "),
       OR(" OR "),
       NOT(" NOT ");

       private final String condicao;

       CondicaoEnum(String condicao){
           this.condicao = condicao;
       }

       private String getCondicao(){
           return this.condicao;
       }

    }
    
     /**
    * Tipo de enumeradores que representam as operaçoes lógicas em uma pesquisa:
    * EQUALS (Operaçao igual (=)),
    * DIFF (Operaçao diferente (!=)),
    * LIKE (Operaçao comparativa de String (LIKE)).
    * LESSER (Operaç?o menor (<)),
    * LESSER_EQ (Operacao menor ou igual (<=)),
    * GREATER (Operaç?o maior (>)),
    * GREATER_EQ (Operaç?o maior ou igual (>=)).
    */
    public enum LogicaEnum {
       EQUALS(" = "),
       DIFF(" != "),
       LIKE(" LIKE "),
       LESSER(" < "),
       LESSER_EQ(" <= "),
       GREATER(" > "),
       GREATER_EQ(" >= ");
       
       private final String logica;

       LogicaEnum(String logica){
           this.logica = logica;
       }

       private String getLogica(){
           return this.logica;
       }

    }
    
    private class Argumento{
        
        private final String campo;
        private final String logica;
        private final Object valor;
        
        public Argumento(String campo, LogicaEnum logica, Object valor){
            
            this.campo = campo;
                this.logica = logica.getLogica();
                    this.valor = valor;
            
        }
        
        public Argumento getArgumento(){
            return this;
        }
        
        public String getCampo(){
            return this.campo;
        }
        
        public String getLogica(){
            return logica;
        }
        
        public Object getValor(){
            return this.valor;
        }
        
    }

}
