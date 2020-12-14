package com.github.dodii.finalreality.model.character.playablecharacters.mage;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.AbstractPlayerCharacter;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class represents a mage character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public abstract class AbstractMageCharacter extends AbstractPlayerCharacter implements IMageCharacter {

    private int mana;

    /**
     * Creates a new mage character.
     *
     * @param name           the character's name
     * @param hp             the character's hp.
     * @param def            the character's def.
     * @param mana           the character's mana.
     * @param turnsQueue     the queue with the characters waiting for their turn.
     */
    public AbstractMageCharacter(@NotNull String name, final int hp, final int def, final int mana,
                                 @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(name, hp, def, turnsQueue);
        this.mana = mana;
    }

    /**
     * @return the mana of the character.
     */
    public int getMana() {
        return mana;
    }

    /**
     * Returns true or false depending on the character
     */
    @Override
    public boolean isMage() {
        return true;
    }

    /**
     * Equips a certain weapon to a character.
     * Checks the weapon's type to follow the
     * rules of the game when equipping by using double dispatch.
     * A mage may only equip staffs.
     * All characters can equip null weapons.
     * @param weapon weapon to be equipped.
     */
    @Override
    public void equip(@NotNull IWeapon weapon) {
        weapon.equipToMage(this);
    }


    /**
     * Returns the hashcode of the character.
     * A pair of mages have the same hashcode if they share
     * the same name, hp, def, mana, equipped weapon and its custom parameter.
     */
    @Override
    public abstract int hashCode();

    /**
     * Compares two mage characters.
     * @param o character to compare.
     * @return true if both are equal.
     */
    @Override
    public abstract boolean equals(final Object o);
}
