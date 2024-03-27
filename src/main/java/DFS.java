import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DFS {
    private final int V;
    private final LinkedList[] adj;

    DFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    List<Integer> dfs(int s) {
        boolean[] visited = new boolean[V];
        List<Integer> list = new LinkedList<>();
        dfsUtil(s, visited, list);
        return list;
    }

    void dfsUtil(int v, boolean[] visited, List<Integer> list) {
        visited[v] = true;
        list.add(v);

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                dfsUtil(n, visited, list);
            }
        }
    }

    public static void main(String[] args) {
        DFS g = new DFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.dfs(2);
    }
}
