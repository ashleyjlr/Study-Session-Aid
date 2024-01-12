package cs3500.pa02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Enables writing text to files and supports the Appendable interface.
 */
public class FileAppendable implements Appendable {
  File file;

  /**
   * Creates a new FileAppendable.
   *
   * @param file where to write outputs.
   */
  public FileAppendable(File file) {
    this.file = Objects.requireNonNull(file);
  }

  /**
   * Writes a given message to the file.
   *
   * @param value the message to write
   */
  void write(String value) {
    try (FileWriter writer = new FileWriter(this.file, true)) {
      writer.write(value);
    } catch (IOException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * Appends a given character sequence to the file.
   *
   * @param csq The character sequence to append.  If {@code csq} is
   *            {@code null}, then the four characters {@code "null"} are
   *            appended to this Appendable.
   * @return a reference to this Appendable
   */
  @Override
  public Appendable append(CharSequence csq) {
    write(String.valueOf(csq));
    return this;
  }

  /**
   * Appends a subsequence of the specified character sequence to this Appendable.
   *
   * @param csq   The character sequence from which a subsequence will be
   *              appended.  If {@code csq} is {@code null}, then characters
   *              will be appended as if {@code csq} contained the four
   *              characters {@code "null"}.
   * @param start The index of the first character in the subsequence
   * @param end   The index of the character following the last character in the
   *              subsequence
   * @return a reference to this Appendable
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) {
    write(String.valueOf(csq).substring(start, end));
    return this;
  }

  /**
   * Appends the specified character to this Appendable.
   *
   * @param c The character to append
   * @return a reference to this Appendable
   */
  @Override
  public Appendable append(char c) {
    write(String.valueOf(c));
    return this;
  }
}
