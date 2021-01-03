package com.github.dodii.finalreality.controller;

import com.github.dodii.finalreality.controller.handlers.*;
import com.github.dodii.finalreality.controller.turnphases.*;
import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidActionException;
import com.github.dodii.finalreality.controller.turnphases.exceptions.InvalidTransitionException;
import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.enemycharacters.IEnemy;
import com.github.dodii.finalreality.model.character.playablecharacters.IPlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.EngineerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.KnightCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.BlackMageCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.IMageCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.mage.WhiteMageCharacter;
import com.github.dodii.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Class that represents the controller of the game.
 * Implementation built following MVC software architecture pattern.
 *
 * @author Rodrigo Oportot.
 */
public class Controller {

    /* Handlers and related objects for notifications of the observer pattern */
    private final IHandler timerHandler = new TimerHandler(this);
    private final IHandler endTurnHandler = new EndTurnHandler(this);
    private final IHandler knockOutHandler = new KnockOutHandler(this);

    /* Turn phases and objects related to the flow of the game */
    //private Turn turnPhase; //

    /* Players of the game */
    private List<IPlayerCharacter> playerParty;
    private List<IEnemy> enemies;

    /* Inventory of the player */
    private List<IWeapon> inventory;

    /* Turns queue */
    private final BlockingQueue<ICharacter> turnsQueue;

    /* Aux objects */
    private GameStatus status;

    /* Turn class manipulating the phases of the game */
    private Phase gamePhase;

    /**
     * Controller constructor
     */
    public Controller() {
        playerParty = new ArrayList<>(4);
        enemies = new ArrayList<>(8);
        inventory = new ArrayList<>();

        turnsQueue = new LinkedBlockingDeque<>();
        status = GameStatus.PLAYING;

        //we set a StartPhase, since's the first
        //part of a turn
        this.setPhase(new StartPhase());
    }

    /**
     * Adds the input character to the party. Checks there aren't
     * more than the defined units (4 per player).
     * Also, cannot add a character if there's already an
     * equal instance of it inside
     * @return true if the character got added to the list.
     */
    private boolean addCharacterToParty(IPlayerCharacter character) {
        if(playerParty.size() < 4 && !playerParty.contains(character)) {
            playerParty.add(character);
            return true;
        }
        return false;
    }

    /**
     * Removes the input character from the player's party.
     * Aux method, not used yet.
     * @param character the character to be removed
     */
    public void removeCharacterFromParty(IPlayerCharacter character) {
        playerParty.remove(character);
    }

    /**
     * Adds the input character to the list. Checks there aren't
     * more than the defined units (8 as the cpu player).
     * Also cannot add a character if there's an equal instance
     * of it inside the list
     * @return true if the character got added to the list.
     */
    private boolean addEnemyToList(IEnemy enemy) {
        if(enemies.size() < 8 && !enemies.contains(enemy)) {
            enemies.add(enemy);
            return true;
        }
        return false;
    }

    //create model object's region

    /**
     * Creates an engineer and adds it to the common class characters'
     * array list. Sets the observer pattern.
     * @param name name of the engineer
     * @param hp hp of the engineer
     * @param def def of the engineer
     * @param turnsQueue turn of the engineer
     * @return the engineer character
     */
    public EngineerCharacter createEngineer(@NotNull String name, int hp, int def,
                                            @NotNull BlockingQueue<ICharacter> turnsQueue) {
        EngineerCharacter engineer = new EngineerCharacter(name, hp, def, turnsQueue);
        if(addCharacterToParty(engineer)) {
            engineer.addKOEventListener(knockOutHandler);
            engineer.addTimerEndedEventListener(timerHandler);
            engineer.addEndTurnEventListener(endTurnHandler);
        }
        return engineer;
    }

    /**
     * Creates a knight and adds it to the list of character the
     * player commands. Sets the observer pattern.
     * @param name name of the knight
     * @param hp hp of the knight
     * @param def def of the knight
     * @param turnsQueue turn of the knight
     * @return the knight character
     */
    public KnightCharacter createKnight(@NotNull String name, int hp, int def,
                                        @NotNull BlockingQueue<ICharacter> turnsQueue) {
        KnightCharacter knight = new KnightCharacter(name, hp, def, turnsQueue);
        if(addCharacterToParty(knight)) {
            knight.addKOEventListener(knockOutHandler);
            knight.addTimerEndedEventListener(timerHandler);
            knight.addEndTurnEventListener(endTurnHandler);
        }
        return knight;
    }

