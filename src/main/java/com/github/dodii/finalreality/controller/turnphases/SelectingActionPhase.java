package com.github.dodii.finalreality.controller.turnphases;

import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidActionException;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Class that represents the selecting phase of
 * a turn.
 * A player character may change its weapon before
 * selecting its attack target.
 *
 * Implements state pattern.
 *
 * @author Rodrigo Oportot.
 */
public class SelectingActionPhase extends Phase {

    private Random rng;
    private ICharacter target;

    /**
     * Constructor.
     * Sets the canAttack boolean to true.
     */
    public SelectingActionPhase() {
        this.rng = new Random();
    }

    /**
     * Receives an enemy character that cannot equip weapons nor
     * select its target, it attacks randomly.
     * Leaves the canEquip boolean as false.
     * @param enemy the enemy which will attack this turn.
     */
    public void receiveEnemy(ICharacter enemy) {
        this.currentCharacter = enemy;

        //then, proceeds to randomly select a player
        //in the party, then will attack.
        int partySize = controller.getPlayerParty().size();
        this.target = controller.getPlayerParty().get(rng.nextInt(partySize));
        toAttackPhase();
    }

    /**
     * Receives a playable character. Can equip and select its
     * enemy target to attack it.
     * Sets the canEquip boolean to true.
     * @param playerCharacter the character.
     */
    public void receivePlayerCharacter(ICharacter playerCharacter) {
        this.canEquip = true;
        this.currentCharacter = playerCharacter;
    }

    /**
     * Equipping a weapon to the current playable character.
     * @param weapon weapon to be equipped.
     * @throws InvalidActionException exception thrown in case the weapon
     * cannot be equipped in the current phase.
     */
    public void equipWeapon(IWeapon weapon) throws InvalidActionException {
        super.equipWeapon(weapon);
    }

    /**
     * Selects the target to attack. Used by the
     * characters commanded by the player.
     * @param target the target.
     */
    @Override
    public void selectAndAttackTarget(ICharacter target) {
        this.target = target;
        toAttackPhase();
    }

    /**
     * @return the name of the phase.
     */
    @Override
    public String toString() {
        return "Selecting Action Phase";
    }

    /**
     * Changes to the attack phase.
     */
    public void toAttackPhase() {
        changePhase(new AttackPhase(currentCharacter, target));
    }

    /**
     * @return true if the phase represents the selection
     * action phase of a turn.
     */
    public boolean isSelectingActionPhase() {
        return true;
    }
}
