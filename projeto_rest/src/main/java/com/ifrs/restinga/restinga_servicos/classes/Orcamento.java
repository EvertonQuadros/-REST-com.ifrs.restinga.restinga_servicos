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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 *
 * @author not275ssd
 */
@Entity
@Table(name = "orcamento")
public class Orcamento extends Entidade{
    
    @OneToOne
    @JoinColumn(name = "contratante_id")
    private Pessoa contratante;
    
    @OneToOne
    @JoinColumn(name = "contratado_id")
    private Pessoa contratado;
    
    @Column(name = "data_orcamento", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_orcamento;
    
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean status;
    
    private String descricao;

    public Pessoa getContratante() {
        return contratante;
    }

    public void setContratante(Pessoa contratante) {
        this.contratante = contratante;
    }

    public Pessoa getContratado() {
        return contratado;
    }

    public void setContratado(Pessoa contratado) {
        this.contratado = contratado;
    }

    public Date getData_orcamento() {
        return data_orcamento;
    }

    public void setData_orcamento(Date data_orcamento) {
        this.data_orcamento = data_orcamento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
   
}
