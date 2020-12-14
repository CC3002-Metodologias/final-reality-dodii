package com.github.dodii.finalreality.model.weapon;

import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.IMageCharacter;

import java.util.Objects;

/**
 * An abstract class  representing a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractWeapon implements IWeapon {

    private final String name;
    private final int dmg;
    private final int weight;

    protected AbstractWeapon(final String name, final int dmg, final int weight) {
        this.name = name;
        this.dmg = dmg;
        this.weight = weight;
    }

    /**
     * @return the name of the weapon.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the damage of the weapon.
     */
    @Override
    public int getDmg() {
        return dmg;
    }

    /**
     * @return the weight of the weapon.
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * Aux method for the double dispatch equip implementation.
     * @param engineer character to equip weapon
     */
    @Override
    public abstract void equipToEngineer(EngineerCharacter engineer);

    /**
     * Aux method for the double dispatch equip implementation
     * @param knight character to equip weapon
     */
    @Override
    public abstract void equipToKnight(KnightCharacter knight);

    /**
     * Aux method for the double dispatch equip implementation
     * @param thief character to equip weapon
     */
    @Override
    public abstract void equipToThief(ThiefCharacter thief);

    /**
     * Aux method for the double dispatch equip implementation
     * @param mage character to equip weapon
     */
    @Override
    public abstract void equipToMage(IMageCharacter mage);

    /**
     * Every weapon will have a different extra parameter to generate
     * its hashcode.
     * @return the hashcode
     */
    @Override
    public abstract int hashCode();

    /**
     * @param o the object (usually an IWeapon instance of a weapon).
     * @return true if both objects are equal.
     */
    @Override
    public abstract boolean equals(final Object o);
}
