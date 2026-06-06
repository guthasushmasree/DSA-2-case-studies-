import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class BellmanFord {

    static final int INF = Integer.MAX_VALUE;

    public static void bellmanFord(List<Edge> edges, int V, int source) {

        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        // Relax all edges V-1 times
        for (int i = 1; i <= V - 1; i++) {
            for (Edge e : edges) {
                if (dist[e.src] != INF &&
                    dist[e.src] + e.weight < dist[e.dest]) {

                    dist[e.dest] = dist[e.src] + e.weight;
                }
            }
        }

        // Check for negative-weight cycle
        boolean negativeCycle = false;

        for (Edge e : edges) {
            if (dist[e.src] != INF &&
                dist[e.src] + e.weight < dist[e.dest]) {

                negativeCycle = true;
                break;
            }
        }

        // Display Results
        if (negativeCycle) {

            System.out.println("\n==================================");
            System.out.println(" NEGATIVE WEIGHT CYCLE DETECTED ");
            System.out.println("==================================");

        } else {

            String[] vertices = {"A", "B", "C", "D", "E"};

            System.out.println("\n==========================================");
            System.out.println(" BELLMAN-FORD SHORTEST PATH RESULTS");
            System.out.println("==========================================");

            System.out.printf("%-10s %-20s\n", "Vertex", "Distance");

            System.out.println("------------------------------------------");

            for (int i = 0; i < V; i++) {
                System.out.printf("%-10s %-20d\n",
                        vertices[i], dist[i]);
            }

            System.out.println("==========================================");
        }
    }

    public static void main(String[] args) {

        int V = 5;
        int source = 0;

        List<Edge> edges = new ArrayList<>();

        // Graph Edges
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 0, 2));
        edges.add(new Edge(4, 3, 7));

        bellmanFord(edges, V, source);
    }
}