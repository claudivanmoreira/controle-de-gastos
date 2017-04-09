/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.rowmappers;

import br.edu.claudivan.controledegastos.dao.rowextractors.RowMapper;
import br.edu.claudivan.controledegastos.domain.Card;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Claudivan Moreira
 */
public class CardRowMapper implements RowMapper<Card>{

    @Override
    public Card mapRow(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getLong("id"));
        card.setName(resultSet.getString("card_name"));
        card.setFlagOfCard(resultSet.getString("flag_of_card"));
        card.setLimit(resultSet.getString("card_limit"));
        card.setInvoiceDueDate(resultSet.getString("invoice_due_date"));
        card.setInvoiceClosingDate(resultSet.getString("invoice_close_date"));
        return card;
    }
    
}
