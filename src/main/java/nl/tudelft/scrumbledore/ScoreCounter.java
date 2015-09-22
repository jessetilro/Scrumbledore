package nl.tudelft.scrumbledore;

/**
 * A counter given to the Game class as an attribute.
 * 
 * @author Floris Doolaard
 * @author David Alderliesten
 */
public class ScoreCounter {
  private int score;

  /**
   * Contructing a a ScoreCounter object.
   *
   */
  public ScoreCounter() {
    // Initial score is 0
    this.score = 0;
  }

  /**
   * Return the current total score.
   * 
   * @return The current score.
   */
  public int getScore() {
    return score;
  }

  /**
   * Returns the current total score in the form of a string.
   * 
   * @return The current score as a string.
   */
  public String getScoreString() {
    String toReturn = "";

    toReturn = toReturn + score;

    return toReturn;
  }

  /**
   * Updating the current score with additional score.
   * 
   * @param addScore
   *          The score to be added to the total score.
   */
  public void updateScore(int addScore) {
    score += addScore;
  }

}