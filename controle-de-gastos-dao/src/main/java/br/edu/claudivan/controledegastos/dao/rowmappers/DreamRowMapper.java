/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.rowmappers;

import br.edu.claudivan.controledegastos.dao.rowextractors.RowMapper;
import br.edu.claudivan.controledegastos.domain.Dream;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Claudivan Moreira
 */
public class DreamRowMapper implements RowMapper<Dream>{

    @Override
    public Dream mapRow(ResultSet resultSet) throws SQLException {
        Dream dream = new Dream();
        dream.setId(resultSet.getLong("id"));
        dream.setName(resultSet.getString("name"));
        dream.setAmountValue(resultSet.getString("amount_value"));
        dream.setValueSaved(resultSet.getString("saved_value"));
        return dream;
    }
    
}
