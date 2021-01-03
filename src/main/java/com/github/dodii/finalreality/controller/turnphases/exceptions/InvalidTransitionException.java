package com.github.dodii.finalreality.controller.turnphases.exceptions;

/**
 * Specific exception that notifies about an invalid
 * transition attempt between phases.
 *
 * @author Rodrigo Oportot.
 */
public class InvalidTransitionException extends Exception {

    public InvalidTransitionException(String message) {
        super(message);
    }
}
