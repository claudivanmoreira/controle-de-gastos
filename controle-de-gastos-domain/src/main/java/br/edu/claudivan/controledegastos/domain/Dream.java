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
public class Dream extends AbstractEntity implements Serializable {

    private String name;
    private String amountValue;
    private String valueSaved;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountValue() {
        return amountValue;
    }

    public void setAmountValue(String amountValue) {
        this.amountValue = amountValue;
    }

    public String getValueSaved() {
        return valueSaved;
    }

    public void setValueSaved(String valueSaved) {
        this.valueSaved = valueSaved;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

}
