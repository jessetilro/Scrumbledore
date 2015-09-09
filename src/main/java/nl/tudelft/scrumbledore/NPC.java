package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing an NPC in a game.
 * 
 * @author Niels Warnars
 */
public class NPC extends LevelElement {
  private int life;
  private int status;
  private boolean hasFruit;
  
  /**
   * Create a new NPC instance.
   * 
   * @param position
   *          Position of the NPC in the level.
   * @param size
   *          Size of the NPC.
   */
  public NPC(Vector position, Vector size) {
    super(position, size);
    
    setGravity(true);
  }

  
  /**
   * Helper method used to determine the position of 
   * a Platform in an ArrayList<Platform> based on
   * a given position.
   * 
   * @param platforms
   *          An ArrayList of Platforms
   * @param pos
   *          The position of a certain Platform
   * @return
   *          The index of a certain Platform in an
   *          ArrayList
   */
  protected static int getIndexFromPos(ArrayList<Platform> platforms, Vector pos) {
    for (int i = 0; i < platforms.size(); i++) {
      if (pos.equals(platforms.get(i).getPosition())) {
        return i;
      }
    }
    
    return -1;
  }
  
  
  /**
   * Returns an ArrayList of platform objects with 
   * the same height.
   * 
   * @param platforms
   *            An ArrayList of platform objects
   * @return
   *            An ArrayList of platform objects
   *            with the same height
   */
  protected ArrayList<Platform> getSameHeightPlatforms(ArrayList<Platform> platforms) {
    ArrayList<Platform> res = new ArrayList<Platform>();
    
    for (Platform platform : platforms) {
      if (this.getPosition().getY() == platform.getPosition().getY()) {
        res.add(platform);
      }
    }
    
    return res;
  }
  
  
  /**
   * Returns a vector array with the left and right
   * boundaries of the underlying floor.
   * 
   * @param platformsIn
   *          An ArrayList of Platforms
   * @return
   *          A Vector array with the left and right
   *          boundaries of the underlying floor
   */
  protected Vector[] floorMovementBoundaries(ArrayList<Platform> platforms) {
    Vector[] floorBoundaries = new Vector[2]; 
    Vector pUnderEnemyPos = Vector.sum(getPosition(), new Vector(0, Constants.BLOCKSIZE));
    
    
    // Get left most platform boundary
    for (int i = getIndexFromPos(platforms, pUnderEnemyPos); i > 0; i--) {
      Vector currentPos = platforms.get(i).getPosition();
      Vector previousPos = platforms.get(i - 1).getPosition();
      Vector expectedPos = Vector.difference(currentPos, new Vector(Constants.BLOCKSIZE, 0));

      if (!previousPos.equals(expectedPos)) {
        floorBoundaries[0] = new Vector(currentPos.getX(), currentPos.getY());
        break;
      }
    }
    
    
    // Get right most platform boundary
    for (int i = getIndexFromPos(platforms, pUnderEnemyPos); i < platforms.size(); i++) {
      Vector currentPos = platforms.get(i).getPosition();
      Vector nextPos = platforms.get(i + 1).getPosition();
      Vector expectedPos = Vector.sum(currentPos, new Vector(Constants.BLOCKSIZE, 0));

      if (!nextPos.equals(expectedPos)) {
        floorBoundaries[1] = new Vector(currentPos.getX(), currentPos.getY());
        break;
      }
    }
   
    
    // Move Y coords up by one position to represent the row of the enemy
    floorBoundaries[0].difference(new Vector(0, Constants.BLOCKSIZE));
    floorBoundaries[1].difference(new Vector(0, Constants.BLOCKSIZE));
    
    return floorBoundaries;
  }
  
  
  /**
   * Returns a vector array with the left and right
   * obstacle boundaries.
   * 
   * @param platformsIn
   *          An ArrayList of Platforms
   * @return
   *          A Vector array with the left and right
   *          obstacle boundaries
   */
  protected Vector[] obstacleMovementBoundaries(ArrayList<Platform> platformsIn) {
    ArrayList<Platform> platforms = new ArrayList<Platform>();
    
    for (Platform platform : platformsIn) {
      platforms.add(platform);
    }
    
    Vector[] boundaries = new Vector[2]; 
    ArrayList<Platform> sameHeightPlatforms = getSameHeightPlatforms(platforms);

    // Instantiate the boundaries as the most left and right positions.
    boundaries[0] = new Vector(0, getPosition().getY());
    boundaries[1] = new Vector(Constants.NUM_BLOCKS * Constants.BLOCKSIZE, getPosition().getY());
    
    // Get the left obstacle boundary
    for (Platform platform : sameHeightPlatforms) {
      double platformX = platform.getPosition().getX();
      if (platformX < getPosition().getX() && platformX > boundaries[0].getX()) {
        boundaries[0] = new Vector(platform.getPosition().getX(), platform.getPosition().getY());
      }
    }
    
    // Get the right obstacle boundary
    for (Platform platform : sameHeightPlatforms) {
      double platformX = platform.getPosition().getX();
      if (platformX > getPosition().getX() && platformX < boundaries[1].getX()) {
        boundaries[1] = new Vector(platform.getPosition().getX(), platform.getPosition().getY());
      }
    }
   
    boundaries[0].sum(new Vector(Constants.BLOCKSIZE, 0));
    boundaries[1].difference(new Vector(Constants.BLOCKSIZE, 0));
    
    return boundaries;
  }
  
  
  /**
   * Returns a vector array with the left and right
   * coordinates of free horizontal movement space.
   * 
   * @param platformsIn
   *          An ArrayList of Platforms
   * @return
   *          A Vector array with left and right coordinates 
   *          of free horizontal movement space.
   */
  protected Vector[] movementBoundaries(ArrayList<Platform> platforms) {
    Vector[] boundaries = floorMovementBoundaries(platforms); 
    Vector[] obstacleBoundaries = obstacleMovementBoundaries(platforms);


    // Alter left boundary if an obstacle is present
    if (obstacleBoundaries[0].getX() > boundaries[0].getX()) {
      boundaries[0] = new Vector(obstacleBoundaries[0].getX(), getPosition().getY());
    }
    // Alter right boundary if an obstacle is present
    if (obstacleBoundaries[1].getX() < boundaries[1].getX()) {
      boundaries[1] = new Vector(obstacleBoundaries[1].getX(), getPosition().getY());
    }
    
    return boundaries;    
  }
  
