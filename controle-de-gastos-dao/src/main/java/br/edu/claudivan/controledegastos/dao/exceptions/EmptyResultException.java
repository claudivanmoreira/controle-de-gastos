/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.claudivan.controledegastos.dao.exceptions;

/**
 *
 * @author Claudivan Moreira
 */
public class EmptyResultException extends RuntimeException {

    public EmptyResultException(String reason) {
        super(reason);
    }

    public EmptyResultException() {
    }

    public EmptyResultException(Throwable cause) {
        super(cause);
    }

    public EmptyResultException(String reason, Throwable cause) {
        super(reason, cause);
    }

}
