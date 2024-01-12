package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class StudyGuideTest {

  // I commented this test out as the creation time was not correctly comparing
  // TA suggested to comment it out as my functionality works and it builds. both on my computer
  // and GitHub (after commenting the test out).

  //  /**
  //   * tests that the created list of markdown files gets sorted in creation order
  //   * with the oldest created first -> most recent
  //   *
  //   * @throws IOException throws based off the use of addFromDirectory
  //   */
  //  @Test
  //  void testSortByCreation() throws IOException {
  //    Path p = Path.of("src/test/resources/exampleDirectoryForPA1");
  //    StudyGuide sg = new StudyGuide();
  //    sg.addFromDirectory(p);
  //
  //    ArrayList<Path> resultList = new ArrayList<>();
  //    Path arrays = Path.of("src/test/resources/exampleDirectoryForPA1/arrays.md");
  //    Path vectors = Path.of("src/test/resources/exampleDirectoryForPA1/vectors.md");
  //    Path favoriteThings =
  //    Path.of("src/test/resources/exampleDirectoryForPA1/favoriteThings.md");
  //
  //    sg.sortBy(new CompareByCreation());
  //
  //    long time = System.currentTimeMillis();
  //    FileTime fileTimeFavCreate = FileTime.fromMillis(time + 1000000);
  //    FileTime fileTimeArraysCreate = FileTime.fromMillis(time - 1000000);
  //    FileTime fileTimeVectorsCreate = FileTime.fromMillis(time + 2000000);
  //
  //    FileTime fileTimeFavAccess = FileTime.fromMillis(time);
  //    FileTime fileTimeArraysAccess = FileTime.fromMillis(time);
  //    FileTime fileTimeVectorsAccess = FileTime.fromMillis(time);
  //
  //    FileTime fileTimeFavMod = FileTime.fromMillis(0);
  //    FileTime fileTimeArraysMod = FileTime.fromMillis(0);
  //    FileTime fileTimeVectorsMod = FileTime.fromMillis(0);
  //
  //
  //    BasicFileAttributeView bvFav = Files.getFileAttributeView(favoriteThings,
  //    BasicFileAttributeView.class);
  //    bvFav.setTimes(fileTimeFavMod, fileTimeFavAccess, fileTimeFavCreate);
  //
  //    BasicFileAttributeView bvArrays = Files.getFileAttributeView(arrays,
  //    BasicFileAttributeView.class);
  //    bvArrays.setTimes(fileTimeArraysMod, fileTimeArraysAccess, fileTimeArraysCreate);
  //
  //    BasicFileAttributeView bvVectors = Files.getFileAttributeView(vectors,
  //    BasicFileAttributeView.class);
  //    bvVectors.setTimes(fileTimeVectorsMod, fileTimeVectorsAccess, fileTimeVectorsCreate);
  //
  //    resultList.add(arrays);
  //    resultList.add(vectors);
  //    resultList.add(favoriteThings);
  //    resultList.add(favoriteThings);
  //    resultList.add(arrays);
  //    resultList.add(vectors);
  //    assertEquals(resultList, sg.getMdFilePaths())
  //  }

  /**
   * tests that the created list of markdown files gets sorted in modification order
   * with the oldest modified first -> most recent
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testSortByModification() throws IOException {
    Path p = Path.of("src/test/resources/exampleDirectoryForPA1");
    StudyGuide sg = new StudyGuide(p, "modified");
    sg.addFromDirectory(p);

    Path arrays = Path.of("src/test/resources/exampleDirectoryForPA1/arrays.md");
    Path vectors = Path.of("src/test/resources/exampleDirectoryForPA1/vectors.md");
    Path favoriteThings =
        Path.of("src/test/resources/exampleDirectoryForPA1/favoriteThings.md");

    long time = System.currentTimeMillis();
    FileTime fileTimeFavCreate = FileTime.fromMillis(time + 1000000);
    FileTime fileTimeArraysCreate = FileTime.fromMillis(time - 1000000);
    FileTime fileTimeVectorsCreate = FileTime.fromMillis(time + 2000000);

    FileTime fileTimeFavAccess = FileTime.fromMillis(time);
    FileTime fileTimeArraysAccess = FileTime.fromMillis(time);
    FileTime fileTimeVectorsAccess = FileTime.fromMillis(time);

    FileTime fileTimeFavMod = FileTime.fromMillis(time + 2000000);
    FileTime fileTimeArraysMod = FileTime.fromMillis(time + 1000000);
    FileTime fileTimeVectorsMod = FileTime.fromMillis(time - 1000000);

    BasicFileAttributeView bvFav =
        Files.getFileAttributeView(favoriteThings, BasicFileAttributeView.class);
    bvFav.setTimes(fileTimeFavMod, fileTimeFavAccess, fileTimeFavCreate);

    BasicFileAttributeView bvArrays =
        Files.getFileAttributeView(arrays, BasicFileAttributeView.class);
    bvArrays.setTimes(fileTimeArraysMod, fileTimeArraysAccess, fileTimeArraysCreate);

    BasicFileAttributeView bvVectors =
        Files.getFileAttributeView(vectors, BasicFileAttributeView.class);
    bvVectors.setTimes(fileTimeVectorsMod, fileTimeVectorsAccess, fileTimeVectorsCreate);


    sg.sortBy();

    ArrayList<Path> resultList = new ArrayList<>();
    resultList.add(vectors);
    resultList.add(arrays);
    resultList.add(favoriteThings);
    assertEquals(resultList, sg.getMdFilePaths());
  }

  /**
   * * tests that the created list of markdown files gets sorted in file name order
   * * with lexicographical ordering
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testSortByFileName() throws IOException {
    Path p = Path.of("src/test/resources/exampleDirectoryForPA1");
    StudyGuide sg = new StudyGuide(p, "filename");
    sg.addFromDirectory(p);
    sg.sortBy();

    ArrayList<Path> resultList = new ArrayList<>();
    Path arrays = Path.of("src/test/resources/exampleDirectoryForPA1/arrays.md");
    Path vectors = Path.of("src/test/resources/exampleDirectoryForPA1/vectors.md");
    Path favoriteThings = Path.of("src/test/resources/exampleDirectoryForPA1/favoriteThings.md");
    resultList.add(arrays);
    resultList.add(favoriteThings);
    resultList.add(vectors);
    assertEquals(resultList, sg.getMdFilePaths());
  }

  /**
   * tests that the to string method prints out a string with the file's
   * contents properly extracted and formatted
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testToStringInfo() throws IOException {
    Path p = Path.of("src/test/resources/exampleDirectoryForPA1");
    Path p2 = Path.of("src/test/resources/studyGuideTest");

    StudyGuide sg = new StudyGuide(p, "filename", p2);
    sg.addFromDirectory(p);
    sg.sortBy();

    String actualOutcome = sg.toStringInfo();

    sg.toMarkDown();

    StudyGuide sg2 = new StudyGuide();
    sg2.addFromDirectory(p2);
    String expectedOutcome = "# Java Arrays\n"
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

    assertEquals(expectedOutcome, actualOutcome);
  }

  /**
   * tests that the file is created and that the contents of said file
   * equal the expected output contents for summaries
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testToMarkDown() throws IOException {
    Path pathInput = Path.of("src/test/resources/exampleDirectoryForPA1");
    StudyGuide sg = new StudyGuide(pathInput, "filename");
    sg.addFromDirectory(pathInput);
    sg.sortBy();
    String actualOutcome = sg.toStringInfo();

    Path pathOutput = Path.of("src/test/resources/outputFile/writeToFileTest.md");
    new StringToFile().writeToFile(pathOutput.toFile(), actualOutcome, ".md");

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
   * tests that the to string method prints out a string with the file's
   * contents properly extracted and formatted for question and answers
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testToSpacesRepetition() throws IOException {
    Path p = Path.of("src/test/resources/exampleDirectoryForPA1");
    Path p2 = Path.of("src/test/resources/studyGuideTest");

    StudyGuide sg = new StudyGuide(p, "filename", p2);
    sg.addFromDirectory(p);
    sg.sortBy();

    String actualOutcome = sg.toStringQuestion();

    sg.toSpacedRepetition();

    StudyGuide sg2 = new StudyGuide();
    sg2.addFromDirectory(p2);
    String expectedOutcome = "What does ROYGBIV stand for?\n"
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

    assertEquals(expectedOutcome, actualOutcome);
  }

  /**
   * tests that the to string method prints out a string with the file's
   * contents properly extracted and formatted for question and answers
   *
   * @throws IOException throws based off the use of addFromDirectory
   */
  @Test
  void testCreateFiles() throws IOException {
    Path p = Path.of("src/test/resources/exampleDirectoryForPA1");
    Path p2 = Path.of("src/test/resources/studyGuideTest");

    StudyGuide sg = new StudyGuide(p, "filename", p2);
    sg.addFromDirectory(p);
    sg.sortBy();
    sg.createFiles();

    String mdQuestion = new MarkDownToQuestions().readFromFile(
        new File("src/test/resources/studyGuideTest.sr"));
    String mdSummary = new MarkDownToInfo().readFromFile(
        new File("src/test/resources/studyGuideTest.md"));

    StudyGuide sg2 = new StudyGuide();
    sg2.addFromDirectory(p2);
    String expectedSummary = "# Java Arrays\n"
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
        + "- v.add(object of type);"
        + "\n";


    String expectedOutcomeQuestions = "What does ROYGBIV stand for?\n"
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
        +  "\n"
        + "Where does the does \"My Favorite Things\" come from?\n"
        + "The Sound of Music\n"
        + "HARD"
        + "\n";

    assertEquals(expectedOutcomeQuestions, mdQuestion);
    assertEquals(expectedSummary, mdSummary);
  }

  /**
   * tests the constructor exceptions for when you don't send in a constructor
   */
  @Test
  void testConstructor() {
    assertThrows(RuntimeException.class, () -> new StudyGuide(
        Path.of("src/test/resources/studyGuideTest.md"), "filename"));
  }
}