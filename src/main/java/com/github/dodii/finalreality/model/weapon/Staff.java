package com.github.dodii.finalreality.model.weapon;

/**
 * A class that represents a staff.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Staff extends AbstractWeapon {

    private int magicDamage;
    /**
     * Creates a staff with a name, a base damage, weight and its type.
     *
     * @see WeaponType
     */
    public Staff(final String name, final int dmg, final int magicDamage, final int weight) {
        super(name, dmg, weight, WeaponType.STAFF);
        this.magicDamage = magicDamage;
    }

    /**
     * Returns the magic damage of the staff.
     */
    public int getMagicDamage() {
        return magicDamage;
    }
}
