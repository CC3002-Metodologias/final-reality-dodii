package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.Axe;
import com.github.dodii.finalreality.model.weapon.Bow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * A class for testing the specific behaviors of Bow-type weapons.
 *
 * @author Rodrigo Oportot.
 */
public class BowTest extends AbstractWeaponTest {

    private Bow testBow;

    /**
     * Set ups a Bow.
     */
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        testBow = new Bow("Test Bow", 15, 10);
    }

    /**
     * Bow constructor test.
     */
    @Test
    public void BowConstructorTest() {
        Bow anotherBow = new Bow("Test Bow", 15, 10);
        Bow differentBow = new Bow("Different Bow", 12, 3);

        checkEquals(anotherBow, testBow);
        checkNotEquals(differentBow, testBow);

        /** Checks if the weapons are in the list of the super class **/
        checkList(testBow);
        checkList(anotherBow);
        assertFalse(testWeapons.contains(differentBow));
    }

}
