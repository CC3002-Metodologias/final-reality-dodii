package com.github.dodii.finalreality.model.character.playablecharacters.mage;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class represents a mage character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractMageCharacter extends PlayerCharacter implements IMageCharacter {

    private int mana;

    /**
     * Creates a new mage character.
     *
     * @param name           the character's name
     * @param hp             the character's hp.
     * @param def            the character's def.
     * @param mana           the character's mana.
     * @param turnsQueue     the queue with the characters waiting for their turn.
     * @param characterClass the class of the mage.
     */
    public AbstractMageCharacter(@NotNull String name, final int hp, final int def, final int mana,
                                 CharacterClass characterClass,
                                 @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, hp, def, characterClass, turnsQueue);
        this.mana = mana;
    }

    /**
     * @return the mana of the character.
     */
    public int getMana() {
        return mana;
    }

    /**
     * Returns the hashcode of the character.
     * A pair of mages have the same hashcode if they share
     * the same name, hp, def, mana, equipped weapon and class.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHP(), getDef(),
                getMana(), getEquippedWeapon(), getCharacterClass());
    }

    /**
     * Compares two mage characters.
     * @param o character to compare.
     * @return true if both are equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IMageCharacter)) {
            return false;
        }

        final IMageCharacter mage = (IMageCharacter) o;
        return getName().equals(mage.getName()) &&
                getHP() == mage.getHP() &&
                getDef() == mage.getDef() &&
                getCharacterClass() == mage.getCharacterClass() &&
                getEquippedWeapon().equals(mage.getEquippedWeapon()) &&
                getMana() == mage.getMana();
    }
}
