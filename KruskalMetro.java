import java.util.*;

class Edge implements Comparable<Edge> {

    String src, dest;
    int weight;

    Edge(String s, String d, int w) {
        src = s;
        dest = d;
        weight = w;
    }

    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalMetro {

    static Map<String, String> parent = new HashMap<>();

    static String find(String node) {

        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }

        return parent.get(node);
    }

    static void union(String a, String b) {

        String rootA = find(a);
        String rootB = find(b);

        if (!rootA.equals(rootB)) {
            parent.put(rootA, rootB);
        }
    }

    public static void main(String[] args) {

        String[] vertices =
                {"M","W","S","E","Y","H","K"};

        for (String v : vertices)
            parent.put(v, v);

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge("E","S",4));
        edges.add(new Edge("K","W",5));
        edges.add(new Edge("S","W",6));
        edges.add(new Edge("M","E",7));
        edges.add(new Edge("M","K",8));
        edges.add(new Edge("Y","E",8));
        edges.add(new Edge("H","Y",9));
        edges.add(new Edge("M","Y",9));
        edges.add(new Edge("M","S",10));
        edges.add(new Edge("H","M",11));
        edges.add(new Edge("M","W",12));
        edges.add(new Edge("H","K",14));

        Collections.sort(edges);

        int total = 0;

        System.out.println("Edges Selected in MST:\n");

        for (Edge e : edges) {

            if (!find(e.src).equals(find(e.dest))) {

                union(e.src, e.dest);

                total += e.weight;

                System.out.println(
                        e.src + " - " +
                        e.dest + " = " +
                        e.weight);
            }
        }

        System.out.println(
                "\nTotal Minimum Cost = "
                        + total + " -crore");
    }
}