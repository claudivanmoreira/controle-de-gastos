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
import br.edu.claudivan.controledegastos.dao.rowmappers.IncomeExpenseRowMapper;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.IncomeExpense;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 */
public class IncomeExpenseDao extends AbstractDao<IncomeExpense> {
    
   public IncomeExpenseDao(DatabaseUtils databaseUtils) {
        super(databaseUtils);
    }
    
    public Long save(IncomeExpense incomeExpense) throws DatabaseAccessException {
        try {
            List<Object> returnedKeys = super.executeUpdateAndReturnGeneratedKeys(QueryNames.INSERT_INCOME_EXPENSE, 
                    new Object[]{incomeExpense.getValue(), incomeExpense.getDate(), incomeExpense.getDescription(), incomeExpense.getIsFixedIncome(), 
                        incomeExpense.getCategory().getId()});
            return ((Integer) returnedKeys.get(0)).longValue();
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to persist Income Expense.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to persist Income Expense.", ex);
        }
    }
    
    public void update(IncomeExpense incomeExpense) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.UPDATE_INCOME_EXPENSE, new Object[]{incomeExpense.getValue(), incomeExpense.getDate(), incomeExpense.getDescription(), 
                        incomeExpense.getIsFixedIncome(), incomeExpense.getCategory().getId(), incomeExpense.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Income Expense.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update Income Expense.", ex);
        }
    }
    
    public void delete(IncomeExpense incomeExpense) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.DELETE_INCOME_EXPENSE, new Object[]{incomeExpense.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Income Expense.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update Income Expense.", ex);
        }
    }
    
    public IncomeExpense selectIncomeExpense(Object expenseId) throws DatabaseAccessException {
        try {
            return super.select(QueryNames.SELECT_INCOME_EXPENSE_BY_ID, new IncomeExpenseRowMapper(), expenseId);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<IncomeExpense> selectAllIncomeExpenses() throws DatabaseAccessException {
        try {
            return super.selectAll(QueryNames.SELECT_ALL_INCOME_EXPENSES, new IncomeExpenseRowMapper(), new Object[]{});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
    
    public IncomeExpense customSelectIncomeExpense(String queryName, Object... parameters) throws DatabaseAccessException {
        try {
            return super.select(queryName, new IncomeExpenseRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<IncomeExpense> customSelectAllIncomeExpense(String sqlKey, Object... parameters) throws DatabaseAccessException {
        try {
            return super.selectAll(sqlKey, new IncomeExpenseRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
}
