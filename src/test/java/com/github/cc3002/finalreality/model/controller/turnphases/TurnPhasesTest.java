package com.github.cc3002.finalreality.model.controller.turnphases;

import com.github.dodii.finalreality.controller.Controller;
import com.github.dodii.finalreality.controller.turnphases.*;
import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidActionException;
import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidTransitionException;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.weapon.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class for testing the Phases classes.
 * Tested mainly through the controller.
 *
 * Also tests the exceptions.
 *
 * @author Rodrigo Oportot.
 */
public class TurnPhasesTest {
    private Phase testPhase;
    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
    }

    /**
     * Tests the correct binding of a controller to
     * the phases' classes.
     */
    @Test
    public void setControllerTest() {
        var phase = new StartPhase();
        phase.setController(controller);

        assertEquals(controller, phase.getController());

        var anotherPhase = new SelectingActionPhase();
        controller.setPhase(anotherPhase);
        assertEquals(controller, anotherPhase.getController());
        assertEquals(anotherPhase, controller.getCurrentPhase());
    }

    /**
     * Checks the basic methods of the classes.
     */
    @Test
    public void creationTests() {
        var startPhase = new StartPhase();

        assertEquals("Start Phase",
                startPhase.toString());
        assertTrue(startPhase.isStartPhase());
        assertFalse(startPhase.isSelectingActionPhase());
        assertFalse(startPhase.isAttackPhase());
        assertFalse(startPhase.isEndPhase());
        assertFalse(startPhase.isWaitingQueuePhase());

        var selectingPhase = new SelectingActionPhase();

        assertEquals("Selecting Action Phase",
                selectingPhase.toString());
        assertTrue(selectingPhase.isSelectingActionPhase());
        assertFalse(selectingPhase.isStartPhase());
        assertFalse(selectingPhase.isAttackPhase());
        assertFalse(selectingPhase.isEndPhase());
        assertFalse(selectingPhase.isWaitingQueuePhase());

        var dummy1 = new EngineerCharacter("Dummy", 10, 10,
                controller.getTurnsQueue());
        var dummy2 = new Enemy("Dummy", 10, 10, 10, 10,
                controller.getTurnsQueue());
        var attackPhase = new AttackPhase(dummy1, dummy2);

        assertEquals("Attack Phase",
                attackPhase.toString());
        assertTrue(attackPhase.isAttackPhase());
        assertFalse(attackPhase.isStartPhase());
        assertFalse(attackPhase.isSelectingActionPhase());
        assertFalse(attackPhase.isEndPhase());
        assertFalse(attackPhase.isWaitingQueuePhase());

        var endPhase = new EndPhase();

        assertEquals("End Phase",
                endPhase.toString());
        assertTrue(endPhase.isEndPhase());
        assertFalse(endPhase.isStartPhase());
        assertFalse(endPhase.isSelectingActionPhase());
        assertFalse(endPhase.isAttackPhase());
        assertFalse(endPhase.isWaitingQueuePhase());

        var waitingPhase = new WaitingQueuePhase();

        assertEquals("Waiting Queue Phase",
                waitingPhase.toString());
        assertTrue(waitingPhase.isWaitingQueuePhase());
        assertFalse(waitingPhase.isStartPhase());
        assertFalse(waitingPhase.isSelectingActionPhase());
        assertFalse(waitingPhase.isAttackPhase());
        assertFalse(waitingPhase.isEndPhase());
    }

    /**
     * Testing the correct transitions between phases.
     * Transitions exceptions handled.
     */
    @Test
    public void transitionsTests() throws InvalidActionException,
            InvalidTransitionException,
            InterruptedException {
        //first
        var phase = new StartPhase();
        phase.setController(controller);

        InvalidTransitionException e1 = assertThrows(InvalidTransitionException.class, () ->
                phase.toStartPhase());
        InvalidTransitionException e2 = assertThrows(InvalidTransitionException.class, () ->
                phase.toAttackPhase());
        InvalidTransitionException e3 = assertThrows(InvalidTransitionException.class, () ->
                phase.toEndPhase());
        InvalidTransitionException e4 = assertThrows(InvalidTransitionException.class, () ->
                phase.toWaitingQueuePhase());

        //first, we test it cannot change to incoherent phases.
        assertTrue(e1.getMessage().contains("Can't change from " + phase.toString() +
                " to Start Phase."));
        assertTrue(e2.getMessage().contains("Can't change from " + phase.toString() +
                " to Attack Phase."));
        assertTrue(e3.getMessage().contains("Can't change from " + phase.toString() +
                " to End Phase."));
        assertTrue(e4.getMessage().contains("Can't change from " + phase.toString() +
                " to Waiting Queue Phase."));

        //now, we check it can pass to the selecting action phase
        phase.toSelectingActionPhase();
        //the new phase in the controller should have a different name
        assertEquals("Selecting Action Phase",
                controller.getCurrentPhase().toString());

        //now, we will test with the next phase
        var phase2 = new SelectingActionPhase();
        phase2.setController(controller);

        InvalidTransitionException e5 = assertThrows(InvalidTransitionException.class, () ->
                phase2.toStartPhase());
        InvalidTransitionException e6 = assertThrows(InvalidTransitionException.class, () ->
                phase2.toSelectingActionPhase());
        InvalidTransitionException e7 = assertThrows(InvalidTransitionException.class, () ->
                phase2.toEndPhase());
        InvalidTransitionException e8 = assertThrows(InvalidTransitionException.class, () ->
                phase2.toWaitingQueuePhase());

        //first, we test it cannot change to incoherent phases.
        assertTrue(e5.getMessage().contains("Can't change from " + phase2.toString() +
                " to Start Phase."));
        assertTrue(e6.getMessage().contains("Can't change from " + phase2.toString() +
                " to Selecting Action Phase."));
        assertTrue(e7.getMessage().contains("Can't change from " + phase2.toString() +
                " to End Phase."));
        assertTrue(e8.getMessage().contains("Can't change from " + phase2.toString() +
                " to Waiting Queue Phase."));

        //it can advance to attack phase, we will pass some dummies to it
        //will be selected as a player, for quick testing (only here, the
        //other part will, of course, be tested too)
        var dummy1 = controller.createEngineer("Dummy1", 100, 100,
                controller.getTurnsQueue());
        var dummy2 = new Enemy("Dummy2", 100, 100, 100, 100,
                controller.getTurnsQueue());
        phase2.receivePlayerCharacter(dummy1);
        //we will equip a heavy weapon
        var axe = controller.createAxe("Axe", 1, 20);
        phase2.equipWeapon(axe);
        phase2.selectAndAttackTarget(dummy2);

        //now, can advance to attack phase
        assertEquals("Attack Phase",
                controller.getCurrentPhase().toString());

        //the next phase
        var phase3 = new AttackPhase(dummy1, dummy2);
        controller.setPhase(phase3);

        InvalidTransitionException e9 = assertThrows(InvalidTransitionException.class, () ->
                phase3.toStartPhase());
        InvalidTransitionException e10 = assertThrows(InvalidTransitionException.class, () ->
                phase3.toSelectingActionPhase());
        InvalidTransitionException e11 = assertThrows(InvalidTransitionException.class, () ->
                phase3.toAttackPhase());
        InvalidTransitionException e12 = assertThrows(InvalidTransitionException.class, () ->
                phase3.toWaitingQueuePhase());

        assertTrue(e9.getMessage().contains("Can't change from " + phase3.toString() +
                " to Start Phase."));
        assertTrue(e10.getMessage().contains("Can't change from " + phase3.toString() +
                " to Selecting Action Phase."));
        assertTrue(e11.getMessage().contains("Can't change from " + phase3.toString() +
                " to Attack Phase."));
        assertTrue(e12.getMessage().contains("Can't change from " + phase3.toString() +
                " to Waiting Queue Phase."));

        //the attack takes place, it can advance to end phase.
        controller.getCurrentPhase().attack(dummy2);
        assertEquals("End Phase", controller.getCurrentPhase().toString());

        //we set another end phase manually for testing purposes
        var phase4 = new EndPhase();
        phase4.setController(controller);

        InvalidTransitionException e13 = assertThrows(InvalidTransitionException.class, () ->
                phase4.toSelectingActionPhase());
        InvalidTransitionException e14 = assertThrows(InvalidTransitionException.class, () ->
                phase4.toAttackPhase());
        InvalidTransitionException e15 = assertThrows(InvalidTransitionException.class, () ->
                phase4.toEndPhase());

        assertTrue(e13.getMessage().contains("Can't change from " + phase4.toString() +
                " to Selecting Action Phase."));
        assertTrue(e14.getMessage().contains("Can't change from " + phase4.toString() +
                " to Attack Phase."));
        assertTrue(e15.getMessage().contains("Can't change from " + phase4.toString() +
                " to End Phase."));

        //first case, empty queue in the controller, so proceeds to waiting queue phase.
        phase4.endTurn();

        assertEquals("Waiting Queue Phase", controller.getCurrentPhase().toString());

        //another case, queue not empty
        //first we add a character to the controller's queue.

        var dummy3 = controller.createEnemy("Dummy3", 1, 1, 1, 0,
                controller.getTurnsQueue());
        var phase4_1 = new EndPhase();
        controller.setPhase(phase4_1);

        //we add the character to the queue manually
        dummy3.waitTurn();
        Thread.sleep(100);
        phase4_1.endTurn();

        assertEquals("Start Phase", controller.getCurrentPhase().toString());

        //lastly, the waiting queue phase
        var phase5 = new WaitingQueuePhase();
        phase5.setController(controller);

        InvalidTransitionException e16 = assertThrows(InvalidTransitionException.class, () ->
                phase5.toSelectingActionPhase());
        InvalidTransitionException e17 = assertThrows(InvalidTransitionException.class, () ->
                phase5.toAttackPhase());
        InvalidTransitionException e18 = assertThrows(InvalidTransitionException.class, () ->
                phase5.toEndPhase());
        InvalidTransitionException e19 = assertThrows(InvalidTransitionException.class, () ->
                phase5.toWaitingQueuePhase());

        assertTrue(e16.getMessage().contains("Can't change from " + phase5.toString() +
                " to Selecting Action Phase."));
        assertTrue(e17.getMessage().contains("Can't change from " + phase5.toString() +
                " to Attack Phase."));
        assertTrue(e18.getMessage().contains("Can't change from " + phase5.toString() +
                " to End Phase."));
        assertTrue(e19.getMessage().contains("Can't change from " + phase5.toString() +
                " to Waiting Queue Phase."));

        phase5.toStartPhase();

        assertEquals("Start Phase", controller.getCurrentPhase().toString());

    }

    /**
     * Testing process that exemplifies a simple turn in the game.
     */
    @Test
    public void phasesFlowTest() throws InterruptedException, InvalidTransitionException, InvalidActionException {
        //we will set two characters in the queue
        var engineer = controller.createEngineer("Engi 1", 10, 10,
                controller.getTurnsQueue());
        var dummy = controller.createEnemy("Dummy", 10, 1, 5,
                10, controller.getTurnsQueue());

        //the character will have an equipped weapon already
        var axe = controller.createAxe("Axe", 4, 5);
        controller.equipWeaponToCharacter(axe, engineer);

        engineer.waitTurn();
        dummy.waitTurn();
        Thread.sleep(1000);

        //we are in start phase, so we can proceed to the next phase
        //the engineer should be first in the queue
        assertEquals("Start Phase", controller.getCurrentPhase().toString());
        controller.toSelectingActionPhase();

        //now, we are in selecting action phase, and the method
        //receivePlayerCharacter should have been called by the controller.
        //we will also equip a different weapon to the engineer
        assertEquals("Selecting Action Phase",
                controller.getCurrentPhase().toString());
        var anotherAxe = controller.createAxe("Axe2", 1, 2);
        controller.getCurrentPhase().equipWeapon(anotherAxe);
        assertEquals(anotherAxe, engineer.getEquippedWeapon());
        controller.getCurrentPhase().selectAndAttackTarget(dummy);

        //now, we are in attack phase. The engineer will attack
        //the enemy
        assertEquals("Attack Phase",
                controller.getCurrentPhase().toString());
        controller.getCurrentPhase().attack(dummy);

        //now, we are in end phase. Then, the phase will end the turn
        //and transition to start phase, since the queue's not empty.
        //One cycle is complete.
        assertEquals("End Phase",
                controller.getCurrentPhase().toString());

        controller.getCurrentPhase().endTurn();
        assertEquals("Start Phase",
                controller.getCurrentPhase().toString());
    }

    /**
     * Set of tests to handle the action exceptions.
     */
    @Test
    public void actionExceptionsTest() {
        var testWeapon = new Axe("Test", 1, 1);
        var testEngineer = controller.createEngineer("Test",
                10, 10, controller.getTurnsQueue());
        var enemy = controller.createEnemy("Enemy", 10, 10,
                10, 10, controller.getTurnsQueue());

        var startPhase = new StartPhase();


        InvalidActionException e1 = assertThrows(InvalidActionException.class, () ->
                startPhase.endTurn());
        InvalidActionException e2 = assertThrows(InvalidActionException.class, () ->
                startPhase.equipWeapon(testWeapon));
        InvalidActionException e3 = assertThrows(InvalidActionException.class, () ->
                startPhase.receivePlayerCharacter(testEngineer));
        InvalidActionException e4 = assertThrows(InvalidActionException.class, () ->
                startPhase.receiveEnemy(enemy));
        InvalidActionException e5 = assertThrows(InvalidActionException.class, () ->
                startPhase.selectAndAttackTarget(enemy));
        InvalidActionException e6 = assertThrows(InvalidActionException.class, () ->
                startPhase.attack(enemy));


    }

}
