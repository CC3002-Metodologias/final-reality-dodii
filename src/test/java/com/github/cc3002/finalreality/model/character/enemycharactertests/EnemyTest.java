package com.github.cc3002.finalreality.model.character.enemycharactertests;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the Enemy class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private static final int ATTACK_DAMAGE = 10;
  private static final int WEIGHT = 10;

  /**
   * Sets up the array and adds an enemy unit to it.
   */
  @BeforeEach
  protected void setUp() {
    super.setUp();
    testCharacters.add(new Enemy(ENEMY_NAME, 10, ATTACK_DAMAGE, DEFENSE,
            WEIGHT, turns));
  }

  /**
   * Tests the constructor, calling the checkConstruction method of the
   * testing super class.
   */
  @Test
  public void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, ATTACK_DAMAGE, DEFENSE,
                    WEIGHT, turns),
        testCharacters.get(0),
        new Enemy(ENEMY_NAME, 5, ATTACK_DAMAGE, DEFENSE, WEIGHT, turns),
        new ThiefCharacter("Test Thief", HP, DEFENSE, turns));

    assertNotEquals(new Enemy("Goblin", 10, 3, DEFENSE,
            WEIGHT, turns), testCharacters.get(0));
    assertNotEquals(new Enemy("Goblin", 10, ATTACK_DAMAGE, 1,
            WEIGHT, turns), testCharacters.get(0));
    assertNotEquals(new Enemy("Goblin", 10, ATTACK_DAMAGE, DEFENSE,
            1, turns), testCharacters.get(0));
  }

  /**
   * Waiting delay for the character's turn. It should be (weight = 10)/10.
   */
  @Override
  @RepeatedTest(3)
  public void waitTurnTest() {
    checkWaitTurn(testCharacters.get(0));
  }

  /**
   * Testing the getDelay method with enemy instances.
   */
  @Override
  @Test
  public void getDelayTest() {
    //enemy of weight = 10.
    assertEquals(1, testCharacters.get(0).getDelay());
  }

  /**
   * Test for the isPlayableCharacter method. Since an enemy character is not a
   * playable character, this method should assert false when called by a character
   * of such type.
   */
  @Test
  public void isPlayableCharacterTest() {
    assertFalse(testCharacters.get(0).isPlayableCharacter());
    assertFalse((new Enemy("Test Enemy", 5, ATTACK_DAMAGE, DEFENSE,
            WEIGHT, turns)).isPlayableCharacter());
  }
}