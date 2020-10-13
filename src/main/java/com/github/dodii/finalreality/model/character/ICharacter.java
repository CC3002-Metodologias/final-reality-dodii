package com.github.dodii.finalreality.model.character;

import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Calculates the character's delay by dividing the weight of the character itself
   * (enemy case) or its equipped weapon's weight.
   * @return the delay in the turn of the character.
   */
  long getDelay();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns the character's hp.
   */
  int getHP();

  /**
   * Returns the character's def.
   */
  int getDef();

  /**
   * Returns the character's class.
   */
  CharacterClass getCharacterClass();

  /**
   * Returns true or false depending on the character.
   */
  boolean isPlayableCharacter();

  /**
   * Returns the hashcode of the character.
   */
  int hashCode();

  /**
   * Compares two characters.
   * @return true if both characters are equal.
   */
  boolean equals(Object o);
}
