import java.util.*;

public class Travelling {
    static class Edge {
        int destination;
        int cost;

        Edge(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static int calculateMinimumDays(int numberOfCities, List<int[]> aerialRoutes) {
        // Create adjacency list to represent the graph
        List<List<Edge>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < numberOfCities; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Add normal road connections simulating dice-like moves
        for (int i = 0; i < numberOfCities; i++) {
            for (int step = 1; step <= 6; step++) {
                if (i + step < numberOfCities) {
                    adjacencyList.get(i).add(new Edge(i + step, 1));
                }
            }
        }

        // Add aerial route connections
        for (int[] route : aerialRoutes) {
            int from = route[0];
            int to = route[1];
            adjacencyList.get(from).add(new Edge(to, 0));
        }

        // Use a priority queue for BFS to find the minimum number of days
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // [currentCity, totalCost]
        boolean[] visited = new boolean[numberOfCities];
        queue.offer(new int[]{0, 0}); // Start from city 0 with 0 days

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentCity = current[0];
            int currentDays = current[1];

            // If we reach the last city, return the number of days
            if (currentCity == numberOfCities - 1) {
                return currentDays;
            }

            // Mark the city as visited
            if (visited[currentCity]) continue;
            visited[currentCity] = true;

            // Explore neighboring cities
            for (Edge edge : adjacencyList.get(currentCity)) {
                if (!visited[edge.destination]) {
                    queue.offer(new int[]{edge.destination, currentDays + edge.cost});
                }
            }
        }

        return -1; // If no path is found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of test cases:");
        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            System.out.println("Enter the total number of cities:");
            int numberOfCities = scanner.nextInt();

            System.out.println("Enter the total number of aerial routes:");
            int numberOfRoutes = scanner.nextInt();

            List<int[]> aerialRoutes = new ArrayList<>();
            System.out.println("Enter the aerial routes in the format: source destination:");
            for (int i = 0; i < numberOfRoutes; i++) {
                int source = scanner.nextInt();
                int destination = scanner.nextInt();
                aerialRoutes.add(new int[]{source, destination});
            }

            int result = calculateMinimumDays(numberOfCities, aerialRoutes);
            System.out.println("Minimum days required to reach city " + (numberOfCities - 1) + ": " + result);
        }

        scanner.close();
    }
}