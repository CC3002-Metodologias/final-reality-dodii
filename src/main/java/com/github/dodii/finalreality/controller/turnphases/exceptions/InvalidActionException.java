package com.github.dodii.finalreality.controller.turnphases.exceptions;

/**
 * Specific exception that notifies about an invalid
 * action attempt in a phase.
 */
public class InvalidActionException extends Exception {

    public InvalidActionException(String message) {
        super(message);
    }
}
