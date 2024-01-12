package cs3500.pa02;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * a comparator that compares two paths by their modification date
 */
public class CompareByModification implements Comparator<Path> {
  /**
   * @param p1 the first object to be compared.
   * @param p2 the second object to be compared.
   * @return returns an int indicating which had a greater value
   */
  @Override
  public int compare(Path p1, Path p2) {
    double comparison = (p1.toFile().lastModified() - p2.toFile().lastModified());
    if (comparison < 0) {
      return -1;
    } else if (comparison > 0) {
      return 1;
    } else {
      return 0;
    }
  }
}
