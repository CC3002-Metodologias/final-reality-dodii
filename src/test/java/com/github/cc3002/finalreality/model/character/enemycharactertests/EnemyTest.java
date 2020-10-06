package com.github.cc3002.finalreality.model.character.enemycharactertests;

import com.github.cc3002.finalreality.model.character.AbstractCharacterTest;
import com.github.dodii.finalreality.model.character.enemycharacters.Enemy;
import com.github.dodii.finalreality.model.character.playablecharacters.CharacterClass;
import com.github.dodii.finalreality.model.character.playablecharacters.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private static final int ATTACK_DAMAGE = 10;
  private static final int WEIGHT = 10;

  @BeforeEach
  void setUp() {
    basicSetUp();
    testCharacters.add(new Enemy(ENEMY_NAME, 10,ATTACK_DAMAGE, DEFENSE,
            WEIGHT, turns));
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, ATTACK_DAMAGE, DEFENSE,
                    WEIGHT, turns),
        testCharacters.get(0),
        new Enemy(ENEMY_NAME, 11, turns),
        new PlayerCharacter(ENEMY_NAME, turns, CharacterClass.THIEF));
  }
}