package cs3500.pa02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * tests for file appendable class
 */
public class FileAppendableTest {
  private FileAppendable fileAppendable;
  private File testFile;

  @BeforeEach
  public void setUp() {
    testFile = new File("testfile.txt");
    fileAppendable = new FileAppendable(testFile);
  }

  @AfterEach
  public void tearDown() {
    // Clean up the test file after each test
    testFile.delete();
  }

  @Test
  public void testAppendCharSequence() throws IOException {
    String message = "Hello, world!";
    fileAppendable.append(message);

    String fileContent = readFileContent(testFile);
    Assertions.assertEquals(message, fileContent);
  }

  @Test
  public void testAppendCharSequenceWithIndices() throws IOException {
    String message = "Hello, world!";
    fileAppendable.append(message, 0, 5);

    String fileContent = readFileContent(testFile);
    Assertions.assertEquals("Hello", fileContent);
  }

  @Test
  public void testAppendChar() throws IOException {
    char c = '!';
    fileAppendable.append(c);

    String fileContent = readFileContent(testFile);
    Assertions.assertEquals(String.valueOf(c), fileContent);
  }

  private String readFileContent(File file) throws IOException {
    return new String(Files.readAllBytes(file.toPath()));
  }

  //  @Test
  //  public void testWriteThrowsIllegalArgumentException() {
  //    String message = null; // Set the message to null intentionally
  //
  //    Assertions.assertThrows(IllegalArgumentException.class, () -> {
  //      fileAppendable.write(message);
  //    })
  //  }
}
