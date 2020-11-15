package com.github.dodii.finalreality.model.character;

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
  private int currentHP;
  private int def;

  private final BlockingQueue<ICharacter> turnsQueue;
  private final String name;
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a character
   * @param turnsQueue the turn in the queue of the character.
   * @param name the name of the character.
   * @param hp the hp of the character.
   * @param def the defense of the character.
   */
  protected AbstractCharacter(@NotNull String name, final int hp, final int def,
                              @NotNull BlockingQueue<ICharacter> turnsQueue) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.hp = hp;
    this.currentHP = hp;
    this.def = def;
  }

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   * It calculates the delay on every subclass depending on the type of the character.
   * Changed the instanceof for a method call, so it won't break SOLID principles.
   */
  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, this.getDelay(), TimeUnit.SECONDS);
  }

  /**
   * Calculates the character's delay by dividing the weight of the character itself
   * (enemy case) or its equipped weapon's weight.
   * @return the delay in the turn of the character.
   */
  @Override
  public abstract long getDelay();

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
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
   * Returns the current HP of the character.
   */
  @Override
  public int getCurrentHP() {
    return currentHP;
  }

  /**
   * Changes the current HP value of the character.
   * @param newHP the value to alter the current HP.
   */
  @Override
  public void setCurrentHP(int newHP) {
      currentHP = Math.max(0, Math.min(newHP, getHP()));
  }

  /**
   * @return true if the unit has 0 HP.
   */
  public boolean isKO() {
    return getCurrentHP() == 0;
  }

  /**
   * @return the defense of the character.
   */
  @Override
  public int getDef() { return def; }

  /**
   * @return true if the character it's an instance of a playable one.
   */
  @Override
  public abstract boolean isPlayableCharacter();

  /**
   * Attack method
   * @param character the target of the attack
   */
  @Override
  public void attack(ICharacter character) {

    //edge-conditions
    if(!(this.isKO() || character.isKO())) {
      int output = this.calculateAttack();
      //receive Attack
    }
    //else can't attack
  }

  /**
   * Calculates the output damage the character does before
   * considering the target's defense.
   * Has a different implementation depending on the subclass.
   * @return Output damage.
   */
  public abstract int calculateAttack();

  /**
   * Returns the hashcode of the character.
   */
  @Override
  public abstract int hashCode();

  /**
   * Compares two characters.
   * @param o character to compare.
   * @return true if both are equal.
   */
  @Override
  public abstract boolean equals(Object o);
}
