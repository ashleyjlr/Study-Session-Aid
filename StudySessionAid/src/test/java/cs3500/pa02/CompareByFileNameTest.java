package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class CompareByFileNameTest {
  /**
   * test comparing two file names where the result is positive
   * indicating that the first input file has a name that is later lexigraphically
   */
  @Test
  void testCompareByFileNamePositive() {
    CompareByFileName compareFileName = new CompareByFileName();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example2ood.md");

    int result = compareFileName.compare(p2, p1);

    assertEquals(1, result);
  }

  /**
   * test comparing two file names where the result is negative
   * indicating that the first input file has a name that is earlier lexigraphically
   */
  @Test
  void testCompareByFileNameNegative() {
    CompareByFileName compareFileName = new CompareByFileName();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example2ood.md");

    int result = compareFileName.compare(p1, p2);

    assertEquals(-1, result);
  }

  /**
   * test comparing two file names where the result is zero
   * indicating that the two input files have the same names
   */
  @Test
  void testCompareByFileNameZero() {
    CompareByFileName compareFileName = new CompareByFileName();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");

    int result = compareFileName.compare(p1, p2);

    assertEquals(0, result);
  }
}