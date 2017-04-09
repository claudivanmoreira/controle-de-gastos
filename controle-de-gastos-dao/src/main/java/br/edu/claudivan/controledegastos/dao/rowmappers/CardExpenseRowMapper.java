/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.rowmappers;

import br.edu.claudivan.controledegastos.dao.rowextractors.RowMapper;
import br.edu.claudivan.controledegastos.domain.Card;
import br.edu.claudivan.controledegastos.domain.CardExpense;
import br.edu.claudivan.controledegastos.domain.Category;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Claudivan Moreira
 */
public class CardExpenseRowMapper implements RowMapper<CardExpense>{

    @Override
    public CardExpense mapRow(ResultSet resultSet) throws SQLException {
        CardExpense cardExpense = new CardExpense();
        cardExpense.setCard(new Card());
        cardExpense.setCategory(new Category());
        
        cardExpense.setDate(new java.util.Date(resultSet.getDate("expense_date").getTime()));
        cardExpense.setDescription(resultSet.getString("description"));
        cardExpense.setId(resultSet.getLong("id"));
        cardExpense.setIsFixedExpense(resultSet.getBoolean("fixed_expense"));
        cardExpense.setNumberOfPaymentInstallments(resultSet.getInt("number_of_payment_installments"));
        cardExpense.setSendEmailReminder(resultSet.getBoolean("send_email_reminder"));
        cardExpense.setValue(resultSet.getDouble("expense_value"));
        cardExpense.getCard().setId(resultSet.getLong("card_id"));
        cardExpense.getCategory().setId(resultSet.getLong("category_id"));
        
        return cardExpense;
    }
    
}
