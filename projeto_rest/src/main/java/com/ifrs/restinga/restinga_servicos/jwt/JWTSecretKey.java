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
package com.ifrs.restinga.restinga_servicos.jwt;

import com.ifrs.restinga.restinga_servicos.utils.Utils;

import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.HashMap;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.configuration.PropertiesConfiguration;

import org.apache.log4j.Logger;

/**
 *
 * @author not275ssd
 */
public class JWTSecretKey {
    
    private final static Logger LOGGER = Logger.getLogger(JWTSecretKey.class);
    
    public static class JWTSecretKeyManager{
        
        public static void GenerateSecretKey(){

            SecretKey secretKey;

            try {

                KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                keyGen.init(192);

                secretKey = keyGen.generateKey();
                String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
                
                HashMap<String,String> key = new HashMap<>();
                key.put("security.secretkey", encodedKey);
                
                Utils.Configurations.setConfiguration("security.properties", key);

            } 
            catch (NoSuchAlgorithmException ex) {
                LOGGER.error(ex.getMessage());
            }

        }

        public static String GetSecretKey(){

//            String encodedKey = 
//                    Utils.Configurations.getConfiguration("security.properties")
//                                                .getString("security.secretkey");
//            
            String encodedKey = "teste";
            
            return encodedKey;
            
//            byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
//            
//            return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"); 
            
        }
        
    }
    
}
