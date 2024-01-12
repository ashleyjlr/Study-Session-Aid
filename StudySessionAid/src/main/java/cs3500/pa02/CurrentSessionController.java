package cs3500.pa02;

import java.util.Iterator;
import java.util.Objects;

/**
 * represents the controller for a study session
 */
public class CurrentSessionController {
  private final Readable input;
  private final Appendable output;
  private ReviewSessionModel reviewSession;
  private int numOfQuestionCounter;

  CurrentSessionController(Readable input, Appendable output) {
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    this.numOfQuestionCounter = 0;
  }

  CurrentSessionController(Readable input, Appendable output, ReviewSessionModel reviewSession)  {
    this.input = Objects.requireNonNull(input);
    this.output = Objects.requireNonNull(output);
    this.numOfQuestionCounter = 0;
    this.reviewSession = reviewSession;
  }


  /**
   * begins the review session by asking for the file path and number of question inputs,
   * then creates a review session model from those inputs.
   */
  public void startStudySession() {
    try {
      Reader reader =
          new ReaderImpl(this.input, "-1");

      output.append("Welcome to the Review Session!\n");

      output.append("Please input your Spaced Repetition (.sr) file path: \n");
      String userInFilePath = reader.read();
      output.append("How many problems would you like to practice?\n");
      String userInProblemNum = reader.read();
      this.reviewSession = new ReviewSessionModel(userInFilePath, userInProblemNum);

      // starts iterating through the questions
      this.iterateThroughProblems();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * iterates through the array list of problems given by the iterator.
   * the user is then able to input from 5 options: mark the question as hard, mark as easy,
   * see the answer, move onto the next question, or end the session early.
   * choosing the options move on or end early will be the last option they can choose,
   * but the other 3 options can be chosen repeatedly in any order or just until they choose
   * move on or end early.
   */
  public void iterateThroughProblems() {
    Iterator<Problem> problemIterator = this.reviewSession.generateProblems();

    boolean reviewEnded = false;

    try {
      while (problemIterator.hasNext() && !reviewEnded) {
        Problem currentProblem = problemIterator.next();
        this.numOfQuestionCounter++;
        this.output.append("Question: " + currentProblem.getQuestion() + "\n");

        boolean moveToNextQuestion = false;

        while (!moveToNextQuestion) {
          output.append("Select an option:\n");
          output.append("1. Mark question as easy\n");
          output.append("2. Mark question as hard\n");
          output.append("3. Show answer\n");
          output.append("4. Go to the next question\n");
          output.append("5. End the review early\n");

          Reader reader =
              new ReaderImpl(this.input, "-1");

          int userInput = Integer.parseInt(reader.read());

          if (userInput == 1) {
            // update the question to easy
            this.reviewSession.changeToEasy(currentProblem);
            output.append("Question marked as easy.\n");
          } else if (userInput == 2) {
            // update the question to hard
            this.reviewSession.changeToHard(currentProblem);
            output.append("Question marked as hard.\n");
          } else if (userInput == 3) {
            // show the answer for the question
            output.append("Answer: " + currentProblem.getAnswer() + "\n");
          } else if (userInput == 4) {
            // go to the next question
            moveToNextQuestion = true;
          } else if (userInput == 5) {
            // end the review early
            reviewEnded = true;
            break;
          } else {
            output.append("Invalid option. Please try again.\n");
          }
        }

        if (!problemIterator.hasNext()) {
          output.append("No more questions available.\n");
          reviewEnded = true;
        }
      }

      // displays the end screen
      this.endStudySession();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * ends the review session by displaying the number of questions answered,
   * the number of questions from easy -> hard,
   * the number of questions from hard -> easy,
   * and the current counts of all questions in the question bank (# of easy, # of hard).
   */
  public void endStudySession() {
    try {
      this.reviewSession.toSr();
      output.append("Good Job!\n\n");

      output.append("You answered " + this.numOfQuestionCounter + " question(s).\n\n");

      int easyToHardNumber = this.reviewSession.calculateEasyToHard();
      output.append(easyToHardNumber + " went from easy to hard.\n\n");

      int hardToEasyNumber = this.reviewSession.calculateHardToEasy();
      output.append(hardToEasyNumber + " went from hard to easy.\n\n");

      output.append("Current Counts in Question Bank:\n");
      int totalHardProblems = this.reviewSession.numberOfHard();
      output.append(totalHardProblems + " Hard Questions\n");
      int totalEasyProblems = this.reviewSession.numberOfEasy();
      output.append(totalEasyProblems + " Easy Questions\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}



