package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.Axe;
import com.github.dodii.finalreality.model.weapon.Sword;
import com.github.dodii.finalreality.model.weapon.WeaponType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * A class for testing the specific behaviors of Sword-type weapons.
 *
 * @author Rodrigo Oportot.
 */
public class SwordTest extends AbstractWeaponTest {

    private Sword testSword;

    /**
     * Sets up a sword.
     */
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        testSword = new Sword("Test Sword", 15, 10);
    }

    /**
     * Sword constructor test.
     */
    @Test
    public void SwordConstructorTest() {
        Sword anotherSword = new Sword("Test Sword", 15, 10);
        Sword differentSword = new Sword("Different Sword", 12, 3);

        checkEquals(anotherSword, testSword);
        checkNotEquals(differentSword, testSword);

        /** Checks if the weapons are in the list of the super class **/
        checkList(testSword);
        checkList(anotherSword);
        assertFalse(testWeapons.contains(differentSword));
    }
}
