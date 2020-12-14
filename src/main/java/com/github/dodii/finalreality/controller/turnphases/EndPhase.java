package com.github.dodii.finalreality.controller.turnphases;

/**
 * Class that represents the end phase of a turn.
 * Implements state pattern.
 *
 * This phase represents the moment a character ends its turn.
 *
 * To be fully implemented on the next version.
 *
 * @author Rodrigo Oportot
 */
public class EndPhase extends Phase {


    /**
     * Goes to the next phase: Next phase
     */
    void nextPhase() {
        //this.changeState(new NextPhase());
    }

    /**
     * @return true if the phase represents the end of a turn
     */
    @Override
    public boolean isEndPhase() {
        return true;
    }
}
