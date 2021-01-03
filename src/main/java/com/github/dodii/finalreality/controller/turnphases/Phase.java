package com.github.dodii.finalreality.controller.turnphases;

import com.github.dodii.finalreality.controller.Controller;
import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidActionException;
import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidTransitionException;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a phase of a turn.
 * Implements state pattern.
 * This classes handle the turn's mechanic
 * of the game, including exceptions and the
 * flow of the phases.
 *
 * @author Rodrigo Oportot.
 */
public class Phase {

    protected Controller controller;

    protected ICharacter currentCharacter;

    protected boolean canAttack = false;
    protected boolean canEquip = false;

    /**
     * Binds the controller to the current turn phase of the game.
     * @param controller the controller to be bound to this phase.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * @return the controller associated to this phase.
     */
    public Controller getController() {
        return controller;
    }

    /**
     * @return the current character of the turn.
     */
    public ICharacter getCurrentCharacter() {
        return currentCharacter;
    }

    /**
     * Changes the actual phase of the turn.
     * @param phase the new phase.
     */
    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    /**
     * @return true if the phase represents the start of a turn.
     */
    public boolean isStartPhase() {
        return false;
    }

    /**
     * @return true if the phase represents the selection
     * action phase of a turn.
     */
    public boolean isSelectingActionPhase() {
        return false;
    }

    /**
     * @return true if the phase represents the attack phase
     * of a turn.
     */
    public boolean isAttackPhase() {
        return false;
    }

    /**
     * @return true if the phase represents the waiting
     * queue phase of a turn.
     */
    public boolean isWaitingQueuePhase() {
        return false;
    }

    /**
     * @return true if the phase represents the end of a turn.
     */
    public boolean isEndPhase() {
        return false;
    }

    /**
     * Changes to the start phase.
     */
    public void toStartPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() +
                        " to Start Phase.");
    }

    /**
     * Changes to the selecting action phase.
     */
    public void toSelectingActionPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() +
                        " to Selecting Action Phase.");
    }

    /**
     * Changes to the attack phase.
     */
    public void toAttackPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() +
                        " to Attack Phase.");
    }

    /**
     * Changes to the end phase.
     */
    public void toEndPhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() +
                        " to End Phase.");
    }

    /**
     * Changes to the waiting queue phase.
     */
    public void toWaitingQueuePhase() throws InvalidTransitionException {
        throw new InvalidTransitionException(
                "Can't change from " + this.toString() +
                        " to Waiting Queue Phase.");
    }

    /**
     * The attack takes place.
     * @param target the target.
     * @throws InvalidActionException exception thrown when the attack
     * cannot take place in the current phase.
     */
    public void attack(@NotNull ICharacter target) throws InvalidActionException {
        if(!canAttack) {
            throw new InvalidActionException("Can't attack on " +
                    this.toString() + ".");
        }
        controller.characterAttacksCharacter(currentCharacter,
                target);
    }

    /**
     * Equipping a weapon to a certain playable character.
     * Makes a cast, since it's a player character.
     * @param weapon weapon to be equipped.
     * @throws InvalidActionException exception thrown in case the weapon
     * cannot be equipped in the current phase.
     */
    public void equipWeapon(IWeapon weapon) throws InvalidActionException {
        if(!canEquip) {
            throw new InvalidActionException("Can't equip right now.");
        }
        controller.equipWeaponToCharacter(weapon,
                (IPlayerCharacter) currentCharacter);
    }

    /**
     * Ends the turn.
     */
    public void endTurn() throws InvalidActionException {
        throw new InvalidActionException(
                "Can't end turn on " + this.toString() + ".");
    }

    /**
     * Receives a playable character. Can equip and select its
     * enemy target to attack.
     * @param playerCharacter the character.
     * @throws InvalidActionException exception thrown when it's
     * not the selecting action phase.
     */
    public void receivePlayerCharacter(ICharacter playerCharacter)
            throws InvalidActionException {
        throw new InvalidActionException(
                "Can't receive a player character on " +
                        this.toString() + ".");
    }

    /**
     * Receives an enemy character that cannot equip weapons nor
     * select its target, it attacks randomly.
     * @param enemy the enemy which will attack this turn.
     * @throws InvalidActionException exception thrown when it's
     * not the selecting action phase.
     */
    public void receiveEnemy(ICharacter enemy)
            throws InvalidActionException {
        throw new InvalidActionException(
                "Can't receive an enemy on " +
                        this.toString() + ".");
    }

    /**
     * Selects the target to attack. Used by the
     * characters commanded by the player.
     * @param target the target.
     * @throws InvalidActionException when the phase doesn't
     * allow to select a target.
     */
    public void selectAndAttackTarget(ICharacter target)
            throws InvalidActionException {
        throw new InvalidActionException(
                "Can't select a target on " +
                        this.toString() + ".");
    }
}
