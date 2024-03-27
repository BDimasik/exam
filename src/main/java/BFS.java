import java.util.LinkedList;
import java.util.List;

public class BFS {
    private final int V;
    private final LinkedList[] adj;

    BFS(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) adj[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    Integer[] bfs(int s) {
        boolean[] visited = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        List<Integer> list = new LinkedList<>();
        while (queue.size() != 0) {
            s = queue.poll();
            list.add(s);

            for (int n : (Iterable<Integer>) adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        return list.toArray(new Integer[0]);
    }

    public static void main(String args[]) {
        BFS g = new BFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.bfs(2);
    }
}
