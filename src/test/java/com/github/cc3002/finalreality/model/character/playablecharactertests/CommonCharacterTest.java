package com.github.cc3002.finalreality.model.character.playablecharactertests;

import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ICommonCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
public class CommonCharacterTest extends PlayerCharacterTest {

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

        testKnight = new KnightCharacter("Test Knight", 10, 5, turns);
        testEngineer = new EngineerCharacter("Test Engineer", 10, 5, turns);
        testThief = new ThiefCharacter("Test Thief", 10, 5, turns);

        testCommonChars.add(testKnight);
        testCommonChars.add(testEngineer);
        testCommonChars.add(testThief);
    }

    /**
     * Constructor test, checks one by one the instances of the three common-class'
     * characters above.
     */
    @Override
    @Test
    public void constructorTest() {
       Enemy testEnemy = new Enemy("Enemy", 10, 8, 5, 10, turns);

       for(var commonChar : testCommonChars) {
           checkConstruction(new PlayerCharacter(commonChar.getName(), commonChar.getHP(),
                   commonChar.getDef(), commonChar.getCharacterClass(), turns),
                   commonChar,
                   new PlayerCharacter("Same Class", 5, 4,
                           commonChar.getCharacterClass(), turns),
                   testEnemy);
       }
    }
}
