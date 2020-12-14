package com.github.dodii.finalreality.model.character.playablecharacters;

import com.github.dodii.finalreality.model.character.AbstractCharacter;
import com.github.dodii.finalreality.model.character.ICharacter;
import java.util.concurrent.BlockingQueue;

import com.github.dodii.finalreality.model.weapon.IWeapon;
import com.github.dodii.finalreality.model.weapon.NullWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractPlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

  private IWeapon equippedWeapon = NullWeapon.uniqueInstance();

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name
   * @param hp
   *     the character's hp.
   * @param def
   *     the character's def.
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public AbstractPlayerCharacter(@NotNull String name, final int hp, final int def,
                                 @NotNull BlockingQueue<ICharacter> turnsQueue) {
    super(name, hp, def, turnsQueue);
  }

  /**
   * @return the equipped weapon of the character.
   */
  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Method to set a weapon. It's called from
   * equip() and the double dispatch aux methods.
   * @param weapon weapon to equip.
   */
  protected void setEquippedWeapon(IWeapon weapon) {
    equippedWeapon = weapon;
  }

  /**
   * Equips a certain weapon to a character.
   * @param weapon weapon to be equipped.
   */
  @Override
  public abstract void equip(IWeapon weapon);

  /**
   * Calculates the character's delay by using the weight of the equipped weapon.
   * @return the delay in the turn of the character.
   */
  @Override
  public long getDelay() {
    return getEquippedWeapon().getWeight() / 10;
  }

  /**
   * @return true if it's a playable character.
   */
  @Override
  public boolean isPlayableCharacter() {
    return true;
  }

  /**
   * Calculates the output damage the character does before
   * considering the target's defense.
   * Considers the damage of the equipped weapon.
   * @return Output damage.
   */
  @Override
  public int calculateAttack() {
    return getEquippedWeapon().getDmg();
  }

  @Override
  public void receiveWeapon(IWeapon weapon) {
    setEquippedWeapon(weapon);
  }

  /**
   * Returns the hashcode of the character.
   * A pair of playable characters have an equivalent hashcode if
   * they share the same name, hp, def, equipped weapon and a custom
   * parameter defined on every subclass.
   */
  @Override
  public abstract int hashCode();

  /**
   * Compares two characters.
   * @param o character to compare.
   * @return true if both are equal.
   */
  @Override
  public abstract boolean equals(final Object o);
}
