package com.github.dodii.finalreality.model.character.playablecharacters;

import com.github.dodii.finalreality.model.character.AbstractCharacter;
import com.github.dodii.finalreality.model.character.ICharacter;
import java.util.Objects;
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
public class PlayerCharacter extends AbstractCharacter implements IPlayerCharacter {

  private IWeapon equippedWeapon = new NullWeapon();

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
   * @param characterClass
   *     the class of this character
   */
  public PlayerCharacter(@NotNull String name, final int hp, final int def,
                         final CharacterClass characterClass,
                         @NotNull BlockingQueue<ICharacter> turnsQueue) {
    super(name, hp, def, characterClass, turnsQueue);
  }

  /**
   * @return the equipped weapon of the character.
   */
  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Calculates the character's delay by using the weight of the equipped weapon.
   * @return the delay in the turn of the character.
   */
  @Override
  public long getDelay() {
    return getEquippedWeapon().getWeight() / 10;
  }

  /**
   * Equips a certain weapon to a character.
   * It checks by double dispatch if the weapon can actually be
   * equipped by the character, depending on its class.
   * @param weapon weapon to be equipped.
   */
  @Override
  public void equip(IWeapon weapon) {
      this.equippedWeapon = weapon;
  }

  /**
   * @return true if it's a playable character.
   */
  @Override
  public boolean isPlayableCharacter() {
    return true;
  }

  /**
   * Returns the hashcode of the character.
   * A pair of playable characters have an equivalent hashcode if
   * they share the same name, hp, def, equipped weapon and its class.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getHP(), getDef(), getEquippedWeapon(),
            getCharacterClass());
  }

  /**
   * Compares two characters.
   * @param o character to compare.
   * @return true if both are equal.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IPlayerCharacter)) {
      return false;
    }

    final IPlayerCharacter playableChar = (IPlayerCharacter) o;
    return getName().equals(playableChar.getName()) &&
            getHP() == playableChar.getHP() &&
            getDef() == playableChar.getDef() &&
            getEquippedWeapon().equals(playableChar.getEquippedWeapon()) &&
            getCharacterClass() == playableChar.getCharacterClass();
  }
}
