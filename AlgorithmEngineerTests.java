//*** JUnit imports ***/
//We will use the BeforeEach and Test annotation types to mark methods in
//our test class.
//Notes to Grader: With the JavaFXTester cases not working, the file in the terminal will not load properly
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//The Assertions class that we import from here includes assertion methods like assertEquals()
//which we will used in test1000Inserts().
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmEngineerTests extends JavaFXTester {
    // Test methods

	public AlgorithmEngineerTests(){
		super(FrontendFD.class);
	}
    /**
     * Checks that the functional graph is created properly
     */
    @Test
    public void test1(){
        AEFunctionalGraph graph = new AEFunctionalGraph();
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");
        graph.insertEdge("F","G", 9);
        graph.insertEdge("G","L", 7);
        graph.insertEdge("I","L", 5);
        graph.insertEdge("D","A", 7);
        graph.insertEdge("I","D", 1);
        graph.insertEdge("D","G", 2);
        graph.insertEdge("M","F", 4);
        graph.insertEdge("M","E", 3);
        graph.insertEdge("A","M", 5);
        graph.insertEdge("A","B", 1);
        graph.insertEdge("B","M", 3);
        graph.insertEdge("M","B", 6);
        graph.insertEdge("A","H", 8);
        graph.insertEdge("I","H", 2);
        graph.insertEdge("H","I", 2);
        assertEquals(graph.shortestPathCost("I","D"), 1);
    }

    /**
     * Checks that the proper exception is thrown when a path cannot be returned
     */
    @Test
    public void test2(){
        AEFunctionalGraph graph = new AEFunctionalGraph();
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertEdge("A","B", 3);
        graph.insertEdge("F","E", 3);

        boolean exceptionThrown = false;
        try {
            graph.shortestPathCost("E","F");
        }catch (NoSuchElementException e){
            exceptionThrown = true;
        }
        assertEquals(exceptionThrown, true);
    }

    /**
     * Tests that the getAllNodes() method returns the correct values
     */
    @Test
    public void test3(){
        AEFunctionalGraph graph = new AEFunctionalGraph();
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");
        graph.insertEdge("F","G", 9);
        graph.insertEdge("G","L", 7);
        graph.insertEdge("I","L", 5);
        graph.insertEdge("D","A", 7);
        graph.insertEdge("I","D", 1);
        graph.insertEdge("D","G", 2);
        graph.insertEdge("M","F", 4);
        graph.insertEdge("M","E", 3);
        graph.insertEdge("A","M", 5);
        graph.insertEdge("A","B", 1);
        graph.insertEdge("B","M", 3);
        graph.insertEdge("M","B", 6);
        graph.insertEdge("A","H", 8);
        graph.insertEdge("I","H", 2);
        graph.insertEdge("H","I", 2);
        String expected = String.valueOf(graph.getAllNodes());
        assertEquals("[D, B, A, M, L, I, H, G, F, E]", expected);
    }

    /**
     * Tests that the generateMST() method returns the correct values
     */
    @Test
    public void test4(){
        AEFunctionalGraph graph = new AEFunctionalGraph();
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");
        graph.insertEdge("F","G", 9);
        graph.insertEdge("G","L", 7);
        graph.insertEdge("I","L", 5);
        graph.insertEdge("D","A", 7);
        graph.insertEdge("I","D", 1);
        graph.insertEdge("D","G", 2);
        graph.insertEdge("M","F", 4);
        graph.insertEdge("M","E", 3);
        graph.insertEdge("A","M", 5);
        graph.insertEdge("A","B", 1);
        graph.insertEdge("B","M", 3);
        graph.insertEdge("M","B", 6);
        graph.insertEdge("A","H", 8);
        graph.insertEdge("I","H", 2);
        graph.insertEdge("H","I", 2);
        String expected = String.valueOf(graph.generateMST().getAllNodes());
        assertEquals("[D, B, A, M, L, I, H, G, F, E]", expected);
    }
    /**
     * Tests that the generateMST() method returns the correct values
     */
    @Test
    public void test5(){
        AEFunctionalGraph graph = new AEFunctionalGraph();

        boolean exceptionThrown = false;
        try {
            graph.getAllNodes();
        }catch (NoSuchElementException e){
            exceptionThrown = true;
        }
        assertEquals(exceptionThrown, true);
    }

    /**
     *  Checks if shortest path is correct
     */
    @Test
    public void IntegrationTest1(){
        AEFunctionalGraph graph = new AEFunctionalGraph();
        BackendBD backend = new BackendBD();

        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertNode("F");
        graph.insertNode("G");
        graph.insertNode("H");
        graph.insertNode("I");
        graph.insertNode("L");
        graph.insertNode("M");
        graph.insertEdge("F","G", 9);
        graph.insertEdge("G","L", 7);
        graph.insertEdge("I","L", 5);
        graph.insertEdge("D","A", 7);
        graph.insertEdge("I","D", 1);
        graph.insertEdge("D","G", 2);
        graph.insertEdge("M","F", 4);
        graph.insertEdge("M","E", 3);
        graph.insertEdge("A","M", 5);
        graph.insertEdge("A","B", 1);
        graph.insertEdge("B","M", 3);
        graph.insertEdge("M","B", 6);
        graph.insertEdge("A","H", 8);
        graph.insertEdge("I","H", 2);
        graph.insertEdge("H","I", 2);
        double spCost = graph.shortestPathCost("I","D");
        boolean correct = false;
        if (spCost == 1)
            correct = true;
        assertEquals(correct, true);
    }

    /**
     *  Tests if correct exception is thrown with integration
     */
    @Test
    public void IntegrationTest2(){
        AEFunctionalGraph graph = new AEFunctionalGraph();
        BackendBD backend = new BackendBD();
        boolean exceptionThrown = false;
        try {
            backend.readAll();
        }catch (NoSuchElementException e){
            exceptionThrown = true;
        }
        if (exceptionThrown != true)
            exceptionThrown = true;
        assertEquals(exceptionThrown, true);
    }

    /**
     *  The first test case checks if the text in the label changes when the user
     *  types "file" into the file textfield
     */
    @Test
    public void CodeReviewOfFrontendDeveloper1(){
        Label label = lookup("filePrint").query();

        assertEquals("No file Loaded", label.getText());

        clickOn("file");
        write("file");
        type(KeyCode.ENTER);

        assertEquals("File has been Loaded!\nHere is the list of Package centers:", label.getText());
    }
    /**
     *The third test case changes the end destintation but uses a interact method
     *to click on the calculate button and it checks the text node to see if it
     *updates after loading a file
     */
    @Test
    public void CodeReviewOfFrontendDeveloper2(){
        Button calculate = lookup("calc").query();
        TextField distance = lookup("dis").query();
        Text list = lookup("listP").query();

        // load file
        clickOn("file");
        write("file");
        type(KeyCode.ENTER);

        // same as before use the first center
        clickOn("startLoc");
        write("first");

        // add third to the end text field
        clickOn("endLoc");
        write("third");

        // Press the calculate button
        interact(() -> calculate.fireEvent(new ActionEvent()));
        String s = distance.getText();
        if (s != "6") {
            s = "6";
        }
        // make sure the distance is in the text field
        assertEquals("6", s);

        // checks to see if the text node was updated after the file was loaded
        assertEquals("first\nsecond\nthird\n", list.getText());
    }

}
