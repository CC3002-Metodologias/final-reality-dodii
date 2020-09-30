package com.github.dodii.finalreality.model.character;

import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractCharacter implements ICharacter {

  /** stats **/
  private int hp;
  private int def;

  private final BlockingQueue<ICharacter> turnsQueue;
  private final String name;
  private IWeapon equippedWeapon = null;
  private ScheduledExecutorService scheduledExecutor;
  private CharacterClass characterClass;

  /**
   * Creates a character
   * @param turnsQueue the turn in the queue of the character.
   * @param name the name of the character.
   * @param hp the hp of the character.
   * @param def the defense of the character.
   * @param characterClass the class of the character.
   */
  protected AbstractCharacter(@NotNull String name, final int hp, final int def,
                              final CharacterClass characterClass,
                              @NotNull BlockingQueue<ICharacter> turnsQueue) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.hp = hp;
    this.def = def;
    this.characterClass = characterClass;
  }

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    if (this instanceof PlayerCharacter) {
      scheduledExecutor
          .schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    } else {
      var enemy = (Enemy) this;
      scheduledExecutor
          .schedule(this::addToQueue, enemy.getWeight() / 10, TimeUnit.SECONDS);
    }
  }

  /**
   * Adds this character to the turns queue.
   */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  /**
   * @return the name of the character.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * @return the hp of the character.
   */
  @Override
  public int getHP() { return hp; }

  /**
   * @return the defense of the character.
   */
  @Override
  public int getDef() { return def; }

  /**
   * @return the class type of the character.
   */
  @Override
  public CharacterClass getCharacterClass() {
    return characterClass;
  }

  /**
   * Compares two characters.
   * @param o character to compare.
   * @return true if both are equal.
   */
  @Override
  public abstract boolean equals(Object o);
}
