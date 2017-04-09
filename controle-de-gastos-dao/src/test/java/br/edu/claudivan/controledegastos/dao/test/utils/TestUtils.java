/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.test.utils;

import br.edu.claudivan.controledegastos.dao.test.DreamDaoTest;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claudivan Moreira
 */
public class TestUtils {
    
    public static DatabaseUtils prepareBD() throws Exception {
        System.out.println("prepareBD...");
        DatabaseUtils dbUtils = DatabaseUtils.newInstance();
        ScriptRunner runner = new ScriptRunner(dbUtils.getDataSource());
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/resources/create-db.sql")));
        runner.execute(reader);
        return dbUtils;
    }
    
    public static void cleanDatabaseBetweenTests(DatabaseUtils dbUtils) {
        try {
            Connection connection = dbUtils.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DreamDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
