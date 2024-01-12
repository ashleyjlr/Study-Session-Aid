package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 *
 */
class StringToProblemsTest {
  /**
   * test that converts a string to an arraylist of problems
   */
  @Test
  void testApply() {
    String srQandAnsFile = "What does ROYGBIV stand for?\n"
        + "red orange yellow green blue indigo violet\n"
        + "HARD\n"
        + "\n"
        + "How much wood would a woodchuck chuck if a woodchuck could chuck wood?\n"
        + "a woodchuck could chuck as much wood as a woodchuck could or 700 pounds\n"
        + "HARD\n"
        + "\n"
        + "How many licks does it take to get to the center of a tootsie pop?\n"
        + "The world may never know\n"
        + "HARD\n"
        + "\n"
        + "What is the name of the villain in Peter Pan?\n"
        + "Captain Hook\n"
        + "HARD\n"
        + "\n"
        + "Where does the does \"My Favorite Things\" come from?\n"
        + "The Sound of Music\n"
        + "HARD";

    Problem problem1 = new Problem("What does ROYGBIV stand for?",
        "red orange yellow green blue indigo violet");
    Problem problem2 = new Problem(
        "How much wood would a woodchuck chuck if a woodchuck could chuck wood?",
        "a woodchuck could chuck as much wood as a woodchuck could or 700 pounds");
    Problem problem3 = new Problem(
        "How many licks does it take to get to the center of a tootsie pop?",
        "The world may never know");
    Problem problem4 = new Problem("What is the name of the villain in Peter Pan?",
        "Captain Hook");
    Problem problem5 = new Problem("Where does the does \"My Favorite Things\" come from?",
        "The Sound of Music");

    ArrayList<Problem> expectedListOfProblems = new ArrayList<>();
    expectedListOfProblems.add(problem1);
    expectedListOfProblems.add(problem2);
    expectedListOfProblems.add(problem3);
    expectedListOfProblems.add(problem4);
    expectedListOfProblems.add(problem5);

    StringToProblems allProblems = new StringToProblems();
    ArrayList<Problem> actualListOfProblems = allProblems.apply(srQandAnsFile);

    assertEquals(expectedListOfProblems, actualListOfProblems);
  }
}