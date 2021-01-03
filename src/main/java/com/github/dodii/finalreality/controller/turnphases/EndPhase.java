package com.github.dodii.finalreality.controller.turnphases;

import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidActionException;

/**
 * Class that represents the end phase of a turn.
 * Implements state pattern.
 *
 * This phase represents the moment a character ends its turn.
 *
 * @author Rodrigo Oportot
 */
public class EndPhase extends Phase {

    public EndPhase() {
        this.canAttack = false;
    }

    /**
     * Ends the turn. Checks the status of the queue, then
     * proceeds in an according way.
     * @throws InvalidActionException thrown in case it's not
     * possible to end the turn in the current phase.
     */
    public void endTurn() throws InvalidActionException {
        //queue empty, the game will wait
        if(controller.getTurnsQueue().isEmpty()) {
            toWaitingQueuePhase();
        }
        //queue not empty, the game will continue and the
        //next character will start its turn.
        else {
            toStartPhase();
        }
    }

    /**
     * Changes to the waiting queue phase.
     * Called in case the queue's empty.
     */
    public void toWaitingQueuePhase() {
        changePhase(new WaitingQueuePhase());
    }

    /**
     * Changes to the start phase. Called in case the queue's not empty.
     */
    public void toStartPhase() { changePhase(new StartPhase());
    }

    /**
     * @return the name of the phase.
     */
    @Override
    public String toString() {
        return "End Phase";
    }

    /**
     * @return true if the phase represents the end of a turn
     */
    @Override
    public boolean isEndPhase() {
        return true;
    }
}
