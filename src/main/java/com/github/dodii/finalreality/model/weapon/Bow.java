package com.github.dodii.finalreality.model.weapon;

/**
 * A class that represents a Bow.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Bow extends AbstractWeapon {

    /**
     * Creates a bow with a name, a base damage, speed and its type.
     *
     * @see WeaponType
     */
    public Bow(final String name, final int dmg, final int weight) {
        super(name, dmg, weight, WeaponType.BOW);
    }
}
