/**
 * @author Yasin Rhamatzada
 *
 * This class represents a node in a graph
 */
import java.util.*;

public class Node {
    private String name;
    private HashSet<Edge> edges = new HashSet<>();
    private boolean visited;
    private LinkedList<String> path = new LinkedList<>();
    private int distance;

    /**
     * This method is the default constructor and takes in no values
     */
    public Node() {

    }

    /**
     * This method is the default constructor and takes in 2 values
     * @param name the name of the node
     * @param visited the boolean value of the node
     */
    public Node(String name, boolean visited) {
        this.name = name;
        this.visited = visited;
    }

    /**
     * This method is the getter for the name
     * @return the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * This method is the setter for the name
     * @param name the name of the node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method is getter for the edges
     * @return the edges hashset
     */
    public HashSet<Edge> getEdges() {
        return edges;
    }

    /**
     * This method is the setter for the edges hashset
     * @param edges the edges hashset
     */
    public void setEdges(HashSet<Edge> edges) {
        this.edges = edges;
    }

    /**
     * This method is the setter for a single edge in the edges hashset
     * @param newEdge the new edge to be added
     */
    public void setEdge(Edge newEdge) {
        edges.add(newEdge);
    }

    /**
     * This method is a getter for the boolean variable visited
     * @return the boolean value for visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * This method is the setter for the visited boolean variable
     * @param visited the boolean variable
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * This method is the getter for the visited variable
     * @return the visited variable
     */
    public boolean getVisited() {
        return visited;
    }

    /**
     * This method is the getter for the path linkedlist
     * @return the path linked list
     */
    public LinkedList<String> getPath() {
        return path;
    }

    /**
     * This method is the setter for the path linkedlist
     * @param path the linkedlist of paths
     */
    public void setPath(LinkedList<String> path) {
        this.path = path;
    }

    /**
     * This method is the getter for the distance variable
     * @return the distance value
     */
    public int getDistance() {
        return distance;
    }

    /**
     * This method is the setter for the distance variable
     * @param distance the distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * This method is the toString for the name
     * @return the string name
     */
    public String nameToString() {
        return name;
    }

}
