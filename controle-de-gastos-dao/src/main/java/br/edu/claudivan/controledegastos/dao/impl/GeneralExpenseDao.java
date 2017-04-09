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
import br.edu.claudivan.controledegastos.dao.rowmappers.GeneralExpenseRowMapper;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.GeneralExpense;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 */
public class GeneralExpenseDao extends AbstractDao<GeneralExpense> {
    
    public GeneralExpenseDao(DatabaseUtils databaseUtils) {
        super(databaseUtils);
    }
    
    public Long save(GeneralExpense generalExpense) throws DatabaseAccessException {
        try {
            List<Object> returnedKeys = super.executeUpdateAndReturnGeneratedKeys(QueryNames.INSERT_GENERAL_EXPENSE, 
                    new Object[]{generalExpense.getValue(), generalExpense.getDate(), generalExpense.getDescription(), generalExpense.getIsFixedExpense(), 
                        generalExpense.getNumberOfPaymentInstallments(), generalExpense.getSendEmailReminder(), generalExpense.getCategory().getId()});
            return ((Integer) returnedKeys.get(0)).longValue();
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to persist Card Expense.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to persist Card Expense.", ex);
        }
    }
    
    public void update(GeneralExpense generalExpense) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.UPDATE_GENERAL_EXPENSE, new Object[]{generalExpense.getValue(), generalExpense.getDate(), generalExpense.getDescription(), generalExpense.getIsFixedExpense(), 
                        generalExpense.getNumberOfPaymentInstallments(), generalExpense.getSendEmailReminder(), generalExpense.getCategory().getId(), generalExpense.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update  Card.", ex);
        }
    }
    
    public void delete(GeneralExpense generalExpense) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.DELETE_GENERAL_EXPENSE, new Object[]{generalExpense.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update  Card.", ex);
        }
    }
    
    public GeneralExpense selectGeneralExpense(Object expenseId) throws DatabaseAccessException {
        try {
            return super.select(QueryNames.SELECT_GENERAL_EXPENSE_BY_ID, new GeneralExpenseRowMapper(), expenseId);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<GeneralExpense> selectAllGeneralExpenses() throws DatabaseAccessException {
        try {
            return super.selectAll(QueryNames.SELECT_ALL_GENERAL_EXPENSES, new GeneralExpenseRowMapper(), new Object[]{});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
    
    public GeneralExpense customSelectGeneralExpense(String queryName, Object... parameters) throws DatabaseAccessException {
        try {
            return super.select(queryName, new GeneralExpenseRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<GeneralExpense> customSelectAllGeneralExpense(String queryName, Object... parameters) throws DatabaseAccessException {
        try {
            return super.selectAll(queryName, new GeneralExpenseRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
}
