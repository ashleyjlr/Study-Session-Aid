package cs3500.pa02;

import static cs3500.pa02.Difficulty.EASY;
import static cs3500.pa02.Difficulty.HARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ProblemTest {

  /**
   * tests that the method, changeDifficulty, correctly changes the difficulty of a problem
   */
  @Test
  void testChangeDifficulty() {
    Problem problemEasy = new Problem("What is 5+5?", "10", EASY);
    Problem problemHard = new Problem("What is 5+5?", "10");
    problemEasy.changeDifficulty(HARD);
    assertEquals(problemHard, problemEasy);
  }

  /**
   * test that equals fails when not given the same type to compare to
   */
  @Test
  void testEqualsFailsWithType() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);

    String problemToString = "How many feet in a yard?\n3\nEASY";
    assertFalse(problem.equals(problemToString));
  }

  /**
   * test that equals fails when not given the same question
   */
  @Test
  void testEqualsFailsWithQuestion() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);
    Problem problemScience = new Problem("What is the symbol for Hydrogen?", "H", EASY);
    assertFalse(problem.equals(problemScience));
  }

  /**
   * test that equals fails when not given the same answer
   */
  @Test
  void testEqualsFailsWithAnswer() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);
    Problem problemScience = new Problem("How many feet in a yard?", "H", EASY);
    assertFalse(problem.equals(problemScience));
  }

  /**
   * tests that the toString method properly returns the string of a problem
   */
  @Test
  void testToString() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);

    String problemToString = "How many feet in a yard?\n3\nEASY";
    assertEquals(problemToString, problem.toString());
  }

  /**
   * tests that two hash codes of the same content of problems passes equality
   */
  @Test
  void testHashCodePass() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);
    Problem problem2 = new Problem("How many feet in a yard?", "3", EASY);

    assertTrue(problem.hashCode() == problem2.hashCode());
  }

  /**
   * tests that two hash codes of different content of problems fail equality
   */
  @Test
  void testHashCodeFail() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);
    Problem problem2 = new Problem("How many feet in a yard?", "H", HARD);

    assertFalse(problem.hashCode() == problem2.hashCode());
  }

  /**
   * tests that two hash codes of different content of problems fail equality
   */
  @Test
  void testCountEasy() {
    Problem problemTrue = new Problem("How many feet in a yard?", "3", EASY);
    Problem problemFalse = new Problem("How many feet in a yard?", "H", HARD);

    assertEquals(1, problemTrue.countEasy());
    assertEquals(0, problemFalse.countEasy());
  }

  /**
   * tests that two hash codes of different content of problems fail equality
   */
  @Test
  void testCountHard() {
    Problem problemFalse = new Problem("How many feet in a yard?", "3", EASY);
    Problem problemTrue = new Problem("How many feet in a yard?", "H", HARD);

    assertEquals(0, problemFalse.countHard());
    assertEquals(1, problemTrue.countHard());
  }

  /**
   * tests getting a difficulty
   */
  @Test
  void testGetDifficulty() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);

    assertEquals(EASY, problem.getDifficulty());
  }

  /**
   * tests getting a question
   */
  @Test
  void testGetQuestion() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);

    assertEquals("How many feet in a yard?", problem.getQuestion());
  }

  /**
   * tests getting an answer
   */
  @Test
  void testGetAnswer() {
    Problem problem = new Problem("How many feet in a yard?", "3", EASY);

    assertEquals("3", problem.getAnswer());
  }
}