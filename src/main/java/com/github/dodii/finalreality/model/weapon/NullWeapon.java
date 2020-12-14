package com.github.dodii.finalreality.model.weapon;

import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.IMageCharacter;

import java.util.Objects;

/**
 * A class that represents a null weapon.
 * Implements singleton pattern.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class NullWeapon extends AbstractWeapon {

    private static final String CUSTOM_PARAMETER = "N";
    private static NullWeapon uniqueInstance;

    private NullWeapon() {
        super("Null Weapon", 0, 0);
    }

    /**
     * Singleton pattern design method.
     * @return the unique instance of a null weapon
     */
    public static NullWeapon uniqueInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new NullWeapon();
        }
        return uniqueInstance;
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
        knight.receiveWeapon(this);
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
        mage.receiveWeapon(this);
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
        if (!(o instanceof NullWeapon)) {
            return false;
        }
        final NullWeapon weapon = (NullWeapon) o;
        return getDmg() == weapon.getDmg() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }
}
