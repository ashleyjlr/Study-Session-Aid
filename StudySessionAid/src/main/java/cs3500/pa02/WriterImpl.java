package cs3500.pa02;

import java.io.IOException;
import java.util.Objects;

/**
 * writer implementation of the writer class
 */
public class WriterImpl implements Writer {
  private final Appendable appendable;

  WriterImpl(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  /**
   * writes content to the given appendable
   *
   * @param phrase the content to write
   */
  @Override // @Override since it's from the interface
  public void write(String phrase) {
    try {
      appendable.append(phrase); // this may fail, hence the try-catch
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
