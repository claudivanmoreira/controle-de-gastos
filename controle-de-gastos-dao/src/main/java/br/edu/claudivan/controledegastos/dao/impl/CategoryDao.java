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
import br.edu.claudivan.controledegastos.dao.rowmappers.CategoryRowMapper;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.Category;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 */
public class CategoryDao extends AbstractDao<Category> {

    public CategoryDao(DatabaseUtils databaseUtils) {
        super(databaseUtils);
    }

    public Long save(Category category) throws DatabaseAccessException {
        try {
            List<Object> returnedKeys = super.executeUpdateAndReturnGeneratedKeys(QueryNames.INSERT_CATEGORY, new Object[]{category.getName(), category.getType().toString()});
            return ((Integer) returnedKeys.get(0)).longValue();
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to persist Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to persist  Card.", ex);
        }
    }

    public void update(Category category) throws DatabaseAccessException {
        try {
            executeUpdate(QueryNames.UPDATE_CATEGORY, new Object[]{category.getName(), category.getType().toString(), category.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update  Card.", ex);
        }
    }

    public void delete(Category category) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.DELETE_CATEGORY, new Object[]{category.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to delete Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to delete  Card.", ex);
        }
    }

    public Category selectCategory(Object categoryId) throws DatabaseAccessException {
        try {
            return super.select(QueryNames.SELECT_CATEGORY_BY_ID, new CategoryRowMapper(), categoryId);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<Category> selectAllCategories() throws DatabaseAccessException {
        try {
            return super.selectAll(QueryNames.SELECT_ALL_CATEGORIES, new CategoryRowMapper(), new Object[]{});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public Category customSelectCategory(String sqlKey, Object... parameters) throws DatabaseAccessException {
        try {
            return super.select(sqlKey, new CategoryRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<Category> customSelectAllCategories(String sqlKey, Object... parameters) throws DatabaseAccessException {
        try {
            return super.selectAll(sqlKey, new CategoryRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

}
