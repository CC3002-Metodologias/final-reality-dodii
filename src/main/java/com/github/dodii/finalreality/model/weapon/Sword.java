package com.github.dodii.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that represents a sword.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Sword extends AbstractWeapon {

  private static String CUSTOM_PARAMETER = "Sw";

  /**
   * Creates a sword with a name, a base damage, speed and its type.
   */
  public Sword(final String name, final int dmg, final int weight) {
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
    if (!(o instanceof Sword)) {
      return false;
    }
    final Sword weapon = (Sword) o;
    return getDmg() == weapon.getDmg() &&
            getWeight() == weapon.getWeight() &&
            getName().equals(weapon.getName());
  }
}