    /**
     * Creates a thief and adds it to the list of character the
     * player commands. Sets the observer pattern.
     * @param name name of the thief
     * @param hp hp of the thief
     * @param def def of the thief
     * @param turnsQueue turn of the thief
     * @return the thief character
     */
    public ThiefCharacter createThief(@NotNull String name, final int hp, final int def,
                                      @NotNull BlockingQueue<ICharacter> turnsQueue) {
        ThiefCharacter thief = new ThiefCharacter(name, hp, def, turnsQueue);
        if(addCharacterToParty(thief)) {
            thief.addKOEventListener(knockOutHandler);
            thief.addTimerEndedEventListener(timerHandler);
            thief.addEndTurnEventListener(endTurnHandler);
        }
        return thief;
    }

    /**
     * Creates a black mage and adds it to the list of characters
     * the player commands. Sets the observer pattern.
     * @param name name of the mage
     * @param hp hp of the mage
     * @param def def of the mage
     * @param turnsQueue turn of the mage
     * @return the mage character
     */
    public BlackMageCharacter createBlackMage(@NotNull String name, int hp, int def, int mana,
                                              @NotNull BlockingQueue<ICharacter> turnsQueue) {
        BlackMageCharacter bMage = new BlackMageCharacter(name, hp, def, mana, turnsQueue);
        if(addCharacterToParty(bMage)) {
            bMage.addKOEventListener(knockOutHandler);
            bMage.addTimerEndedEventListener(timerHandler);
            bMage.addEndTurnEventListener(endTurnHandler);
        }
        return bMage;
    }

    /**
     * Creates a white mage and adds it to the list of character
     * the player commands. Sets the observer pattern.
     * @param name name of the mage
     * @param hp hp of the mage
     * @param def def of the mage
     * @param turnsQueue turn of the mage
     * @return the mage character
     */
    public WhiteMageCharacter createWhiteMage(@NotNull String name, int hp, int def, int mana,
                                              @NotNull BlockingQueue<ICharacter> turnsQueue) {
        WhiteMageCharacter wMage = new WhiteMageCharacter(name, hp, def, mana, turnsQueue);
        if(addCharacterToParty(wMage)) {
            wMage.addKOEventListener(knockOutHandler);
            wMage.addTimerEndedEventListener(timerHandler);
            wMage.addEndTurnEventListener(endTurnHandler);
        }
        return wMage;
    }

    /**
     * Creates an enemy character and adds it to the list of enemies
     * the cpu player commands. Sets the observer pattern.
     * @param name name of the enemy
     * @param hp hp of the enemy
     * @param atk atk of the enemy
     * @param def def of the enemy
     * @param weight weight of the enemy
     * @param turnsQueue turn of the enemy
     * @return the enemy character
     */
    public Enemy createEnemy(@NotNull String name, int hp, int atk, int def, int weight,
                             @NotNull BlockingQueue<ICharacter> turnsQueue) {
        Enemy enemy = new Enemy(name, hp, atk, def, weight, turnsQueue);
        if(addEnemyToList(enemy)) {
            enemy.addKOEventListener(knockOutHandler);
            enemy.addTimerEndedEventListener(timerHandler);
            enemy.addEndTurnEventListener(endTurnHandler);
        }
        return enemy;
    }

    /**
     * Adds a weapon to the inventory, but first checks
     * there isn't an equal weapon already inside it.
     * @param weapon weapon to add
     */
    public void addWeaponToInventory(IWeapon weapon) {
        if(!inventory.contains(weapon)) {
            inventory.add(weapon);
        }
    }

    /**
     * Creates an axe and adds it to the list of weapons.
     * @param name name of the axe
     * @param dmg dmg of the axe
     * @param weight weight of the axe
     * @return the axe created
     */
    public Axe createAxe(String name, int dmg, int weight) {
        Axe axe = new Axe(name, dmg, weight);
        addWeaponToInventory(axe);
        return axe;
    }

    /**
     * Creates a bow and adds it to the list of weapons.
     * @param name name of the bow
     * @param dmg dmg of the bow
     * @param weight weight of the bow
     * @return the bow created
     */
    public Bow createBow(String name, int dmg, int weight) {
        Bow bow = new Bow(name, dmg, weight);
       addWeaponToInventory(bow);
        return bow;
    }

    /**
     * Creates a knife and adds it to the list of weapons.
     * @param name name of the knife
     * @param dmg dmg of the knife
     * @param weight weight of the knife
     * @return the knife created
     */
    public Knife createKnife(String name, int dmg, int weight) {
        Knife knife = new Knife(name, dmg, weight);
        addWeaponToInventory(knife);
        return knife;
    }

