package com.github.dodii.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that represents a staff.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Staff extends AbstractWeapon {

    private static String CUSTOM_PARAMETER = "St";
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
