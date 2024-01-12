package cs3500.pa02;

import java.nio.file.Path;
import java.util.Comparator;

/**
 * a comparator that compares two paths by the name of their files
 */
public class CompareByFileName implements Comparator<Path>  {
  /**
   * @param p1 the first object to be compared.
   * @param p2 the second object to be compared.
   * @return an int indicating the path with the name that comes first lexicographically
   */
  @Override
  public int compare(Path p1, Path p2) {
    return p1.toFile().getName().compareTo(p2.toFile().getName());
  }
}
