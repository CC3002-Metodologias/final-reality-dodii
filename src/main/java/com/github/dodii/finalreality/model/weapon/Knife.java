package com.github.dodii.finalreality.model.weapon;

/**
 * A class that represents a Knife.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Knife extends AbstractWeapon {

    /**
     * Creates a knife with a name, a base damage, speed and its type.
     *
     * @see WeaponType
     */
    public Knife(final String name, final int dmg, final int weight) {
        super(name, dmg, weight, WeaponType.KNIFE);
    }
}
