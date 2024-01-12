package cs3500.pa02;

import static cs3500.pa02.Difficulty.EASY;
import static cs3500.pa02.Difficulty.HARD;

/**
 * represents a problem within a study session and contains question, answer, and difficulty.
 */
public class Problem {
  private final String question;
  private final String answer;
  private Difficulty difficulty;

  Problem(String question, String answer) {
    this.question = question;
    this.answer = answer;
    this.difficulty = HARD;
  }

  Problem(String question, String answer, Difficulty difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   * gets the difficulty of this problem
   *
   * @return the difficulty of this problem
   */
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * a method that will change the difficulty of this problem
   *
   * @param difficulty which represents either Hard or Easy
   */
  public void changeDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * a method that will output the string of this problem
   *
   * @return a string representing this problem
   */
  @Override
  public String toString() {
    return this.question + "\n" + this.answer + "\n" + this.difficulty.toString();
  }

  /**
   * @param other which represents the other object to be compared to
   * @return a boolean which represents whether this object is equal to the given object
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Problem otherProblem)) {
      return false;
    }

    return this.question.equals(otherProblem.question) && this.answer.equals(otherProblem.answer);
  }

  /**
   * overrides hashcode for this problem
   *
   * @return an int representing the hash coded of a problem
   */
  public int hashCode() {
    return this.question.hashCode() * this.answer.hashCode() + this.difficulty.hashCode();
  }

  /**
   * checks whether the difficulty of the problem is hard, if yes it returns 1
   *
   * @return an int indicating the difficulty of this problem as hard
   */
  public int countHard() {
    if (this.difficulty == HARD) {
      return 1;
    }
    return 0;
  }

  /**
   * checks whether the difficulty of the problem is easy, if yes it returns 1
   *
   * @return an int indicating the difficulty of this problem as easy
   */
  public int countEasy() {
    if (this.difficulty == EASY) {
      return 1;
    }
    return 0;
  }

  /**
   * gets the question of this problem
   *
   * @return returns the string of the question in this problem
   */
  public String getQuestion() {
    return this.question;
  }

  /**
   * gets the answer of this problem
   *
   * @return returns the string of the answer in this problem
   */
  public String getAnswer() {
    return this.answer;
  }
}
