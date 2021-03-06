package nl.tudelft.scrumbledore.level.modifier;

import static org.junit.Assert.assertEquals;

import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.Bubble;
import nl.tudelft.scrumbledore.level.element.DynamicElement;
import nl.tudelft.scrumbledore.level.element.Fruit;
import nl.tudelft.scrumbledore.level.element.NPC;
import nl.tudelft.scrumbledore.level.element.Player;

//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the Kinetics class.
 * 
 * @author Floris Doolaard
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class KineticsLevelModifierTest {

  private KineticsLevelModifier kinetics;

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @Before
  public void setUpBeforeClass() {
    kinetics = new KineticsLevelModifier();
  }

  /**
   * When the an element has a speed larger than its friction vector, the friction vector simply
   * needs to be absolutely subtracted from the speed vector. (MCDC)
   */
  @Test
  public void testApplyFrictionSpeedLargerThanFriction() {
    DynamicElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    // Set speed
    el.getSpeed().sum(new Vector(4, -4));
    // Set friction
    el.getFriction().setX(8);
    el.getFriction().setY(8);
    // The expected resulting speed vector given the delta
    Vector expectedOut = new Vector(2, -2);

    kinetics.applyFriction(el, .25);

    assertEquals(expectedOut, el.getSpeed());
  }

  /**
   * When an element has a speed x entry being smaller than the friction x entry to be subtracted,
   * its speed x entry should be set to 0. (MCDC)
   */
  @Test
  public void testApplyFrictionSpeedXSmaller() {
    DynamicElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    // Set speed
    el.getSpeed().sum(new Vector(4, -4));
    // Set friction
    el.getFriction().setX(8);
    el.getFriction().setY(4);
    // The expected resulting speed vector given the delta
    Vector expectedOut = new Vector(0, -1);

    kinetics.applyFriction(el, .75);

    assertEquals(expectedOut, el.getSpeed());
  }

  /**
   * When an element has a speed y entry being smaller than the friction y entry to be subtracted,
   * its speed y entry should be set to 0. (MCDC)
   */
  @Test
  public void testApplyFrictionSpeedYSmaller() {
    DynamicElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    // Set speed
    el.getSpeed().sum(new Vector(4, -4));
    // Set friction
    el.getFriction().setX(4);
    el.getFriction().setY(8);
    // The expected resulting speed vector given the delta
    Vector expectedOut = new Vector(1, 0);

    kinetics.applyFriction(el, .75);

    assertEquals(expectedOut, el.getSpeed());
  }

  /**
   * The position of an element should be updated correctly when adding it's speed vector and taking
   * the delta into account.
   */
  @Test
  public void testMove() {
    DynamicElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    el.getSpeed().sum(new Vector(2, 2));
    kinetics.move(el, 0.5);
    // For branch coverage:
    kinetics.move(null, 1);
    assertEquals(new Vector(2, 3), el.getPosition());
  }

  /**
   * The position of an element should be updated correctly when removing it's speed vector and
   * taking the delta into account.
   */
  @Test
  public void testRevertMove() {
    DynamicElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    el.getSpeed().sum(new Vector(2, 2));
    kinetics.revertMove(el, 0.5);
    // For branch coverage:
    kinetics.revertMove(null, 1);
    assertEquals(new Vector(0, 1), el.getPosition());
  }

  /**
   * When a Level is modified, the position of the Player should be updated correctly.
   */
  @Test
  public void testModifyPlayer() {
    Player player = new Player(new Vector(0, 0), new Vector(0, 0));
    player.getSpeed().sum(new Vector(4, 4));
    Level level = new Level();
    level.addElement(player);
    Vector expectedPosition = new Vector(2, 2);

    kinetics.modify(level, .5);

    assertEquals(expectedPosition, player.getPosition());
  }

  /**
   * When a Level is modified, the position and speed of the Bubbles should be updated correctly.
   */
  @Test
  public void testModifyBubbles() {
    // Dummy player
    Player player = new Player(new Vector(0, 0), new Vector(0, 0));
    // Fixtures of interest
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(0, 0));
    bubble.getSpeed().sum(new Vector(4, 4));
    bubble.getFriction().setX(4);
    Level level = new Level();
    level.addElement(bubble);
    level.addElement(player);
    Vector expectedPosition = new Vector(2, 2);
    Vector expectedSpeed = new Vector(2, 4);

    kinetics.modify(level, .5);

    assertEquals(expectedPosition, bubble.getPosition());
    assertEquals(expectedSpeed, bubble.getSpeed());
  }

  /**
   * When a Level is modified, the position of the NPC's should be updated correctly.
   */
  @Test
  public void testModifyNPC() {
    // Dummy player
    Player player = new Player(new Vector(0, 0), new Vector(0, 0));
    // Fixtures of interest
    NPC npc = new NPC(new Vector(0, 0), new Vector(0, 0));
    npc.getSpeed().sum(new Vector(4, 4));
    Level level = new Level();
    level.addElement(npc);
    level.addElement(player);
    Vector expectedPosition = new Vector(2, 2);

    kinetics.modify(level, .5);

    assertEquals(expectedPosition, npc.getPosition());
  }

  /**
   * When a Level is modified, the position of the Fruits should be updated correctly.
   */
  @Test
  public void testModifyFruit() {
    // Dummy player
    Player player = new Player(new Vector(0, 0), new Vector(0, 0));
    // Fixtures of interest
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(0, 0));
    fruit.getSpeed().sum(new Vector(4, 4));
    Level level = new Level();
    level.addElement(fruit);
    level.addElement(player);
    Vector expectedPosition = new Vector(2, 2);

    kinetics.modify(level, .5);

    assertEquals(expectedPosition, fruit.getPosition());
  }
}
