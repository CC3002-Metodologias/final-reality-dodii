package com.github.dodii.finalreality.model.character.playablecharacters.mage;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a black type mage character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class WhiteMageCharacter extends AbstractMageCharacter {

    private static final String CUSTOM_PARAMETER = "W";

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
        super(name, hp, def, mana, turnsQueue);
    }

    /**
     * Returns the hashcode of the character.
     * A pair of mages have the same hashcode if they share
     * the same name, hp, def, mana, equipped weapon and its custom parameter.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHP(), getDef(),
                getMana(), getEquippedWeapon(), CUSTOM_PARAMETER);
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
        if (!(o instanceof WhiteMageCharacter)) {
            return false;
        }

        final WhiteMageCharacter mage = (WhiteMageCharacter) o;
        return getName().equals(mage.getName()) &&
                getHP() == mage.getHP() &&
                getDef() == mage.getDef() &&
                getEquippedWeapon().equals(mage.getEquippedWeapon()) &&
                getMana() == mage.getMana();
    }
}
