package cs3500.pa02;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * represents a function object that converts a string to an array list of problems
 */
public class StringToProblems implements Function<String, ArrayList<Problem>> {
  /**
   * @param stringOfProblems the function argument which will be
   *                         a string of the contents of the SR file
   * @return an array list of problems created from the string
   */
  @Override
  public ArrayList<Problem> apply(String stringOfProblems) {
    ArrayList<Problem> allProblems = new ArrayList<>();
    // splits it between the blank lines between sections
    String[] sections = stringOfProblems.split("\n\n");

    // iterates through the sections of three lines
    for (String section : sections) {
      // splits each line into 3
      String[] lines = section.split("\n");
      // creates an instance of the problem from the strings
      // and adds it to the arraylist
      String question = lines[0];
      String answer = lines[1];
      Difficulty difficulty = Difficulty.valueOf(lines[2]);
      Problem problem = new Problem(question, answer, difficulty);
      allProblems.add(problem);
    }
    return allProblems;
  }
}
