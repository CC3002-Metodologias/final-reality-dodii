package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
   * @param type WeaponType
   * @return an IWeapon instance of a weapon with the static stats parameters.
   */
  protected IWeapon getIWeapon(@NotNull WeaponType type) {
    switch (type) {
      case AXE:
        return new Axe(AXE_NAME, DAMAGE, WEIGHT);
      case BOW:
        return new Bow(BOW_NAME, DAMAGE, WEIGHT);
      case KNIFE:
        return new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
      case STAFF:
        return new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, WEIGHT);
      case SWORD:
        return new Sword(SWORD_NAME, DAMAGE, WEIGHT);
      default:
        return new NullWeapon();
    }
  }

  /**
   * It sets up an array list and fills it with IWeapons instances of
   * weapons. Also sets up individual instances of the weapons.
   */
  @BeforeEach
  public void setUp() {
    testWeapons = new ArrayList<>();

    testAxe = getIWeapon(WeaponType.AXE);
    testWeapons.add(testAxe);

    testBow = getIWeapon(WeaponType.BOW);
    testWeapons.add(testBow);

    testKnife = getIWeapon(WeaponType.KNIFE);
    testWeapons.add(testKnife);

    testStaff = getIWeapon(WeaponType.STAFF);
    testWeapons.add(testStaff);

    testSword = getIWeapon(WeaponType.SWORD);
    testWeapons.add(testSword);

    testNull = getIWeapon(WeaponType.NULL);
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
   *
   */
  @Test
  public void constructorTest() {
    var expectedAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    var expectedStaff = new Staff(STAFF_NAME, DAMAGE, MAGIC_DAMAGE, WEIGHT);
    var expectedSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    var expectedBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    var expectedKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);

    /** Axe **/
    checkEquals(expectedAxe, testAxe);
    checkNotEquals(expectedAxe, testBow);
    checkNotEquals(expectedAxe, testKnife);
    checkNotEquals(expectedAxe, testStaff);
    checkNotEquals(expectedAxe, testSword);

    /** Bow **/
    checkEquals(expectedBow, testBow);
    checkNotEquals(expectedBow, testAxe);
    checkNotEquals(expectedBow, testKnife);
    checkNotEquals(expectedBow, testStaff);
    checkNotEquals(expectedBow, testSword);

    /** Knife **/
    checkEquals(expectedKnife, testKnife);
    checkNotEquals(expectedKnife, testAxe);
    checkNotEquals(expectedKnife, testBow);
    checkNotEquals(expectedKnife, testStaff);
    checkNotEquals(expectedKnife, testSword);

    /** Staff **/
    checkEquals(expectedStaff, testStaff);
    checkNotEquals(expectedStaff, testAxe);
    checkNotEquals(expectedStaff, testBow);
    checkNotEquals(expectedStaff, testKnife);
    checkNotEquals(expectedStaff, testSword);

    /** Sword **/
    checkEquals(expectedSword, testSword);
    checkNotEquals(expectedSword, testAxe);
    checkNotEquals(expectedSword, testBow);
    checkNotEquals(expectedSword, testKnife);
    checkNotEquals(expectedSword, testStaff);

  }
}