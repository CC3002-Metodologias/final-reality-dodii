package com.github.dodii.finalreality.model.character.enemycharacters;

import com.github.dodii.finalreality.model.character.AbstractCharacter;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class Enemy extends AbstractCharacter implements IEnemy {

  private final int weight;
  private final int atk;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final String name, final int hp, final int atk, final int def,
               final int weight, @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, hp, def, CharacterClass.ENEMY, turnsQueue);
    this.weight = weight;
    this.atk = atk;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Returns the attack of this enemy.
   */
  public int getAtk() {
    return atk;
  }

  /**
   * @return true if it's an instance of a playable character.
   */
  @Override
  public boolean isPlayableCharacter() {
    return false;
  }

  /**
   * Compares two characters.
   * Method adjusted to the enemy type of character.
   * @param o the enemy character to compare.
   * @return true if both characters are equal.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getName().equals(enemy.getName()) &&
            getHP() == enemy.getHP() &&
            getDef() == enemy.getDef() &&
            getCharacterClass() == enemy.getCharacterClass() &&
            getWeight() == enemy.getWeight() &&
            getAtk() == enemy.getAtk();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }
}
