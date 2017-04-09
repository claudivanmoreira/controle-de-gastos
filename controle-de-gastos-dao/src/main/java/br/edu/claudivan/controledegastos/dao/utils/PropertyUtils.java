/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.utils;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Claudivan Moreira
 */
public class PropertyUtils {
    
    public static Properties getPropertiesFromClassPath(String fileName) {
        Properties readedProperties = new Properties();
        try {
            readedProperties.load(PropertyUtils.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException ex) {
            throw new RuntimeException("Error while trying to read file " + fileName + ": " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Generic error while trying to read file " + fileName + ": " + ex.getMessage(), ex);
        }
        return readedProperties;
    }
}
