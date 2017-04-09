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
import br.edu.claudivan.controledegastos.dao.rowmappers.CardExpenseRowMapper;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.CardExpense;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 */
public class CardExpenseDao extends AbstractDao<CardExpense> {
    
    public CardExpenseDao(DatabaseUtils databaseUtils) {
        super(databaseUtils);
    }
    
    public Long save(CardExpense cardExpense) throws DatabaseAccessException {
        try {
            List<Object> returnedKeys = super.executeUpdateAndReturnGeneratedKeys(QueryNames.INSERT_CARD_EXPENSE, 
                    new Object[]{cardExpense.getValue(), cardExpense.getDate(), cardExpense.getDescription(), cardExpense.getIsFixedExpense(), 
                        cardExpense.getNumberOfPaymentInstallments(), cardExpense.getSendEmailReminder(), cardExpense.getCard().getId(), cardExpense.getCategory().getId()});
            return ((Integer) returnedKeys.get(0)).longValue();
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to persist Card Expense.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to persist Card Expense.", ex);
        }
    }
    
    public void update(CardExpense cardExpense) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.UPDATE_CARD_EXPENSE, new Object[]{cardExpense.getValue(), cardExpense.getDate(), cardExpense.getDescription(), cardExpense.getIsFixedExpense(), 
                        cardExpense.getNumberOfPaymentInstallments(), cardExpense.getSendEmailReminder(), cardExpense.getCard().getId(), cardExpense.getCategory().getId(), cardExpense.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update  Card.", ex);
        }
    }
    
    public void delete(CardExpense cardExpense) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.DELETE_CARD_EXPENSE, new Object[]{cardExpense.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update  Card.", ex);
        }
    }
    
    public CardExpense selectCardExpense(Object expenseId) throws DatabaseAccessException {
        try {
            return super.select(QueryNames.SELECT_CARD_EXPENSE_BY_ID, new CardExpenseRowMapper(), expenseId);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card Expense.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<CardExpense> selectAllCardExpenses() throws DatabaseAccessException {
        try {
            return super.selectAll(QueryNames.SELECT_ALL_CARD_EXPENSES, new CardExpenseRowMapper(), new Object[]{});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card Expense.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
    
    public CardExpense customSelectCardExpense(String queryName, Object... parameters) throws DatabaseAccessException {
        try {
            return super.select(queryName, new CardExpenseRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card Expense.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<CardExpense> customSelectAllCardExpenses(String queryName, Object... parameters) throws DatabaseAccessException {
        try {
            return super.selectAll(queryName, new CardExpenseRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card Expense.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
}
