package com.github.dodii.finalreality.controller.handlers;

import com.github.dodii.finalreality.controller.Controller;
import com.github.dodii.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;

/**
 * Handler that notifies when a character ends its turn
 *
 * @author Rodrigo Oportot
 */
public class EndTurnHandler implements IHandler {

    private final Controller controller;

    /**
     * The handler constructor
     * @param controller the controller associated to the game
     */
    public EndTurnHandler(final Controller controller) {
        this.controller = controller;
    }

    /**
     * This method gets called when the bounded property is changed.
     * Notifies when the turn of a character ends.
     * @param evt when a turn ends
     */
    @Override
    public void propertyChange(final @NotNull PropertyChangeEvent evt) {
        controller.onTurnEnded((ICharacter) evt.getNewValue());
    }
}
