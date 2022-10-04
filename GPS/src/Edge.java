/**
 * @author Yasin Rhamatzada
 *
 * This class represents an edge between 2 nodes
 */
public class Edge {
    private Node A;
    private Node B;
    private int cost = 0;

    /**
     * This method is the default constructor and takes in 3 values
     * @param a the node at position A
     * @param b the node at position B
     * @param cost the cost/weight of the edge
     */
    public Edge(Node a, Node b, int cost) {
        A = a;
        B = b;
        this.cost = cost;
    }

    /**
     * This method is the tostring for the edge class
     * @return the string representation of the edge
     */
    public String toString() {
        return "";
    }

    /**
     * This method is the getter for the A node
     * @return node A
     */
    public Node getA() {
        return A;
    }

    /**
     * This method is the setter for the A node
     * @param a the node A
     */
    public void setA(Node a) {
        A = a;
    }

    /**
     * This method is the getter for the B node
     * @return node B
     */
    public Node getB() {
        return B;
    }

    /**
     * This method is the setter for node B
     * @param b node B
     */
    public void setB(Node b) {
        B = b;
    }

    /**
     * This method is the getter for the cost
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * This method is the setter for the cost
     * @param cost the cost/weight
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * This method is the toString for both edges A and B
     * @return the string representation of both A and B
     */
    public String edgeToString() {
        return A.nameToString() + " to " + B.nameToString() + " " + cost;
    }
}
