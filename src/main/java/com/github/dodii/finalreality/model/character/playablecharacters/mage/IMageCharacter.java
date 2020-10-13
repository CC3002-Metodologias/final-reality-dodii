package com.github.dodii.finalreality.model.character.playablecharacters.mage;

import com.github.dodii.finalreality.model.character.playablecharacters.IPlayerCharacter;

/**
 * An interface that represents a mage type playable character of the game.
 *
 * @author Rodrigo Oportot.
 */
public interface IMageCharacter extends IPlayerCharacter {

    /**
     * Returns the mana of the character.
     */
    int getMana();

}
