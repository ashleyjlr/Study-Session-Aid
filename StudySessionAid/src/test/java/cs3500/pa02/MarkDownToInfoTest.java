package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * test for all methods in MarkDownToStringTest
 */
class MarkDownToInfoTest {
  /**
   * test for apply with the arrays.md file
   */
  @Test
  void testApplyArrays() {
    Path pathGiven = Path.of("src/test/resources/exampleDirectoryForPA1/arrays.md");
    String givenResult = new MarkDownToInfo().apply(pathGiven);

    Path pathExpected =
        Path.of("src/test/resources/exampleDirectoryForPA1EQUALS/arraysResult.md");
    String expectedResult = new MarkDownToInfo().readFromFile(pathExpected.toFile());

    assertEquals(expectedResult, givenResult);
  }

  /**
   * test for apply with the favoriteThings.md file
   */
  @Test
  void testApplyFavoriteThings() {
    Path pathGiven = Path.of("src/test/resources/exampleDirectoryForPA1/favoriteThings.md");
    String givenResult = new MarkDownToInfo().apply(pathGiven);

    Path pathExpected =
        Path.of("src/test/resources/exampleDirectoryForPA1EQUALS/favoriteThingsResult.md");
    String expectedResult = new MarkDownToInfo().readFromFile(pathExpected.toFile());

    assertEquals(expectedResult, givenResult);
  }

  /**
   * test for apply with a file that does not currently exist on this device
   */
  @Test
  void testApplyUnknownFile() {
    Path pathGiven = Path.of("src/test/resources/exampleDirectoryForPA1/fake.md");
    assertThrows(RuntimeException.class, () -> new MarkDownToInfo().apply(pathGiven));
  }
}