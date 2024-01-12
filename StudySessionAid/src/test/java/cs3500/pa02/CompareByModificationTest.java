package cs3500.pa02;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import org.junit.jupiter.api.Test;

class CompareByModificationTest {
  /**
   * test comparing two file modification times, where the result is positive
   * indicating that the first input file was modified earlier
   */
  @Test
  void testCompareByModificationPositive() throws IOException {
    CompareByModification compareModification = new CompareByModification();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example2ood.md");

    long time = System.currentTimeMillis();
    FileTime fileTimeP1Create = FileTime.fromMillis(time);
    FileTime fileTimeP2Create = FileTime.fromMillis(time - 10000);

    FileTime fileTimeP1Access = FileTime.fromMillis(time);
    FileTime fileTimeP2Access = FileTime.fromMillis(time);

    FileTime fileTimeP1Mod = FileTime.fromMillis(time - 10000);
    FileTime fileTimeP2Mod = FileTime.fromMillis(time + 10000);

    BasicFileAttributeView bvPath1 = Files.getFileAttributeView(p1, BasicFileAttributeView.class);
    bvPath1.setTimes(fileTimeP1Mod, fileTimeP1Access, fileTimeP1Create);

    BasicFileAttributeView bvPath2 = Files.getFileAttributeView(p2, BasicFileAttributeView.class);
    bvPath2.setTimes(fileTimeP2Mod, fileTimeP2Access, fileTimeP2Create);

    int result = compareModification.compare(p2, p1);

    assertEquals(1, result);
  }

  /**
   * test comparing two file names where the result is negative
   * indicating that the first input file was modified later
   */
  @Test
  void testCompareByModificationNegative() throws IOException {
    CompareByModification compareModification = new CompareByModification();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example2ood.md");

    long time = System.currentTimeMillis();
    FileTime fileTimeP1Create = FileTime.fromMillis(time);
    FileTime fileTimeP2Create = FileTime.fromMillis(time + 10000);

    FileTime fileTimeP1Access = FileTime.fromMillis(time);
    FileTime fileTimeP2Access = FileTime.fromMillis(time);

    FileTime fileTimeP1Mod = FileTime.fromMillis(time + 10000);
    FileTime fileTimeP2Mod = FileTime.fromMillis(time - 10000);

    BasicFileAttributeView bvPath1 = Files.getFileAttributeView(p1, BasicFileAttributeView.class);
    bvPath1.setTimes(fileTimeP1Mod, fileTimeP1Access, fileTimeP1Create);

    BasicFileAttributeView bvPath2 = Files.getFileAttributeView(p2, BasicFileAttributeView.class);
    bvPath2.setTimes(fileTimeP2Mod, fileTimeP2Access, fileTimeP2Create);

    int result = compareModification.compare(p2, p1);
    assertEquals(-1, result);
  }

  /**
   * test comparing two file modification where the result is zero
   * indicating that the two input files have the same modification time
   */
  @Test
  void testCompareByModificationZero() {
    CompareByModification compareModification = new CompareByModification();

    Path p1 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");
    Path p2 = Path.of("src/test/resources/exampleFilesForPA1/example1ood.md");

    int result = compareModification.compare(p1, p2);

    assertEquals(0, result);
  }
}