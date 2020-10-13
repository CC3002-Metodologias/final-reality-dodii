package com.github.dodii.finalreality.model.weapon;

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
     * @return the type of the weapon.
     */
    WeaponType getType();

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
