package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.Knife;
import com.github.dodii.finalreality.model.weapon.NullWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * A class for testing the specific behaviors of the Null-Type weapons.
 * In the future, NullWeapon will implement singleton design pattern.
 *
 * @author Rodrigo Oportot.
 */
public class NullWeaponTest extends AbstractWeaponTest {

    private NullWeapon testNull;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        testNull = new NullWeapon();
    }

    @Test
    public void NullWeaponConstructorTest() {
        NullWeapon anotherNull = new NullWeapon();

        checkEquals(anotherNull, testNull);
        checkNotEquals(testWeapons.get(0), testNull);

        /** Checks if the weapons are in the list of the super class **/
        checkList(testNull);
        checkList(anotherNull);
    }
}
