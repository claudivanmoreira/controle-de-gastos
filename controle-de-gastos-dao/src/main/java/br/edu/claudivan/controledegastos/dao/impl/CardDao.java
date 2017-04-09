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
import br.edu.claudivan.controledegastos.dao.rowmappers.CardRowMapper;
import br.edu.claudivan.controledegastos.dao.utils.DatabaseUtils;
import br.edu.claudivan.controledegastos.domain.Card;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Claudivan Moreira
 */
public class CardDao extends AbstractDao<Card> {

    public CardDao(DatabaseUtils databaseUtils) {
        super(databaseUtils);
    }

    public Card save(Card card) throws DatabaseAccessException {
        try {
            List<Object> returnedKeys = super.executeUpdateAndReturnGeneratedKeys(QueryNames.INSERT_CARD, new Object[]{card.getName(), card.getFlagOfCard(), card.getLimit(), card.getInvoiceDueDate(), card.getInvoiceClosingDate()});
            card.setId(((Integer) returnedKeys.get(0)).longValue());
            return card;
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to persist Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to persist  Card.", ex);
        }
    }

    public void update(Card card) throws DatabaseAccessException {
        try {
            executeUpdate(QueryNames.UPDATE_CARD, new Object[]{card.getName(), card.getFlagOfCard(), card.getLimit(), card.getInvoiceDueDate(), card.getInvoiceClosingDate(), card.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to update Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to update  Card.", ex);
        }
    }

    public void delete(Card card) throws DatabaseAccessException {
        try {
            super.executeUpdate(QueryNames.DELETE_CARD, new Object[]{card.getId()});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to delete Card.", ex);
        } catch (Exception ex) {
            throw new DatabaseAccessException("Generic error while trying to delete  Card.", ex);
        }
    }

    public Card selectCard(Object cardId) throws DatabaseAccessException {
        try {
            return super.select(QueryNames.SELECT_CARD_BY_ID, new CardRowMapper(), cardId);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<Card> selectAllCards() throws DatabaseAccessException {
        try {
            return super.selectAll(QueryNames.SELECT_ALL_CARDS, new CardRowMapper(), new Object[]{});
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public Card customSelectCard(String sqlKey, Object... parameters) throws DatabaseAccessException {
        try {
            return super.select(sqlKey, new CardRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }

    public List<Card> customSelectAllCards(String sqlKey, Object... parameters) throws DatabaseAccessException {
        try {
            return super.selectAll(sqlKey, new CardRowMapper(), parameters);
        } catch (SQLException ex) {
            throw new DatabaseAccessException("Error while trying to find Card.", ex);
        } catch (EmptyResultException ex) {
            throw ex;
        }
    }
}
