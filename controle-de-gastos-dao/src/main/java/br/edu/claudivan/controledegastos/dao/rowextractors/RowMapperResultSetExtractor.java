/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.rowextractors;

import br.edu.claudivan.controledegastos.dao.exceptions.EmptyResultException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 * @param <T>
 */
public class RowMapperResultSetExtractor<T> {

    private final RowMapper rowMapper;
    private final ResultSet resultSet;

    public RowMapperResultSetExtractor(RowMapper rowMapper, ResultSet resultSet) {
        this.rowMapper = rowMapper;
        this.resultSet = resultSet;
    }

    public T extractSingle() throws SQLException {
        if (resultSet.next()) {
            return (T) this.rowMapper.mapRow(resultSet);
        } else {
            throw new EmptyResultException("No rows retuned for query.");
        }
    }
    
    public List<T> extractList() throws SQLException {
        List<T> results = new ArrayList();
        while (resultSet.next()) {
            results.add((T) this.rowMapper.mapRow(resultSet));
        }
        if (results.isEmpty()) throw new EmptyResultException("No rows retuned for query.");
        return results;
    }
}
