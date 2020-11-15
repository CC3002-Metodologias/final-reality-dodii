package com.github.cc3002.finalreality.model.character;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.weapon.Axe;
import com.github.dodii.finalreality.model.weapon.IWeapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

  //testing seed
  protected long testSeed;

  /**
   * Initial testing set up.
   */
  @BeforeEach
  protected void setUp() {
    turns = new LinkedBlockingQueue<>();
    testWeapon = new Axe("Test Axe", 15, 10);
    testCharacters = new ArrayList<>();

    testSeed = new Random().nextLong();

  }

  /**
   * Testing the getCurrentHP and isKO methods.
   */
  @Test
  public abstract void testHP();

  /**
   * Aux method to test getCurrentHP and isKO methods.
   * @param character
   */
  protected void checkCurrentHP(@NotNull ICharacter character) {
    //full hp and 0 damage
    int fullHP = character.getCurrentHP();
    character.setCurrentHP(character.getCurrentHP());
    assertEquals(fullHP, character.getCurrentHP());

    // alters the HP by a random integer value between
    // [-maxHP * 2, maxHP * 2]
    int testValue = new Random(testSeed).nextInt(4 * character.getHP() + 1)
            - 2 * character.getHP();

    character.setCurrentHP(testValue);

    //checks the hp doesn't get set to non-valid numbers.
    assertTrue(0 <= character.getHP() &&
                    character.getCurrentHP() <= character.getHP(),
            character.getCurrentHP() + " is not a valid hp value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
    //checks the isKO method
    if (character.getCurrentHP() == 0) {
      assertTrue(character.isKO());
    }
    else {
      assertFalse(character.isKO());
    }
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

  /**
   * Attack method test
   */
  @Test
  public abstract void attackTest();

  /**
   * calculateDamage method test
   */
  @Test
  public abstract void calculateDamageTest();

}
