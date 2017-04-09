/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.rowmappers;

import br.edu.claudivan.controledegastos.dao.rowextractors.RowMapper;
import br.edu.claudivan.controledegastos.domain.Category;
import br.edu.claudivan.controledegastos.domain.IncomeExpense;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Claudivan Moreira
 */
public class IncomeExpenseRowMapper implements RowMapper<IncomeExpense>{

    @Override
    public IncomeExpense mapRow(ResultSet resultSet) throws SQLException {
        IncomeExpense incomeExpense = new IncomeExpense();
        incomeExpense.setCategory(new Category());
        
        incomeExpense.setDate(new java.util.Date(resultSet.getDate("expense_date").getTime()));
        incomeExpense.setDescription(resultSet.getString("description"));
        incomeExpense.setIsFixedIncome(resultSet.getBoolean("fixed_expense"));
        incomeExpense.setId(resultSet.getLong("id"));
        incomeExpense.setValue(resultSet.getDouble("expense_value"));
        incomeExpense.getCategory().setId(resultSet.getLong("category_id"));
        
        return incomeExpense;
    }
    
}
