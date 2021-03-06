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
 * Authors: �verton Quadros do Couto (eqdcouto@restinga.ifrs.edu.br)
 *          Rodrigo Sebben           (rsebben@restinga.ifrs.edu.br)
 *          Natalia Schnechel        (nsschenechel@restinga.ifrs.edu.br)
 * 
 * Created: 03/04/2017
 */
package com.ifrs.restinga.restinga_servicos.classes;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author not275ssd
 */
@Entity
@Table(name = "contato_tipo")
public class Contato_tipo extends Entidade {

    private String nomeTipoContato;

    public String getNomeTipoContato() {
        return nomeTipoContato;
    }

    public void setNomeTipoContato(String nomeTipoContato) {
        this.nomeTipoContato = nomeTipoContato;
    }
 
}
