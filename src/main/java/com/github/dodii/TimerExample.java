package com.github.dodii;

import com.github.dodii.finalreality.model.character.ICharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import com.github.dodii.finalreality.model.character.playablecharacters.common.ThiefCharacter;
import com.github.dodii.finalreality.model.weapon.Axe;
import com.github.dodii.finalreality.model.weapon.IWeapon;
import com.github.dodii.finalreality.model.weapon.Knife;
import com.github.dodii.finalreality.model.weapon.WeaponType;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Ignacio Slater Muñoz.
 */
public class TimerExample {

  public static void main(String[] args) throws InterruptedException {
    BlockingQueue<ICharacter> queue = new LinkedBlockingQueue<>();
    Random rng = new Random();
    for (int i = 0; i < 10; i++) {
      // Gives a random speed to each character to generate different waiting times
      var weapon = new Knife("", 0, rng.nextInt(50));
      var character = new ThiefCharacter(Integer.toString(i), 10, 5, queue);
      character.equip(weapon);
      character.waitTurn();
    }
    // Waits for 6 seconds to ensure that all characters have finished waiting
    Thread.sleep(6000);
    while (!queue.isEmpty()) {
      // Pops and prints the names of the characters of the queue to illustrate the turns
      // order
      System.out.println(queue.poll().getName());
    }
  }
}
