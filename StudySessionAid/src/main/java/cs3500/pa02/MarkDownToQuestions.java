package cs3500.pa02;

import java.nio.file.Path;

/**
 * a function object that extends the abstract class to format a MarkDown file into a string of
 * questions and answers, with an automatic hard difficulty, and a new line in between each section
 */
public class MarkDownToQuestions extends FileToString {

  /**
   * @param mdFilePath the function argument which is the path to read from
   * @return returns a string of Q and As
   */
  @Override
  public String apply(Path mdFilePath) {
    String allContentsOfFile = this.readFromFile(mdFilePath.toFile());

    StringBuilder result = new StringBuilder();
    int start = allContentsOfFile.indexOf("[[");
    int end = allContentsOfFile.indexOf("]]");

    while (start >= 0 && end >= 0) {
      // get the content between "[[" and "]]"
      String content = allContentsOfFile.substring(start + 2, end);

      // check if the content contains ":::"
      if (content.contains(":::")) {
        // format the content properly
        String[] parts = content.split(":::");
        result.append(parts[0].trim()).append("\n");
        result.append(parts[1].trim()).append("\n");
        result.append("HARD" + "\n");
        result.append("\n");
      }

      // move the start and end indices to the next occurrence
      start = allContentsOfFile.indexOf("[[", end + 2);
      end = allContentsOfFile.indexOf("]]", end + 2);
    }

    String theFinalResult = result.toString();
    int lastNewlineIndex = theFinalResult.lastIndexOf("\n");
    if (lastNewlineIndex >= 0) {
      return theFinalResult.substring(0, lastNewlineIndex);

    }
    return theFinalResult;
  }
}
