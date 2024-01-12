package cs3500.pa02;

import java.nio.file.Path;

/**
 * a function object the converts a sr file's contents to a string
 */
public class SrToQuestions extends FileToString {

  /**
   * @param srFilePath the function argument which represents the sr file to read from
   * @return simply the string, without any modifications, of all the contents in the file
   */
  @Override
  public String apply(Path srFilePath) {
    return this.readFromFile(srFilePath.toFile());
  }
}
