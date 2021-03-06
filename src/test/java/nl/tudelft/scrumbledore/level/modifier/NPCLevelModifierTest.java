package nl.tudelft.scrumbledore.level.modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.LevelElementAction;
import nl.tudelft.scrumbledore.level.element.NPC;

/**
 * Test Suite for NPC Level Modifier class.

 * @author Niels Warnars
 */
@SuppressWarnings("deprecation")
public class NPCLevelModifierTest {

  private NPC npc;
  private Level level;
  private NPCLevelModifier modifier;
  
  /**
   * Set-up the NPC Level Modifier, NPC and Level objects.
   */
  @Before
  public void setUp() {
    modifier = new NPCLevelModifier();
    npc = new NPC(new Vector(0, 0), new Vector(32, 32));
    level = new Level();
    
    level.addElement(npc);
  }
  
  /**
   * When a modify is performed on an NPC with the MoveLeft its speed should be set to
   * -NPC_SPEED and the MoveLeft action should be removed.
   */
  @Test
  public void testModifyMoveLeft() {
    npc.clearActions();
    npc.addAction(LevelElementAction.MoveLeft);
    
    modifier.modify(level, 1.0d);
    
    assertEquals(-Constants.NPC_SPEED, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
    assertFalse(npc.hasAction(LevelElementAction.MoveLeft));
  }
  
  /**
   * When a modify is performed on an NPC with the MoveRight its speed should be set to
   * +NPC_SPEED and the MoveRight action should be removed.
   */
  @Test
  public void testModifyMoveRight() {
    npc.clearActions();
    npc.addAction(LevelElementAction.MoveRight);
    
    modifier.modify(level, 1.0d);
    
    assertEquals(Constants.NPC_SPEED, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
    assertFalse(npc.hasAction(LevelElementAction.MoveRight));
  }
  
  /**
   * When a modify is performed on an NPC with the Jump its vertical speed should be set to
   * PLAYER_JUMP / -1.1 and the Jump action should be removed.
   */
  @Test
  public void testModifyJump() {
    npc.clearActions();
    npc.addAction(LevelElementAction.Jump);
    
    modifier.modify(level, 1.0d);
    
    assertEquals(Constants.PLAYER_JUMP / -1.1, npc.getSpeed().getY(), Constants.DOUBLE_PRECISION);
    assertFalse(npc.hasAction(LevelElementAction.Jump));
  }

}
