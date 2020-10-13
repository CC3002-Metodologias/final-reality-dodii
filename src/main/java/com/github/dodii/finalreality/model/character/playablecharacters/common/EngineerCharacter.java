package com.github.dodii.finalreality.model.character.playablecharacters.common;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * A class that represents an engineer character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class EngineerCharacter extends PlayerCharacter implements ICommonCharacter {

    /**
     * Creates a new character.
     *
     * @param name           the character's name
     * @param hp             the character's hp.
     * @param def            the character's def.
     * @param turnsQueue     the queue with the characters waiting for their turn
     */
    public EngineerCharacter(@NotNull String name, int hp, int def,
                             @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, hp, def, CharacterClass.ENGINEER, turnsQueue);
    }
}
