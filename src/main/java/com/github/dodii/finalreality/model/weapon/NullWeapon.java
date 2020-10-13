package com.github.dodii.finalreality.model.weapon;

/**
 * A class that represents a null weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class NullWeapon extends AbstractWeapon {

    public NullWeapon() {
        super("Null Weapon", 0, 0, WeaponType.NULL);
    }
}
