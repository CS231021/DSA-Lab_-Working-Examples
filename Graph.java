import java.util.*;

class Graph {
    private int vertices;
    private LinkedList<Integer> adjList[];

    Graph(int v) {
        vertices = v;
        adjList = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    void calculateDegree() {
        for (int i = 0; i < vertices; i++) {
            System.out.println("Degree of node " + i + ": " + adjList[i].size());
        }
    }

    void BFS(int start) {
        boolean visited[] = new boolean[vertices];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (Integer neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        for (Integer neighbor : adjList[v]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    void DFS(int start) {
        boolean visited[] = new boolean[vertices];
        DFSUtil(start, visited);
    }

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        g.calculateDegree();

        System.out.print("BFS starting from node 0: ");
        g.BFS(0);

        System.out.print("\nDFS starting from node 0: ");
        g.DFS(0);
    }
}