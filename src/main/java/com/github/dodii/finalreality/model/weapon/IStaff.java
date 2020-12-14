package com.github.dodii.finalreality.model.weapon;

/**
 * Interface for the staff weapons of the game
 * Can only be equipped by mages
 *
 * @author Rodrigo Oportot.
 */
public interface IStaff extends IWeapon {

    /**
     * Returns the magic damage of the staff.
     */
    int getMagicDamage();
}
