package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing a Level in the Game.
 * 
 * @author Jesse Tilro
 *
 */
public class Level {

  private ArrayList<Platform> platforms;
  private ArrayList<Fruit> fruits;
  private ArrayList<NPC> npcs;
  private ArrayList<Bubble> bubbles;
  private Player player;


  /**
   * Constructs a new Level instance.
   */
  public Level() {
    platforms = new ArrayList<Platform>();
    bubbles = new ArrayList<Bubble>();
    fruits = new ArrayList<Fruit>();
    npcs = new ArrayList<NPC>();
  }

  /**
   * Add a LevelElement to this Level.
   * 
   * @param element
   *          A LevelElement.
   */
  public void addElement(LevelElement element) {
    if (element.getClass().equals(Platform.class)) {
      platforms.add((Platform) element);
    } else if (element.getClass().equals(NPC.class)) {
      npcs.add((NPC) element);
    } else if (element.getClass().equals(Fruit.class)) {
      fruits.add((Fruit) element);
    } else if (element.getClass().equals(Player.class)) {
      player = (Player) element;
    } 
  }

  /**
   * Returns an ArrayList of Platform elements.
   * 
   * @return
   *          An ArrayList of Platform elements
   */
  public ArrayList<Platform> getPlatforms() {
    return platforms;
  }
  
  /**
   * Returns an ArrayList of Fruit elements.
   * 
   * @return
   *          An ArrayList of Fruit elements
   */
  public ArrayList<Fruit> getFruits() {
    return fruits;
  }
  
  /**
   * Returns an ArrayList of NPC elements.
   * 
   * @return
   *          An ArrayList of NPC elements
   */
  public ArrayList<NPC> getNPCs() {
    return npcs;
  }


  /**
   * Returns a Player object.
   * 
   * @return
   *          A player object
   */
  public Player getPlayer() {
    return player;
  }
  
  public ArrayList<Bubble> getBubbles() {
    return bubbles;
  }

}