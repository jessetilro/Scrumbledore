package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;
import nl.tudelft.scrumbledore.level.element.Bubble;
import nl.tudelft.scrumbledore.level.element.Fruit;
import nl.tudelft.scrumbledore.level.element.LevelElement;
import nl.tudelft.scrumbledore.level.element.NPC;
import nl.tudelft.scrumbledore.level.element.Platform;
import nl.tudelft.scrumbledore.level.element.Player;
import nl.tudelft.scrumbledore.level.element.PlayerElement;
import nl.tudelft.scrumbledore.level.powerup.PowerupPickUp;

/**
 * Class representing a Level in the Game.
 * 
 * @author Jesse Tilro
 */
public class Level {
  private ArrayList<Platform> platforms;
  private ArrayList<Fruit> fruits;
  private ArrayList<NPC> npcs;
  private ArrayList<Bubble> projectiles;
  private ArrayList<PlayerElement> players;
  private ArrayList<Bubble> encapEnemies;
  private ArrayList<PowerupPickUp> powerups;

  /**
   * Constructs a new Level instance.
   */
  public Level() {
    platforms = new ArrayList<Platform>();
    projectiles = new ArrayList<Bubble>();
    encapEnemies = new ArrayList<Bubble>();
    fruits = new ArrayList<Fruit>();
    npcs = new ArrayList<NPC>();
    players = new ArrayList<PlayerElement>();
    powerups = new ArrayList<PowerupPickUp>();
  }

  /**
   * Add a LevelElement to this Level.
   * 
   * @param element
   *          A LevelElement.
   */
  public void addElement(LevelElement element) {
    if (element instanceof Platform) {
      platforms.add((Platform) element);
    } else if (element instanceof NPC) {
      npcs.add((NPC) element);
    } else if (element instanceof Fruit) {
      fruits.add((Fruit) element);
    } else if (element instanceof Player) {
      players.add((Player) element);
    } else if (element instanceof Bubble) {
      projectiles.add((Bubble) element);
    } else if (element instanceof PowerupPickUp) {
      powerups.add((PowerupPickUp) element);
    }
  }

  /**
   * Get all the dynamic elements in the Level (elements that are updated every cycle).
   * 
   * @return All Dynamic Level Elements.
   */
  public ArrayList<LevelElement> getDynamicElements() {
    ArrayList<LevelElement> elements = new ArrayList<LevelElement>();
    elements.addAll(players);
    elements.addAll(npcs);
    elements.addAll(fruits);
    elements.addAll(projectiles);
    elements.addAll(powerups);
    return elements;
  }

  /**
   * Get all the static elements in the Level (elements that are not updated every cycle).
   * 
   * @return All Static Level Elements.
   */
  public ArrayList<LevelElement> getStaticElements() {
    ArrayList<LevelElement> elements = new ArrayList<LevelElement>();
    elements.addAll(platforms);
    return elements;
  }

  /**
   * Returns an ArrayList of Platform elements.
   * 
   * @return An ArrayList of Platform elements.
   */
  public ArrayList<Platform> getPlatforms() {
    return platforms;
  }

  /**
   * Returns an ArrayList of Fruit elements.
   * 
   * @return An ArrayList of Fruit elements.
   */
  public ArrayList<Fruit> getFruits() {
    return fruits;
  }

  /**
   * Returns an ArrayList of NPC elements.
   * 
   * @return An ArrayList of NPC elements.
   */
  public ArrayList<NPC> getNPCs() {
    return npcs;
  }

  /**
   * Returns an ArrayList of Player objects.
   * 
   * @return An ArrayList of Player objects.
   */
  public ArrayList<PlayerElement> getPlayers() {
    return players;
  }

  /**
   * Returns an ArrayList of Bubble objects.
   * 
   * @return An ArrayList of Bubble objects.
   */
  public ArrayList<Bubble> getBubbles() {
    return projectiles;
  }

  /**
   * Returns an ArrayList of Bubble objects with enemies in them.
   * 
   * @return An ArrayList of Bubble objects with enemies in them.
   */
  public ArrayList<Bubble> getEnemyBubbles() {
    return encapEnemies;
  }

  /**
   * Returns an ArrayList of Powerup objects.
   * 
   * @return an ArrayList of Powerup objects.
   */
  public ArrayList<PowerupPickUp> getPowerups() {
    return powerups;
  }

}
