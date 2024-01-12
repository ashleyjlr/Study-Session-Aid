package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * represents the study guide of a group of markdown files
 */
public class StudyGuide {
  private ArrayList<Path> mdFilePaths;
  private String sortFlag;
  private Path outputFilePath;

  // main constructor
  StudyGuide(Path inputDirectory, String sortFlag, Path outputFilePath) throws IOException {
    if (!Files.isDirectory(inputDirectory)) {
      throw new RuntimeException("Input of argument 0 has to be a valid directory.");
    }
    this.addFromDirectory(inputDirectory);
    this.sortFlag = sortFlag;
    this.outputFilePath = outputFilePath;
  }

  // convenience constructor
  StudyGuide() {
    this.mdFilePaths = new ArrayList<>();
  }
  // convenience constructor

  StudyGuide(Path inputDirectory, String sortFlag) throws IOException {
    if (!Files.isDirectory(inputDirectory)) {
      throw new RuntimeException("Input of argument 0 has to be a valid directory.");
    }
    this.addFromDirectory(inputDirectory);
    this.sortFlag = sortFlag;
  }

  /**
   * getter that returns the markdown file paths from this class
   *
   * @return returns an arraylist of Paths that lead to MarkDown files
   */
  public ArrayList<Path> getMdFilePaths() {
    return this.mdFilePaths;
  }

  /**
   * adds all the markdown files form a directory to this arraylist field
   *
   * @param root represents the root directory
   * @throws IOException based off the use of walk file tree
   */
  public void addFromDirectory(Path root) throws IOException {
    this.mdFilePaths = new ArrayList<>();
    Files.walkFileTree(root, new MarkDownAccumulator(this.mdFilePaths));
  }

  /**
   * sorts the arraylist based on the given type from the string
   **/
  public void sortBy() {
    if (this.sortFlag.equals("filename")) {
      this.mdFilePaths.sort(new CompareByFileName());
    } else if (sortFlag.equals("created")) {
      this.mdFilePaths.sort(new CompareByCreation());
    } else if (sortFlag.equals("modified")) {
      this.mdFilePaths.sort(new CompareByModification());
    } else {
      throw new RuntimeException("Not a valid sorting flag.");
    }
  }

  /**
   * turns an array of strings into a single string
   *
   * @param resultMdStringsList is the result of all strings in each mark down list
   * @return a String of a study guide and all necessary headings and summaries
   */
  public String toString(ArrayList<String> resultMdStringsList) {
    String resultMdStrings = "";

    // adds each file as a string to on string, seperated by a new line
    for (String s : resultMdStringsList) {
      resultMdStrings = resultMdStrings + '\n' + s;
    }
    // removes the first and last new line of a file
    // (the leading and following new lines)
    int firstNewLine = resultMdStrings.indexOf("\n");
    resultMdStrings = resultMdStrings.substring(firstNewLine + 1);
    int lastNewLine = resultMdStrings.lastIndexOf("\n");
    resultMdStrings = resultMdStrings.substring(0, lastNewLine);
    return resultMdStrings;
  }

  /**
   * turns the given md files arraylist of paths to a single string
   * of the summarized content, formatted correctly
   *
   * @return a String of a study guide and all necessary headings and summaries
   */
  public String toStringInfo() {
    ArrayList<String> resultMdStringsList = new ArrayList<>();
    for (Path p : this.mdFilePaths) {
      resultMdStringsList.add(new MarkDownToInfo().apply(p));
    }
    return this.toString(resultMdStringsList).trim();
  }


  /**
   * turns the given md files arraylist of paths to a single string
   * of the questions and answers
   *
   * @return a String of a sr with all q and as
   */
  public String toStringQuestion() {
    ArrayList<String> resultMdStringsList = new ArrayList<>();
    for (Path p : this.mdFilePaths) {
      resultMdStringsList.add(new MarkDownToQuestions().apply(p));
    }
    return this.toString(resultMdStringsList).trim();
  }

  /**
   * creates the markdown file and the sr file when called by the driver class
   */
  public void createFiles() {
    this.sortBy();
    this.toMarkDown();
    this.toSpacedRepetition();
  }

  /**
   * converts all the markdown files to a string of summaries and
   * writes that string to the given location of the markdown file
   */
  public void toMarkDown() {
    String studyGuideString = this.toStringInfo();
    new StringToFile().writeToFile(this.outputFilePath.toFile(),
        studyGuideString, ".md");
  }

  /**
   * converts all the markdown files to a string of q and a's
   * and writes that string to the given location of the markdown file
   */
  public void toSpacedRepetition() {
    String studyGuideString = this.toStringQuestion();
    new StringToFile().writeToFile(this.outputFilePath.toFile(),
        studyGuideString, ".sr");
  }
}
