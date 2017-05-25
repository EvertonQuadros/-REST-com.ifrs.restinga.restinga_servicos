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

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 *
 * @author not275ssd
 */
@Entity
@Table(name = "pessoa_profissao")
public class Pessoa_profissao extends Entidade {

    @Id
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    
    @Id
    @OneToOne
    @JoinColumn(name = "profissao_id")
    private Profissao profissao;
    
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean flg_principal;
 
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Profissao getProfissao() {
        return profissao;
    }

    public void setProfissao(Profissao profissao) {
        this.profissao = profissao;
    }

    public boolean isFlg_principal() {
        return flg_principal;
    }

    public void setFlg_principal(boolean flg_principal) {
        this.flg_principal = flg_principal;
    } 

    @Override
    public int hashCode() {
        
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.pessoa);
        hash = 53 * hash + Objects.hashCode(this.profissao);
        return hash;
        
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Pessoa_profissao other = (Pessoa_profissao) obj;
        
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        
        return Objects.equals(this.profissao, other.profissao);
        
    }
    
}
