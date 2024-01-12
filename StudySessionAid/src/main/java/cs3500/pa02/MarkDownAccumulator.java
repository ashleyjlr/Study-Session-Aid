package cs3500.pa02;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * class that implements FileVisitor and the corresponding methods.
 * represents the accumulation of all markdown files in a file visitor
 */
public class MarkDownAccumulator implements FileVisitor<Path> {
  private final ArrayList<Path> mdFilePath;

  MarkDownAccumulator(ArrayList<Path> mdFilePath) {
    this.mdFilePath = mdFilePath;
  }

  /**
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return continues walking down the file directory
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return checks if the current file is a MarkDown file.
   *     if so, adds it to the mdFilePaths array list and continues walking the path.
   *     if not, continues walking the path.
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (file.toFile().getName().endsWith(".md")) {
      mdFilePath.add(file);
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return continues walking down the file directory
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc)  {
    System.err.println("File fail: " + exc);
    return FileVisitResult.CONTINUE;
  }

  /**
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return continues walking down the file directory
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    System.err.println("Post Visit Directory: " + exc);
    return FileVisitResult.CONTINUE;
  }
}
