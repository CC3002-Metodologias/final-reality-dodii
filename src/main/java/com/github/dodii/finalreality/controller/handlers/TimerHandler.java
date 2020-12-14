package com.github.dodii.finalreality.controller.handlers;

import com.github.dodii.finalreality.controller.Controller;
import com.github.dodii.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;

/**
 * Class that handles the start of a character's turn.
 *
 * @author Rodrigo Oportot
 */
public class TimerHandler implements IHandler {

    private final Controller controller;

    /**
     * Handler constructor
     * @param controller the controller associated to the game
     */
    public TimerHandler(final Controller controller) {
        this.controller = controller;
    }

    /**
     * This method gets called when the bounded property is changed.
     * Notifies when the turn of a character starts.
     * @param evt when a turn starts
     */
    @Override
    public void propertyChange(final @NotNull PropertyChangeEvent evt) {
        controller.onTimerEnded((ICharacter) evt.getNewValue());
    }
}
