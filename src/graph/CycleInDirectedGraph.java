package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Victor on 2017/12/5.
 */
public class CycleInDirectedGraph {
    private int V; // No. of vertices
    private List<Integer>[] adj;
    private boolean[] visited;
    private boolean[] onStack;
    private int[] edgeTo;
    private Deque<Integer> cycle;

    private CycleInDirectedGraph(int V) {
        this.V = V;
        adj = new ArrayList[V]; // 采用邻接表存储
        visited = new boolean[V];
        onStack = new boolean[V];
        edgeTo = new int[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            visited[i] = false;
            onStack[i] = false;
        }
    }

    private void addEdge(int u, int v) {
        adj[u].add(v);
    }

    private void detectCycle() {
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(int u) {
        visited[u] = true;
        onStack[u] = true;
        for (int v : adj[u]) {
            if (hasCycle()) return;
            if (!visited[v]) {
                edgeTo[u] = v;
                dfs(v);
            } else if (onStack[v]) {
                // v 是环的结束， u是环的开始
                cycle = new ArrayDeque<>();
                for (int i = v; i != u; i = edgeTo[i]) {
                    cycle.add(i);
                }
                cycle.add(u);
                cycle.add(v);
            }
        }
        onStack[u] = false;
    }

    private void printCycle() {
        if (hasCycle()) {
            while (!cycle.isEmpty()) {
                System.out.println(cycle.poll());
            }
        } else {
            System.out.println("no cycle");
        }

    }

    private boolean hasCycle() {
        return cycle != null;
    }

    public static void main(String[] args) {
        CycleInDirectedGraph g = new CycleInDirectedGraph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(3, 1);
        g.addEdge(4, 3);
        g.addEdge(4, 5);
        g.detectCycle();
        g.printCycle();
    }
}
