package cs3500.pa02;

import static cs3500.pa02.Difficulty.EASY;
import static cs3500.pa02.Difficulty.HARD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class ReviewSessionModelTest {

  /**
   * tests exception in constructor
   */
  @Test
  void testConstructorException() {

    assertThrows(RuntimeException.class,
        () -> new ReviewSessionModel("", ""));
  }

  /**
   * tests changing the problem to easy
   */
  @Test
  void testChangeToEasy() {
    ReviewSessionModel model = new ReviewSessionModel();
    Problem hardProblem = new Problem("hi?", "goodbye.", HARD);
    Problem easyProblem = new Problem("hi?", "goodbye.", Difficulty.EASY);

    model.changeToEasy(hardProblem);
    assertEquals(easyProblem, hardProblem);
  }

  /**
   * tests changing the problem to easy, when it is easy
   */
  @Test
  void testChangeToEasyFromEasy() {
    ReviewSessionModel model = new ReviewSessionModel();
    Problem easyProblem = new Problem("hi?", "goodbye.", Difficulty.EASY);
    Problem easyProblemNew = new Problem("hi?", "goodbye.", Difficulty.EASY);

    model.changeToEasy(easyProblemNew);
    assertEquals(easyProblem, easyProblemNew);
  }


  /**
   * tests changing the problem to hard
   */
  @Test
  void testChangeToHard() {
    ReviewSessionModel model = new ReviewSessionModel();
    Problem hardProblem = new Problem("hi?", "goodbye.", HARD);
    Problem easyProblem = new Problem("hi?", "goodbye.", Difficulty.EASY);

    model.changeToHard(easyProblem);
    assertEquals(hardProblem, easyProblem);
  }

  /**
   * tests changing the problem to hard, when it is hard
   */
  @Test
  void testChangeToHardFromHard() {
    ReviewSessionModel model = new ReviewSessionModel();
    Problem hardProblem = new Problem("hi?", "goodbye.", HARD);
    Problem hardProblemNew = new Problem("hi?", "goodbye.", HARD);

    model.changeToHard(hardProblemNew);
    assertEquals(hardProblem, hardProblemNew);
  }

  /**
   * test calculate easy to hard
   */
  @Test
  void testCalculateEasyToHard() {
    ReviewSessionModel model = new ReviewSessionModel();
    Problem problem = new Problem("I hate", "this", Difficulty.EASY);
    model.changeToHard(problem);

    assertEquals(1, model.calculateEasyToHard());
  }

  /**
   * test calculate hard to easy
   */
  @Test
  void testCalculateHardToEasy() {
    ReviewSessionModel model = new ReviewSessionModel();
    Problem problem = new Problem("I hate", "this", HARD);
    model.changeToEasy(problem);

    assertEquals(1, model.calculateHardToEasy());
  }


  /**
   * test toProblems
   */
  @Test
  void testToProblems() {

    ArrayList<Problem> expectedList = new ArrayList<>();
    Problem problem1 = new Problem(
        "In which sport are barani, rudolph, and randolph all techniques?",
        "Trampolining",
        HARD);

    Problem problem2 = new Problem(
        "What is 5 + 5?",
        "10",
        EASY);

    Problem problem3 = new Problem(
        "What is the distance from the sun?",
        "94.01 million mi",
        HARD);

    Problem problem4 = new Problem(
        "What is socially known as man's \"best friend\"?",
        "Dog",
        EASY);

    expectedList.add(problem1);
    expectedList.add(problem2);
    expectedList.add(problem3);
    expectedList.add(problem4);

    ReviewSessionModel reviewSession = new ReviewSessionModel(
        "src/test/resources/exampleDirectoryForPA2/QUESTIONBANK.sr",
        "4", new Random(1));
    assertEquals(expectedList, reviewSession.toProblems());
  }

  /**
   * test number of easy
   */
  @Test
  void testNumberOfEasy() {
    ReviewSessionModel reviewSession = new ReviewSessionModel(
        "src/test/resources/exampleDirectoryForPA2/QUESTIONBANK.sr",
        "4");

    assertEquals(2, reviewSession.numberOfEasy());
  }

  /**
   * test number of hard
   */
  @Test
  void testNumberOfHard() {
    ReviewSessionModel reviewSession = new ReviewSessionModel(
        "src/test/resources/exampleDirectoryForPA2/QUESTIONBANK.sr",
        "4");

    assertEquals(2, reviewSession.numberOfHard());
  }

  /**
   * test to SR
   */
  @Test
  void testToSr() {
    ReviewSessionModel reviewSession =
        new ReviewSessionModel(
            "src/test/resources/exampleDirectoryForPA2/QUESTIONBANKCOPY.sr",
        "4");

    reviewSession.toSr();
    String resultString = new cs3500.pa02.SrToQuestions().readFromFile(
        new File("src/test/resources/exampleDirectoryForPA2/QUESTIONBANKCOPY.sr"));

    String expectedString = "In which sport are barani, rudolph, and randolph all techniques?\n"
        + "Trampolining\n"
        + "HARD\n"
        + "\n"
        + "What is 5 + 5?\n"
        + "10\n"
        + "EASY\n"
        + "\n"
        + "What is the distance from the sun?\n"
        + "94.01 million mi\n"
        + "HARD\n"
        + "\n"
        + "What is socially known as man's \"best friend\"?\n"
        + "Dog\n"
        + "EASY"
        + "\n";

    assertEquals(expectedString, resultString);
  }


  /**
   * tests generate problem
   */
  @Test
  public void testGenerateProblem() {
    ReviewSessionModel reviewSession = new ReviewSessionModel(
        "src/test/resources/exampleDirectoryForPA2/QUESTIONBANKCOPY.sr",
        "4", new Random(1));

    Iterator<Problem> iterator = reviewSession.generateProblems();
    List<Problem> actualProblems = new ArrayList<>();
    while (iterator.hasNext()) {
      actualProblems.add(iterator.next());
    }
    ArrayList<Problem> expectedList = new ArrayList<>();
    Problem problem1 = new Problem(
        "In which sport are barani, rudolph, and randolph all techniques?",
        "Trampolining",
        HARD);

    Problem problem2 = new Problem(
        "What is 5 + 5?",
        "10",
        EASY);

    Problem problem3 = new Problem(
        "What is the distance from the sun?",
        "94.01 million mi",
        HARD);

    Problem problem4 = new Problem(
        "What is socially known as man's \"best friend\"?",
        "Dog",
        EASY);

    expectedList.add(problem1);
    expectedList.add(problem3);
    expectedList.add(problem4);
    expectedList.add(problem2);



    assertEquals(expectedList, actualProblems);
  }

  /**
   * tests generate problem with a larger input number
   */
  @Test
  public void testGenerateProblemLargerNumber() {
    ReviewSessionModel reviewSession = new ReviewSessionModel(
        "src/test/resources/exampleDirectoryForPA2/QUESTIONBANKCOPY.sr",
        "5", new Random(1));

    Iterator<Problem> iterator = reviewSession.generateProblems();
    List<Problem> actualProblems = new ArrayList<>();
    while (iterator.hasNext()) {
      actualProblems.add(iterator.next());
    }
    ArrayList<Problem> expectedList = new ArrayList<>();
    Problem problem1 = new Problem(
        "In which sport are barani, rudolph, and randolph all techniques?",
        "Trampolining",
        HARD);

    Problem problem2 = new Problem(
        "What is 5 + 5?",
        "10",
        EASY);

    Problem problem3 = new Problem(
        "What is the distance from the sun?",
        "94.01 million mi",
        HARD);

    Problem problem4 = new Problem(
        "What is socially known as man's \"best friend\"?",
        "Dog",
        EASY);

    expectedList.add(problem1);
    expectedList.add(problem3);
    expectedList.add(problem4);
    expectedList.add(problem2);



    assertEquals(expectedList, actualProblems);
  }
}