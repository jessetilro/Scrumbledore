package nl.tudelft.scrumbledore;

/**
 * The Kinetics class handles the position/speed of levelelements.
 * 
 * @author Floris Doolaard
 *
 */
public final class Kinetics {
  private Kinetics() {

  }

  /**
   * Update the position of the LevelElement by adding the speed.
   * 
   * @param el
   *          The element whose position has to be updated with its speed.
   * @param d
   *          The number of steps since last executing this function.
   */
  public static void addSpeed(LevelElement el, double d) {
    el.getPosition().sum(Vector.scale(el.getSpeed(), d));
  }

  /**
   * Update all elements in a given Level.
   * 
   * @param level
   *          The level whose elements should be updated.
   * @param d
   *          The number of steps since last executing this function.
   */
  public static void update(Level level, double d) {
    for (LevelElement el : level.getElements()) {
      addSpeed(el, d);
    }
  }
}
