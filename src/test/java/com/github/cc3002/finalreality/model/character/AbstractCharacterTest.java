package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import com.github.dodii.finalreality.model.weapon.Axe;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import com.github.dodii.finalreality.model.weapon.WeaponType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected List<ICharacter> testCharacters;
  protected IWeapon testWeapon;
  protected static final int HP = 15;
  protected static final int DEFENSE = 3;

  /**
   * Initial testing set up.
   */
  @BeforeEach
  protected void setUp() {
    turns = new LinkedBlockingQueue<>();
    testWeapon = new Axe("Test Axe", 15, 10);
    testCharacters = new ArrayList<>();
  }

  /**
   * Tests the correct functioning of the constructor methods by comparing
   * the input characters.
   * This tests the equals and the hashcode methods.
   * @param expectedCharacter the expected character to be compared.
   * @param testEqualCharacter the actual character we want to compare.
   * @param sameClassDifferentCharacter a character of the same class but with different qualities.
   * @param differentClassCharacter a character of a different class.
   */
  protected void checkConstruction(final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter) {

    assertEquals(expectedCharacter, testEqualCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());

    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter.hashCode(), testEqualCharacter.hashCode());

    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertNotEquals(testEqualCharacter.hashCode(), differentClassCharacter.hashCode());

  }

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  protected void checkWaitTurn(@NotNull ICharacter character) {
    Assertions.assertTrue(turns.isEmpty());

    long seconds = character.getDelay();
    long milliseconds = seconds * 1000;
    character.waitTurn();

    try {
      //immediate action, a player character has no weapon equipped or it's a
      //weightless enemy.
      if (seconds == 0) {
        Assertions.assertEquals(0, turns.size());
        //waits a bit
        Thread.sleep(100);
      }
      else {
        // Thread.sleep is not accurate so this values may be changed to adjust the
        // acceptable error margin.
        // We're testing that the character waits approximately
        // ((weight || weapon weight) / 10 )seconds.

        //first, it waits for a quarter of the time.
        Thread.sleep(milliseconds / 4);
        Assertions.assertEquals(0, turns.size());
        //then, a bit more than the rest (originally 3/4)
        Thread.sleep((milliseconds * 4) / 5);
        Assertions.assertEquals(1, turns.size());
      }
      Assertions.assertEquals(character, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Abstract method implemented on each testing subclass. All implementations
   * will call the above checkWaitTurn method to test a correct functioning.
   */
  @Test
  public abstract void waitTurnTest();

  /**
   * Testing the getDelay method.
   */
  @Test
  public abstract void getDelayTest();

  /**
   * Tests the isPlayableCharacter method.
   */
  @Test
  public abstract void isPlayableCharacterTest();

}
