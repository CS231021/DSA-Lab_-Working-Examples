class GraphNode {
    String label;
    GraphNode[] neighbours;
    boolean isVisited;

    public GraphNode(String label) {
        this.label = label;
        this.isVisited = false;
    }

    public void addNeighbours(GraphNode[] neighbours) {
        this.neighbours = neighbours;
    }
}

class NodeList {
    Node front;
    Node rear;

    static class Node {
        GraphNode data;
        Node next;

        public Node(GraphNode data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void insertLast(GraphNode newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public GraphNode deleteFirst() {
        if (isEmpty()) return null;
        GraphNode temp = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return temp;
    }
}

class GraphQueue {
    NodeList nodeList;

    public GraphQueue() {
        nodeList = new NodeList();
    }

    public boolean isEmpty() {
        return nodeList.isEmpty();
    }

    public void enqueue(GraphNode newData) {
        nodeList.insertLast(newData);
    }

    public GraphNode deque() {
        return nodeList.deleteFirst();
    }

    public GraphNode front() {
        if (nodeList.front == null) {
            return null;
        } else {
            return nodeList.front.data;
        }
    }
}

class GraphStructure {
    GraphNode[] nodes;
    int totalNodes;

    public GraphStructure(int totalNodes) {
        this.totalNodes = totalNodes;
        nodes = new GraphNode[totalNodes];
    }

    public GraphNode createNode(String label) {
        for (int i = 0; i < totalNodes; i++) {
            if (nodes[i] == null) {
                nodes[i] = new GraphNode(label);
                return nodes[i];
            }
        }
        return null; // Graph is full
    }

    public void addNeighbours(GraphNode node, GraphNode[] neighbours) {
        node.addNeighbours(neighbours);
    }

    public void printGraphBFS(GraphQueue queue) {
        while (!queue.isEmpty()) {
            GraphNode current = queue.deque();

            if (!current.isVisited) {
                current.isVisited = true;
                System.out.println(current.label);

                if (current.neighbours != null) {
                    for (GraphNode neighbour : current.neighbours) {
                        if (!neighbour.isVisited) {
                            queue.enqueue(neighbour);
                        }
                    }
                }
            }
        }
    }
}

public class BFS {
    public static void main(String[] args) {
        GraphStructure graph = new GraphStructure(5);
        GraphNode a, b, c, d, e;

        a = graph.createNode("A");
        b = graph.createNode("B");
        c = graph.createNode("C");
        d = graph.createNode("D");
        e = graph.createNode("E");

        graph.addNeighbours(a, new GraphNode[]{b, d});
        graph.addNeighbours(b, new GraphNode[]{a, c});
        graph.addNeighbours(c, new GraphNode[]{b});
        graph.addNeighbours(d, new GraphNode[]{a, e});
        graph.addNeighbours(e, new GraphNode[]{d});

        System.out.println("Printing graph: BFS");
        GraphQueue queue = new GraphQueue();
        queue.enqueue(a);
        graph.printGraphBFS(queue);
    }
}
