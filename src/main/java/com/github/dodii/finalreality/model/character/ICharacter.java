package com.github.dodii.finalreality.model.character;

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
   * Returns the current HP of the character.
   */
  int getCurrentHP();

  /**
   * Changes the current HP value of the character.
   * @param value the value to alter the current HP.
   */
  void setCurrentHP(int value);

  /**
   * @return true if the unit has 0 HP.
   */
  boolean isKO();

  /**
   * Returns the character's def.
   */
  int getDef();

  /**
   * Returns true or false depending on the character.
   */
  boolean isPlayableCharacter();

  /**
   * Attack method
   * @param target character attacked.
   */
  void attack(ICharacter target);

  /**
   * Calculates the output damage the character does before
   * considering the target's defense.
   * Has a different implementation depending on the subclass.
   * @return Output damage.
   */
  int calculateAttack();

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
