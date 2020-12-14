package com.github.dodii.finalreality.controller.turnphases;

/**
 * Class that represents a phase of a turn.
 * Implements state pattern.
 *
 * To be fully implemented on the next version.
 *
 * @author Rodrigo Oportot
 */
public class Phase {
    private Turn turn;

    /**
     * Links the turn of the game to this phase
     * @param turn
     */
    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    /**
     * Changes the actual phase of the turn.
     * @param phase the new phase.
     */
    protected void changePhase(Phase phase) {
        turn.setPhase(phase);
    }

    /**
     * Sets the next phase
     */
    void nextPhase() {

    }

    /**
     * @return true if the phase represents the start of a turn
     */
    public boolean isStartPhase() {
        return false;
    }

    /**
     * @return true if the phase represents the end of a turn
     */
    public boolean isEndPhase() {
        return false;
    }
}
