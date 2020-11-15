package com.github.dodii.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that represents a Bow.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Bow extends AbstractWeapon {

    private static String CUSTOM_PARAMETER = "B";

    /**
     * Creates a bow with a name, a base damage, speed and its type.
     */
    public Bow(final String name, final int dmg, final int weight) {
        super(name, dmg, weight);
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
