package com.github.dodii.finalreality.model.weapon;

import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.IMageCharacter;

/**
 * An interface that represents a weapon.
 * Holds all the available methods for generic weapons.
 *
 * @author Rodrigo Oportot.
 */
public interface IWeapon {

    /**
     * @return the name of the weapon.
     */
    String getName();

    /**
     * @return the damage of the weapon.
     */
    int getDmg();

    /**
     * @return the weight of the weapon.
     */
    int getWeight();

    /**
     * Aux method for the double dispatch equip implementation.
     * @param engineer character to equip weapon
     */
    void equipToEngineer(EngineerCharacter engineer);

    /**
     * Aux method for the double dispatch equip implementation
     * @param knight character to equip weapon
     */
    void equipToKnight(KnightCharacter knight);

    /**
     * Aux method for the double dispatch equip implementation
     * @param thief character to equip weapon
     */
    void equipToThief(ThiefCharacter thief);

    /**
     * Aux method for the double dispatch equip implementation
     * @param mage character to equip weapon
     */
    void equipToMage(IMageCharacter mage);

    /**
     * @return the hashcode
     */
    int hashCode();

    /**
     * @param o the object (usually an IWeapon instance of a weapon).
     * @return true if both objects are equal.
     */
    boolean equals(final Object o);
}
