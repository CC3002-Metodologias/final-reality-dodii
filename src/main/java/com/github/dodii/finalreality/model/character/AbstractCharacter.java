package com.github.dodii.finalreality.model.character;

import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.github.dodii.finalreality.controller.handlers.IHandler;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractCharacter implements ICharacter {

  /* stats */
  private int hp;
  private int currentHP;
  private int def;

  private final BlockingQueue<ICharacter> turnsQueue;
  private final String name;
  private ScheduledExecutorService scheduledExecutor;

  /* Observer */
  private final PropertyChangeSupport knockedOutEvent =
          new PropertyChangeSupport(this);
  private final PropertyChangeSupport timerEvent =
          new PropertyChangeSupport(this);
  private final PropertyChangeSupport endTurnEvent =
          new PropertyChangeSupport(this);

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
   * Listener method for the observer implementation. The controller will observe
   * the character and will get notified when it gets K.O'd
   * @param handler the handler in question
   */
  public void addKOEventListener(IHandler handler) {
    knockedOutEvent.addPropertyChangeListener(handler);
  }

  /**
   * Listener method for the observer implementation. The controller will observe
   * the character and will get notified when it starts its turn.
   * @param handler the handler in question
   */
  public void addTimerEndedEventListener(IHandler handler) {
    timerEvent.addPropertyChangeListener(handler);
  }

  /**
   * Listener method for the observer implementation. The controller will observe
   * the character and will get notified when it ends its turn.
   * @param handler the handler in question
   */
  public void addEndTurnEventListener(IHandler handler) {
    endTurnEvent.addPropertyChangeListener(handler);
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
   * When weight/10 seconds has passed, the handler will get
   * notified the character was added to the queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    //Fue añadido a la cola después del tiempo de espera
    //de weight/10, por lo que se notifica al controlador
    //del suceso para que proceda del paso 5 al 1.
    //De esta forma, puede saber que el siguiente personaje
    //está listo para ser seleccionado y comenzar su turno.
    timerEvent.firePropertyChange(getName() + " was" +
            " added to the queue", null, this);
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
  public int getHP() {
    return hp;
  }

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
   * Returns true or false depending on the character
   */
  @Override
  public boolean isMage() {
    return false;
  }

  /**
   * Attack method
   * Checks if the attacker or the target is already KO.
   * In that case, nothing happens.
   * If it's not the case, calculates the output damage with
   * calculateAttack(), then the target makes a call to receiveAttack()
   * method.
   * Can't attack themselves.
   * When a character attacks, it triggers the end turn notification
   * to its handler.
   * @param character the target of the attack
   * @return true if the attack succeeds, false otherwise.
   */
  @Override
  public boolean attack(ICharacter character) {

    //edge-conditions, neither of the characters
    //should be K.O to interact. Can't attack itself.
    if(!(this.isKO() || character.isKO()) && !this.equals(character)) {
      int output = this.calculateAttack();
      character.receiveAttack(output);
      //notification for the end of the turn
      endTurnEvent.firePropertyChange(getName() + " ended its turn",
              null, this);
      return true;
    }
    //else doesn't attack
    return false;
  }

  /**
   * Receives an attack from the opponent.
   * It calculates the final damage received as
   * actual damage = received damage - defense.
   *
   * If the character gets K.O'd, it will trigger the knocked out
   * event and will notify its listeners about it.
   *
   * @param receivedDamage output damage of the opponent.
   */
  @Override
  public void receiveAttack(int receivedDamage) {
    int actualDamage = receivedDamage - getDef();
    int newHp = getCurrentHP() - actualDamage;
    setCurrentHP(newHp);

    if(isKO()) {
      knockedOutEvent.firePropertyChange(getName() + " is KO",
              null, this);
    }
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
