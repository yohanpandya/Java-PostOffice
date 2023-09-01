import java.util.*;

public class AEFunctionalGraph<NodeType, EdgeType extends Number>
        extends BaseGraph<NodeType,EdgeType>
        implements FunctionalGraph<NodeType, EdgeType>{
    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in its node field).
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<AEFunctionalGraph.SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, AEFunctionalGraph.SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(AEFunctionalGraph.SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * @return
     */
    @Override public List<NodeType> getAllNodes() {
        List<NodeType> nodeList = new ArrayList<>();
        for (Map.Entry<NodeType, Node> entry : nodes.entrySet()) {
            nodeList.add(entry.getKey());
        }
        if (nodeList.size() > 0) {
            return nodeList;
        }
        else throw new NoSuchElementException("No nodes :(");
    }

    /**
     * read the data passed by DW from a matrix-represented graph
     * @param data
     */
    @Override public void readGraph(EdgeType[][] data, Hashtable<Integer, NodeType> mapping) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null && data[i][j].doubleValue() > 0) {
                    NodeType source = mapping.get(i);
                    NodeType dest = mapping.get(j);
                    insertNode(source);
                    insertNode(dest);
                    insertEdge(source, dest, data[i][j]);
                }
            }
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        // Start and end node
        Node s = nodes.get(start);
        Node e = nodes.get(end);
        // Make sure nodes arent null
        if (s == null || e == null){
            throw new NoSuchElementException("Nodes are null");
        }
        // Initialize start node, pqueue for unchecked nodes, and hashtable for unchecked
        SearchNode startNode = new SearchNode(s,0,null);
        PriorityQueue<SearchNode> searchQueue = new PriorityQueue<>();
        searchQueue.add(startNode);
        Hashtable<NodeType,Double> checked = new Hashtable<>();

        // Loop through unchecked nodes until all possibilities have been checked
        while (!searchQueue.isEmpty()){
            // Get closest node from pqueue and check if its been checked or is the end
            SearchNode toCheck = searchQueue.poll();
            if (checked.contains(toCheck.node)){
                continue;
            }
            if (toCheck.node == e){
                return toCheck;
            }
            // Since this node is unchecked add to checked nodes and add its adjacent nodes to the pqueue
            checked.put(toCheck.node.data, toCheck.cost);

            for (Edge edge : toCheck.node.edgesLeaving){
                Node adj  = edge.successor;
                double cost = toCheck.cost + edge.data.doubleValue();
                AEFunctionalGraph.SearchNode adjSearch = new AEFunctionalGraph.SearchNode(adj,cost,toCheck);
                searchQueue.add(adjSearch);
            }
        }
        // If at this point there is no path throw excpetion
        throw new NoSuchElementException("No path found");
    }

/**
 * Returns the list of data values from nodes along the shortest path
 * from the node with the provided start value through the node with the
 * provided end value.  This list of data values starts with the start
 * value, ends with the end value, and contains intermediary values in the
 * order they are encountered while traversing this shorteset path.  This
 * method uses Dijkstra's shortest path algorithm to find this solution.
 *
 * @param start the data item in the starting node for the path
 * @param end   the data item in the destination node for the path
 * @return list of data item from node along this shortest path
 */
@Override public List<NodeType> shortestPathData(NodeType start, NodeType end) {
    // Get the end node from the path and make node to track
    SearchNode e = computeShortestPath(start,end);
    SearchNode t = e;
    // List of data
    ArrayList<NodeType> list = new ArrayList<NodeType>();
    // While the node being tracked is not the start end of the path, add to list
    while(!t.node.data.equals(start)){
        list.add(t.node.data);
        // Update to predecessor
        t = t.predecessor;
    }
    // Add the start and set list to correct order
    list.add(t.node.data);
    // New list with reverse order
    ArrayList<NodeType> reverseList = new ArrayList<NodeType>();
    for (int i = list.size() - 1; i >= 0; i--){
        reverseList.add(list.get(i));
    }
    return reverseList;
}

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end   the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    @Override public double shortestPathCost(NodeType start, NodeType end) {
        try {
            return computeShortestPath(start,end).cost;
        }catch (Exception e){
            throw new NoSuchElementException("Couldnt find cost");
        }
    }

    /**
     * Generate MST for the graph and return the MST as a graph
     *
     * @return MST as a graph
     */
    @Override public FunctionalGraph<NodeType, EdgeType> generateMST() {
        AEFunctionalGraph<NodeType, EdgeType> mst = new AEFunctionalGraph<>();
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.data.doubleValue()));
        Set<NodeType> visitedNodes = new HashSet<>();

        // Get the first node in the graph
        NodeType startNode = null;
        for (NodeType node : getAllNodes()) {
            startNode = node;
            break;
        }

        if (startNode == null) {
            return mst;
        }

        visitedNodes.add(startNode);
        // Add all edges of the first node to the edgeQueue
        Node firstNode = nodes.get(startNode);
        edgeQueue.addAll(firstNode.edgesLeaving);

        while (!edgeQueue.isEmpty()) {
            Edge currentEdge = edgeQueue.poll();
            NodeType source = currentEdge.predecessor.data;
            NodeType dest = currentEdge.successor.data;

            if (visitedNodes.contains(dest)) {
                continue;
            }

            visitedNodes.add(dest);
            mst.insertNode(source);
            mst.insertNode(dest);
            mst.insertEdge(source, dest, (EdgeType) currentEdge.data);

            Node destNode = nodes.get(dest);
            for (Edge edge : destNode.edgesLeaving) {
                if (!visitedNodes.contains(edge.successor.data)) {
                    edgeQueue.add(edge);
                }
            }
        }
        return mst;
    }
}
