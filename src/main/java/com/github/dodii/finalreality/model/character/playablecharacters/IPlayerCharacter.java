package com.github.dodii.finalreality.model.character.playablecharacters;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.weapon.*;

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
     * Checks by Double Dispatch if the character can actually
     * equip a certain weapon, depending on its class.
     */
    void equip(IWeapon weapon);

    /**
     * Receives a weapon and actually equips it. It gets called from
     * the weapon to equip.
     * @param weapon weapon to be equipped.
     */
    void receiveWeapon(IWeapon weapon);
    
}
