package cs3500.pa02;

import java.util.Objects;
import java.util.Scanner;

/**
 * reader implementation of the reader interface
 */
public class ReaderImpl implements Reader {
  // In ReaderImpl ...

  // Fields
  private final Readable readable;
  private final String escapeSequence;

  // Constructor
  ReaderImpl(Readable readable, String escapeSequence) {
    this.readable = Objects.requireNonNull(readable);
    this.escapeSequence = Objects.requireNonNull(escapeSequence);
  }

  /**
   * reads a readable and exits based on the escape sequence
   * the one used in the controller is '-1' as used in the lab
   *
   * @return a string of the content read
   */
  @Override
  public String read() {
    // We need a Scanner to make the Readable, well, readable.
    Scanner scanner = new Scanner(readable);

    // We can easily append to our StringBuilder to collect an output
    StringBuilder output = new StringBuilder();

    // This while loop should look familiar!
    // 1. Check there's more to read
    // 2. Check that the "more to read" isn't the "I'm done" sequence
    while (scanner.hasNextLine() && !scanner.hasNext(escapeSequence)) {
      // If we want to read, add to the output
      output.append(scanner.nextLine());
    }

    // When we've read all there is to read ... return the output
    return output.toString();
  }
}
