package com.github.dodii.finalreality.model.character.playablecharacters.common;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.AbstractPlayerCharacter;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents an engineer character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Rodrigo Oportot.
 */
public class EngineerCharacter extends AbstractPlayerCharacter implements ICommonCharacter {

    private static String CUSTOM_PARAMETER = "E";

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
        super(name, hp, def, turnsQueue);
    }

    @Override
    public int calculateAttack() {
        return 0;
    }

    /**
     * Equips a certain weapon to a character.
     * Checks the weapon's type to follow the
     * rules of the game when equipping.
     * @param weapon weapon to be equipped.
     */
    @Override
    public void equip(IWeapon weapon) {

    }

    /**
     * Returns the hashcode of the character.
     * A pair of playable characters have an equivalent hashcode if
     * they share the same name, hp, def, equipped weapon and a custom
     * parameter defined on every subclass.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getHP(), getDef(), getEquippedWeapon(),
                CUSTOM_PARAMETER);
    }

    /**
     * Compares two characters.
     * @param o character to compare.
     * @return true if both are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EngineerCharacter)) {
            return false;
        }
        final EngineerCharacter engineerCharacter = (EngineerCharacter) o;
        return getName().equals(engineerCharacter.getName()) &&
                getHP() == engineerCharacter.getHP() &&
                getDef() == engineerCharacter.getDef() &&
                getEquippedWeapon().equals(engineerCharacter.getEquippedWeapon());
    }
}
