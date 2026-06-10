import java.util.Arrays;

public class BellmanFordBMTC {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        String[] hubs = {"MJC", "KEM", "JAY", "KOR", "WHF", "HBR", "MRT"};

        Edge[] edges = {
            new Edge(0, 1, 8),    // MJC -> KEM
            new Edge(0, 2, 5),    // MJC -> JAY
            new Edge(0, 3, 12),   // MJC -> KOR
            new Edge(2, 3, 4),    // JAY -> KOR
            new Edge(1, 5, 7),    // KEM -> HBR
            new Edge(1, 4, 10),   // KEM -> WHF
            new Edge(3, 4, 6),    // KOR -> WHF
            new Edge(3, 6, 9),    // KOR -> MRT
            new Edge(4, 5, 3),    // WHF -> HBR
            new Edge(5, 6, 11),   // HBR -> MRT
            new Edge(4, 6, -3)    // WHF -> MRT
        };

        int V = 7;
        int source = 0; // MJC

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        System.out.println("Iteration 0:");
        printDistances(dist, hubs);

        // Bellman-Ford
        for (int i = 1; i <= V - 1; i++) {

            boolean updated = false;

            for (Edge e : edges) {

                if (dist[e.src] != Integer.MAX_VALUE &&
                    dist[e.src] + e.weight < dist[e.dest]) {

                    dist[e.dest] = dist[e.src] + e.weight;
                    updated = true;
                }
            }

            System.out.println("\nIteration " + i + ":");
            printDistances(dist, hubs);

            if (!updated) {
                System.out.println("\nAlgorithm converged early at Iteration " + i);
                break;
            }
        }

        // Negative cycle check
        boolean negativeCycle = false;

        for (Edge e : edges) {
            if (dist[e.src] != Integer.MAX_VALUE &&
                dist[e.src] + e.weight < dist[e.dest]) {
                negativeCycle = true;
                break;
            }
        }

        if (negativeCycle) {
            System.out.println("\nNegative Weight Cycle Detected!");
        } else {
            System.out.println("\nNo Negative Weight Cycle.");
        }

        System.out.println("\nFinal Shortest Distances from MJC:");
        for (int i = 0; i < V; i++) {
            System.out.println(hubs[i] + " = " + dist[i]);
        }
    }

    static void printDistances(int[] dist, String[] hubs) {

        for (int i = 0; i < dist.length; i++) {

            if (dist[i] == Integer.MAX_VALUE)
                System.out.print(hubs[i] + "=INF  ");
            else
                System.out.print(hubs[i] + "=" + dist[i] + "  ");
        }

        System.out.println();
    }
}
