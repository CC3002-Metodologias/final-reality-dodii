package com.github.dodii.finalreality.controller.turnphases;

/**
 * Class that represents the a turn of the game.
 * Implements state pattern.
 *
 * To be fully implemented on the next version.
 *
 * @author Rodrigo Oportot
 */
public class Turn {
    private Phase phase;

    /**
     * Constructor turn
     */
    public Turn() {
        this.setPhase(new StartPhase());
    }

    /**
     * Sets the curent phase of the turn
     * @param aPhase the current phase of the turn
     */
    void setPhase(Phase aPhase) {
        this.phase = aPhase;
        this.phase.setTurn(this);
    }

    /**
     * @return true if the phase represents the start of a turn
     */
    public boolean isStartPhase() {
        return phase.isStartPhase();
    }

    /**
     * @return true if the phase represents the end of a turn
     */
    public boolean isEndPhase() {
        return phase.isEndPhase();
    }
}
