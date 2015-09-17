package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the Player class.
 * 
 * @author Jesse Tilro
 *
 */
public class PlayerTest extends LevelElementTest {

  private Player player;

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new Player(position, size);
  }

  /**
   * Set the bubble field to a new Bubble instance to use as a test object.
   */
  @Before
  public void setUp() {
    player = new Player(new Vector(0, 0), new Vector(0, 0));
  }

  /**
   * When a new Player element is created, initially it should have gravity, be alive, should not be
   * firing and looking right.
   */
  @Test
  public void testConstuctor() {
    assertTrue(player.hasGravity());
    assertTrue(player.isAlive());
    assertFalse(player.isFiring());
    assertEquals(PlayerAction.MoveRight, player.getLastMove());
  }

  /**
   * When a player action is added to a bubble's action queue, a call to hasAction for that action
   * should return true.
   */
  @Test
  public void testHasActionTrue() {
    player.addAction(PlayerAction.MoveLeft);
    assertTrue(player.hasAction(PlayerAction.MoveLeft));
  }

  /**
   * When a player action was not added to a bubble's action queue, a call to hasAction for that
   * action should return false.
   */
  @Test
  public void testHasActionFalse() {
    player.addAction(PlayerAction.MoveLeft);
    assertFalse(player.hasAction(PlayerAction.MoveRight));
  }

  /**
   * When a player action queue is cleared, it should not have the actions anymore which were added
   * before.
   */
  @Test
  public void testClearActions() {
    player.addAction(PlayerAction.MoveLeft);
    player.clearActions();
    assertFalse(player.hasAction(PlayerAction.MoveLeft));
  }

  /**
   * When a player has an action removed from its action queue, it should no longer have the action.
   */
  @Test
  public void testRemoveAction() {
    player.addAction(PlayerAction.MoveLeft);
    player.addAction(PlayerAction.MoveLeft);
    player.removeAction(PlayerAction.MoveLeft);
    assertFalse(player.hasAction(PlayerAction.MoveLeft));
  }

  /**
   * Test the firing field getter/setter.
   */
  @Test
  public void testFiring() {
    player.setFiring(true);
    assertTrue(player.isFiring());
  }

  /**
   * Test the alive field getter/setter.
   */
  @Test
  public void testAlive() {
    player.setAlive(false);
    assertFalse(player.isAlive());
  }

  /**
   * Test the lastMove field getter/setter.
   */
  @Test
  public void testLastMove() {
    player.setLastMove(PlayerAction.MoveLeft);
    assertEquals(PlayerAction.MoveLeft, player.getLastMove());
    player.setLastMove(PlayerAction.MoveRight);
    assertEquals(PlayerAction.MoveRight, player.getLastMove());
    // Actions other than MoveLeft and MoveRight should be ignored.
    player.setLastMove(PlayerAction.Jump);
    assertEquals(PlayerAction.MoveRight, player.getLastMove());
  }

  /**
   * Two player instances with the same position and size should be considered equal.
   */
  @Test
  public void testEqualsTrue() {
    Player player2 = new Player(player.getPosition(), player.getSize());
    assertEquals(player, player2);
  }

  /**
   * Two player instances with a different position or size should not be considered equal.
   */
  @Test
  public void testEqualsFalse() {
    Player player2 = new Player(new Vector(42, 42), player.getSize());
    Player player3 = new Player(player.getPosition(), new Vector(42, 42));
    assertFalse(player.equals(player2));
    assertFalse(player.equals(player3));
  }

  /**
   * A Player instance and an instance of another class should not be considered equal.
   */
  @Test
  public void testEqualsFalseOtherClass() {
    Fruit fruit = new Fruit(player.getPosition(), player.getSize());
    assertFalse(player.equals(fruit));
  }

  /**
   * The stubbed method hashCode should just return zero (line coverage).
   */
  @Test
  public void testHashCode() {
    assertEquals(0, player.hashCode());
  }
}
