package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class SrToQuestionsTest {
  /**
   * test for apply with the arrays.md file
   */
  @Test
  void testApplyArrays() {
    Path pathGiven =
        Path.of("src/test/resources/exampleDirectoryForPA2EQUALS/arraysResult.sr");
    String givenResult = new SrToQuestions().apply(pathGiven);

    Path pathExpected =
        Path.of("src/test/resources/exampleDirectoryForPA2EQUALS/arraysResult.sr");
    String expectedResult = new SrToQuestions().readFromFile(pathExpected.toFile());

    assertEquals(expectedResult, givenResult);
  }

  /**
   * test for apply with the favoriteThings.md file
   */
  @Test
  void testApplyFavoriteThings() {
    Path pathGiven =
        Path.of("src/test/resources/exampleDirectoryForPA2EQUALS/favoriteThingsResult.sr");
    String givenResult = new SrToQuestions().apply(pathGiven);

    Path pathExpected =
        Path.of("src/test/resources/exampleDirectoryForPA2EQUALS/favoriteThingsResult.sr");
    String expectedResult = new SrToQuestions().readFromFile(pathExpected.toFile());

    assertEquals(expectedResult, givenResult);
  }

  /**
   * test for apply with a file that does not currently exist on this device
   */
  @Test
  void testApplyUnknownFile() {
    Path pathGiven = Path.of("src/test/resources/exampleDirectoryForPA1/fake.md");
    assertThrows(RuntimeException.class, () -> new SrToQuestions().apply(pathGiven));
  }
}