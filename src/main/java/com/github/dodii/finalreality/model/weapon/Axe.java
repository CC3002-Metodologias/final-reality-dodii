package com.github.dodii.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that represents an Axe.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Axe extends AbstractWeapon {

    private static String CUSTOM_PARAMETER = "A";

    /**
     * Creates an axe with a name, a base damage, weight and its type.
     */
    public Axe(final String name, final int dmg, final int weight) {
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
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe weapon = (Axe) o;
        return getDmg() == weapon.getDmg() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }
}

