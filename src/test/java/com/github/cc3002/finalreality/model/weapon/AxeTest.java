package com.github.cc3002.finalreality.model.weapon;

import com.github.dodii.finalreality.model.weapon.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the specific behaviors of Axe-type weapons.
 *
 * @author Rodrigo Oportot.
 */
public class AxeTest extends AbstractWeaponTest {

    private Axe testAxe;

    /**
     * Set up for an Axe.
     */
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        testAxe = new Axe("Test Axe", 15, 10);
    }


    /**
     * Axe constructor Test.
     */
    @Test
    public void AxeConstructorTest() {
        //super.constructorTest();
        Axe anotherAxe = new Axe("Test Axe", 15, 10);
        Axe differentAxe = new Axe("Different Axe", 12, 3);

        checkEquals(anotherAxe, testAxe);
        checkNotEquals(differentAxe, testAxe);

        /** Checks if the weapons are in the list of the super class **/
        checkList(testAxe);
        checkList(anotherAxe);
        assertFalse(testWeapons.contains(differentAxe));
    }


}
