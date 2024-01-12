package cs3500.pa02;

import java.nio.file.Path;
import java.util.Arrays;

/**
 * function object that converts a markdown file to a string
 * headings lead with # or ## are kept
 * and bullet points are created with information between "[[" and "]]"
 */
public class MarkDownToInfo extends FileToString {
  /**
   * Takes the necessary headers and important information
   * and returns a summary of the file as a string, properly formatted
   *
   * @param mdFilePath the file path of the file to convert
   * @return returns a string of only the strings that are surrounded by [[ and ]]
   *     or the titles that start with # or ##
   */
  @Override
  public String apply(Path mdFilePath) {
    String allContentsOfFile = this.readFromFile(mdFilePath.toFile());

    StringBuilder newContents = new StringBuilder();
    String[] lines = allContentsOfFile.split("\n");
    for (String line : lines) {
      // if statement for the heading
      if (line.startsWith("#")) {
        newContents.append("\n");
        newContents.append(line).append("\n");
      } else if (line.startsWith("-")) { // else if statement for bullet points
        // removes leading and trailing whitespace
        String bulletPoint = line.substring(1).trim();

        // initialize variables for start and end of brackets
        int start = bulletPoint.indexOf("[[");
        int end = bulletPoint.indexOf("]]");

        if (end < 0) {
          // get the next line in the loop to look for ]]
          int nextLineIndex = Arrays.asList(lines).indexOf(line) + 1;
          bulletPoint += " ";
          while (nextLineIndex < lines.length) {
            line = lines[nextLineIndex];
            bulletPoint += line;
            end = bulletPoint.indexOf("]]");
            if (end >= 0)
              break;
            nextLineIndex++;
          }
        }

        // loop to extract all occurrences of content inside brackets
        while (start >= 0 && end >= 0) {
          // check if ':::' exists between brackets
          int delimiterStart = bulletPoint.indexOf(":::", start + 2);
          if (delimiterStart >= 0 && delimiterStart < end) {
            // ':::' found, skip the content between brackets
            start = bulletPoint.indexOf("[[", end + 2);
            end = bulletPoint.indexOf("]]", end + 2);
          } else {
            // ':::' not found, append the content inside brackets to newContents
            if (end > start) {
              newContents.append("- ").append(bulletPoint, start + 2, end).append("\n");
            }
            // move the start and end indices to the next occurrence
            start = bulletPoint.indexOf("[[", end + 2);
            end = bulletPoint.indexOf("]]", end + 2);

            if (end < 0 && start > 0) {
              // get the next line in the loop to look for ]]
              int nextLineIndex = Arrays.asList(lines).indexOf(line) + 1;
              bulletPoint += " ";
              while (nextLineIndex < lines.length) {
                line = lines[nextLineIndex];
                bulletPoint += line;
                end = bulletPoint.indexOf("]]");
                if (end >= 0)
                  break;
                nextLineIndex++;
              }
            }
          }
        }
      }
    }
    int firstNewLine = newContents.indexOf("\n");
    newContents = new StringBuilder(newContents.substring(firstNewLine + 1));
    return newContents.toString();
  }
}