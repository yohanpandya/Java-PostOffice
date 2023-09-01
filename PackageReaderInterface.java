
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface PackageReaderInterface {
    //returns arraylist of package centers that are created from the dot file
    //throws exception if file cannot be read
    public ArrayList<PackageCenterDW> read(File filename) throws FileNotFoundException;

    public double getWeight(String p1, String p2);
    public Double[][] getWeightsMatrix();
    public int getNeighborCount(PackageCenterDW p1);
    public List<PackageCenterDW> getNeighbors(PackageCenterDW p1);
}
