package com.github.cc3002.finalreality.model.character.playablecharactertests;

import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.AbstractPlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ICommonCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.weapon.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Set of tests for the Common characters of the game.
 *
 * Since there aren't any specialized behaviors for the common
 * characters yet, this testing class will remain kinda empty for now,
 * acting as a placeholder. It will test the constructors.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class CommonCharacterTest extends AbstractPlayerCharacterTest {

    private KnightCharacter testKnight;
    private ThiefCharacter testThief;
    private EngineerCharacter testEngineer;
    private List<ICommonCharacter> testCommonChars;

    /**
     * Set ups common-class characters and adds them to a list.
     */
    @BeforeEach
    public void setUp() {
        super.setUp();

        testCommonChars = new ArrayList<>();

        testKnight = new KnightCharacter(KNIGHT_NAME, 10, 5, turns);
        testEngineer = new EngineerCharacter(ENGINEER_NAME, 10, 5, turns);
        testThief = new ThiefCharacter(THIEF_NAME, 10, 5, turns);

        testCommonChars.add(testKnight);
        testCommonChars.add(testEngineer);
        testCommonChars.add(testThief);
    }

    /**
     * Tests the isMage method.
     * Only common characters.
     */
    @Test
    public void isMageTest() {
        for (var playableChar : testCommonChars) {
            assertFalse(playableChar.isMage());
        }
    }

    @Override
    @Test
    public void attackTest() {
        //we set some weapons and an enemy for testing purposes
        super.weaponSetUp();
        var targetPractice = new Enemy("Target", 50, 3, 3, 10, turns);

        //common characters attack weaponless (null weapon equipped)
        assertTrue(testKnight.attack(targetPractice));
        assertTrue(testEngineer.attack(targetPractice));
        assertTrue(testThief.attack(targetPractice));
        assertEquals(50, targetPractice.getCurrentHP());
        assertFalse(targetPractice.isKO());

        //with adequate weapons equipped
        //all have 10 dmg, target has 3 def
        testKnight.equip(testSword);
        testEngineer.equip(testAxe);
        testThief.equip(testKnife);

        assertTrue(testKnight.attack(targetPractice));
        assertEquals(43, targetPractice.getCurrentHP());
        assertFalse(targetPractice.isKO());

        assertTrue(testEngineer.attack(targetPractice));
        assertEquals(36, targetPractice.getCurrentHP());
        assertFalse(targetPractice.isKO());

        assertTrue(testThief.attack(targetPractice));
        assertEquals(29, targetPractice.getCurrentHP());
        assertFalse(targetPractice.isKO());

        //they can't attack themselves
        assertFalse(testKnight.attack(testKnight));
        assertFalse(testEngineer.attack(testEngineer));
        assertFalse(testThief.attack(testThief));

        //they attack each other for testing purposes
        assertTrue(testKnight.attack(testEngineer));
        assertEquals(5, testEngineer.getCurrentHP());
        assertFalse(testEngineer.isKO());

        assertTrue(testEngineer.attack(testThief));
        assertEquals(5, testThief.getCurrentHP());
        assertFalse(testThief.isKO());

        assertTrue(testThief.attack(testKnight));
        assertEquals(5, testKnight.getCurrentHP());
        assertFalse(testKnight.isKO());

    }

    /**
     * Test for the equip and getEquippedWeapon method. This requires instancing
     * the character as IPlayerCharacters and not as an ICharacter type, since the last one
     * doesn't have the equip() and getEquippedWeapon() methods.
     * The equip mechanic implements a double dispatch design. The characters equip a weapon,
     * then call the respective equipToClass() methods inside the weapon's classes to check
     * if the character can actually equip said weapon.
     * It tests the common classes.
     */
    @Override
    @Test
    public void equipWeaponTest() {
        super.weaponSetUp();
        //all characters can equip a null weapon

        //Knight characters can only equip swords, axes and knives.
        assertEquals(testNullWeapon, testKnight.getEquippedWeapon());

        testKnight.equip(testSword);
        assertEquals(testSword, testKnight.getEquippedWeapon());

        testKnight.equip(testAxe);
        assertEquals(testAxe, testKnight.getEquippedWeapon());

        testKnight.equip(testKnife);
        assertEquals(testKnife, testKnight.getEquippedWeapon());

        testKnight.equip(testStaff);
        assertEquals(testKnife, testKnight.getEquippedWeapon());

        testKnight.equip(testBow);
        assertEquals(testKnife, testKnight.getEquippedWeapon());

        //Thief characters can only equip swords, knives and bows.
        assertEquals(testNullWeapon, testThief.getEquippedWeapon());

        testThief.equip(testSword);
        assertEquals(testSword, testThief.getEquippedWeapon());

        testThief.equip(testKnife);
        assertEquals(testKnife, testThief.getEquippedWeapon());

        testThief.equip(testBow);
        assertEquals(testBow, testThief.getEquippedWeapon());

        testThief.equip(testAxe);
        assertEquals(testBow, testThief.getEquippedWeapon());

        testThief.equip(testStaff);
        assertEquals(testBow, testThief.getEquippedWeapon());

        //Engineer characters can only equip axes and bows.
        assertEquals(testNullWeapon, testEngineer.getEquippedWeapon());

        testEngineer.equip(testAxe);
        assertEquals(testAxe, testEngineer.getEquippedWeapon());

        testEngineer.equip(testBow);
        assertEquals(testBow, testEngineer.getEquippedWeapon());

        testEngineer.equip(testSword);
        assertEquals(testBow, testEngineer.getEquippedWeapon());

        testEngineer.equip(testKnife);
        assertEquals(testBow, testEngineer.getEquippedWeapon());

        testEngineer.equip(testStaff);
        assertEquals(testBow, testEngineer.getEquippedWeapon());

    }

    /**
     * Constructor test, checks one by one the instances of the three characters.
     */
    @Override
    @Test
    public void constructorTest() {
       Enemy testEnemy = new Enemy("Enemy", 10, 8, 5, 10, turns);

       for(var commonChar : testCommonChars) {
           checkConstruction(getSpecificIPlayerCharacter(commonChar.getName(), commonChar.getHP(),
                   commonChar.getDef(), turns),
                   commonChar,
                   getSpecificIPlayerCharacter(commonChar.getName(), 5, 4, turns),
                   testEnemy);
       }
    }
}
