package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import datastructures.graph.WeightedGraph;

class Node<N> implements Comparable<Node<N>> {
    N id;
    Double cost;

    Node(N id, double cost) {
        this.id = id;
        this.cost = cost;
    }

    void setCost(double newCost) {
        cost = newCost;
    }

    @Override
    public int compareTo(Node<N> other) {
        return this.cost.compareTo(other.cost);
    }
}

/**
 * 
 * Dijkstra's Shortest Path Algorithm to find the shortest path in a weighted
 * graph
 * 
 * @author LucasBraganca
 */
public class Dijkstra<N> {

    WeightedGraph<N> graph;
    N origin;
    N destination;

    public Dijkstra(WeightedGraph<N> graph, N origin, N destination) {
        this.graph = graph;
        this.origin = origin;
        this.destination = destination;
    }

    public List<N> findShortestPath() {
        Set<N> nodes = graph.getNodes();
        Map<N, Double> cost = new HashMap<>();
        Map<N, N> predecessor = new HashMap<>();
        Set<N> nodesVisited = new HashSet<>();

        for (N node : nodes) {
            cost.put(node, Double.POSITIVE_INFINITY);
            predecessor.put(node, null);
        }

        cost.put(origin, 0.0);
        nodesVisited.add(origin);

        PriorityQueue<Node<N>> queue = new PriorityQueue<>();
        queue.add(new Node<N>(origin, cost.get(origin)));

        while (!queue.isEmpty()) {
            Node<N> u = queue.remove();
            for (N neighbour : graph.getNeighborNodes(u.id)) {
                Node<N> v = new Node<>(neighbour, cost.get(neighbour));
                if (!nodesVisited.contains(v.id)) {
                    double newCost = cost.get(u.id)
                            + graph.getEdgeWeight(u.id, v.id);
                    if (newCost < v.cost) {
                        cost.put(v.id, newCost);
                        predecessor.put(v.id, u.id);
                        updateNodeCostInQueue(queue, v, newCost);
                    }
                }
            }
            nodesVisited.add(u.id);
        }

        List<N> shortestPath = new ArrayList<>();

        N v = destination;
        while (predecessor.get(v) != null) {
            shortestPath.add(v);
            v = predecessor.get(v);
        }

        shortestPath.add(origin);

        return shortestPath;
    }

    private void updateNodeCostInQueue(PriorityQueue<Node<N>> queue,
            Node<N> node, double newCost) {
        if (queue.contains(node)) {
            queue.remove(node);
        }
        node.setCost(newCost);
        queue.add(node);
    }

    public double shortestPathTotalWeight() {
        List<N> nodes = findShortestPath();
        double total = 0.0;
        for (int i = 0; i < nodes.size() - 1; i++) {
            total += graph.getEdgeWeight(nodes.get(i + 1), nodes.get(i));
        }
        return total;
    }
}
