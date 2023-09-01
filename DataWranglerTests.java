import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/*** JUnit imports ***/
//We will use the BeforeEach and Test annotation types to mark methods in
//our test class.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//The Assertions class that we import from here includes assertion methods like assertEquals()
//which we will used in test1000Inserts().
import static org.junit.jupiter.api.Assertions.assertEquals;

//More details on each of the imported elements can be found here:
//https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/package-summary.html
/*** JUnit imports end  ***/


public class DataWranglerTests {
	public static void main (String[] args) {
		DataWranglerTests d = new DataWranglerTests();
		d.test1();
		d.test2();
		d.test3();
		d.test4();
		d.test5();
	}
	/*
	 * Test that the arrayList created in the read method stores correct the packageCenterDW objects
	 */
	@Test
	public void test1() {
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
		assertEquals("Houston", aod.get(3).getName());
		assertEquals("Phoenix", aod.get(4).getName());
		assertEquals("Philadelphia", aod.get(5).getName());
		assertEquals("San_Antonio", aod.get(6).getName());
		assertEquals("San_Diego", aod.get(7).getName());
		assertEquals("Dallas", aod.get(8).getName());
		assertEquals("San_Jose", aod.get(9).getName());
		assertEquals(10, aod.size());
		
		
	}
	//test that the matrix created stores the right values
	@Test
	public void test2() {
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
		Double[][] weightsMatrix = d.getWeightsMatrix();
		/*for(int i =0; i < weightsMatrix.length;i++) {
			for(int j =0; j < weightsMatrix[0].length;j++) {
				System.out.print(weightsMatrix[i][j] + " ");
			}
			System.out.println();
		}*/
		assertEquals(2.37, weightsMatrix[1][0]);
		assertEquals(4.75, weightsMatrix[9][0]);
		assertEquals(3.05, weightsMatrix[2][1]);
		assertEquals(1.88, weightsMatrix[3][2]);
		assertEquals(2.76, weightsMatrix[4][3]);
		assertEquals(3.59, weightsMatrix[5][4]);
		assertEquals(3.98, weightsMatrix[6][5]);
		assertEquals(4.12, weightsMatrix[7][6]);
		assertEquals(2.64, weightsMatrix[8][7]);
		assertEquals(2.91, weightsMatrix[9][8]);
		
	}
	//make sure getMatrixPosition is correct for each packageCenterDW
	@Test
	public void test3() {
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
		assertEquals(0, aod.get(0).getMatrixPosition());
		assertEquals(1, aod.get(1).getMatrixPosition());
		assertEquals(2, aod.get(2).getMatrixPosition());
		assertEquals(3, aod.get(3).getMatrixPosition());
		assertEquals(4, aod.get(4).getMatrixPosition());
		assertEquals(5, aod.get(5).getMatrixPosition());
		assertEquals(6, aod.get(6).getMatrixPosition());
		assertEquals(7, aod.get(7).getMatrixPosition());
		assertEquals(8, aod.get(8).getMatrixPosition());
		assertEquals(9, aod.get(9).getMatrixPosition());
		
	}
	//test getWeight
	@Test
	public void test4() {
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
		assertEquals(2.37, d.getWeight("New_York", "Los_Angeles"));
	}
	
