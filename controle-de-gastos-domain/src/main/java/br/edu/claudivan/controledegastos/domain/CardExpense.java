/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Claudivan Moreira
 */
public class CardExpense extends Expense implements Serializable {

    private Card card;
    private Boolean sendEmailReminder;
    private Boolean isFixedExpense;
    private Integer numberOfPaymentInstallments;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Boolean getSendEmailReminder() {
        return sendEmailReminder;
    }

    public void setSendEmailReminder(Boolean sendEmailReminder) {
        this.sendEmailReminder = sendEmailReminder;
    }

    public Boolean getIsFixedExpense() {
        return isFixedExpense;
    }

    public void setIsFixedExpense(Boolean isFixedExpense) {
        this.isFixedExpense = isFixedExpense;
    }

    public Integer getNumberOfPaymentInstallments() {
        return numberOfPaymentInstallments;
    }

    public void setNumberOfPaymentInstallments(Integer numberOfPaymentInstallments) {
        this.numberOfPaymentInstallments = numberOfPaymentInstallments;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
