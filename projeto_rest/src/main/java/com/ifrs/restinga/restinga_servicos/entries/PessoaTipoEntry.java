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
package com.ifrs.restinga.restinga_servicos.entries;

import com.ifrs.restinga.restinga_servicos.classes.Pessoa_tipo;

import javax.ws.rs.Path;

/**
 *
 * @author not275ssd
 */
//http://localhost:8080/entry-point/restingaservicos/pessoatipo
@Path("/entry-point/restingaservicos/pessoatipo")
public class PessoaTipoEntry extends EntryPoint{
    
    public PessoaTipoEntry() {
        super(PessoaTipoEntry.class,
                Pessoa_tipo.class,
                "/entry-point/restingaservicos/pessoatipo");
    }
    
}
