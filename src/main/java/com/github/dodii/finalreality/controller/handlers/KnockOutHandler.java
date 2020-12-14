package com.github.dodii.finalreality.controller.handlers;

import com.github.dodii.finalreality.controller.Controller;
import com.github.dodii.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;

/**
 * Handler that notifies when a character gets K.O'd
 * Part of the observer pattern design
 *
 * @author Rodrigo Oportot
 */
public class KnockOutHandler implements IHandler {

    private final Controller controller;

    /**
     * Handler constructor
     * @param controller the controller of the game
     */
    public KnockOutHandler(Controller controller) {
        this.controller = controller;
    }

    /**
     * Notifies the controller when a character gets K.O'd
     * @param evt the event itself
     */
    @Override
    public void propertyChange(@NotNull PropertyChangeEvent evt) {
        controller.onKnockedOutCharacter((ICharacter) evt.getNewValue());
    }
}
