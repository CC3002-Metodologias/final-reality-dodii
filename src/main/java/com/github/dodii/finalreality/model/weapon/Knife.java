package com.github.dodii.finalreality.model.weapon;

import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.IMageCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A class that represents a Knife.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Knife extends AbstractWeapon {

    private static final String CUSTOM_PARAMETER = "K";

    /**
     * Creates a knife with a name, a base damage, speed and its type.
     */
    public Knife(final String name, final int dmg, final int weight) {
        super(name, dmg, weight);
    }

    /**
     * Aux method for the double dispatch equip implementation.
     * @param engineer character to equip weapon
     */
    @Override
    public void equipToEngineer(EngineerCharacter engineer) {
        //doesn't equip//
    }

    /**
     * Aux method for the double dispatch equip implementation
     * @param knight character to equip weapon
     */
    @Override
    public void equipToKnight(@NotNull KnightCharacter knight) {
        knight.receiveWeapon(this);
    }

    /**
     * Aux method for the double dispatch equip implementation
     * @param thief character to equip weapon
     */
    @Override
    public void equipToThief(@NotNull ThiefCharacter thief) {
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
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife weapon = (Knife) o;
        return getDmg() == weapon.getDmg() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }
}
