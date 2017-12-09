package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by Victor on 2017/12/4.
 */
public class TopoSort {
    private int V; // No. of vertices
    private List<Integer>[] adj;
    private boolean[] visited;
    private Deque<Integer> stack;

    private TopoSort(int V) {
        this.V = V;
        adj = new ArrayList[V]; // 采用邻接表存储
        visited = new boolean[V];
        stack = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            visited[i] = false;
        }

    }

    private void addEdge(int u, int v) {
        adj[u].add(v);
    }

    private void topoDfsSort() {

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private void dfs(int u) {
        visited[u] = true;
        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs(v);
            }
        }
        stack.push(u);
    }

    public void topoKahnSort() {
        List<Integer> result = new ArrayList<>();

        // 初始化所有顶点的入度 时间复杂度O(V + E)
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) {
                indegree[v]++;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int u = 0; u < V; u++) {
            if (indegree[u] == 0) {
                // 将所有入度为0的顶点加入到Queue中
                queue.add(u);
            }
        }

        int cnt = 0; // 看遍历到哪了
        while (!queue.isEmpty()) {
            // 获取顶点并将顶点所在的边的另一端顶点的入度减一
            int u = queue.poll();
            cnt++;
            result.add(u);
            for (int v : adj[u]) {
                if (--indegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        if (cnt < V) {
            System.out.println("There is a cycle!");
        } else {
            for (int vertices : result) {
                System.out.print(vertices + " ");
            }
        }
    }

    public static void main(String[] args) {
        TopoSort g = new TopoSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("kahn topo sort");
        g.topoKahnSort();
        System.out.println("\ndfs topo sort");
        g.topoDfsSort();
    }
}
