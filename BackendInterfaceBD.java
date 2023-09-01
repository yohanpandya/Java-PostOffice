import java.util.List;

public interface BackendInterfaceBD {
    // reads all the PackageCenter and adds them to the graph
    public void readAll(String fileName);

    // Returns the list of nodes along the path of the shortest path
    public List<PackageCenterDW> shortestPath(PackageCenterDW start, PackageCenterDW end);

    // Takes in the list of nodes that is shortest path between the two packageCenters and
    // Then looks if it’s in a specific range and will decide what company is takes
    public String length(List<PackageCenterDW> shortest);

    // Returns a specific package center if inside the graph
    public PackageCenterDW getPackageCenterByName(String str);

}
