package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * A class for testing the specific behaviors of Staff-type weapons.
 *
 * @author Rodrigo Oportot.
 */
public class StaffTest extends AbstractWeaponTest {

    private Staff testStaff;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        testStaff = new Staff("Test Staff", 15, 5, 10);
    }

    @Test
    public void StaffConstructorTest() {
        Staff anotherStaff = new Staff("Test Staff", 15, 5, 10);
        Staff differentStaff = new Staff("Different Staff", 12, 2, 3);

        checkEquals(anotherStaff, testStaff);
        checkNotEquals(differentStaff, testStaff);

        /** Checks if the weapons are in the list of the super class **/
        checkList(testStaff);
        checkList(anotherStaff);
        assertFalse(testWeapons.contains(differentStaff));
    }
}
