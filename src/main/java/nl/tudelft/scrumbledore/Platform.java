package nl.tudelft.scrumbledore;

/**
 * Class representing a Platform in a game.
 * 
 * @author Niels Warnars
 */
public class Platform extends LevelElement {

  /**
   * Create a new Platform instance.
   * 
   * @param position
   *          Position of the platform in the level.
   * @param size
   *          Size of the platform.
   */
  public Platform(Vector position, Vector size) {
    super(position, size);
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
    if (other instanceof Platform) {
      Platform that = (Platform) other;
      return (this.getPosition().equals(that.getPosition()) && this.getSize() == that.getSize());
    }
    
    return false;
  }

}
