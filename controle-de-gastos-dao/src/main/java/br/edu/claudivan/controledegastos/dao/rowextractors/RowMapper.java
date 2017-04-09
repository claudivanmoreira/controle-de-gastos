/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.rowextractors;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Claudivan Moreira
 * @param <T>
 */
public interface RowMapper <T> {
    
    public T mapRow(ResultSet resultSet) throws SQLException;
    
}
