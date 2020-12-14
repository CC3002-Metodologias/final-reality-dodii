package com.github.cc3002.finalreality.model.controller;

import com.github.dodii.finalreality.controller.Controller;
import com.github.dodii.finalreality.controller.GameStatus;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.enemycharacters.IEnemy;
import com.github.dodii.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.BlackMageCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.WhiteMageCharacter;
import com.github.dodii.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the controller of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class ControllerTest {

    private Controller controller;

    private List<IPlayerCharacter> testParty;
    private List<IEnemy> enemyTestList;

    private EngineerCharacter testEngineer;
    private KnightCharacter testKnight;
    private ThiefCharacter testThief;
    private BlackMageCharacter testBlackMage;
    private WhiteMageCharacter testWhiteMage;
    private Enemy testEnemy;

    private BlockingQueue<ICharacter> turns;

    private Axe testAxe;
    private Bow testBow;
    private Knife testKnife;
    private Staff testStaff;
    private Sword testSword;

    /**
     * Initial setup
     */
    @BeforeEach
    public void setUp() {
        this.controller = new Controller();
        this.turns = new LinkedBlockingDeque<>();

        this.testParty = new ArrayList<>(4);
        this.enemyTestList = new ArrayList<>(8);
    }

    /**
     * Set ups some characters for testing purposes
     */
    @BeforeEach
    public void charactersSetUp() {
        testEngineer = new EngineerCharacter("Test Engineer", 10, 10, turns);
        testKnight = new KnightCharacter("Test Knight", 10, 10, turns);
        testThief = new ThiefCharacter("Test Thief", 10, 10, turns);
        testBlackMage = new BlackMageCharacter("Test Black Mage", 10, 10,
                10, turns);
        testWhiteMage = new WhiteMageCharacter("Test White Mage", 10, 10,
                10, turns);
        testEnemy = new Enemy("Test Enemy", 10, 10, 10, 10, turns);
    }

    /**
     * Set ups some weapons for testing purposes
     */
    @BeforeEach
    public void weaponsSetUp() {
        testAxe = new Axe("Test Axe", 10, 10);
        testBow = new Bow("Test Bow", 10, 10);
        testKnife = new Knife("Test Knife", 10, 10);
        testStaff = new Staff("Test Staff", 10, 10, 10);
        testSword = new Sword("Test Sword", 10, 10);
    }

    /**
     * Tests the createClassCharacter methods.
     * Checks they are in the list.
     */
    @Test
    public void createCharactersTest() {
        var localEngineer = controller.createEngineer("Test Engineer", 10, 10, turns);
        assertEquals(1, controller.getPlayerParty().size());

        //this new engineer won't be added, since there is an identical instance
        //of it already in the party
        var anotherEngineer = controller.createEngineer("Test Engineer", 10, 10, turns);
        assertEquals(1, controller.getPlayerParty().size());

        var localKnight = controller.createKnight("Test Knight", 10, 10, turns);
        assertEquals(2, controller.getPlayerParty().size());

        var localThief = controller.createThief("Test Thief", 10, 10, turns);
        assertEquals(3, controller.getPlayerParty().size());

        var localBlackMage = controller.createBlackMage("Test Black Mage", 10,
                10, 10, turns);
        assertEquals(4, controller.getPlayerParty().size());

        var localWhiteMage = controller.createWhiteMage("Test White Mage", 10,
                10, 10, turns);
        assertEquals(4, controller.getPlayerParty().size());

        var localEnemy = controller.createEnemy("Test Enemy", 10, 10,
                10, 10, turns);
        assertEquals(1, controller.getEnemiesList().size());
        //this new ennemy won't be added, since there is an identical instance
        //of it already in the party
        var anotherEnemy = controller.createEnemy("Test Enemy", 10, 10,
                10, 10, turns);
        assertEquals(1, controller.getEnemiesList().size());

        assertEquals(testEngineer, localEngineer);
        assertTrue(controller.getPlayerParty().contains(localEngineer));
        testParty.add(localEngineer);

        assertEquals(testKnight, localKnight);
        assertTrue(controller.getPlayerParty().contains(localKnight));
        testParty.add(localKnight);

        assertEquals(testThief, localThief);
        assertTrue(controller.getPlayerParty().contains(localThief));
        testParty.add(localThief);

        assertEquals(testBlackMage, localBlackMage);
        assertTrue(controller.getPlayerParty().contains(localBlackMage));
        testParty.add(localBlackMage);

        //doesn't contains this character, since the limit is 4 characters per player
        assertEquals(testWhiteMage, localWhiteMage);
        assertFalse(controller.getPlayerParty().contains(localWhiteMage));

        assertEquals(testParty, controller.getPlayerParty());

        assertEquals(testEnemy, localEnemy);
        assertTrue(controller.getEnemiesList().contains(localEnemy));
        enemyTestList.add(localEnemy);

        assertEquals(enemyTestList, controller.getEnemiesList());
    }

    /**
     * Tests the createWeapon methods.
     * Doesn't test the NullWeapon object, since the player won't have the
     * chance to create an instance of it.
     */
    @Test
    public void createWeapons() {
        assertEquals(testAxe, controller.createAxe("Test Axe", 10, 10));
        assertEquals(testBow, controller.createBow("Test Bow", 10, 10));
        assertEquals(testKnife, controller.createKnife("Test Knife", 10, 10));
        assertEquals(testStaff, controller.createStaff("Test Staff", 10, 10, 10));
        assertEquals(testSword, controller.createSword("Test Sword", 10, 10));
    }

    /**
     * Tests the getters of the character's parameters, such as its name,
     * stats, etc.
     */
    @Test
    public void gettersTest() {
        assertEquals("Test Engineer", controller.getCharacterName(testEngineer));
        assertEquals(10, controller.getCharacterHP(testEngineer));
        assertEquals(10, controller.getCharacterCurrentHP(testEngineer));
        assertEquals(10, controller.getCharacterDef(testEngineer));
        assertEquals(10, controller.getCharacterMana(testBlackMage));
        assertEquals(NullWeapon.uniqueInstance(), controller.getCharacterWeapon(testEngineer));

        assertEquals("Test Enemy", controller.getCharacterName(testEnemy));
        assertEquals(10, controller.getEnemyAtk(testEnemy));
        assertEquals(10, controller.getEnemyWeight(testEnemy));

    }

    /**
     * Tests the equip and unequip methods.
     */
    @Test
    public void equipAndUnequipTest() {
        //doesn't equip since it's not in the inventory yet
        controller.equipWeaponToCharacter(testAxe, testEngineer);
        assertEquals(NullWeapon.uniqueInstance(), testEngineer.getEquippedWeapon());

        //we add an axe to the inventory and equip it to the engineer
        controller.addWeaponToInventory(testAxe);
        assertTrue(controller.getInventory().contains(testAxe));
        controller.equipWeaponToCharacter(testAxe, testEngineer);
        //we check the engineer actually equipped the weapon and
        //the inventory no longer stores it
        assertEquals(testAxe, testEngineer.getEquippedWeapon());
        assertFalse(controller.getInventory().contains(testAxe));

        //we check the test axe cannot be equipped to a different character,
        //since it's not in the inventory
        controller.equipWeaponToCharacter(testAxe, testKnight);
        assertEquals(NullWeapon.uniqueInstance(), controller.getCharacterWeapon(testKnight));

        //we unequip the axe of the engineer
        controller.unequipCharacter(testEngineer);
        assertTrue(controller.getInventory().contains(testAxe));
        assertEquals(NullWeapon.uniqueInstance(), testEngineer.getEquippedWeapon());
    }

    /**
     * Set of tests for the attack() method
     */
    @Test
    public void attackTest() {
        controller.addWeaponToInventory(testAxe);
        controller.equipWeaponToCharacter(testAxe, testEngineer);
        var dummy = new Enemy("Dummy", 100, 12, 3, 10, turns);

        controller.characterAttacksCharacter(testEngineer, dummy);
        assertEquals(93, controller.getCharacterCurrentHP(dummy));

        controller.characterAttacksCharacter(dummy, testEngineer);
        assertEquals(8, controller.getCharacterCurrentHP(testEngineer));

        var testKnife2 = new Knife("Knife", 12, 5);
        controller.addWeaponToInventory(testKnife2);
        controller.equipWeaponToCharacter(testKnife2, testThief);

        controller.characterAttacksCharacter(testThief, testEngineer);
        assertEquals(6, controller.getCharacterCurrentHP(testEngineer));

        var dummy2 = new Enemy("Dummy2", 100, 5, 5, 10, turns);
        controller.characterAttacksCharacter(dummy2, dummy);
        assertEquals(91, controller.getCharacterCurrentHP(dummy));
    }

    /**
     * Test for the defeat method.
     * Tests the notification of knockOut Handler.
     * A party of characters get killed by a single enemy, so
     * the defeat notification gets triggered.
     */
    @Test
    public void defeatTest() {
        var localEngineer = controller.createEngineer("Test Engineer", 10,
                10, turns);
        var localKnight = controller.createKnight("Test Knight", 10,
                10, turns);
        var localThief = controller.createThief("Test Thief", 10, 10,
                turns);
        var localBlackMage = controller.createBlackMage("Test Black Mage", 10,
                10, 10, turns);

        var localBoss = controller.createEnemy("Invincible Boss", 100, 50,
                50, 1, turns);

        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        //all player characters die.
        controller.characterAttacksCharacter(localBoss, localEngineer);
        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        controller.characterAttacksCharacter(localBoss, localKnight);
        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        controller.characterAttacksCharacter(localBoss, localThief);
        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        //Here, all the party died, so the notification must have gotten
        //triggered and the controller should have changed its status
        //to defeat.
        controller.characterAttacksCharacter(localBoss, localBlackMage);
        assertEquals(GameStatus.DEFEAT, controller.getGameStatus());
    }

    /**
     * Test for the victory method and onKnockedOutCharacter method.
     * Tests the notification of knockOut Handler.
     * A party of characters destroy all enemies.
     * There will be only 4 enemies in this case.
     */
    @Test
    public void victoryTest() {
        weaponsSetUp();
        var localEngineer = controller.createEngineer("Test Engineer", 10,
                10, turns);
        controller.addWeaponToInventory(testAxe);
        controller.equipWeaponToCharacter(testAxe, localEngineer);

        var localKnight = controller.createKnight("Test Knight", 10,
                10, turns);
        controller.addWeaponToInventory(testSword);
        controller.equipWeaponToCharacter(testSword, localKnight);

        var localThief = controller.createThief("Test Thief", 10, 10,
                turns);
        controller.addWeaponToInventory(testKnife);
        controller.equipWeaponToCharacter(testKnife, localThief);

        var localBlackMage = controller.createBlackMage("Test Black Mage", 10,
                10, 10, turns);
        controller.addWeaponToInventory(testStaff);
        controller.equipWeaponToCharacter(testStaff, localBlackMage);

        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        var enemy1 = controller.createEnemy("Enemy 1", 1, 1,
                1, 1, turns);
        var enemy2 = controller.createEnemy("Enemy 2", 1, 1,
                1, 1, turns);
        var enemy3 = controller.createEnemy("Enemy 3", 1, 1,
                1, 1, turns);
        var enemy4 = controller.createEnemy("Enemy 4", 1, 1,
                1, 1, turns);

        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        //all enemies characters die.
        controller.characterAttacksCharacter(localEngineer, enemy1);
        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        controller.characterAttacksCharacter(localKnight, enemy2);
        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        controller.characterAttacksCharacter(localThief, enemy3);
        assertEquals(GameStatus.PLAYING, controller.getGameStatus());

        //Here, all the enemies died, so the notification must have gotten
        //triggered and the controller should have changed its status
        //to victory
        controller.characterAttacksCharacter(localBlackMage, enemy4);
        assertEquals(GameStatus.VICTORY, controller.getGameStatus());
    }

    /**
     * Tests the correct functioning of the start
     * and end notifications of the character's turns.
     */
    @Test
    public void endAndStartTurnTest() throws InterruptedException {
        //a heavy weight weapon for testing purposes
        var heavyAxe = controller.createAxe("Heavy Axe",
                10, 50);
        //we create a test character and assign it the actual queue
        //of the controller, the one that will be used in the game.
        var queueEngineer = controller.createEngineer("Queue " +
                "Engineer", 10, 10, controller.getTurnsQueue());

        controller.equipWeaponToCharacter(heavyAxe, queueEngineer);

        //the character will be added to the queue manually
        controller.getTurnsQueue().add(queueEngineer);
        assertTrue(controller.getTurnsQueue().contains(queueEngineer));

        //now, the character will manually attack, and the controller
        //should get notified that its turn ended.
        var dummy = controller.createEnemy("Dummy", 10, 10,
                10, 10, controller.getTurnsQueue());

        controller.characterAttacksCharacter(queueEngineer, dummy);

        //the controller should have popped the character from the queue
        //and executed the waitTurn() method
        assertFalse(controller.getTurnsQueue().contains(queueEngineer));

        //now it must be waiting, we will make the thread sleep to ensure
        //the waiting time has passed
        Thread.sleep(6000);

        //now, the executed timer should have ended and the controller
        //should have gotten notified that the queue is not empty
        assertTrue(controller.getTurnsQueue().contains(queueEngineer));

    }

}
