package com.github.dodii.finalreality.model.weapon;

import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.IMageCharacter;

import java.util.Objects;

/**
 * A class that represents a Bow.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Bow extends AbstractWeapon {

    private static final String CUSTOM_PARAMETER = "B";

    /**
     * Creates a bow with a name, a base damage, speed and its type.
     */
    public Bow(final String name, final int dmg, final int weight) {
        super(name, dmg, weight);
    }

    /**
     * Aux method for the double dispatch equip implementation.
     * @param engineer character to equip weapon
     */
    @Override
    public void equipToEngineer(EngineerCharacter engineer) {
        engineer.receiveWeapon(this);
    }

    /**
     * Aux method for the double dispatch equip implementation
     * @param knight character to equip weapon
     */
    @Override
    public void equipToKnight(KnightCharacter knight) {
        //doesn't equip//
    }

    /**
     * Aux method for the double dispatch equip implementation
     * @param thief character to equip weapon
     */
    @Override
    public void equipToThief(ThiefCharacter thief) {
        thief.receiveWeapon(this);
    }

    /**
     * Aux method for the double dispatch equip implementation
     * @param mage character to equip weapon
     */
    @Override
    public void equipToMage(IMageCharacter mage) {
        //doesn't equip//
    }

    /**
     * Every weapon will have a different extra parameter to generate
     * its hashcode.
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDmg(), getWeight(),
                CUSTOM_PARAMETER);
    }

    /**
     * @param o the object (usually an IWeapon instance of a weapon).
     * @return true if both objects are equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow weapon = (Bow) o;
        return getDmg() == weapon.getDmg() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }
}
