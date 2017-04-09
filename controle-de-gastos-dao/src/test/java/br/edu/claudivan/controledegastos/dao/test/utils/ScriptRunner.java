/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Claudivan Moreira
 */
public class ScriptRunner {

    private final BasicDataSource dataSource;

    public ScriptRunner(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void execute(BufferedReader bufferedReader) throws IOException, SQLException {
        StringBuilder scriptBuilder = new StringBuilder();
        while (bufferedReader.ready()) {
            scriptBuilder.append(bufferedReader.readLine());
        }
        Connection con = this.dataSource.getConnection();
        Statement st = con.createStatement();
        st.execute(scriptBuilder.toString());
        con.close();
    }
}
