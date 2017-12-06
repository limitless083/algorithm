package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 2017/12/6.
 */
public class CycleInUndirectedGraph {
    private int V; // No. of vertices
    private List<Integer>[] adj;
    private boolean[] visited;
    private boolean hasCycle;

    private CycleInUndirectedGraph(int V) {
        this.V = V;
        adj = new ArrayList[V]; // 采用邻接表存储
        visited = new boolean[V];
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
            if (w != v) {
                // 只往下遍历不往上遍历
                if (!visited[w]) {
                    dfs(w, u);
                } else {
                    hasCycle = true;
                    return;
                }
            }
        }
    }

    private boolean hasCycle() {
        return hasCycle;
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
        } else {
            System.out.println("There are no cycles!");
        }
    }
}
