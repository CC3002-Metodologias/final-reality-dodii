package com.github.cc3002.finalreality.model.character.playablecharactertests;

import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.BlackMageCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.WhiteMageCharacter;
import com.github.dodii.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Set of tests for the Abstract Mage character class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class AbstractMageCharacterTest extends AbstractPlayerCharacterTest {

    protected WhiteMageCharacter whiteMage;
    protected BlackMageCharacter blackMage;

    protected final static int MANA = 5;

    /**
     * Sets up a white and a black mage for testing purposes.
     */
    @BeforeEach
    public void setUp() {
        super.setUp();

        whiteMage = new WhiteMageCharacter(WHITE_MAGE_NAME, HP, DEFENSE, MANA, turns);
        blackMage = new BlackMageCharacter(BLACK_MAGE_NAME, HP, DEFENSE, MANA, turns);
    }

    /**
     * Mages constructor Test.
     */
    @Override
    @Test
    public void constructorTest() {
        var enemy = new Enemy("Enemy", 10, 3, 3, 3, turns);

        checkConstruction(new WhiteMageCharacter(WHITE_MAGE_NAME, HP, DEFENSE, MANA, turns),
                whiteMage,
                new WhiteMageCharacter("Different White Mage", 8, DEFENSE, MANA, turns),
                enemy);

        checkConstruction(new BlackMageCharacter(BLACK_MAGE_NAME, HP, DEFENSE, MANA, turns),
                blackMage,
                new BlackMageCharacter("Different Black Mage", 8, DEFENSE, MANA, turns),
                enemy);
    }

    /**
     * Tests the HP related methods by calling the super class checkCurrentHP()
     * method.
     */
    @Override
    @RepeatedTest(5)
    public void testHP() {
        checkCurrentHP(blackMage);
        checkCurrentHP(whiteMage);
    }

    /**
     * waitTurn() test, with Mages instances, with and without staff equipped.
     */
    @Override
    @RepeatedTest(3)
    public void waitTurnTest() {
        //weaponless
        checkWaitTurn(whiteMage);
        turns.clear();

        checkWaitTurn(blackMage);
        turns.clear();

        //weapon equipped of weight = 10
        Staff localStaff = new Staff("Staff", 15, 10, 10);
        whiteMage.equip(localStaff);
        blackMage.equip(localStaff);

        checkWaitTurn(whiteMage);
        turns.clear();

        checkWaitTurn(blackMage);
        turns.clear();
    }

    /**
     * Test for the equip and getEquippedWeapon method. This requires instancing
     * the character as IPlayerCharacters and not as an ICharacter type, since the last one
     * doesn't have the equip() and getEquippedWeapon() methods.
     * It tests the common classes.
     */
    @Override
    public void equipWeaponTest() {
        super.weaponSetUp();
    }
}