    /**
     * Creates a sword and adds it to the list of weapons.
     * @param name name of the sword
     * @param dmg dmg of the sword
     * @param weight weight of the sword
     * @return the sword created
     */
    public Sword createSword(String name, int dmg, int weight) {
        Sword sword = new Sword(name, dmg, weight);
        addWeaponToInventory(sword);
        return sword;
    }

    /**
     * Creates a staff and adds it to the list of weapons.
     * @param name name of the staff
     * @param dmg dmg of the staff
     * @param weight weight of the staff
     * @return the staff created
     */
    public Staff createStaff(String name, int dmg, int magicDamage, int weight) {
        Staff staff = new Staff(name, dmg, magicDamage, weight);
        addWeaponToInventory(staff);
        return staff;
    }

    //end region

    /**
     * @return the inventory containing the weapons of the player
     */
    public List<IWeapon> getInventory() {
        return inventory;
    }

    /**
     * A character attacks another character
     * @param attacker attacker
     * @param target target
     */
    public void characterAttacksCharacter(@NotNull ICharacter attacker,
                                          @NotNull ICharacter target) {
        attacker.attack(target);
        //after the attack, the respective phase will end the turn,
        //advancing to the end phase.
    }

    /**
     * Try method to check the attack. Handles the exception.
     * @param attacker the attacker.
     * @param target the target.
     */
    public void tryToAttack(@NotNull ICharacter attacker,
                            @NotNull ICharacter target) {
        try {
            //character actually attacks, then changes to
            //end phase
            gamePhase.attack(target);
            //in end phase, the endTurn method gets called
            gamePhase.endTurn();
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the list of characters the player commands
     */
    public List<IPlayerCharacter> getPlayerParty() {
        return playerParty;
    }

    /**
     * @return the list of enemies the cpu player commands
     */
    public List<IEnemy> getEnemiesList() {
        return enemies;
    }

    /**
     * @param character the character
     * @return the name of the character
     */
    public String getCharacterName(@NotNull ICharacter character) {
        return character.getName();
    }

    /**
     * @param character the character
     * @return the hp of the character
     */
    public int getCharacterHP(@NotNull ICharacter character) {
        return character.getHP();
    }

    /**
     * @param character the character
     * @return the current hp of the character
     */
    public int getCharacterCurrentHP(@NotNull ICharacter character) {
        return character.getCurrentHP();
    }

    /**
     * @param character the character
     * @return the defense of the character
     */
    public int getCharacterDef(@NotNull ICharacter character) {
        return character.getDef();
    }

    /**
     * @param character the character
     * @return the weapon equipped by the character
     */
    public IWeapon getCharacterWeapon(@NotNull IPlayerCharacter character) {
        return character.getEquippedWeapon();
    }

    /**
     * @param character the character
     * @return the hp of the character
     */
    public int getCharacterMana(@NotNull IMageCharacter character) {
        return character.getMana();
    }

    /**
     * @param enemy the enemy character
     * @return the attack of the character
     */
    public int getEnemyAtk(@NotNull IEnemy enemy) {
        return enemy.getAtk();
    }

    /**
     * @param enemy the enemy character
     * @return the weight of the character
     */
    public int getEnemyWeight(@NotNull IEnemy enemy) {
        return enemy.getWeight();
    }

    /**
     * Equips a weapon to a certain playable character.
     * Removes it from the inventory of the player if it got
     * equipped by the character.
     * If the weapon's not in the inventory, then it doesn't
     * proceed.
     * @param weapon weapon to be equipped
     * @param characterToEquip character that equips the weapon
     */
    public void equipWeaponToCharacter(@NotNull IWeapon weapon,
                                       @NotNull IPlayerCharacter characterToEquip){
        if(!inventory.contains(weapon)) return;
        characterToEquip.equip(weapon);
        /* Checks the weapon was actually equipped */
        if(characterToEquip.getEquippedWeapon().equals(weapon)) {
            inventory.remove(weapon);
        }
    }

    /**
     * Try method to check if the character can actually equip a weapon.
     * Handles the exception.
     * @param weapon the attacker.
     */
    public void tryToEquip(@NotNull IWeapon weapon) {
        try {
            gamePhase.equipWeapon(weapon);
        } catch (InvalidActionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unequips the current weapon a character holds.
     * Technically, the controller equips it with a null weapon object.
     * If the character already has a null weapon equipped, it won't have
     * any effect anyway.
     * The weapon's also returned to the inventory of the player.
     */
    public void unequipCharacter(@NotNull IPlayerCharacter character) {
        if(character.getEquippedWeapon().equals(NullWeapon.uniqueInstance())) {
            return;
        }
        addWeaponToInventory(character.getEquippedWeapon());
        character.equip(NullWeapon.uniqueInstance());
    }

    /**
     * Sets the current phase of the game.
     * Also binds the controller to said phase.
     * @param phase current phase to be bound with the controller.
     */
    public void setPhase(@NotNull Phase phase) {
        this.gamePhase = phase;
        phase.setController(this);
    }

    /**
     * @return the current phase.
     */
    public Phase getCurrentPhase() {
        return gamePhase;
    }

    /**
     * Advances from the start phase to the selecting
     * action phase by calling the respective method on
     * the phase class.
     */
    public void toSelectingActionPhase()
            throws InvalidTransitionException,
            InvalidActionException {
        var currentChar = getFirstQueueCharacter();
        gamePhase.toSelectingActionPhase();

        //if it's a playable character, it will be passed as such
        //To the phase, then will be down casted.
        if(currentChar.isPlayableCharacter()) {
            gamePhase.receivePlayerCharacter(currentChar);
        }
        else {
            gamePhase.receiveEnemy(currentChar);
        }
    }

    /**
     * This method gets notified when the set time of a character's timer
     * ends, so the controller can proceed from step 5 to 1 of the turn
     * The first character on the queue is selected. If it's a playable
     * character, the player can command it. If it's an enemy, it will
     * attack randomly to a character of the player's party. This behavior
     * is driven by the phases' classes.
     */
    public void onTimerEnded(ICharacter character)
            throws InvalidTransitionException {
        //here, the controller should have gotten notified
        //that a character was added to the queue, so it can
        //select it to continue with step 1 of the turns' implementation.
        if(gamePhase.isWaitingQueuePhase()) {
            gamePhase.toStartPhase();
        }
    }

    /**
     * Method that returns the first element of the queue.
     * Works along the start of a turn. On the next version, said character will be commanded
     * by the player or the cpu, depending on its type.
     */
    public ICharacter getFirstQueueCharacter() {
        return turnsQueue.element();
    }

    /**
     * @return the queue of the character's turns.
     */
    public BlockingQueue<ICharacter> getTurnsQueue() {
        return turnsQueue;
    }

    /**
     * This method gets notified when a character ends its turn, so it can be
     * removed from the queue.
     * Then, it runs the timer of the character, as said on step 3
     * of the 2.2 section about said implementation.
     */
    public void onTurnEnded(@NotNull ICharacter character)
            throws InvalidTransitionException, InvalidActionException {
        popQueueCharacter();
        character.waitTurn();

        //changes to end phase.
        gamePhase.toEndPhase();
    }

    /**
     * @return the first character in the queue. Also
     * removes it from said queue.
     */
    public ICharacter popQueueCharacter() {
        return turnsQueue.poll();
    }

    /**
     * Notifies when a character get's K.O'd
     * Checks if all playable characters or all enemies are K.O'd
     * to determine if the player wins or loses the battle.
     */
    public void onKnockedOutCharacter(@NotNull ICharacter knockedOutCharacter) {
        boolean check = false;

        /* if check's still false after the cycle, it means all characters
        from the list are KO'd */
        /* We check the list that contains the recent KO'd character */
        /* First checks if the character's a player character */
        if(getPlayerParty().contains(knockedOutCharacter)) {
            for (var character : getPlayerParty()) {
                if(!character.isKO()) check = true;
            }
            if (!check) defeat();
        }
        /* If not, then it must be an enemy */
        else {
            for (var enemy : getEnemiesList()) {
                if(!enemy.isKO()) check = true;
            }
            if (!check) victory();
        }
    }

    /**
     * @return the aux enum of the current status of the game.
     */
    public GameStatus getGameStatus() {
        return status;
    }

    /**
     * Method that gets executed when the player wins the game.
     * Changes the status of the game.
     * Momentarily, it doesn't do anything more.
     */
    public void victory() {
        status = GameStatus.VICTORY;
        System.out.println("You have won the battle! But war continues...");
    }

    /**
     * Method that gets executed when the player loses the game.
     * Changes the status of the game.
     * Momentarily, it doesn't do anything more.
     */
    public void defeat() {
        status = GameStatus.DEFEAT;
        System.out.println("You have lost this battle, but do not let" +
                "hope faint, there's still a chance yet! ");
    }


}
