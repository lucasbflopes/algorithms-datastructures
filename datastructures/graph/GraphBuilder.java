package datastructures.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphBuilder<N> implements Graph<N> {

    private Map<N, Set<N>> adjList = new HashMap<>();
    private boolean isDirected;

    private GraphBuilder(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     * Static factory method to create a directed graph, i.e., a graph in which
     * the edges have a particular direction associated with them.
     * 
     * @return a directed graph
     */
    public static <N> GraphBuilder<N> newDirectedGraph() {
        return new GraphBuilder<N>(true);
    }

    /**
     * Static factory method to create an undirected graph, i.e., a graph in
     * which the edges are bidirectional.
     * 
     * @return a directed graph
     */
    public static <N> GraphBuilder<N> newUndirectedGraph() {
        return new GraphBuilder<N>(false);
    }

    @Override
    public Set<N> getNodes() {
        return adjList.keySet();
    }

    @Override
    public Set<N> getNeighborNodes(N node) {
        return adjList.getOrDefault(node, new HashSet<N>());
    }

    @Override
    public int getOutDegree(N node) {
        return adjList.get(node).size();
    }

    @Override
    public int getInDegree(N node) {
        int inDegree = 0;
        for (N n : adjList.keySet()) {
            if (adjList.get(n).contains(node)) {
                inDegree++;
            }
        }
        return inDegree;
    }

    public void addEdge(N nodeU, N nodeV) {
        adjList.putIfAbsent(nodeU, new HashSet<N>());
        adjList.putIfAbsent(nodeV, new HashSet<N>());
        adjList.get(nodeU).add(nodeV);

        if (!isDirected) {
            adjList.get(nodeV).add(nodeU);
        }
    }

    public void addEdge(Edge<N> edge) {
        N nodeU = edge.getNodeU();
        N nodeV = edge.getNodeV();
        addEdge(nodeU, nodeV);
    }

    @Override
    public Set<Edge<N>> getEdges() {
        Set<Edge<N>> edges = new HashSet<>();
        for (N node : adjList.keySet()) {
            Set<N> neighbors = adjList.get(node);
            for (N neighbour : neighbors) {
                Edge<N> edge = new Edge<>(node, neighbour);
                edges.add(edge);
            }
        }
        return edges;
    }

}
