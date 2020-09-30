package com.github.dodii.finalreality.model.weapon;

/**
 * A class that represents an Axe.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Axe extends AbstractWeapon {

    /**
     * Creates an axe with a name, a base damage, weight and its type.
     *
     * @see WeaponType
     */
    public Axe(final String name, final int dmg, final int weight) {
        super(name, dmg, weight, WeaponType.AXE);
    }
}

