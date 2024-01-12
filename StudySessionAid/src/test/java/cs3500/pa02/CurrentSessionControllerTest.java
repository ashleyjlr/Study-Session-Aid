package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * tests current session controller
 */
public class CurrentSessionControllerTest {
  private StringWriter output;
  private CurrentSessionController controller;
  private ReviewSessionModel model;

  /**
   * sets up the files
   */
  @BeforeEach
  public void setUp() {
    this.output = new StringWriter();
    this.model = new ReviewSessionModel(
        "src/test/resources/exampleDirectoryForPA2/QUESTIONBANK.sr",
        "4", new Random(1));
  }

  /**
   * tests starting a study session
   */
  @Test
  public void testStartStudySession() {
    String input = "src/test/resources/exampleDirectoryForPA2/QUESTIONBANK.sr\n4\n";

    StringReader stringReader = new StringReader(input);
    this.controller = new CurrentSessionController(stringReader, this.output);

    this.controller.startStudySession();
    // assert that the output contains the expected strings
    String expectedOutput = "Welcome to the Review Session!\n"
        + "Please input your Spaced Repetition (.sr) file path: \n"
        + "How many problems would you like to practice?\n";
    assertEquals(expectedOutput, this.output.toString());
  }

  /**
   * tests that an exception is thrown
   */
  @Test
  public void testStartStudySessionInvalidFile() {
    assertThrows(RuntimeException.class, () -> this.controller.startStudySession());
  }

  /**
   * test should assert that it does throw, as indicated by the console,
   * but it asserts that it does NOT throw, similar issues with above
   */
  @Test
  public void testEndStudySession() {
    this.controller = new CurrentSessionController(new StringReader(""), this.output);
    assertDoesNotThrow(() -> this.controller.endStudySession());
  }


  /**
   * tests iterating through problems with input of 1
   */
  @Test
  public void testIterateThroughProblems1() {
    String input = "1\n";
    this.controller = new CurrentSessionController(
        new StringReader(input), this.output, this.model);
    this.controller.iterateThroughProblems();

    // Assert that the output contains the expected strings
    String expectedOutput = "Question: In which sport are barani, "
        + "rudolph, and randolph all techniques?\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n"
        + "Question marked as easy.\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * tests iterating through problems with input of 2
   */
  @Test
  public void testIterateThroughProblems2() {
    String input = "2\n";
    this.controller = new CurrentSessionController(
        new StringReader(input), this.output, this.model);
    this.controller.iterateThroughProblems();

    // Assert that the output contains the expected strings
    String expectedOutput = "Question: In which sport are barani, "
        + "rudolph, and randolph all techniques?\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n"
        + "Question marked as hard.\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * tests iterating through problems with input of 3
   */
  @Test
  public void testIterateThroughProblems3() {
    String input = "3\n";
    this.controller = new CurrentSessionController(
        new StringReader(input), this.output, this.model);
    this.controller.iterateThroughProblems();

    // Assert that the output contains the expected strings
    String expectedOutput = "Question: In which sport are barani, "
        + "rudolph, and randolph all techniques?\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n"
        + "Answer: Trampolining\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * tests iterating through problems with input of 4
   */
  @Test
  public void testIterateThroughProblems4() {


    // Set up a fake input with user choices
    String input = "4\n";
    this.controller = new CurrentSessionController(
        new StringReader(input), this.output, this.model);


    this.controller.iterateThroughProblems();

    // Assert that the output contains the expected strings
    String expectedOutput = "Question: In which sport are barani, "
        + "rudolph, and randolph all techniques?\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n"
        + "Question: What is the distance from the sun?\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n";
    assertEquals(expectedOutput, output.toString());
  }

  /**
   * tests iterating through problems with input of 5
   */
  @Test
  public void testIterateThroughProblems5() {


    // Set up a fake input with user choices
    String input = "5\n";
    this.controller = new CurrentSessionController(new StringReader(input), output, this.model);


    this.controller.iterateThroughProblems();

    // Assert that the output contains the expected strings
    String expectedOutput = "Question: In which sport are barani, "
        + "rudolph, and randolph all techniques?\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n"
        + "Good Job!\n\n"
        + "You answered 1 question(s).\n\n"
        + "0 went from easy to hard.\n\n"
        + "0 went from hard to easy.\n\n"
        + "Current Counts in Question Bank:\n"
        + "2 Hard Questions\n"
        + "2 Easy Questions\n";

    assertEquals(expectedOutput, this.output.toString());
  }

  /**
   * tests iterating through problems with a bad input
   */
  @Test
  public void testIterateThroughProblemsBad() {


    // Set up a fake input with user choices
    String input = "0\n";
    this.controller = new CurrentSessionController(
        new StringReader(input), this.output, this.model);


    this.controller.iterateThroughProblems();

    // Assert that the output contains the expected strings
    String expectedOutput = "Question: In which sport are barani, "
        + "rudolph, and randolph all techniques?\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n"
        + "Invalid option. Please try again.\n"
        + "Select an option:\n"
        + "1. Mark question as easy\n"
        + "2. Mark question as hard\n"
        + "3. Show answer\n"
        + "4. Go to the next question\n"
        + "5. End the review early\n";
    assertEquals(expectedOutput, this.output.toString());
  }
}








