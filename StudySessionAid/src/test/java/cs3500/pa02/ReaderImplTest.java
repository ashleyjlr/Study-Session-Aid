package cs3500.pa02;

import java.io.StringReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test for the readerimpl class
 */
public class ReaderImplTest {
  private ReaderImpl reader;

  @BeforeEach
  public void setUp() {
    String text = "Hello, world!\nThis is a test.";
    reader = new ReaderImpl(new StringReader(text), "###");
  }

  @Test
  public void testRead() {
    String expectedOutput = "Hello, world!This is a test.";
    String actualOutput = reader.read();
    Assertions.assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testReadWithEscapeSequence() {
    String text = "###";
    reader = new ReaderImpl(new StringReader(text), "###");
    String expectedOutput = "";
    String actualOutput = reader.read();
    Assertions.assertEquals(expectedOutput, actualOutput);
  }
}
