package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 2017/12/9.
 */
public class Prim {
    List<Vertex> vertices = new ArrayList<>(); //顶点集
    List<Edge> edges = new ArrayList<>(); // 边集
    List<Vertex> U = new ArrayList<>();
    List<Edge> result = new ArrayList<>();

    public void buildGraph() {
        Vertex v1 = new Vertex("a");
        Vertex v2 = new Vertex("b");
        Vertex v3 = new Vertex("c");
        Vertex v4 = new Vertex("d");
        Vertex v5 = new Vertex("e");

        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        Edge e1 = new Edge(v1, v2, 5);
        Edge e2 = new Edge(v1, v4, 2);
        Edge e3 = new Edge(v2, v3, 3);
        Edge e4 = new Edge(v4, v3, 1);
        Edge e5 = new Edge(v4, v5, 4);
        Edge e6 = new Edge(v3, v5, 6);

        edges.add(e1);
        edges.add(e2);
        edges.add(e3);
        edges.add(e4);
        edges.add(e5);
        edges.add(e6);

    }

    public void prim() {
        Vertex start = vertices.get(0);
        U.add(start);
        for (int i = 0; i < vertices.size() - 1; i++) { //添加n-1条边就够了
            Edge minEdge = new Edge(start, start, 1000); //为了找到V-U最小的边
            for (Vertex v : U) {
                for (Edge e : edges) {
                    if ((e.vertex1 == v && !U.contains(e.vertex2)) || (e.vertex2 == v && !U.contains(e.vertex1))) {
                        if (minEdge.weight > e.weight) {
                            minEdge = e;
                        }
                    }
                }
            }
            if (U.contains(minEdge.vertex1)) {
                U.add(minEdge.vertex2);
            } else {
                U.add(minEdge.vertex1);
            }
            result.add(minEdge);
        }

    }

    public static void main(String[] args) {
        Prim g = new Prim();
        g.buildGraph();
        g.prim();
        int totalWeight = 0;
        for (Edge e : g.result) {
            System.out.println(e.vertex1.key + e.vertex2.key);
            totalWeight += e.weight;
        }
        System.out.println("total weight:" + totalWeight);
    }

    class Vertex {
        public String key;

        Vertex(String key) {
            this.key = key;
        }
    }

    class Edge {
        public Vertex vertex1;
        public Vertex vertex2;
        public int weight;

        Edge(Vertex v1, Vertex v2, int weight) {
            this.vertex1 = v1;
            this.vertex2 = v2;
            this.weight = weight;
        }
    }
}
