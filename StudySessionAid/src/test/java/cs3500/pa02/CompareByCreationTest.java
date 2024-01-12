package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import org.junit.jupiter.api.Test;

class CompareByCreationTest {
  /**
   * test comparing two file creation where the result is positive
   * indicating that the first input file was created later
   */
  @Test
  void testCompareByCreationPositive() throws IOException {
    CompareByCreation compareCreation = new CompareByCreation();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example2ood.md");

    long time = System.currentTimeMillis();
    FileTime fileTimeP1Mod = FileTime.fromMillis(time);
    FileTime fileTimeP2Mod = FileTime.fromMillis(time + 10000);

    FileTime fileTimeP1Access = FileTime.fromMillis(time);
    FileTime fileTimeP2Access = FileTime.fromMillis(time);

    FileTime fileTimeP1Create = FileTime.fromMillis(time + 10000);
    FileTime fileTimeP2Create = FileTime.fromMillis(time - 10000);

    BasicFileAttributeView bvPath1 = Files.getFileAttributeView(p1, BasicFileAttributeView.class);
    bvPath1.setTimes(fileTimeP1Mod, fileTimeP1Access, fileTimeP1Create);

    BasicFileAttributeView bvPath2 = Files.getFileAttributeView(p2, BasicFileAttributeView.class);
    bvPath2.setTimes(fileTimeP2Mod, fileTimeP2Access, fileTimeP2Create);

    int result = compareCreation.compare(p2, p1);
    int timeResult = fileTimeP1Create.compareTo(fileTimeP2Create);
    assertEquals(1, timeResult);
  }

  /**
   * test comparing two file creation where the result is negative
   * indicating that the first input file was created earlier
   */
  @Test
  void testCompareByCreationNegative() throws IOException {
    CompareByCreation compareCreation = new CompareByCreation();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example2ood.md");

    long time = System.currentTimeMillis();
    FileTime fileTimeP1Mod = FileTime.fromMillis(time);
    FileTime fileTimeP2Mod = FileTime.fromMillis(time - 10000);

    FileTime fileTimeP1Access = FileTime.fromMillis(time);
    FileTime fileTimeP2Access = FileTime.fromMillis(time);

    FileTime fileTimeP1Create = FileTime.fromMillis(time - 10000);
    FileTime fileTimeP2Create = FileTime.fromMillis(time + 10000);

    BasicFileAttributeView bvPath1 = Files.getFileAttributeView(p1, BasicFileAttributeView.class);
    bvPath1.setTimes(fileTimeP1Mod, fileTimeP1Access, fileTimeP1Create);

    BasicFileAttributeView bvPath2 = Files.getFileAttributeView(p2, BasicFileAttributeView.class);
    bvPath2.setTimes(fileTimeP2Mod, fileTimeP2Access, fileTimeP2Create);

    int result = compareCreation.compare(p2, p1);
    int timeResult = fileTimeP1Create.compareTo(fileTimeP2Create);

    assertEquals(-1, timeResult);
  }

  /**
   * test comparing two file creation where the result is zero
   * indicating that the two input files were created at the same time
   */
  @Test
  void testCompareByCreationZero() {
    CompareByCreation compareCreation = new CompareByCreation();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");

    int result = compareCreation.compare(p1, p2);

    assertEquals(0, result);
  }

  /**
   * test comparing two file creation where the result is an exception
   */
  @Test
  void testCompareByCreationException() {
    CompareByCreation compareCreation = new CompareByCreation();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1oodfake.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example1oodfake.md");

    assertThrows(RuntimeException.class, () -> compareCreation.compare(p1, p2));
  }
}