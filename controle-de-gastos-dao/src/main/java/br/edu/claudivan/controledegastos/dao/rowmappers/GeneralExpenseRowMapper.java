/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.rowmappers;

import br.edu.claudivan.controledegastos.dao.rowextractors.RowMapper;
import br.edu.claudivan.controledegastos.domain.Category;
import br.edu.claudivan.controledegastos.domain.GeneralExpense;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Claudivan Moreira
 */
public class GeneralExpenseRowMapper implements RowMapper<GeneralExpense>{

    @Override
    public GeneralExpense mapRow(ResultSet resultSet) throws SQLException {
        GeneralExpense generalExpense = new GeneralExpense();
        generalExpense.setCategory(new Category());
        
        generalExpense.setDate(new java.util.Date(resultSet.getDate("expense_date").getTime()));
        generalExpense.setDescription(resultSet.getString("description"));
        generalExpense.setId(resultSet.getLong("id"));
        generalExpense.setIsFixedExpense(resultSet.getBoolean("fixed_expense"));
        generalExpense.setNumberOfPaymentInstallments(resultSet.getInt("number_of_payment_installments"));
        generalExpense.setSendEmailReminder(resultSet.getBoolean("send_email_reminder"));
        generalExpense.setValue(resultSet.getDouble("expense_value"));
        generalExpense.getCategory().setId(resultSet.getLong("category_id"));
        
        return generalExpense;
    }
    
}
