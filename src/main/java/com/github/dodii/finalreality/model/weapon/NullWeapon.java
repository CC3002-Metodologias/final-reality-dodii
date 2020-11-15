package com.github.dodii.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that represents a null weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class NullWeapon extends AbstractWeapon {

    private static String CUSTOM_PARAMETER = "N";

    public NullWeapon() {
        super("Null Weapon", 0, 0);
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
