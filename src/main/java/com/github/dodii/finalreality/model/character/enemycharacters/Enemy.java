package com.github.dodii.finalreality.model.character.enemycharacters;

import com.github.dodii.finalreality.model.character.AbstractCharacter;
import com.github.dodii.finalreality.model.character.ICharacter;

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

  private final int atk;
  private final int weight;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   */
  public Enemy(@NotNull final String name, final int hp, final int atk, final int def,
               final int weight, @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, hp, def, turnsQueue);
    this.atk = atk;
    this.weight = weight;
  }

  /**
   * Calculates the character's delay by using the weight of the this enemy-type
   * character.
   * @return the delay in the turn of the character.
   */
  @Override
  public long getDelay() {
    return this.weight / 10;
  }

  /**
   * Returns the attack of this enemy.
   */
  public int getAtk() {
    return atk;
  }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

  /**
   * @return true if it's an instance of a playable character.
   */
  @Override
  public boolean isPlayableCharacter() {
    return false;
  }

  /**
   * Calculates the output damage the character does before
   * considering the target's defense.
   * Has a different implementation depending on the subclass.
   * In this case, it's just the stat.
   * @return Output damage.
   */
  @Override
  public int calculateAttack() {
    return atk;
  }

  /**
   * Returns the hashcode of the character.
   * A pair of enemies have the same hashcode if they share the same
   * name, hp, atk, def, enemy class and weight.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getName(), getHP(), getAtk(), getDef(),
            getWeight());
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
            getAtk() == enemy.getAtk() &&
            getDef() == enemy.getDef() &&
            getWeight() == enemy.getWeight();
  }
}
