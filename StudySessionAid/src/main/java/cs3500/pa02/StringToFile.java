package cs3500.pa02;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * represents a file writer
 * contains the method to write to a file based on the given file and contents
 */
public class StringToFile {
  /**
   * Writes the given String to the given filepath.
   *
   * @param file     where to write the contents
   * @param contents contents to write to the file
   * @param fileExtension the given file extension to write to
   */
  public void writeToFile(File file, String contents, String fileExtension) {

    // Add .md to the end of the file path.
    // You may need to change this to get the desired user-experience that was asked for.
    Path path = Path.of(file.toPath() + fileExtension);

    // Convert String to data for writing ("raw" byte data)
    byte[] data = contents.getBytes();

    // The path may not exist, or we may not have permissions to write to it,
    // in which case we need to handle that error (hence try-catch)
    try {
      // Built-in convenience method for writing data to a file.
      // Markdown is really just plain text with some
      // special syntax, so you can add `.md` to the file-path to write a Markdown file.
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
