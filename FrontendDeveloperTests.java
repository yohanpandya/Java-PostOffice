import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import edu.wisc.cs.cs400.JavaFXTester;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

class FrontendFDTests extends JavaFXTester {

    public FrontendFDTests() {
        // you must specify the Application being tested by passing its class
        // to the parent class through the constructor, like this:
        super(FrontendFD.class);
    }

  /**
   * The first test case checks if the text in the label changes when the user types "file" into the
   * file textfield
   *
   * @return
   */
  @Test
  public void test1() {
    Label label = lookup("filePrint").query();

    assertEquals("No file Loaded", label.getText());

    clickOn("file");
    write("input.dot");
    type(KeyCode.ENTER);

    assertEquals("File has been Loaded!\nHere is the list of Package centers:", label.getText());
  }

  /**
   * The second test case checks the placeholder classes and to see if the application gathers the
   * correct information from them
   */
  @Test
  public void test2() {
    TextField distance = lookup("dis").query();

    // load file
    clickOn("file");
    write("input.dot");
    type(KeyCode.ENTER);

    // add first to the start text field
    clickOn("startLoc");
    write("New_York");

    // add second to the end text field
    clickOn("endLoc");
    write("Los_Angeles");

    clickOn("calc");

    assertEquals("2.37", distance.getText());
  }

  /**
   * The third test case changes the end destintation but uses a interact method to click on the
   * calculate button and it checks the text node to see if it updates after loading a file
   */
  @Test
  public void test3() {
    Button calculate = lookup("calc").query();
    TextField distance = lookup("dis").query();
    Text list = lookup("listP").query();

    // load file
    clickOn("file");
    write("input.dot");
    type(KeyCode.ENTER);

    // same as before use the first center
    clickOn("startLoc");
    write("New_York");

    // add third to the end text field
    clickOn("endLoc");
    write("Dallas");

    // Press the calculate button
    interact(() -> calculate.fireEvent(new ActionEvent()));

    // make sure the distance is in the text field
    assertEquals("7.66", distance.getText());

    // checks to see if the text node was updated after the file was loaded
    assertEquals("New_York\nLos_Angeles\nChicago\nHouston\nPhoenix\n" +
            "Philadelphia\nSan_Antonio\nSan_Diego\nDallas\nSan_Jose\n", list.getText());
  }

  /**
   * The fourth test case tests the reset button which should make sure the textfield start is wiped
   * after being typed into
   */
  @Test
  public void test4() {
    TextField start = lookup("startLoc").query();
    Button reset = lookup("reset").query();

    // click on the startLoc textfield and type "first"
    clickOn("startLoc");
    write("first");

    // Press the reset button
    interact(() -> reset.fireEvent(new ActionEvent()));

    // check that the text field got set back to an emtpy string and does NOT
    // contain "first"
    assertEquals("", start.getText());
  }

  /**
   * The ;ast test case click on the clear button that will wipe the application clean to start off
   */
  @Test
  public void test5() {
    TextField start = lookup("startLoc").query();
    Label label = lookup("filePrint").query();
    Button clear = lookup("clear").query();

    // click on the startLoc textfield and type "first"
    clickOn("startLoc");
    write("first");

    // click on the file textfield and type "file" then hit ENTER
    clickOn("file");
    write("input.dot");
    type(KeyCode.ENTER);

    // press the clear button
    interact(() -> clear.fireEvent(new ActionEvent()));

    // Check that both text fields were clear and set back to how the user would
    // open the app
    assertEquals("", start.getText());
    assertEquals("No file Loaded", label.getText());
  }

  /**
   * The first integrated test case checks the overall functionality of using input.dot file and then inputting locations
   */
  @Test
  public void integratedTest1() {
    TextField distance = lookup("dis").query();

    // load file
    clickOn("file");
    write("input.dot");
    type(KeyCode.ENTER);

    // add first to the start text field
    clickOn("startLoc");
    write("Phoenix");

    // add second to the end text field
    clickOn("endLoc");
    write("Chicago");

    clickOn("calc");

    assertEquals("4.64", distance.getText());
  }

  /**
   * The second test case checks if the reset button still clears the fields properly
   */
  @Test
  public void integratedTest2() {
    TextField distance = lookup("dis").query();

    // load file
    clickOn("file");
    write("input.dot");
    type(KeyCode.ENTER);

    // add first to the start text field
    clickOn("startLoc");
    write("New_York");

    // add second to the end text field
    clickOn("endLoc");
    write("Houston");

    clickOn("calc");
    clickOn("reset");

    assertEquals("", distance.getText());
  }

  /**
   * The test case reviewDW1 tests the PackageCenterDW loading the dot file
   * and checking if they were properly added after using the method "read"
   */
  @Test
  public void reviewDW1(){
    PackageReaderDW d = new PackageReaderDW();
    File f = new File("input.dot");
    ArrayList<PackageCenterDW> aod = new ArrayList<PackageCenterDW> ();

    try{

      //System.out.println("RAN");
      aod = d.read(f);
      //System.out.println(aod.toString());
    }catch(FileNotFoundException e) {

      System.out.println("1");

    }
    assertEquals("New_York", aod.get(0).getName());
    assertEquals("Los_Angeles", aod.get(1).getName());
    assertEquals("Chicago", aod.get(2).getName());

    assertEquals(10, aod.size());
  }

  /**
   * Test case reviewDW2 tries to run a non-functional dot file
   * in the case of it throwing the FileNotFoundException
   */
  @Test
  public void reviewDW2(){
    PackageReaderDW d = new PackageReaderDW();
    File f = new File("d");
    ArrayList<PackageCenterDW> aod = new ArrayList<PackageCenterDW> ();

    boolean exceptionThrown = false;
    try{
      //System.out.println("RAN");
      d.read(f);
      //System.out.println(aod.toString());
    }catch(FileNotFoundException e) {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);
  }

}

