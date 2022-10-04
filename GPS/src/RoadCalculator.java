/**
 * @author Yasin Rhamatzada
 *
 * This class is the driver class
 */
import java.util.*;
import big.data.DataSource;

public class RoadCalculator {
    private static final HashMap<String, Node> graph = new HashMap<>();
    private static final LinkedList<Edge> mst = new LinkedList<>();

    /**
     * This method is the driver class for the graph
     *
     * @param args a string array of arguments
     */
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        boolean end = false;
        String start;

        System.out.print("Please enter graph URL: ");
        String URL = inp.nextLine();

        System.out.println("Loading Map...\n");
        // CHANGE URL TO USER INPUT ***************
        HashMap<String, Node> cities = new HashMap<>();
        DataSource ds = DataSource.connectXML(URL);
        ds.load();
        String cityNamesStr = ds.fetchString("cities");
        String[] cityNames = cityNamesStr.substring(1, cityNamesStr.length() - 1).replace("\"", "").split(",");
        String roadNamesStr = ds.fetchString("roads");
        String[] roadNames = roadNamesStr.substring(1, roadNamesStr.length() - 1).split("\",\"");

        roadNames[0] = roadNames[0].substring(1, roadNames[0].length());
        roadNames[roadNames.length - 1] = roadNames[roadNames.length - 1].substring(0, roadNames[roadNames.length - 1].length() - 1);

        System.out.println("Cities: \n");
        for (String cityName : cityNames) {
            System.out.println(cityName);
            buildGraph(cityName);
        }

        System.out.println("\nRoad Names: \n");
        for (String roadName : roadNames) {
            String[] split = roadName.split(",");
            System.out.printf("%s to %s: %s\n", split[0], split[1], split[2]);
            Edge newEdge = new Edge(graph.get(split[0]), graph.get(split[1]), Integer.parseInt(split[2]));
            graph.get(split[0]).setEdge(newEdge);
            graph.get(split[1]).setEdge(newEdge);
        }

        System.out.println("\nMinimum Spanning Tree: \n");
        buildMST(graph, cityNames);
        for (Edge edge : mst) System.out.println(edge.edgeToString()); //printing out the mst

        while (!end) {
            System.out.print("\nEnter a starting point for shortest path or Q to quit: ");
            start = inp.nextLine();
            if (start.length() == 1 && Character.toUpperCase(start.charAt(0)) == 'Q') {
                System.out.println("Goodbye; PSA, there's a cop on the right in 3 miles!");
                end = true;
            } else {
                HashSet<String> names = new HashSet<>(Arrays.asList(cityNames));

                System.out.print("Enter a destination: ");
                String dest = inp.nextLine();
                if (!names.contains(start) || !names.contains(dest))
                    System.out.println("Inputted start or destination does not exist.");
                else {
                    int dist = Dijkstra(graph, start, dest, cityNames);
                    System.out.println("Distance: " + dist);
                    String temp = graph.get(dest).getPath().toString();
                    temp = temp.substring(1, temp.length() - 1);
                    String path = start + ", " + temp + ", " + dest;
                    System.out.println("Path: " + path);
                    graph.get(dest).setPath(new LinkedList<>());
                } //close wrong input else
            }
        }//close while
    }//close main

    /**
     * This method is the buildGraph to build the hashmap graph
     *
     * @param location the location aka cityname
     */
    public static void buildGraph(String location) {
        Node newNode = new Node(location, false);
        graph.put(location, newNode);
    }//close buildGraph

    /**
     * This method is the MST builder
     *
     * @param graph the graph
     * @param node  the city names
     */
    public static void buildMST(HashMap<String, Node> graph, String[] node) {
        //building the MST starting at the first node
        Edge minEdge = null; //creating minimum costing edge reference // new Edge(0)
        boolean opt = false;

        ArrayList<Edge> allEdges = new ArrayList<>();
        graph.get(node[0]).setVisited(true); //set visited for first node to true
        for (int i = 0; i < node.length - 1; i++) {  //loop nodes - 1 times, for each edge of the MST
            Edge[] edges = graph.get(node[i]).getEdges().toArray(new Edge[graph.get(node[i]).getEdges().size()]); //convert hashset to array
            allEdges.addAll(Arrays.asList(edges)); //adding node edges to allEdges arrayList
            for (Edge allEdge : allEdges) { //loop the length of the edges for the current node
                if (minEdge == null)
                    minEdge = allEdge;
                else if (allEdge.getA().isVisited() && !allEdge.getB().isVisited() && allEdge.getCost() < minEdge.getCost()) {
                    minEdge = allEdge;
                    opt = true;
                } else if (!allEdge.getA().isVisited() && allEdge.getB().isVisited() && allEdge.getCost() < minEdge.getCost()) {
                    minEdge = allEdge;
                    opt = false;
                }
            } //close edges for

            if (opt) minEdge.getB().setVisited(true);
            else minEdge.getA().setVisited(true);

            allEdges.remove(minEdge);
            mst.add(minEdge); //add minEdge to MST linked list
            minEdge = null; //resetting minEdge
        } //close mst for
    }

    /**
     * This method is Dijkstra's algorithm
     *
     * @param graph  the graph
     * @param source the starting node
     * @param dest   the ending node
     * @param cities the cities array
     * @return the int
     */
    public static int Dijkstra(HashMap<String, Node> graph, String source, String dest, String[] cities) {
        HashSet<Node> set = new HashSet<>(); //creating empty set
        for (String city : cities) {
            if (graph.get(city) == graph.get(source))
                graph.get(city).setDistance(0);
            else
                graph.get(city).setDistance(1000); //setting all nodes distance to -
        }

        for (int i = 0; i < graph.size(); i++) {
            Node nodeLow = new Node();
            nodeLow.setDistance(1000);
            for (String city : cities)
                if (!set.contains(graph.get(city)) && graph.get(city).getDistance() < nodeLow.getDistance())
                    nodeLow = graph.get(city);

            set.add(nodeLow);

            for (Edge a : nodeLow.getEdges()) {
                int wt = nodeLow.getDistance() + a.getCost();
                if (a.getA() == nodeLow) {
                    if (wt < a.getB().getDistance()) {
                        a.getB().setDistance(wt);
                        a.getB().getPath().addLast(nodeLow.getName());
                    }
                } else if (a.getB() == nodeLow) {
                    if (wt < a.getA().getDistance()) {
                        a.getA().setDistance(wt);
                        a.getA().getPath().addLast(nodeLow.getName());
                    }
                }//close else if
            }//close for
        }//close main for
        return graph.get(dest).getDistance();
    }//close main

}