	@Test
	public void test5() {
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
		//System.out.println(d.getNeighbors( aod.get(0)).get(0));
		assertEquals("Los_Angeles",d.getNeighbors( aod.get(0)).get(0).getName());
		assertEquals("San_Jose",d.getNeighbors( aod.get(0)).get(1).getName());
		assertEquals(2,d.getNeighborCount( aod.get(0)));
		
		
		
	}
	
	
	//INTEGRATION TESTS
	@Test
	public void AECodeReviewTest1() {
	        AEFunctionalGraph<String, Integer> dj = new AEFunctionalGraph<String, Integer>();
	        dj.insertNode("A");
	        dj.insertNode("B");
	        dj.insertNode("D");
	        dj.insertNode("E");
	        dj.insertNode("F");
	        dj.insertNode("G");
	        dj.insertNode("H");
	        dj.insertNode("I");
	        dj.insertNode("L");
	        dj.insertNode("M");
	        dj.insertEdge("F", "G", 9);
	        dj.insertEdge("D", "A", 7);
	        dj.insertEdge("A", "M", 5);
	        dj.insertEdge("D", "G", 2);
	        dj.insertEdge("A", "B", 1);
	        dj.insertEdge("A", "H", 8);
	        dj.insertEdge("G", "L", 7);
	        dj.insertEdge("M", "E", 3);
	        dj.insertEdge("B", "M", 3);
	        dj.insertEdge("H", "B", 6);
	        dj.insertEdge("H", "I", 2);
	        dj.insertEdge("I", "L", 5);
	        dj.insertEdge("M", "F", 4);
	        dj.insertEdge("I", "H", 2);
	        dj.insertEdge("I", "D", 1);
	        
	        assertEquals(dj.shortestPathCost("I","G"), 3);
	    
	}
	@Test
	public void AECodeReviewTest2() {
        AEFunctionalGraph<String, Integer> dj = new AEFunctionalGraph<String, Integer>();
        dj.insertNode("A");
        dj.insertNode("B");
        dj.insertNode("D");
        dj.insertNode("E");
        dj.insertNode("F");
        dj.insertNode("G");
        dj.insertNode("H");
        dj.insertNode("I");
        dj.insertNode("L");
        dj.insertNode("M");
        dj.insertEdge("F", "G", 9);
        dj.insertEdge("D", "A", 7);
        dj.insertEdge("A", "M", 5);
        dj.insertEdge("D", "G", 2);
        dj.insertEdge("A", "B", 1);
        dj.insertEdge("A", "H", 8);
        dj.insertEdge("G", "L", 7);
        dj.insertEdge("M", "E", 3);
        dj.insertEdge("B", "M", 3);
        dj.insertEdge("H", "B", 6);
        dj.insertEdge("H", "I", 2);
        dj.insertEdge("I", "L", 5);
        dj.insertEdge("M", "F", 4);
        dj.insertEdge("I", "H", 2);
        dj.insertEdge("I", "D", 1);
        String expected = String.valueOf(dj.getAllNodes());
        assertEquals("[D, B, A, M, L, I, H, G, F, E]", expected);
    
	}
	@Test
	public void IntegrationTest1() {
		//Test Backend's readall
		BackendBD b = new BackendBD();
		b.readAll("input.dot");
		assertEquals(10,b.mapping.size());
		//System.out.println( b.mapping.get(1).getName());
		assertEquals("New_York", b.mapping.get(0).getName());
		assertEquals("Los_Angeles", b.mapping.get(1).getName());
		assertEquals("Chicago", b.mapping.get(2).getName());
		assertEquals("Houston", b.mapping.get(3).getName());
		assertEquals("Phoenix", b.mapping.get(4).getName());
		assertEquals("Philadelphia", b.mapping.get(5).getName());
		assertEquals("San_Antonio", b.mapping.get(6).getName());
		assertEquals("San_Diego", b.mapping.get(7).getName());
		assertEquals("Dallas", b.mapping.get(8).getName());
		assertEquals("San_Jose", b.mapping.get(9).getName());
		
	}
	@Test
	public void IntegrationTest2() {
		//test Backend's getPackageCenterByName
		BackendBD b = new BackendBD();
		b.readAll("input.dot");
		assertEquals("Los_Angeles", b.getPackageCenterByName("Los_Angeles").getName());
		assertEquals("New_York", b.getPackageCenterByName("New_York").getName());
		assertEquals("San_Jose", b.getPackageCenterByName("San_Jose").getName());
		assertEquals("San_Diego", b.getPackageCenterByName("San_Diego").getName());
		assertEquals("Dallas", b.getPackageCenterByName("Dallas").getName());
		
	}
	
}
