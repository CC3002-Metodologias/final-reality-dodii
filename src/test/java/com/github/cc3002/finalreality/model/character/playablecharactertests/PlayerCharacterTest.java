package com.github.cc3002.finalreality.model.character.playablecharactertests;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.github.dodii.finalreality.model.weapon.NullWeapon;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Set of tests for the PlayerCharacter class.
 *
 * This class is made for testing all the methods related to the common
 * functioning of the playable characters, such as common-type characters or
 * the mage ones. Behaviors such as equipping weapons will be the focus of
 * the testing process.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
class PlayerCharacterTest extends AbstractCharacterTest {

  protected static final String BLACK_MAGE_NAME = "Vivi";
  protected static final String KNIGHT_NAME = "Adelbert";
  protected static final String WHITE_MAGE_NAME = "Eiko";
  protected static final String ENGINEER_NAME = "Cid";
  protected static final String THIEF_NAME = "Zidane";
  protected Map<CharacterClass, String> characterNames;

  protected List<PlayerCharacter> testPlayerCharacters;

  /**
   * Setup method.
   * Creates an EnunMap containing pairs of the name and the class of each
   * test player character (except mages, since their behavior is more
   * specialized than the others), then inserts this sequence of playable characters
   * into two arrays, one from the super class containing ICharacter-type
   * elements and a second one of this very PlayerCharacterTest class, containing
   * PlayerCharacter-type objects.
   */
  @BeforeEach
  protected void setUp() {
    super.setUp();
    testPlayerCharacters = new ArrayList<>();

    characterNames = new EnumMap<>(CharacterClass.class);
    characterNames.put(CharacterClass.KNIGHT, KNIGHT_NAME);
    characterNames.put(CharacterClass.ENGINEER, ENGINEER_NAME);
    characterNames.put(CharacterClass.THIEF, THIEF_NAME);

    for (var characterClass :
        characterNames.keySet()) {
      testCharacters.add(
          new PlayerCharacter(characterNames.get(characterClass), 10, 3,
                  characterClass, turns));
      testPlayerCharacters.add(
              new PlayerCharacter(characterNames.get(characterClass), 10, 3,
                      characterClass, turns));
    }
  }

  /**
   * Checks the class' constructor and equals/hashcode method works properly.
   * On a side note, it checks both PlayerCharacter and AbstractCharacter classes'
   * constructor.
   */
  @Test
  void constructorTest() {
    var enemy = new Enemy("Enemy", 10, 3, 3, 3, turns);
    for (var character :
        testCharacters) {
      var characterClass = character.getCharacterClass();
      var characterName = characterNames.get(characterClass);
      checkConstruction(new PlayerCharacter(characterName, 10, 3, characterClass, turns),
          character,
          new PlayerCharacter("Test", 10, 3, characterClass, turns),
          new PlayerCharacter(characterName, 10, 3,
              characterClass == CharacterClass.THIEF ? CharacterClass.BLACK_MAGE
                  : CharacterClass.THIEF, turns));
      assertNotEquals(character, enemy);
      assertNotEquals(new PlayerCharacter(characterName, 1, 3, characterClass,
             turns), character);
      assertNotEquals(new PlayerCharacter(characterName, 10, 1, characterClass,
              turns), character);
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
   * the character as PlayerCharacter and not as an ICharacter type, since the last one
   * doesn't have the specific equip() and getEquippedWeapon().
   */
  @Test
  void equipWeaponTest() {
    for (var character :
        testPlayerCharacters) {
      assertEquals(new NullWeapon(), character.getEquippedWeapon());
      character.equip(testWeapon);
      assertEquals(testWeapon, character.getEquippedWeapon());
    }
  }
}
