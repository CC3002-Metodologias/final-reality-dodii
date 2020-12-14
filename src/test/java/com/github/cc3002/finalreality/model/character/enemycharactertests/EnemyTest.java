package com.github.cc3002.finalreality.model.character.enemycharactertests;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
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
   * Tests the HP related methods by calling the super class checkCurrentHP()
   * method.
   */
  @Override
  @RepeatedTest(10)
  public void testHP() {
    checkCurrentHP(testCharacters.get(0));
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

  /**
   * Tests the isMage method.
   */
  @Test
  public void isMageTest() {
    assertFalse(testCharacters.get(0).isMage());
  }

  /**
   * Attack method test for enemy units.
   * Checks attack(), receiveAttack() and calculateAttack() methods.
   */
  @Override
  @Test
  public void attackTest() {
    var knight = new KnightCharacter("Target", 10, 4, turns);
    var anotherEnemy = new Enemy("Target", 10, 3, 10, 10, turns);

    //Can't attack itself
    assertFalse(testCharacters.get(0).attack(testCharacters.get(0)));

    //enemy attacks knight
    assertTrue(testCharacters.get(0).attack(knight));
    assertEquals(4, knight.getCurrentHP());
    assertFalse(knight.isKO());

    //enemy attacks anotherEnemy
    //suffers no dmg, since it has 10 defense
    assertTrue(testCharacters.get(0).attack(anotherEnemy));
    assertEquals(10, anotherEnemy.getCurrentHP());

    //knight attacks enemy
    //has no weapon (null weapon equipped) first
    assertTrue(knight.attack(testCharacters.get(0)));
    assertEquals(10, testCharacters.get(0).getCurrentHP());

    //15 dmg axe
    knight.equip(testWeapon);
    assertTrue(knight.attack(testCharacters.get(0)));
    assertEquals(0, testCharacters.get(0).getCurrentHP());
    assertTrue(testCharacters.get(0).isKO());

    //enemy shouldn't be able to attack now, so the knight
    //character doesn't receive any damage.
    assertFalse(testCharacters.get(0).attack(knight));
    assertEquals(4, knight.getCurrentHP());

    //since the enemy character's still dead, the knight
    //shouldn't be able to attack it
    assertFalse(knight.attack(testCharacters.get(0)));
  }
}