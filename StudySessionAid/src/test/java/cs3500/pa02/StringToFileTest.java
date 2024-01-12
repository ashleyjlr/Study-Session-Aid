package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class StringToFileTest {

  /**
   * tests the write to file method by creating the expected string as well and then reading the
   * string form the created file for markdown files
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testWriteToFileMarkDown() throws IOException {
    Path pathInput = Path.of("src/test/resources/exampleDirectoryForPA1");
    StudyGuide sg = new StudyGuide(pathInput, "filename");
    sg.sortBy();
    String actualOutcome = sg.toStringInfo();

    String expectedStrings = "# Java Arrays\n"
        + "- An **array** is a collection of variables of the same type\n"
        + "\n"
        + "## Declaring an Array\n"
        + "- General Form: type[] arrayName;\n"
        + "- only creates a reference\n"
        + "- no array has actually been created yet\n"
        + "\n"
        + "## Creating an Array (Instantiation)\n"
        + "- General form: arrayName = new type[numberOfElements];\n"
        + "- numberOfElements must be a positive Integer.\n"
        + "- Gotcha: Array size is not modifiable once instantiated.\n"
        + "\n"
        + "# My Favorite Things\n"
        + "- roses\n"
        + "- kittens\n"
        + "- copper\n"
        + "- mittens\n"
        + "- paper\n"
        + "- things\n"
        + "\n"
        + "# Vectors\n"
        + "- Vectors act like resizable arrays\n"
        + "\n"
        + "## Declaring a vector\n"
        + "- General Form: Vector<type> v = new Vector();\n"
        + "- type needs to be a valid reference type\n"
        + "\n"
        + "## Adding an element to a vector\n"
        + "- v.add(object of type);";
    assertEquals(expectedStrings, actualOutcome);
  }

  /**
   * tests the write to file method by creating the expected string as well and then reading the
   * string form the created file for sr files
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testWriteToFileStudyRep() throws IOException {
    Path pathInput = Path.of("src/test/resources/exampleDirectoryForPA1");
    StudyGuide sg = new StudyGuide(pathInput, "filename");
    sg.sortBy();
    String actualOutcome = sg.toStringQuestion();

    String expectedStrings = "What does ROYGBIV stand for?\n"
        + "red orange yellow green blue indigo violet\n"
        + "HARD\n"
        + "\n"
        + "How much wood would a woodchuck chuck if a woodchuck could chuck wood?\n"
        + "a woodchuck could chuck as much wood as a woodchuck could or 700 pounds\n"
        + "HARD\n"
        + "\n"
        + "How many licks does it take to get to the center of a tootsie pop?\n"
        + "The world may never know\n"
        + "HARD\n"
        + "\n"
        + "What is the name of the villain in Peter Pan?\n"
        + "Captain Hook\n"
        + "HARD\n"
        + "\n"
        + "Where does the does \"My Favorite Things\" come from?\n"
        + "The Sound of Music\n"
        + "HARD";
    assertEquals(expectedStrings, actualOutcome);
  }
}