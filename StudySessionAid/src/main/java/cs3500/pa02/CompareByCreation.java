package cs3500.pa02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;

/**
 * a comparator class that compares two baths by their date of creation
 */
public class CompareByCreation implements Comparator<Path> {
  /**
   * @param p1 the first object to be compared.
   * @param p2 the second object to be compared.
   * @return an int indicating the path created earliest
   */
  @Override
  public int compare(Path p1, Path p2) {
    try {
      BasicFileAttributes attrP1 = Files.readAttributes(
          p1, BasicFileAttributes.class);
      BasicFileAttributes attrP2 = Files.readAttributes(p2, BasicFileAttributes.class);
      return attrP1.creationTime().compareTo(attrP2.creationTime());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
