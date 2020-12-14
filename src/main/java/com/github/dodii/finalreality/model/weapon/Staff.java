package com.github.dodii.finalreality.model.weapon;

import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.IMageCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A class that represents a staff.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Staff extends AbstractWeapon {

    private static final String CUSTOM_PARAMETER = "St";
    private final int magicDamage;

    /**
     * Creates a staff with a name, a base damage, a base magic damage,
     * weight and its type.
     *
     */
    public Staff(final String name, final int dmg, final int magicDamage, final int weight) {
        super(name, dmg, weight);
        this.magicDamage = magicDamage;
    }

    /**
     * Returns the magic damage of the staff.
     */
    public int getMagicDamage() {
        return magicDamage;
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
    public void equipToKnight(KnightCharacter knight) {
        //doesn't equip//
    }

    /**
     * Aux method for the double dispatch equip implementation
     * @param thief character to equip weapon
     */
    @Override
    public void equipToThief(ThiefCharacter thief) {
        //doesn't equip//
    }

    /**
     * Aux method for the double dispatch equip implementation
     * @param mage character to equip weapon
     */
    @Override
    public void equipToMage(@NotNull IMageCharacter mage) {
        mage.receiveWeapon(this);
    }

    /**
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDmg(), getMagicDamage(), getWeight(),
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
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff weapon = (Staff) o;
        return getDmg() == weapon.getDmg() &&
                getMagicDamage() == weapon.getMagicDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }
}
