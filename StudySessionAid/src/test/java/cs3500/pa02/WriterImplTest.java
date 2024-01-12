package cs3500.pa02;

import java.io.IOException;
import java.io.StringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * tests for the writerimpl class
 */
public class WriterImplTest {
  private StringWriter writer;
  private WriterImpl writerImpl;

  /**
   * sets up the tests
   */
  @BeforeEach
  public void setup() {
    writer = new StringWriter();
    writerImpl = new WriterImpl(writer);
  }

  /**
   * tests write method
   */
  @Test
  public void testWrite() {
    String phrase = "Hello, world!";
    writerImpl.write(phrase);
    Assertions.assertEquals(phrase, writer.toString());
  }

  /**
   * test write with mutltiple strings
   */
  @Test
  public void testWriteMultiplePhrases() {
    String phrase1 = "Hello, ";
    String phrase2 = "world!";
    writerImpl.write(phrase1);
    writerImpl.write(phrase2);
    Assertions.assertEquals(phrase1 + phrase2, writer.toString());
  }

  /**
   * tests write with empty string
   */
  @Test
  public void testWriteEmptyPhrase() {
    String phrase = "";
    writerImpl.write(phrase);
    Assertions.assertEquals(phrase, writer.toString());
  }

  /**
   * tests the exception thrown by write
   */
  @Test
  public void testWriteWithException() {
    Appendable appendable = new Appendable() {
      @Override
      public Appendable append(CharSequence csq) throws IOException {
        throw new IOException("IOException occurred");
      }

      @Override
      public Appendable append(CharSequence csq, int start, int end) throws IOException {
        throw new IOException("IOException occurred");
      }

      @Override
      public Appendable append(char c) throws IOException {
        throw new IOException("IOException occurred");
      }
    };
    WriterImpl writerImpl = new WriterImpl(appendable);

    Assertions.assertThrows(RuntimeException.class, () -> writerImpl.write("Test"));
  }
}
