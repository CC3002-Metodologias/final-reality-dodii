package com.github.dodii.finalreality.model.weapon;

import java.util.Objects;

/**
 * An abstract class  representing a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractWeapon implements IWeapon {

    private final String name;
    private final int dmg;
    private final int weight;
    private final WeaponType type;

    protected AbstractWeapon(final String name, final int dmg, final int weight,
                             final WeaponType type) {
        this.name = name;
        this.dmg = dmg;
        this.weight = weight;
        this.type = type;
    }

    /**
     * @return the name of the weapon.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the damage of the weapon.
     */
    @Override
    public int getDmg() {
        return dmg;
    }

    /**
     * @return the weight of the weapon.
     */
    @Override
    public int getWeight() {
        return weight;
    }

    /**
     * @return the type of the weapon.
     */
    @Override
    public WeaponType getType() {
        return type;
    }

    /**
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDmg(), getWeight(), getType());
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
        if (!(o instanceof IWeapon)) {
            return false;
        }
        final IWeapon weapon = (IWeapon) o;
        return getDmg() == weapon.getDmg() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName()) &&
                getType() == weapon.getType();
    }
}
