package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Victor on 2017/12/6.
 */
public class CycleInUndirectedGraph {
    private int V; // No. of vertices
    private List<Integer>[] adj;
    private boolean[] visited;
//    private boolean hasCycle;
    private int[] edgeTo;
    private Deque<Integer> cycle;

    private CycleInUndirectedGraph(int V) {
        this.V = V;
        adj = new ArrayList[V]; // 采用邻接表存储
        visited = new boolean[V];
        edgeTo = new int[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            visited[i] = false;
        }
    }

    private void addEdge(int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    private void detectCycle() {
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, i);
            }
        }
    }

    private void dfs(int u, int v) {
        // v 表示parent顶点, u表示当前顶点
        visited[u] = true;
        for (int w : adj[u]) {
            if (this.hasCycle()) return;
            if (w != v) {
                // 只往下遍历不往上遍历
                if (!visited[w]) {
                    // 当前遍历到的顶点w对应了顶点u
                    edgeTo[w] = u;
                    dfs(w, u);
                } else {
                    cycle = new ArrayDeque<>();
                    for (int x = u; x != w; x = edgeTo[x]) {
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(u);
                    return;
                }
            }
        }
    }

    private boolean hasCycle() {
        return cycle != null;
    }

    private void printCycle() {
        if (cycle == null) return;
        while (!cycle.isEmpty()) {
            System.out.print(cycle.pop() + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CycleInUndirectedGraph g = new CycleInUndirectedGraph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 1);
        g.addEdge(0, 5);

        g.detectCycle();

        if (g.hasCycle()) {
            System.out.println("There is a cycle!");
            g.printCycle();
        } else {
            System.out.println("There are no cycles!");
        }
    }
}
