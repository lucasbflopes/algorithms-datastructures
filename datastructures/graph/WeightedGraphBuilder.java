package datastructures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WeightedGraphBuilder<N> implements WeightedGraph<N> {

    private Map<N, Map<N, Double>> adjList = new HashMap<>();
    private boolean isDirected;

    private WeightedGraphBuilder(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     * Static factory method to create a directed graph, i.e., a graph in which
     * the edges have a particular direction associated with them.
     * 
     * @return a directed graph
     */
    public static <N> WeightedGraphBuilder<N> newDirectedGraph() {
        return new WeightedGraphBuilder<N>(true);
    }

    /**
     * Static factory method to create an undirected graph, i.e., a graph in
     * which the edges are bidirectional.
     * 
     * @return a directed graph
     */
    public static <N> WeightedGraphBuilder<N> newUndirectedGraph() {
        return new WeightedGraphBuilder<N>(false);
    }

    @Override
    public Set<N> getNodes() {
        return adjList.keySet();
    }

    @Override
    public Set<N> getNeighborNodes(N node) {
        if (adjList.containsKey(node)) {
            Map<N, Double> neighbors = adjList.get(node);
            return neighbors.keySet();
        } else {
            return new HashSet<N>();
        }
    }

    @Override
    public int getOutDegree(N node) {
        Map<N, Double> neighbors = adjList.get(node);
        return neighbors.size();
    }

    @Override
    public int getInDegree(N node) {
        int inDegree = 0;
        Set<N> allNodes = adjList.keySet();
        for (N n : allNodes) {
            Map<N, Double> neighbors = adjList.get(n);
            if (neighbors.containsKey(node)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    public void addEdge(N nodeU, N nodeV, double weight) {
        adjList.putIfAbsent(nodeU, new HashMap<N, Double>());
        adjList.putIfAbsent(nodeV, new HashMap<N, Double>());
        adjList.get(nodeU).put(nodeV, weight);

        if (!isDirected) {
            adjList.get(nodeV).put(nodeU, weight);
        }
    }

    public void addEdge(Edge<N> edge) {
        N nodeU = edge.getNodeU();
        N nodeV = edge.getNodeV();
        double weight = edge.getWeight();

        addEdge(nodeU, nodeV, weight);
    }

    public double getEdgeWeight(N nodeU, N nodeV) {
        if (!adjList.containsKey(nodeU)) {
            throw new RuntimeException("Node " + nodeU.toString()
                    + " does not exist");
        }
        return adjList.get(nodeU).getOrDefault(nodeV, 0.0);
    }

    @Override
    public double getEdgeWeight(Edge<N> edge) {
        N nodeU = edge.getNodeU();
        N nodeV = edge.getNodeV();
        return getEdgeWeight(nodeU, nodeV);
    }

    @Override
    public Set<Edge<N>> getEdges() {
        Set<Edge<N>> edges = new HashSet<>();
        for (N node : adjList.keySet()) {
            Map<N, Double> neighbors = adjList.get(node);
            for (N neighbour : neighbors.keySet()) {
                Edge<N> edge = new Edge<>(node, neighbour,
                                          getEdgeWeight(node, neighbour));
                edges.add(edge);
            }
        }
        return edges;
    }
}
