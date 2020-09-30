package com.github.dodii.finalreality.model.character.playablecharacters;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.weapon.IWeapon;

/**
 * An interface that represents a playable character of the game.
 * Holds the available methods for such types of characters.
 *
 * @author Rodrigo Oportot.
 */

public interface IPlayerCharacter extends ICharacter {

    /**
     * Returns this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();

    /**
     * Equips a weapon to the playable character.
     */
    void equip(IWeapon weapon);
}
