package com.github.dodii.finalreality.model.character.playablecharacters.mage;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a black type mage character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class WhiteMageCharacter extends AbstractMageCharacter {
    /**
     * Creates a new mage character.
     *
     * @param name           the character's name
     * @param hp             the character's hp.
     * @param def            the character's def.
     * @param mana           the character's mana.
     * @param turnsQueue     the queue with the characters waiting for their turn
     */
    public WhiteMageCharacter(@NotNull String name, int hp, int def, int mana,
                              @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, hp, def, mana, CharacterClass.WHITE_MAGE, turnsQueue);
    }
}
