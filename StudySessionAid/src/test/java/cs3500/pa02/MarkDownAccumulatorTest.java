package cs3500.pa02;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class MarkDownAccumulatorTest {
  /**
   * tests that the file visit continues
   *
   * @throws IOException throws based off File.readAttributes
   */
  @Test
  void testFileVisit() throws IOException {
    Path dir = Path.of("src/test/resources/studyGuideTest");
    BasicFileAttributes attrP1 = Files.readAttributes(
        dir, BasicFileAttributes.class);
    assertEquals(FileVisitResult.CONTINUE,
        new MarkDownAccumulator(new ArrayList<>()).visitFile(dir, attrP1));
  }

  /**
   * test that the file fail continues
   */
  @Test
  void testFileVisitFail() {
    Path dir = Path.of("src/test/resources/studyGuideTest");
    assertEquals(FileVisitResult.CONTINUE,
        new MarkDownAccumulator(new ArrayList<>()).visitFileFailed(dir, new IOException()));
  }

  /**
   * tests that the previsit continues
   *
   * @throws IOException throws based off File.readAttributes
   */
  @Test
  void testFilePreVisit() throws IOException {
    Path dir = Path.of("src/test/resources/studyGuideTest");
    BasicFileAttributes attrP1 = Files.readAttributes(
        dir, BasicFileAttributes.class);
    assertEquals(FileVisitResult.CONTINUE,
        new MarkDownAccumulator(new ArrayList<>()).preVisitDirectory(dir, attrP1));
  }

  /**
   * tests that the post visit continues
   */
  @Test
  void testFilePostVisit() {
    Path dir = Path.of("src/test/resources/studyGuideTest");
    assertEquals(FileVisitResult.CONTINUE,
        new MarkDownAccumulator(new ArrayList<>()).postVisitDirectory(dir, new IOException()));
  }

}