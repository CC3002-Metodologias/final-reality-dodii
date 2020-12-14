package com.github.dodii.finalreality.controller.turnphases;

/**
 * Class that represents the start phase of a turn.
 * Implements state pattern.
 *
 * This phase represents the moment when the controller
 * takes the fist character in the queue.
 *
 * To be fully implemented on the next version.
 *
 * @author Rodrigo Oportot
 */
public class StartPhase extends Phase {

    /**
     * Goes to the next phase: Next phase
     */
    void nextPhase() {
        //this.changeState(new NextPhase());
    }

    /**
     * @return true if the phase represents the start of a turn
     */
    @Override
    public boolean isStartPhase() {
        return true;
    }
    
}