  /**
   * Check whether a given object is equal to this instance.
   * 
   * @param other
   *          Another instance.
   * @return A Boolean.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof NPC) {
      NPC that = (NPC) other;
      return (this.getPosition().equals(that.getPosition()) 
          && this.getSize().equals(that.getSize()));
    }
    
    return false;
  }


  /**
   * Get the life of an NPC.
   * 
   * @return
   *        The life of an NPC
   */
  public int getLife() {
    return life;
  }


  /**
   * Set the life of an NPC.
   * 
   * @param life
   *        The life of an NPC
   */
  public void setLife(int life) {
    this.life = life;
  }


  /**
   * Get the status of an NPC.
   * 
   * @return
   *        The status of an NPC
   */
  public int getStatus() {
    return status;
  }


  /**
   * Set the status of an NPC.
   * 
   * @param status
   *        The status of an NPC
   */
  public void setStatus(int status) {
    this.status = status;
  }


  /**
   * Check whether an NPC has a fruit.
   * 
   * @return
   *        Whether an NPC has a fruit
   */
  public boolean hasFruit() {
    return hasFruit;
  }


  /**
   * Set whether an NPC has a fruit.
   * 
   * @param hasFruit
   *         Whether an NPC has a fruit.
   */
  public void setHasFruit(boolean hasFruit) {
    this.hasFruit = hasFruit;
  }
  
}
