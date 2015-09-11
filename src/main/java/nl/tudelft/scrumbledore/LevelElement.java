package nl.tudelft.scrumbledore;

/**
 * Abstract class representing an element that can be placed in a Level.
 * 
 * @author Jesse Tilro
 *
 */
public abstract class LevelElement {

  private Vector position;
  private Vector size;
  private Vector speed;
  private Vector friction;
  private boolean gravity;

  /**
   * Create a new LevelElement instance.
   * 
   * @param position
   *          Position of the element in the level.
   * @param size
   *          Size of the element.
   */
  public LevelElement(Vector position, Vector size) {
    this.position = position;
    this.size = size;
    // Initial speed is 0.
    this.speed = new Vector(0, 0);
    // Initial friction is 0.
    this.friction = new Vector(0, 0);
    // By default not affected by Gravity.
    this.gravity = false;
  }

  /**
   * Get the position vector of this element.
   * 
   * @return Position Vector.
   */
  public Vector getPosition() {
    return position;
  }

  /**
   * Get the X coordinate of the element.
   * 
   * @return double
   */
  public double posX() {
    return position.getX();
  }

  /**
   * Get the Y coordinate of the element.
   * 
   * @return double
   */
  public double posY() {
    return position.getY();
  }

  /**
   * Get the size vector of this element.
   * 
   * @return Size Vector.
   */
  public Vector getSize() {
    return size;
  }

  /**
   * Get the width of the element.
   * 
   * @return double
   */
  public double width() {
    return size.getX();
  }

  /**
   * Get the height of the element.
   * 
   * @return double
   */
  public double height() {
    return size.getY();
  }

  /**
   * Get the speed vector of this element.
   * 
   * @return Speed Vector.
   */
  public Vector getSpeed() {
    return speed;
  }

  /**
   * Get the horizontal speed of the element.
   * 
   * @return double
   */
  public double hSpeed() {
    return speed.getX();
  }

  /**
   * Get the vertical speed of the element.
   * 
   * @return double
   */
  public double vSpeed() {
    return speed.getY();
  }

  /**
   * Get the friction vector of this element.
   * 
   * @return Friction Vector.
   */
  public Vector getFriction() {
    return friction;
  }

  /**
   * Get the horizontal friction.
   * 
   * @return Horizontal friction.
   */
  public double hFric() {
    return friction.getX();
  }

  /**
   * Get the vertical friction.
   * 
   * @return Vertical friction.
   */
  public double vFric() {
    return friction.getY();
  }

  /**
   * Check whether this LevelElement is affected by Gravity.
   * 
   * @return Boolean
   */
  public boolean hasGravity() {
    return gravity;
  }

  /**
   * Set the property determining whether this LevelElement is affected by gravity.
   * 
   * @param gravity
   *          A boolean
   */
  public void setGravity(boolean gravity) {
    this.gravity = gravity;
  }

  /**
   * Get the absolute Y-coordinate of the top of this element, given the position and size.
   * 
   * @return Y-coordinate of top.
   */
  public double getTop() {
    return position.getY() - size.getY() / 2;
  }

  /**
   * Get the absolute Y-coordinate of the bottom of this element, given the position and size.
   * 
   * @return Y-coordinate of bottom.
   */
  public double getBottom() {
    return position.getY() + size.getY() / 2;
  }

  /**
   * Get the absolute X-coordinate of the left side of this element, given the position and size.
   * 
   * @return X-coordinate of left side.
   */
  public double getLeft() {
    return position.getX() - size.getX() / 2;
  }

  /**
   * Get the absolute X-coordinate of the right side of this element, given the position and size.
   * 
   * @return X-coordinate of right side.
   */
  public double getRight() {
    return position.getX() + size.getX() / 2;
  }

  /**
   * Get the distance to another LevelElement.
   * 
   * @param other
   *          The other element to measure the distance to.
   * @return The distance.
   */
  public double distance(LevelElement other) {
    return getPosition().distance(other.getPosition());
  }

  /**
   * Check whether another element is within range of this element using a circular radius by
   * computing the distance.
   * 
   * @param other
   *          The other element.
   * @param range
   *          The range (of the circal).
   * @return A boolean.
   */
  public boolean inRadiusRangeOf(LevelElement other, double range) {
    double dist = distance(other);
    return (dist <= range);
  }

  /**
   * Check whether another element is within range of this element using a box. The box is a square
   * axis-aligned bounding box, with dimensions of twice the given range. It has the position of
   * this element as its center.
   * 
   * @param other
   *          The other element.
   * @param range
   *          The range (a half of the dimensions of the square box).
   * @return A boolean.
   */
  public boolean inBoxRangeOf(LevelElement other, double range) {
    boolean inX = (other.posX() >= posX() - range && other.posX() <= posX() + range);
    boolean inY = (other.posY() >= posY() - range && other.posY() <= posY() + range);
    return (inX && inY);
  }

}