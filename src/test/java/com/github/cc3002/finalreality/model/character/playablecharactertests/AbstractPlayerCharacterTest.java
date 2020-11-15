package com.github.cc3002.finalreality.model.character.playablecharactertests;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.AbstractPlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.IPlayerCharacter;

import java.util.*;
import java.util.concurrent.BlockingQueue;

import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.BlackMageCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.WhiteMageCharacter;
import com.github.dodii.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Set of tests for the AbstractPlayerCharacter class.
 *
 * This class is made for testing all the methods related to the common
 * functioning of the playable characters, such as common-type characters or
 * the mage ones. Behaviors such as equipping weapons will be the focus of
 * the testing process.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractPlayerCharacterTest extends AbstractCharacterTest {

  protected static final String BLACK_MAGE_NAME = "Vivi";
  protected static final String KNIGHT_NAME = "Adelbert";
  protected static final String WHITE_MAGE_NAME = "Eiko";
  protected static final String ENGINEER_NAME = "Cid";
  protected static final String THIEF_NAME = "Zidane";
  protected Map<String, String> characterNames;

  protected static final int COMMON_HP = 10;
  protected static final int COMMON_DEF = 3;
  protected static final int COMMON_MANA = 5;

  protected List<IPlayerCharacter> testPlayerCharacters;

  protected Axe testAxe;
  protected Bow testBow;
  protected Knife testKnife;
  protected Staff testStaff;
  protected Sword testSword;
  protected NullWeapon testNullWeapon;

  /**
   * Aux switch method to create particular instances of IPlayerCharacters.
   * @param characterName static name of the character
   * @return an IPlayerCharacter instance with the static stats parameters.
   */
  protected IPlayerCharacter getIPlayerCharacter(@NotNull String characterName) {
    switch (characterName) {
      case BLACK_MAGE_NAME:
        return new BlackMageCharacter(BLACK_MAGE_NAME, COMMON_HP, COMMON_DEF, COMMON_MANA,
                turns);
      case KNIGHT_NAME:
        return new KnightCharacter(KNIGHT_NAME, COMMON_HP, COMMON_DEF,
                turns);
      case WHITE_MAGE_NAME:
        return new WhiteMageCharacter(WHITE_MAGE_NAME, COMMON_HP, COMMON_DEF, COMMON_MANA,
                turns);
      case ENGINEER_NAME:
        return new EngineerCharacter(ENGINEER_NAME, COMMON_HP, COMMON_DEF,
                turns);
      default: //thief
        return new ThiefCharacter(THIEF_NAME, COMMON_HP, COMMON_DEF,
                turns);
    }
  }

  /**
   * Aux switch method to create particular instances of IPlayerCharacters.
   * @param characterName static name of the character
   * @return an IPlayerCharacter instance with the static stats parameters.
   */
  protected IPlayerCharacter getSpecificIPlayerCharacter(@NotNull String characterName, int hp,
                                                         int def, BlockingQueue<ICharacter> t) {
    switch (characterName) {
      case BLACK_MAGE_NAME:
        return new BlackMageCharacter(BLACK_MAGE_NAME, hp, def, COMMON_MANA, t);
      case KNIGHT_NAME:
        return new KnightCharacter(KNIGHT_NAME, hp, def, t);
      case WHITE_MAGE_NAME:
        return new WhiteMageCharacter(WHITE_MAGE_NAME, hp, def, COMMON_MANA, t);
      case ENGINEER_NAME:
        return new EngineerCharacter(ENGINEER_NAME, hp, def, t);
      default: //thief
        return new ThiefCharacter(THIEF_NAME, hp, def, t);
    }
  }

  /**
   * Setup method.
   * Creates a HashMap containing the names of each
   * test player character (except mages, since their behavior is more
   * specialized than the others), then inserts this sequence of playable characters
   * into two arrays, one from the super class containing ICharacter-type
   * elements and a second one of IPlayerCharacter class.
   */
  @BeforeEach
  protected void setUp() {
    super.setUp();
    testPlayerCharacters = new ArrayList<>();

    characterNames = new HashMap<>();
    characterNames.put(KNIGHT_NAME, KNIGHT_NAME);
    characterNames.put(ENGINEER_NAME, ENGINEER_NAME);
    characterNames.put(THIEF_NAME, THIEF_NAME);

    for (var characterClass : characterNames.keySet()) {
      testCharacters.add(
          getIPlayerCharacter(characterClass));
      testPlayerCharacters.add(
              getIPlayerCharacter(characterClass));
    }
  }

  /**
   * Tests the HP related methods by calling the super class checkCurrentHP()
   * method.
   */
  @Override
  @Test
  public void testHP() {
    for(var playableChar : testPlayerCharacters) {
      checkCurrentHP(playableChar);
    }
  }

  /**
   * Checks the class' constructor and equals/hashcode method works properly.
   * On a side note, it checks both AbstractPlayerCharacter and AbstractCharacter classes'
   * constructor.
   */
  @Test
  void constructorTest() {
    var enemy = new Enemy("Enemy", 10, 3, 3, 3, turns);
    for (var character :
        testCharacters) {
      var characterClass = character.getName();
      var characterName = characterNames.get(characterClass);
      checkConstruction(getIPlayerCharacter(characterName),
          character,
          getSpecificIPlayerCharacter(characterName, 5, 2, turns),
          getIPlayerCharacter(characterName.equals(THIEF_NAME) ? BLACK_MAGE_NAME
                  : THIEF_NAME));
      assertNotEquals(character, enemy);

      //more asserts for coverage
      assertNotEquals(getSpecificIPlayerCharacter(characterName, 5, 3, turns),
              character);
      assertNotEquals(getSpecificIPlayerCharacter(characterName, 10, 1, turns),
              character);
    }
  }

  /**
   * This testing method uses all ICharacter instances
   * inside the list to test.
   * This test takes some time but it's necessary to ensure
   * every instance is tested
   */
  @Override
  @RepeatedTest(3)
  public void waitTurnTest() {
    //weaponless
    for (var character : testCharacters) {
      checkWaitTurn(character);
      //clears the queue for testing purposes
      turns.clear();
    }

    //weapon equipped of weight = 10
    for (var character : testPlayerCharacters) {
      character.equip(testWeapon);
      checkWaitTurn(character);
      turns.clear();
    }
  }

  /**
   * Tests the getDelay method with playable character instances.
   */
  @Override
  @Test
  public void getDelayTest() {
    //No weapon equipped.
    assertEquals(0, testCharacters.get(0).getDelay());

    //with weapon of weight = 10 equipped.
    testPlayerCharacters.get(0).equip(testWeapon);
    assertEquals(testPlayerCharacters.get(0).getEquippedWeapon().getWeight()/10,
            testPlayerCharacters.get(0).getDelay());
  }

  /**
   * Test for the isPlayableCharacter method. This test should assert
   * true for all instances of a playable character, including common and
   * mage ones.
   */
  @Override
  @Test
  public void isPlayableCharacterTest() {
    for (var playableChar : testCharacters) {
      assertTrue(playableChar.isPlayableCharacter());
    }
  }


  /**
   * Test for the equip and getEquippedWeapon method. This requires instancing
   * the character as AbstractPlayerCharacter and not as an ICharacter type, since the last one
   * doesn't have the equip() and getEquippedWeapon() methods.
   */
  @Test
  public abstract void equipWeaponTest();

  /**
   * Sets up test weapons.
   */
  protected void weaponSetUp() {
    testAxe = new Axe("Test Axe", 10, 10);
    testBow = new Bow("Test Bow", 10, 10);
    testKnife = new Knife("Test Knife", 10, 10);
    testStaff = new Staff("Test Staff", 10,5, 10);
    testSword = new Sword("Test Sword", 10, 10);
    testNullWeapon = new NullWeapon();

  }
}
