package com.github.dodii.finalreality.controller.turnphases;

/**
 * Class that represents the waiting for queue phase
 * of a turn.
 * The game waits (the controller gets notified by the model)
 * until the queue's not empty, in case it was before.
 *
 * Implements state pattern.
 *
 * @author Rodrigo Oportot
 */
public class WaitingQueuePhase extends Phase {

    /**
     * Constructor for the phase.
     */
    public WaitingQueuePhase() {
        this.canAttack = false;
    }

    /**
     * Changes to the start phase.
     * Called when the queue's not empty anymore.
     */
    public void toStartPhase() { changePhase(new StartPhase()); }

    /**
     * @return the name of the phase.
     */
    @Override
    public String toString() {
        return "Waiting Queue Phase";
    }

    /**
     * @return true if the phase represents the waiting
     * queue phase of a turn.
     */
    public boolean isWaitingQueuePhase() {
        return true;
    }
}
