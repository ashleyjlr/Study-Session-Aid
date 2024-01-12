package cs3500.pa02;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * represents the model for a review session
 */
public class ReviewSessionModel {
  private Path pathToFile;
  private ArrayList<Problem> allProblems;
  private int problemAmount;
  private int changedToHard;
  private int changedToEasy;
  private Random random;

  ReviewSessionModel(String pathToFile, String problemAmount) {
    try {
      Path path = Path.of(pathToFile);
      this.pathToFile = path;
    } catch (RuntimeException e) {
      throw new RuntimeException("Please provide an .sr file.");
    }
    try {
      int problemNum = Integer.parseInt(problemAmount);
      this.problemAmount = problemNum;
    } catch (NumberFormatException e) {
      throw new RuntimeException("Provide an int.");
    }

    this.allProblems = this.toProblems();
    this.changedToHard = 0;
    this.changedToEasy = 0;
    this.random = new Random();
  }

  ReviewSessionModel(String pathToFile, String problemAmount, Random random) {
    try {
      Path path = Path.of(pathToFile);
      this.pathToFile = path;
    } catch (RuntimeException e) {
      throw new RuntimeException("Please provide an .sr file.");
    }
    try {
      int problemNum = Integer.parseInt(problemAmount);
      this.problemAmount = problemNum;
    } catch (NumberFormatException e) {
      throw new RuntimeException("Provide an int.");
    }

    this.allProblems = this.toProblems();
    this.changedToHard = 0;
    this.changedToEasy = 0;
    this.random = random;
  }

  ReviewSessionModel() {
    this.changedToHard = 0;
    this.changedToEasy = 0;
  }

  /**
   * converts the path file to a string to an array list of problems
   *
   * @return an array list of each individual problem extracted from the given file
   */
  public ArrayList<Problem> toProblems() {
    return new StringToProblems().apply(new SrToQuestions().apply(this.pathToFile));
  }

  /**
   * converts the arraylist of problems to strings to write to the file
   * used when the session is over to show the updated problems
   */
  public void toSr() {
    String stringForSrFile = "";
    for (Problem p : this.allProblems) {
      stringForSrFile += p.toString() + "\n\n";
    }
    int lastNewLines = stringForSrFile.lastIndexOf("\n") - 1;

    new StringToFile().writeToFile(this.pathToFile.toFile(),
        stringForSrFile.substring(0, lastNewLines), "");
  }

  /**
   * a generator for random lists using the amount of problems needed for this review session
   *
   * @return returns an iterator that iterates over a list of shuffled questions from hard to easy
   */
  public Iterator<Problem> generateProblems() {
    if (this.allProblems.size() < this.problemAmount) {
      this.problemAmount = this.allProblems.size();
    }
    Map<Difficulty, List<Problem>> problems =
        this.allProblems.stream().collect(Collectors.groupingBy(Problem::getDifficulty));

    List<Problem> hardProblems = problems.get(Difficulty.HARD);
    Collections.shuffle(hardProblems, this.random);
    List<Problem> easyProblems = problems.get(Difficulty.EASY);
    Collections.shuffle(easyProblems, this.random);

    List<Problem> allSortedAndShuffledProblems = new ArrayList<>();
    allSortedAndShuffledProblems.addAll(hardProblems);
    allSortedAndShuffledProblems.addAll(easyProblems);

    List<Problem> sessionProblems = new ArrayList<>();
    for (int i = 0; i < this.problemAmount; i += 1) {
      sessionProblems.add(allSortedAndShuffledProblems.get(i));
    }
    return sessionProblems.listIterator();
  }

  /**
   * counts the number of hard problems
   *
   * @return the number of hard problems in this review session
   */
  public int numberOfHard() {
    int counter = 0;
    for (Problem p : this.allProblems) {
      counter = counter + p.countHard();
    }
    return counter;
  }

  /**
   * counts the number of easy problems
   *
   * @return the number of easy problems in this review session
   */
  public int numberOfEasy() {
    int counter = 0;
    for (Problem p : this.allProblems) {
      counter = counter + p.countEasy();
    }
    return counter;
  }

  /**
   * calculates the number of questions changed from easy to hard
   *
   * @return an int of the number of questions changed from easy to hard
   */
  public int calculateEasyToHard() {
    return this.changedToHard;
  }

  /**
   * calculates the number of questions changed from hard to easy
   *
   * @return an int of the number of questions changed from hard to easy
   */
  public int calculateHardToEasy() {
    return this.changedToEasy;
  }

  /**
   * sets a given problem's difficulty to hard
   *
   * @param problem represents the problem to be changed
   */
  public void changeToHard(Problem problem) {
    if (problem.getDifficulty() != Difficulty.HARD) {
      problem.changeDifficulty(Difficulty.HARD);
      this.changedToHard = this.changedToHard + 1;
    }
  }

  /**
   * sets a given problem's difficulty to easy
   *
   * @param problem represents the problem to be changed
   */
  public void changeToEasy(Problem problem) {
    if (problem.getDifficulty() != Difficulty.EASY) {
      problem.changeDifficulty(Difficulty.EASY);
      this.changedToEasy = this.changedToEasy + 1;
    }
  }
}
