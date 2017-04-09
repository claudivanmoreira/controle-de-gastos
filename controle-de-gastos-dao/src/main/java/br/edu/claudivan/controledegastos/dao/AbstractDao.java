/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao;

import br.edu.claudivan.controledegastos.dao.exceptions.EmptyResultException;
import br.edu.claudivan.controledegastos.dao.rowextractors.RowMapper;
import br.edu.claudivan.controledegastos.dao.rowextractors.RowMapperResultSetExtractor;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 * @param <T>
 */
public abstract class AbstractDao<T> {

    private final DatabaseUtils databaseUtils;

    public AbstractDao(DatabaseUtils databaseUtils) {
        this.databaseUtils = databaseUtils;
    }

    protected List<Object> executeUpdateAndReturnGeneratedKeys(String queryKey, Object... parameters) throws SQLException {
        Connection conn = this.databaseUtils.getConnection();
        PreparedStatement statement = createPreparedStatement(conn, queryKey, parameters);
        statement.executeUpdate();
        ResultSet queryResult = statement.getGeneratedKeys();
        List<Object> result = new ArrayList<>();
        while (queryResult.next()) {
            result.add(queryResult.getObject(1));
        }
        return result;
    }

    protected int executeUpdate(String queryKey, Object... parameters) throws SQLException {
        Connection conn = this.databaseUtils.getConnection();
        PreparedStatement statement = createPreparedStatement(conn, queryKey, parameters);
        return statement.executeUpdate();
    }

    protected T select(String queryKey, RowMapper<T> rowMapper, Object... parameters) throws SQLException, EmptyResultException {
        Connection conn = this.databaseUtils.getConnection();
        PreparedStatement statement = createPreparedStatement(conn, queryKey, parameters);
        T singleResult = getQuerySingleResult(rowMapper, statement.executeQuery());
        databaseUtils.closeConnection(conn);
        return singleResult;
    }

    protected List<T> selectAll(String sqlKey, RowMapper<T> rowMapper, Object... parameters) throws SQLException, EmptyResultException {
        Connection conn = this.databaseUtils.getConnection();
        PreparedStatement statement = createPreparedStatement(conn, sqlKey, parameters);
        List<T> listResult = getQueryResultList(rowMapper, statement.executeQuery());
        databaseUtils.closeConnection(conn);
        return listResult;
    }

    private PreparedStatement createPreparedStatement(Connection conn, String queryKey, Object... parameters) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(databaseUtils.getSqlQuery(queryKey), Statement.RETURN_GENERATED_KEYS);
        for (int i = 1; i < parameters.length + 1; i++) {
            statement.setObject(i, parameters[i - 1]);
        }
        return statement;
    }

    private List<T> getQueryResultList(RowMapper<T> rowMapper, ResultSet resultSet) throws SQLException {
        RowMapperResultSetExtractor<T> resultSetExtractor = new RowMapperResultSetExtractor(rowMapper, resultSet);
        return resultSetExtractor.extractList();
    }

    private T getQuerySingleResult(RowMapper<T> rowMapper, ResultSet resultSet) throws SQLException {
        RowMapperResultSetExtractor<T> resultSetExtractor = new RowMapperResultSetExtractor(rowMapper, resultSet);
        return resultSetExtractor.extractSingle();
    }
}
