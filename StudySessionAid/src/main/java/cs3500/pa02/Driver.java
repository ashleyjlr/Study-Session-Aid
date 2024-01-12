package cs3500.pa02;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Path;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - first argument is an input directory, second is a flag for comparing,
   *             third is an output file path
   * @throws IOException failure in the program after the inputs and main method is called
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 3) {
      Path input = Path.of(args[0]);
      String compareFlag = args[1];
      Path output = Path.of(args[2]);
      StudyGuide finalStudyGuide = new StudyGuide(input, compareFlag, output);
      // makes the markdown file and the spaced repetition file
      finalStudyGuide.createFiles();
    } else if (args.length == 0) {
      CurrentSessionController currentSessionController = new
          CurrentSessionController(new InputStreamReader(System.in), new PrintStream(System.out));
      currentSessionController.startStudySession();
    } else {
      throw new RuntimeException("Please either provide three arguments for a StudyGuide + Q&A "
          + "or no arguments to start a Study Session");
    }
  }
}