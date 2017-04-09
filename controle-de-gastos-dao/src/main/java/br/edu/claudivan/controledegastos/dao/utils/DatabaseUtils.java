/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Claudivan Moreira
 */
public class DatabaseUtils {
    
    private final String DB_CONFIG_FILE = "database.properties";
    private final String DRIVER_CLASS_NAME_KEY = "database.driver.className";
    private final String DB_URL_KEY = "database.url";
    private final String DB_USERNAME_KEY = "database.username";
    private final String DB_PASSWORD_KEY = "database.password";
    private final Properties databaseProperties;
    private static BasicDataSource dataSource;
    private static final DatabaseUtils DB_UTILS = new DatabaseUtils();

    private DatabaseUtils() {
        databaseProperties = PropertyUtils.getPropertiesFromClassPath(DB_CONFIG_FILE);
        createDataSource();
    }
    
    public static DatabaseUtils newInstance() {
        return DB_UTILS;
    }
    
    private void createDataSource() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(databaseProperties.getProperty(DRIVER_CLASS_NAME_KEY));
        dataSource.setUrl(databaseProperties.getProperty(DB_URL_KEY));
        dataSource.setUsername(databaseProperties.getProperty(DB_USERNAME_KEY));
        dataSource.setPassword(databaseProperties.getProperty(DB_PASSWORD_KEY));
        
        dataSource.setInitialSize(3);
        dataSource.setMaxIdle(3);
        dataSource.setMaxTotal(10);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxOpenPreparedStatements(180);
        dataSource.setMaxWaitMillis(5000);
        dataSource.setLogExpiredConnections(true);
        dataSource.setLogAbandoned(true);
    }
    
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
    
    public String getSqlQuery(String sqlKey) throws SQLException {
        return databaseProperties.getProperty(sqlKey);
    }
    
    public void closeConnection(Connection dbConnection) throws SQLException {
        if (dbConnection != null) {
            dbConnection.close();
        }
    }
    
    public void closeConnection(Connection dbConnection, ResultSet queryResultSet, PreparedStatement queryStatement) throws SQLException {
        if (queryStatement != null) {
            queryStatement.close();
        }
        
        if (queryResultSet != null) {
            queryResultSet.close();
        }
        
        closeConnection(dbConnection);
    }
}
