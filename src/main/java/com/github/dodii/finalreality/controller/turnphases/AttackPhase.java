package com.github.dodii.finalreality.controller.turnphases;

import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidActionException;
import com.github.dodii.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

/**
 * Class that represents the attack phase of a
 * turn.
 *
 * An enemy character will attack a player character
 * randomly.
 *
 * The attack takes place, the damage is calculated and
 * then received by the target.
 *
 * After that, the character gets popped from the queue and
 * its timer gets activated.
 *
 * Implements state pattern.
 *
 * @author Rodrigo Oportot
 */
public class AttackPhase extends Phase {
    ICharacter target;

    public AttackPhase(@NotNull ICharacter attacker,
                       @NotNull ICharacter target) {
        this.currentCharacter = attacker;
        this.canAttack = true;
        this.canEquip = false;

        this.target = target;
    }

    /**
     * The attack takes place.
     */
    @Override
    public void attack(@NotNull ICharacter target)
            throws InvalidActionException {
        super.attack(target);
        //here, the attack takes place, so it can no longer
        //attack after it. Also can't equip weapons anymore.
        this.canAttack = false;
    }

    /**
     * Changes to the end phase.
     */
    public void toEndPhase() {
        changePhase(new EndPhase());
    }

    /**
     * @return the name of the phase.
     */
    @Override
    public String toString() {
        return "Attack Phase";
    }

    /**
     * @return true if the phase represents the attack phase
     * of a turn.
     */
    public boolean isAttackPhase() {
        return true;
    }

}
