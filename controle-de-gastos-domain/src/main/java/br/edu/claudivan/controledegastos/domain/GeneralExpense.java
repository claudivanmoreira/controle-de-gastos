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
public class GeneralExpense extends Expense implements Serializable {

    private Boolean sendEmailReminder;
    private Boolean isFixedExpense;
    private Integer numberOfPaymentInstallments;

    public Boolean getSendEmailReminder() {
        return sendEmailReminder;
    }

    public void setSendEmailReminder(Boolean sendEmailReminder) {
        this.sendEmailReminder = sendEmailReminder;
    }

    public Integer getNumberOfPaymentInstallments() {
        return numberOfPaymentInstallments;
    }

    public void setNumberOfPaymentInstallments(Integer numberOfPaymentInstallments) {
        this.numberOfPaymentInstallments = numberOfPaymentInstallments;
    }

    public Boolean getIsFixedExpense() {
        return isFixedExpense;
    }

    public void setIsFixedExpense(Boolean isFixedExpense) {
        this.isFixedExpense = isFixedExpense;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

}
