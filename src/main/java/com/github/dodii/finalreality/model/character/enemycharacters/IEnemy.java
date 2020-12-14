package com.github.dodii.finalreality.model.character.enemycharacters;


import com.github.dodii.finalreality.model.character.ICharacter;

/**
 * An interface that represents an enemy character of the game.*
 *
 * @author Rodrigo Oportot.
 */
public interface IEnemy extends ICharacter {

    /**
     * Returns the unit's weight.
     */
    int getWeight();

    /**
     * Returns the attack of the character.
     */
    int getAtk();
}
