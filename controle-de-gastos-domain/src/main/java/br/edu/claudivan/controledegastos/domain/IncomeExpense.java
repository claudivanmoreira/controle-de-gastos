/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.domain;

import java.io.Serializable;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 *
 * @author Claudivan Moreira
 */
public class IncomeExpense extends Expense implements Serializable {

    private Boolean isFixedIncome;

    public Boolean getIsFixedIncome() {
        return isFixedIncome;
    }

    public void setIsFixedIncome(Boolean isFixedIncome) {
        this.isFixedIncome = isFixedIncome;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
