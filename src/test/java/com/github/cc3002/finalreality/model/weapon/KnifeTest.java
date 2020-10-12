package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.Axe;
import com.github.dodii.finalreality.model.weapon.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * A class for testing the specific behaviors of Knife-type weapons.
 *
 * @author Rodrigo Oportot.
 */
public class KnifeTest extends AbstractWeaponTest {
    private Knife testKnife;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        testKnife = new Knife("Test Knife", 15, 10);
    }

    @Test
    public void KnifeConstructorTest() {
        Knife anotherKnife = new Knife("Test Knife", 15, 10);
        Knife differentKnife = new Knife("Different Knife", 12, 3);

        checkEquals(anotherKnife, testKnife);
        checkNotEquals(differentKnife, testKnife);

        /** Checks if the weapons are in the list of the super class **/
        checkList(testKnife);
        checkList(anotherKnife);
        assertFalse(testWeapons.contains(differentKnife));
    }
}
