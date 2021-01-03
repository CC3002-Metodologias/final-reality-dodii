package com.github.dodii.finalreality.controller.turnphases;

import com.github.dodii.finalreality.model.character.ICharacter;

/**
 * Class that represents the start phase of a turn.
 * Implements state pattern.
 *
 * This phase represents the moment when the controller
 * takes the first character in the queue.
 *
 * @author Rodrigo Oportot
 */
public class StartPhase extends Phase {

    /**
     * Constructor.
     */
    public StartPhase() {
    }

    /**
     * @return the name of the phase.
     */
    @Override
    public String toString() {
        return "Start Phase";
    }

    /**
     * Changes to the selecting action phase.
     * Method called by the view.
     */
    @Override
    public void toSelectingActionPhase() {
        changePhase(new SelectingActionPhase());
    }

    /**
     * @return true if the phase represents the start of a turn.
     */
    @Override
    public boolean isStartPhase() {
        return true;
    }
}
