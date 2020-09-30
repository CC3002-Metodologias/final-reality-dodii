package com.github.dodii.finalreality.model.weapon;

/**
 * A class that represents a sword.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Sword extends AbstractWeapon {

  /**
   * Creates a sword with a name, a base damage, speed and its type.
   *
   * @see WeaponType
   */
  public Sword(final String name, final int dmg, final int weight) {
    super(name, dmg, weight, WeaponType.SWORD);
  }
}
