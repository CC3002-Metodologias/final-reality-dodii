package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.Axe;
import com.github.dodii.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the specific behaviors of Staff-type weapons.
 *
 * @author Rodrigo Oportot.
 */
public class StaffTest extends AbstractWeaponTest {

    private Staff testStaff;

    /**
     * Sets up a staff. This constructor is required since the present code of
     * staffs is different from the other weapons.
     */
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        testStaff = new Staff("Test Staff", 15, 5, 10);
    }

    /**
     * Staff constructor test.
     * Creates various amounts of weapons to test.
     */
    @Test
    public void StaffConstructorTest() {
        Staff anotherStaff = new Staff("Test Staff", 15, 5, 10);
        Staff differentStaff = new Staff("Different Staff", 12, 2, 3);
        Staff otherStaff = new Staff("Other Staff", 15, 5, 2);
        Axe totallyDifferentWeapon = new Axe("Test Staff", 15, 10);

        checkEquals(anotherStaff, testStaff);
        checkNotEquals(differentStaff, testStaff);
        checkNotEquals(otherStaff, testStaff);
        checkNotEquals(totallyDifferentWeapon, testStaff);
        checkNotEquals(new Staff("Testing name staff", 15,
                5, 10), testStaff);
        checkNotEquals(new Staff ("Test Staff", 15,
                1, 10), testStaff);

        /** Checks if the weapons are in the list of the super class **/
        checkList(testStaff);
        checkList(anotherStaff);
        assertFalse(testWeapons.contains(differentStaff));
    }
}
