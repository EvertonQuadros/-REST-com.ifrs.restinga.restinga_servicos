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

import com.ifrs.restinga.restinga_servicos.classes.Entidade;
import com.ifrs.restinga.restinga_servicos.db.GenericDAO;

import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

//TODO Adicionar novos métodos criados para inserç?o,delete e update como sobrescrita no email (forbidden).

//Endereço (localhost): http://localhost:8080/entry-point/restingaservicos

/**
 *
 * @author not275ssd
 * @param <T>
 */
//@Path("/entry-point/restingaservicos") 
public class EntryPoint<T> {

    private final Logger LOGGER;
    private final String url;
    protected GenericDAO genericDAO;
    
    /**
     *
     * @param tipo
     * @param subTipo
     * @param url
     */
    public EntryPoint(Class<T> tipo, Class<? extends Entidade> subTipo, String url){
        
        this.LOGGER = Logger.getLogger(tipo);
            this.url = url;
                this.genericDAO = new GenericDAO(LOGGER,subTipo);
            
    }
    
    public EntryPoint(){
        
        this.LOGGER = Logger.getLogger(EntryPoint.class);
            this.url = "/entry-point/restingaservicos";
            
    }

    public Logger getLOGGER() {
        return LOGGER;
    }

    public String getUrl() {
        return url;
    }
    
    public GenericDAO getGenericDAO() {
        return genericDAO;
    }
    
    /**
     *
     * @return
     * @throws java.net.URISyntaxException
     */
    @GET
    @Produces("text/html")
    public Response telaApresentacao() throws URISyntaxException {
        
        //adicionar aqui o link da home do projeto web em LUA.
        LOGGER.debug("Cliente executando o método telaApresentacao().");
        return Response.temporaryRedirect(new java.net.URI("http://jersey.java.net")).build();

    }  

    @GET
    @Path("/get/{param}")
    @Produces("application/json")
    public Response get(@PathParam("param") int id){

        getLOGGER().debug("Cliente executando metodo (get(int id))");
        getLOGGER().debug("retornando resultados:");
       
        try {
            return Response.ok(genericDAO.getDAO(id).toString()).build();
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("text/html")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
        
        }
        
    }
    
    @GET
    @Path("/list")
    @Produces("application/json")
    public Response list(){
        
        getLOGGER().debug("Cliente executando metodo (list())");
        getLOGGER().debug("retornando resultados (limit 1000):");
       
        try {
            return Response.ok(genericDAO.listDAO(1000).toString()).build();
        } 
        catch (Exception ex) {
            
            getLOGGER().error(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .type("text/html")
                    .entity(Response.Status.INTERNAL_SERVER_ERROR 
                            + " : ".concat(ex.getMessage())).build();
        
        }
        
    }
    
//    @POST
//    @Path("/")
//    @Consumes("application/json")
//    public Response inserePessoa(Pessoa pessoa) throws SQLException {
//
//        LOGGER.info("Cliente executando metodo (inserePessoa())");
//        LOGGER.info("Abrindo conexao com o banco de dados...");
//        connection = getDBConnection();
//        String result = "";
//
//        if (!pessoa.getNome().equals("")) {
//
//            try {
//
//                connection.setAutoCommit(false);
//
//                preparedStatement = null;
//                String InsertQuery = "INSERT INTO Pessoas" + "(nome) values" + "(?)";
//
//                LOGGER.info("Gerando insercao no banco de dados...");
//
//                preparedStatement = connection.prepareStatement(InsertQuery);
//                preparedStatement.setString(1, pessoa.getNome());
//                preparedStatement.executeUpdate();
//                preparedStatement.close();
//
//                connection.commit();
//
//                result = pessoa.getNome() + " inserido com sucesso!";
//                LOGGER.info(result);
//
//            } 
//            catch (SQLException e) {
//                LOGGER.error("SQLException:" + e.getLocalizedMessage());
//            } 
//            catch (Exception e) {
//                LOGGER.error("Exception:" + mostraStackTrace(e));
//            } 
//            finally {
//
//                LOGGER.info("Finalizando conexao com o banco de dados...");
//                connection.close();
//
//            }
//
//        } 
//        else {
//
//            result = "E necessario informar um valor!";
//            LOGGER.warn(result);
//
//        }
//
//        LOGGER.info("Retornando resposta...");
//        return Response.status(201).entity(result).build();
//
//    }
//
//    @GET
//    @Path("/delete/{param}")
//    @Produces("application/json")
//    public Response deletaPessoa(@PathParam("param") int id) throws SQLException {
//
//        String response = "";
//        LOGGER.info("Cliente executando metodo (deletaPessoa())");
//
//        try {
//
//            String InsertQuery = "DELETE FROM Pessoas WHERE id=?";
//
//            LOGGER.info("Abrindo conexao com o banco de dados...");
//            connection = getDBConnection();
//            preparedStatement = null;
//
//            LOGGER.info("Gerando remocao no banco de dados...");
//
//            preparedStatement = connection.prepareStatement(InsertQuery);
//            preparedStatement.setInt(1, id);
//            response = "" + preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        } 
//        catch (SQLException e) {
//            LOGGER.error("SQLException:" + e.getLocalizedMessage());
//        } 
//        catch (Exception e) {
//            LOGGER.error("Exception:" + mostraStackTrace(e));
//        } 
//        finally {
//
//            LOGGER.info("Finalizando conexao com o banco de dados...");
//            connection.close();
//
//        }
//
//        if (response.equals("1")) {
//            response = "Registro excluido com sucesso!";
//        } 
//        else {
//            response = "registro nao encontrado!";
//        }
//
//        LOGGER.info("Retornando resposta: " + response);
//        return Response.status(201).entity(response).build();
//
//    }
//
//    @POST
//    @Path("/update/")
//    @Consumes("application/json")
//    public Response atualizaPessoa(Pessoa pessoa) throws SQLException {
//
//        String result = "";
//        LOGGER.info("Cliente executando metodo (atualizaPessoa())");
//
//        try {
//
//            String InsertQuery = "UPDATE Pessoas SET nome = ? where id = ?";
//
//            LOGGER.info("Abrindo conexao com o banco de dados...");
//            connection = getDBConnection();
//
//            preparedStatement = null;
//
//            LOGGER.info("Gerando update no banco de dados...");
//            preparedStatement = connection.prepareStatement(InsertQuery);
//            preparedStatement.setInt(2, pessoa.getId());
//            preparedStatement.setString(1, pessoa.getNome());
//            result = "" + preparedStatement.executeUpdate();
//            preparedStatement.close();
//
//        } 
//        catch (SQLException e) {
//            LOGGER.error("SQLException:" + e.getLocalizedMessage());
//        } 
//        catch (Exception e) {
//            LOGGER.error("Exception:" + mostraStackTrace(e));
//        } 
//        finally {
//
//            LOGGER.info("Finalizando conexao com o banco de dados...");
//            connection.close();
//
//        }
//
//        if (result.equals("1")) {
//            result = "Registro atualizado com sucesso!";
//        } 
//        else {
//            result = "registro nao encontrado!";
//        }
//
//        LOGGER.info("Retornando resposta: " + result);
//        return Response.status(201).entity(result).build();
//
//    }

}
