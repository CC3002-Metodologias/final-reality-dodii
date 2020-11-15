package com.github.cc3002.finalreality.model.character.playablecharactertests;

import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.AbstractPlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ICommonCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Override
    public void attackTest() {

    }

    @Override
    public void calculateDamageTest() {

    }

    /**
     * Test for the equip and getEquippedWeapon method. This requires instancing
     * the character as IPlayerCharacters and not as an ICharacter type, since the last one
     * doesn't have the equip() and getEquippedWeapon() methods.
     * It tests the common classes.
     */
    @Override
    @Test
    public void equipWeaponTest() {
        super.weaponSetUp();

    }

    /**
     * Constructor test, checks one by one the instances of the three common-class'
     * above characters.
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
