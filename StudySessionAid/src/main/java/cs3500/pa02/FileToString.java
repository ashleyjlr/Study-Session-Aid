package cs3500.pa02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.function.Function;

/**
 * an abstract class of all the file to string function objects
 * used in markdown -> info, markdown -> questions, sr -> questions
 */
public abstract class FileToString implements Function<Path, String> {

  /**
   * reads all the contents from a file to a String
   *
   * @param file A file object which corresponds to a path in the file system and some information
   *             at that path
   * @return the contents of the file
   */
  public String readFromFile(File file) {
    // Initialize a Scanner to read the file
    Scanner sc;
    try { // The file may not exist, in which case we need to handle that error (hence try-catch)
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    // Use the Scanner to iterate through the file line-by-line and accumulate its
    // contents in a string
    StringBuilder content = new StringBuilder(); // StringBuilder is more efficient than
    // String concatenation
    while (sc.hasNextLine()) { // Check there is another unread line in the file
      content.append(sc.nextLine()).append("\n"); // Read the aforementioned line
    }

    return content.toString(); // Return the contents collected in the StringBuilder
  }

  /**
   * abstract method implemented in the children
   *
   * @param path the function argument of the file to read from
   * @return returns a string of the properly formatted and/or extracted contents from the files
   */
  @Override
  public abstract String apply(Path path);
}
