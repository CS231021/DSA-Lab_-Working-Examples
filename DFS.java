class Vertex {
    String label;
    boolean isVisited;
    Vertex[] neighbours;

    public Vertex(String label) {
        this.label = label;
        this.isVisited = false;
    }

    public void addNeighbours(Vertex[] neighbours) {
        this.neighbours = new Vertex[neighbours.length];
        System.arraycopy(neighbours, 0, this.neighbours, 0, neighbours.length);
    }
}

class LinkedList {
    Node front;
    Node rear;

    static class Node {
        public Vertex data;
        public Node next;

        public Node(Vertex data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void insertLast(Vertex newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public void insertFirst(Vertex newData) {
        Node newNode = new Node(newData);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.next = front;
            front = newNode;
        }
    }

    public Vertex deleteFirst() {
        if (isEmpty()) return null;
        Vertex temp = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return temp;
    }

    public Vertex deleteLast() {
        if (isEmpty()) return null;
        if (front == rear) { // Single element case
            Vertex temp = rear.data;
            front = rear = null;
            return temp;
        }

        Node current = front;
        while (current.next != rear) {
            current = current.next;
        }
        Vertex temp = rear.data;
        current.next = null;
        rear = current;
        return temp;
    }

    public void displayList() {
        Node current = front;
        while (current != null) {
            System.out.print(current.data.label + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class LinkedListStack {
    LinkedList li = new LinkedList();

    public void push(Vertex element) {
        li.insertFirst(element);
    }

    public Vertex pop() {
        return li.deleteFirst();
    }

    public boolean isEmpty() {
        return li.isEmpty();
    }

    public Vertex peek() {
        return li.front != null ? li.front.data : null;
    }

    public void displayStack() {
        System.out.println("Stack contents: ");
        li.displayList();
    }
}

class Edge {
    Vertex start;
    Vertex end;

    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }
}

class Graph {
    private int v;
    Edge[] edgeList;

    public Graph(int v) {
        this.v = v;
    }

    public Vertex createVertex(String label) {
        return new Vertex(label);
    }

    public Edge createEdge(Vertex start, Vertex end) {
        return new Edge(start, end);
    }

    public void createEdgeList(Edge[] edges) {
        edgeList = new Edge[edges.length];
        System.arraycopy(edges, 0, edgeList, 0, edges.length);
    }

    public void printGraphDFS(LinkedListStack stack) {
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if (!current.isVisited) {
                current.isVisited = true;
                System.out.println(current.label);

                if (current.neighbours != null) {
                    for (Vertex neighbor : current.neighbours) {
                        if (!neighbor.isVisited) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
        }
    }
}

public class DFS {
    public static void main(String[] args) {
        Graph graph = new Graph(5);

        Vertex a = graph.createVertex("A");
        Vertex b = graph.createVertex("B");
        Vertex c = graph.createVertex("C");
        Vertex d = graph.createVertex("D");
        Vertex e = graph.createVertex("E");

        a.addNeighbours(new Vertex[]{b, d});
        b.addNeighbours(new Vertex[]{a, c});
        c.addNeighbours(new Vertex[]{b});
        d.addNeighbours(new Vertex[]{a, e});
        e.addNeighbours(new Vertex[]{d});

        LinkedListStack stack = new LinkedListStack();
        stack.push(a);

        System.out.println("Printing Graph Depth First Traversal:");
        graph.printGraphDFS(stack);
    }
}
