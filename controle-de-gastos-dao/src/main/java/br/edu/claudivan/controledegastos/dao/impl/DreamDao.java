/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.impl;

import br.edu.claudivan.controledegastos.dao.AbstractDao;
import br.edu.claudivan.controledegastos.dao.constants.QueryNames;
import br.edu.claudivan.controledegastos.dao.exceptions.DatabaseAccessException;
import br.edu.claudivan.controledegastos.dao.exceptions.EmptyResultException;
import br.edu.claudivan.controledegastos.dao.rowmappers.DreamRowMapper;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.Dream;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 */
public class DreamDao extends AbstractDao<Dream> {

    public DreamDao(DatabaseUtils databaseUtils) {
        super(databaseUtils);
    }

    public Long save(Dream dream) throws DatabaseAccessException {
        try {
            List<Object> returnedKeys = super.executeUpdateAndReturnGeneratedKeys(QueryNames.INSERT_DREAM, new Object[]{dream.getName(), dream.getAmountValue(), dream.getValueSaved()});
            return ((Integer) returnedKeys.get(0)).longValue();
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to persist Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to persist  Card.", ex);
        }
    }

    public void update(Dream dream) throws DatabaseAccessException {
        try {
            executeUpdate(QueryNames.UPDATE_DREAM, new Object[]{dream.getName(), dream.getAmountValue(), dream.getValueSaved(), dream.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update  Card.", ex);
        }
    }

    public void delete(Dream dream) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.DELETE_DREAM, new Object[]{dream.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to delete Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to delete  Card.", ex);
        }
    }

    public Dream selectDream(Object dreamId) throws DatabaseAccessException {
        try {
            return super.select(QueryNames.SELECT_DREAM_BY_ID, new DreamRowMapper(), dreamId);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<Dream> selectAllDreams() throws DatabaseAccessException {
        try {
            return super.selectAll(QueryNames.SELECT_ALL_DREAMS, new DreamRowMapper(), new Object[]{});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public Dream customSelectDream(String queryName, Object... parameters) throws DatabaseAccessException {
        try {
            return super.select(queryName, new DreamRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<Dream> customSelectAllDreams(String sqlKey, Object... parameters) throws DatabaseAccessException {
        try {
            return super.selectAll(sqlKey, new DreamRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
}
