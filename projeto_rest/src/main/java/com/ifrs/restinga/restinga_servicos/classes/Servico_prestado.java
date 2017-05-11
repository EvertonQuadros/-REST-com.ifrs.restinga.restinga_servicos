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
@Table(name = "servico_prestado")
public class Servico_prestado extends Entidade {

    @OneToOne
    @JoinColumn(name = "pessoa_Contratante_id")
    private Pessoa contratante;
    
    @OneToOne
    @JoinColumn(name = "pessoa_Contratada_id")
    private Pessoa contratada;
    
    private String descricao;
    private int nota_contratado;
    
    @Column(name = "data_contrato", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_contrato;
    
    @Column(name = "data_termino", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data_termino;
    
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean orcamento;
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Pessoa getContratante() {
        return contratante;
    }

    public void setContratante(Pessoa contratante) {
        this.contratante = contratante;
    }

    public Pessoa getContratada() {
        return contratada;
    }

    public void setContratada(Pessoa contratada) {
        this.contratada = contratada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNota_contratado() {
        return nota_contratado;
    }

    public void setNota_contratado(int nota_contratado) {
        this.nota_contratado = nota_contratado;
    }

    public Date getData_contrato() {
        return data_contrato;
    }

    public void setData_contrato(Date data_contrato) {
        this.data_contrato = data_contrato;
    }

    public Date getData_termino() {
        return data_termino;
    }

    public void setData_termino(Date data_termino) {
        this.data_termino = data_termino;
    }

    public boolean isOrcamento() {
        return orcamento;
    }

    public void setOrcamento(boolean orcamento) {
        this.orcamento = orcamento;
    }
    
}
