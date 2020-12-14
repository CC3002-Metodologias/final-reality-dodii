package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.BlackMageCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.WhiteMageCharacter;
import com.github.dodii.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the common behaviors of weapons.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class AbstractWeaponTest {

  private static final String AXE_NAME = "Test Axe";
  private static final String STAFF_NAME = "Test Staff";
  private static final String SWORD_NAME = "Test Sword";
  private static final String BOW_NAME = "Test Bow";
  private static final String KNIFE_NAME = "Test Knife";
  private static final int DAMAGE = 15;
  private static final int MAGIC_DAMAGE = 5;
  private static final int WEIGHT = 10;

  protected List<IWeapon> testWeapons;

  private IWeapon testAxe;
  private IWeapon testBow;
  private IWeapon testKnife;
  private IWeapon testStaff;
  private IWeapon testSword;
  private IWeapon testNull;

  /**
   * Aux switch method to create particular instances of IWeapons.
   * Used mostly for efficient and faster creation of objects.
   * @param weaponName name of the weapon
   * @return an IWeapon instance of a weapon with the static stats parameters.
  */
  protected IWeapon getIWeapon(@NotNull String weaponName) {
    switch (weaponName) {
      case AXE_NAME:
        return new Axe(AXE_NAME, DAMAGE, WEIGHT);
      case BOW_NAME:
        return new Bow(BOW_NAME, DAMAGE, WEIGHT);
      case KNIFE_NAME:
        return new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
      case STAFF_NAME:
        return new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, WEIGHT);
      case SWORD_NAME:
        return new Sword(SWORD_NAME, DAMAGE, WEIGHT);
      default:
        return NullWeapon.uniqueInstance();
    }
  }

  /**
   * It sets up an array list and fills it with IWeapons instances of
   * weapons. Also sets up individual instances of the weapons.
   */
  @BeforeEach
  public void setUp() {
    testWeapons = new ArrayList<>();

    testAxe = getIWeapon(AXE_NAME);
    testWeapons.add(testAxe);

    testBow = getIWeapon(BOW_NAME);
    testWeapons.add(testBow);

    testKnife = getIWeapon(KNIFE_NAME);
    testWeapons.add(testKnife);

    testStaff = getIWeapon(STAFF_NAME);
    testWeapons.add(testStaff);

    testSword = getIWeapon(SWORD_NAME);
    testWeapons.add(testSword);

    testNull = getIWeapon("Null Weapon");
    testWeapons.add(testNull);

  }

  /**
   * Checks if the input weapon is already inside the
   * test weapons' list.
   * @param weapon said weapon
   */
  protected void checkList(IWeapon weapon) {
    assertTrue(testWeapons.contains(weapon));
  }

  /**
   * Checks the inputs weapons are equal.
   *
   */
  protected void checkEquals(IWeapon weapon, IWeapon other) {
    assertEquals(weapon, weapon);
    assertEquals(weapon.hashCode(), weapon.hashCode());

    assertNotSame(weapon, other);

    assertEquals(weapon, other);
    assertEquals(weapon.hashCode(), other.hashCode());
  }

  /**
   * Checks the input weapons are not equal.
   */
  protected void checkNotEquals(IWeapon weapon, IWeapon other) {
    assertNotEquals(weapon, null);

    assertNotEquals(weapon, other);
    assertNotEquals(weapon.hashCode(), other.hashCode());

    assertNotEquals(weapon, new Object());
  }

  /**
   *
   * Constructor test for all instances of weapons.
   * It checks the equals and hashcode methods.
   * Creates a various amount of different instances to test.
   *
   */
  @Test
  public void constructorTest() {
    Axe expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    Staff expectedStaff = new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, WEIGHT);
    Sword expectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    Bow expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    Knife expectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
    NullWeapon expectedNull = NullWeapon.uniqueInstance();

    Axe aux_Axe = new Axe("Aux Axe", DAMAGE, 1);
    /* Axe **/
    checkEquals(expectedAxe, testAxe);
    checkNotEquals(expectedAxe, testBow);
    checkNotEquals(expectedAxe, testKnife);
    checkNotEquals(expectedAxe, testStaff);
    checkNotEquals(expectedAxe, testSword);
    checkNotEquals(expectedAxe, testNull);
    checkNotEquals(aux_Axe, testAxe);

    /* particular case */
    Sword aux_Sword = new Sword("Test Axe", DAMAGE, WEIGHT);
    checkNotEquals(aux_Sword, testAxe);

    Bow aux_Bow = new Bow("Test Axe", 1, WEIGHT);
    /* Bow **/
    checkEquals(expectedBow, testBow);
    checkNotEquals(expectedBow, testAxe);
    checkNotEquals(expectedBow, testKnife);
    checkNotEquals(expectedBow, testStaff);
    checkNotEquals(expectedBow, testSword);
    checkNotEquals(expectedBow, testNull);
    checkNotEquals(aux_Bow, expectedBow);

    /* Knife **/
    checkEquals(expectedKnife, testKnife);
    checkNotEquals(expectedKnife, testAxe);
    checkNotEquals(expectedKnife, testBow);
    checkNotEquals(expectedKnife, testStaff);
    checkNotEquals(expectedKnife, testSword);
    checkNotEquals(expectedKnife, testNull);

    /* Staff **/
    checkEquals(expectedStaff, testStaff);
    checkNotEquals(expectedStaff, testAxe);
    checkNotEquals(expectedStaff, testBow);
    checkNotEquals(expectedStaff, testKnife);
    checkNotEquals(expectedStaff, testSword);
    checkNotEquals(expectedStaff, testNull);

    /* Sword **/
    checkEquals(expectedSword, testSword);
    checkNotEquals(expectedSword, testAxe);
    checkNotEquals(expectedSword, testBow);
    checkNotEquals(expectedSword, testKnife);
    checkNotEquals(expectedSword, testStaff);
    checkNotEquals(expectedSword, testNull);

    /* Null Weapon */
    checkNotEquals(expectedNull, testAxe);
    checkNotEquals(expectedNull, testBow);
    checkNotEquals(expectedNull, testKnife);
    checkNotEquals(expectedNull, testStaff);
    checkNotEquals(expectedNull, testSword);
  }

  /**
   * Test for the double dispatch implementation of the equip mechanic built
   * with double dispatch.
   */
  @Test
  public void equipToClassTest() {
    var queue = new LinkedBlockingDeque<ICharacter>();
    var knight = new KnightCharacter("Knight", 10, 10, queue);
    var engineer = new EngineerCharacter("Engineer", 10, 10, queue);
    var thief = new ThiefCharacter("Thief", 10, 10, queue);
    var bMage = new BlackMageCharacter("Black Mage", 5, 5, 5, queue);
    var wMage = new WhiteMageCharacter("White Mage", 5, 5, 5, queue);

    var charsArray = new ArrayList<IPlayerCharacter>();
    charsArray.add(knight);
    charsArray.add(engineer);
    charsArray.add(thief);
    charsArray.add(bMage);
    charsArray.add(wMage);

    /* check the null weapon first */
    for (var chars : charsArray) {
      assertEquals(testNull, chars.getEquippedWeapon());
      assertEquals(testNull, chars.getEquippedWeapon());
    }

    /* equipToClass of NullWeapon */
    testNull.equipToKnight(knight);
    assertEquals(testNull, knight.getEquippedWeapon());

    testNull.equipToEngineer(engineer);
    assertEquals(testNull, engineer.getEquippedWeapon());

    testNull.equipToThief(thief);
    assertEquals(testNull, thief.getEquippedWeapon());

    testNull.equipToMage(bMage);
    assertEquals(testNull, bMage.getEquippedWeapon());

    testNull.equipToMage(wMage);
    assertEquals(testNull, wMage.getEquippedWeapon());

    /* equipToClass of Axe */
    testAxe.equipToKnight(knight);
    assertEquals(testAxe, knight.getEquippedWeapon());

    testAxe.equipToEngineer(engineer);
    assertEquals(testAxe, engineer.getEquippedWeapon());

    testAxe.equipToThief(thief);
    assertEquals(testNull, thief.getEquippedWeapon());

    testAxe.equipToMage(bMage);
    assertEquals(testNull, bMage.getEquippedWeapon());

    testAxe.equipToMage(wMage);
    assertEquals(testNull, wMage.getEquippedWeapon());

    /* equipToClass of Bow */
    testBow.equipToKnight(knight);
    assertEquals(testAxe, knight.getEquippedWeapon());

    testBow.equipToEngineer(engineer);
    assertEquals(testBow, engineer.getEquippedWeapon());

    testBow.equipToThief(thief);
    assertEquals(testBow, thief.getEquippedWeapon());

    testBow.equipToMage(bMage);
    assertEquals(testNull, bMage.getEquippedWeapon());

    testBow.equipToMage(wMage);
    assertEquals(testNull, wMage.getEquippedWeapon());

    /* equipToClass of Knife */
    testKnife.equipToKnight(knight);
    assertEquals(testKnife, knight.getEquippedWeapon());

    testKnife.equipToEngineer(engineer);
    assertEquals(testBow, engineer.getEquippedWeapon());

    testKnife.equipToThief(thief);
    assertEquals(testKnife, thief.getEquippedWeapon());

    testKnife.equipToMage(bMage);
    assertEquals(testNull, bMage.getEquippedWeapon());

    testKnife.equipToMage(wMage);
    assertEquals(testNull, wMage.getEquippedWeapon());

    /* equipToClass of Sword */
    testSword.equipToKnight(knight);
    assertEquals(testSword, knight.getEquippedWeapon());

    testSword.equipToEngineer(engineer);
    assertEquals(testBow, engineer.getEquippedWeapon());

    testSword.equipToThief(thief);
    assertEquals(testSword, thief.getEquippedWeapon());

    testSword.equipToMage(bMage);
    assertEquals(testNull, bMage.getEquippedWeapon());

    testSword.equipToMage(wMage);
    assertEquals(testNull, wMage.getEquippedWeapon());

    /* equipToClass of Staff */
    testStaff.equipToKnight(knight);
    assertEquals(testSword, knight.getEquippedWeapon());

    testStaff.equipToEngineer(engineer);
    assertEquals(testBow, engineer.getEquippedWeapon());

    testStaff.equipToThief(thief);
    assertEquals(testSword, thief.getEquippedWeapon());

    testStaff.equipToMage(bMage);
    assertEquals(testStaff, bMage.getEquippedWeapon());

    testStaff.equipToMage(wMage);
    assertEquals(testStaff, wMage.getEquippedWeapon());

  }
}