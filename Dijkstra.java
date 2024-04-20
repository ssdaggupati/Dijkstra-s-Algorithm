import java.util.*;

public class Dijkstra {

    // Method to find the shortest path using Dijkstra's Algorithm
    public static void dijkstra(Map<Integer, List<int[]>> graph, int source, int[] dist) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            if (distance > dist[node]) continue;

            for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                int newDistance = distance + weight;
                if (newDistance < dist[nextNode]) {
                    dist[nextNode] = newDistance;
                    pq.offer(new int[]{nextNode, newDistance});
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of nodes and validate
        int nodes = 0;
        while (nodes <= 0) {
            System.out.print("Enter the number of nodes (should be greater than 0): ");
            nodes = scanner.nextInt();
        }

        // Input the number of edges and validate
        int edges = 0;
        while (edges <= 0 || edges > nodes * (nodes - 1) / 2) {
            System.out.print("Enter the number of edges (should be between 1 and " + (nodes * (nodes - 1) / 2) + "): ");
            edges = scanner.nextInt();
        }

        // Create the graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < edges; i++) {
            System.out.println("Enter edge " + (i + 1) + " details (source destination weight):");
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.computeIfAbsent(source, k -> new ArrayList<>()).add(new int[]{destination, weight});
        }

        // Input the source node and validate
        int sourceNode = 0;
        while (sourceNode <= 0 || sourceNode > nodes) {
            System.out.print("Enter the source node (should be between 1 and " + nodes + "): ");
            sourceNode = scanner.nextInt();
        }

        // Initialize array to store distances from source
        int[] dist = new int[nodes + 1]; // Adjusted array size
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[sourceNode] = 0;

        // Call Dijkstra's Algorithm
        dijkstra(graph, sourceNode, dist);

        // Print shortest distances from source to all nodes
        for (int i = 1; i <= nodes; i++) {
            System.out.println("Distance from source to node " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
        
        // Close the scanner object
        scanner.close();
    }
    
    /*
     * Test Case:
     * Input:
     * Enter the number of nodes (should be greater than 0): 6
     * Enter the number of edges (should be between 1 and 15): 8
     * Enter edge 1 details (source destination weight):
     * 1 2 4 
     * Enter edge 2 details (source destination weight):
     * 1 4 2
     * Enter edge 3 details (source destination weight):
     * 2 3 1
     * Enter edge 4 details (source destination weight):
     * 2 5 5
     * Enter edge 5 details (source destination weight):
     * 3 6 6
     * Enter edge 6 details (source destination weight):
     * 5 3 3
     * Enter edge 8 details (source destination weight):
     * 4 5 3
     * Enter the source node (should be between 1 and 6): 1
     * 
     * Output:
     * Distance from source to node 1: 0
     * Distance from source to node 2: 4
     * Distance from source to node 3: 5
     * Distance from source to node 4: 2
     * Distance from source to node 5: 5
     * Distance from source to node 6: 7
     */    
}
