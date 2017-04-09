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
public class Card extends AbstractEntity implements Serializable {

    private String name;
    private String flagOfCard;
    private String limit;
    private String InvoiceDueDate;
    private String InvoiceClosingDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagOfCard() {
        return flagOfCard;
    }

    public void setFlagOfCard(String flagOfCard) {
        this.flagOfCard = flagOfCard;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getInvoiceDueDate() {
        return InvoiceDueDate;
    }

    public void setInvoiceDueDate(String InvoiceDueDate) {
        this.InvoiceDueDate = InvoiceDueDate;
    }

    public String getInvoiceClosingDate() {
        return InvoiceClosingDate;
    }

    public void setInvoiceClosingDate(String InvoiceClosingDate) {
        this.InvoiceClosingDate = InvoiceClosingDate;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
