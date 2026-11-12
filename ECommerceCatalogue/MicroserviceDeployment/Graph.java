package ECommerceCatalogue.MicroserviceDeployment;

import java.util.*;

public class Graph {

    static class GraphData {

        Map<String, List<String>> adj = new LinkedHashMap<>();

        // Add dependency
        void addEdge(String from, String to) {

            adj.putIfAbsent(from, new ArrayList<>());
            adj.putIfAbsent(to, new ArrayList<>());

            adj.get(from).add(to);
        }

        // BFS using Kahn's Algorithm
        void bfs() {

            Map<String, Integer> indegree = new LinkedHashMap<>();

            for (String node : adj.keySet()) {
                indegree.put(node, 0);
            }

            // Calculate indegree
            for (String node : adj.keySet()) {

                for (String neighbour : adj.get(node)) {

                    indegree.put(
                        neighbour,
                        indegree.get(neighbour) + 1
                    );
                }
            }

            Queue<String> queue = new LinkedList<>();

            for (String node : indegree.keySet()) {

                if (indegree.get(node) == 0) {
                    queue.add(node);
                }
            }

            List<String> order = new ArrayList<>();

            while (!queue.isEmpty()) {

                String current = queue.poll();

                order.add(current);

                for (String neighbour : adj.get(current)) {

                    indegree.put(
                        neighbour,
                        indegree.get(neighbour) - 1
                    );

                    if (indegree.get(neighbour) == 0) {
                        queue.add(neighbour);
                    }
                }
            }

            System.out.println("BFS Deployment Order: " + order);

            if (order.size() != adj.size()) {
                System.out.println("Circular dependency detected!");
            } else {
                System.out.println("No circular dependency.");
            }
        }

        // DFS helper method
        void dfsVisit(
            String node,
            Set<String> visited,
            List<String> order
        ) {

            visited.add(node);

            for (String neighbour : adj.get(node)) {

                if (!visited.contains(neighbour)) {
                    dfsVisit(neighbour, visited, order);
                }
            }

            order.add(node);
        }

        // DFS Topological Sort
        void dfs() {

            Set<String> visited = new HashSet<>();
            List<String> order = new ArrayList<>();

            for (String node : adj.keySet()) {

                if (!visited.contains(node)) {
                    dfsVisit(node, visited, order);
                }
            }

            Collections.reverse(order);

            System.out.println("DFS Deployment Order: " + order);
        }
    }

    public static void main(String[] args) {

        GraphData g = new GraphData();

        // E-commerce microservice dependencies
        g.addEdge("Cart", "Pricing");
        g.addEdge("Pricing", "Inventory");
        g.addEdge("Inventory", "Database");
        g.addEdge("Payment", "Database");
        g.addEdge("ProductCatalog", "Cart");

        System.out.println("MICROSERVICE DEPLOYMENT");

        System.out.println("\nBFS Deployment:");
        g.bfs();

        System.out.println("\nDFS Deployment:");
        g.dfs();

        // Introduce circular dependency
        System.out.println("\nCYCLE DETECTION:");

        g.addEdge("Database", "Cart");

        g.bfs();
    }
}
