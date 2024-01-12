package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DriverTest {
  /**
   * tests the main method with an incorrect amount of args
   */
  @Test
  public void testDriverMainMethodArgsError() {
    String[] argument = {"src/test/resources/exampleDirectoryForPA1",
        "src/test/resources/exampleDirectoryForPA1OutputFilename/driverOutput"};

    assertThrows(RuntimeException.class, () -> Driver.main(argument));
  }

  /**
   * tests the main method with an invalid input for the first argument
   */
  @Test
  public void testDriverMainMethodInputOneError() {
    String[] args = {"p/'",
        "filename",
        "src/test/resources/exampleDirectoryForPA1OutputFilename/driverOutput"};

    assertThrows(RuntimeException.class, () -> Driver.main(args));
  }

  /**
   * tests the main method with an invalid input for the first argument
   */
  @Test
  public void testDriverMainMethodInputOneError2() {
    String[] args = {"src/test/resources/outputFile/writeToFileTest.md",
        "filename",
        "src/test/resources/exampleDirectoryForPA1OutputFilename/driverOutput"};

    assertThrows(RuntimeException.class, () -> Driver.main(args));
  }


  /**
   * tests the main method with an invalid input for the third argument
   */
  @Test
  public void testDriverMainMethodInputThreeError() {
    String[] args = {"src/test/resources/exampleDirectoryForPA1",
        "filename",
        "p/'"};

    assertThrows(RuntimeException.class, () -> Driver.main(args));
  }

  /**
   * tests the main method, comparing based on file name
   */
  @Test
  public void testDriverMainMethodFileName() {
    String[] args = {"src/test/resources/exampleDirectoryForPA1",
        "filename",
        "src/test/resources/exampleDirectoryForPA1OutputFilename/driverOutput"};

    assertDoesNotThrow(() -> Driver.main(args));
  }

  /**
   * tests the main method, comparing based on creation date
   */
  @Test
  public void testDriverMainMethodCreated() {
    String[] args = {"src/test/resources/exampleDirectoryForPA1",
        "created",
        "src/test/resources/exampleDirectoryForPA1OutputCreated/driverOutput"};

    assertDoesNotThrow(() -> Driver.main(args));
  }

  /**
   * tests the main method, comparing based on modification date
   */
  @Test
  public void testDriverMainMethodModified() {
    String[] args = {"src/test/resources/exampleDirectoryForPA1",
        "modified",
        "src/test/resources/exampleDirectoryForPA1OutputModified/driverOutput"};

    assertDoesNotThrow(() -> Driver.main(args));
  }

  /**
   * tests the main method, throwing an error because of an invalid comparison flag
   */
  @Test
  public void testDriverMainMethodIncorrectCompare() {
    String[] args = {"src/test/resources/exampleDirectoryForPA1",
        "bad",
        "src/test/resources/exampleDirectoryForPA1OutputModified/driverOutput"};

    assertThrows(RuntimeException.class, () -> Driver.main(args));
  }

  /**
   * tests the main method, throwing an error because of the third argument being a directory
   */
  @Test
  public void testDriverMainMethodDirectoryThirdArgument() {
    String[] args = {"src/test/resources/exampleDirectoryForPA1",
        "bad",
        "src/test/resources/exampleDirectoryForPA1OutputModified"};

    assertThrows(RuntimeException.class, () -> Driver.main(args));
  }

  /**
   * tests the main method, with no arguments
   */
  @Test
  public void testDriverNoArgs() {
    String[] args = {};
    Driver driver = new Driver();
    assertDoesNotThrow(() -> Driver.main(args));
  }
}