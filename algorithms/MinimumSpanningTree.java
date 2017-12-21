package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datastructures.DisjointSet;
import datastructures.graph.Edge;
import datastructures.graph.WeightedGraph;

/**
 * Find Minimum Spanning Tree using Kruskal's Algorithm.
 * 
 * @author Lucas Bragança
 */
public class MinimumSpanningTree<N> {

    WeightedGraph<N> graph;

    public MinimumSpanningTree(WeightedGraph<N> graph) {
        this.graph = graph;
    }

    public Set<Edge<N>> find() {
        Set<Edge<N>> A = new HashSet<>();
        Set<N> nodes = graph.getNodes();
        DisjointSet<N> djSet = new DisjointSet<>(nodes);
        Set<Edge<N>> edges = graph.getEdges();
        List<Edge<N>> edgeList = new ArrayList<>(edges);

        Collections.sort(edgeList);

        for (Edge<N> edge : edgeList) {
            N nodeU = edge.getNodeU();
            N nodeV = edge.getNodeV();
            if (!djSet.find(nodeU, nodeV)) {
                A.add(edge);
                djSet.union(nodeU, nodeV);
            }
        }
        return A;
    }

    public double totalWeight() {
        Set<Edge<N>> edgesInMST = find();
        double total = 0;
        for (Edge<N> edge : edgesInMST) {
            total += graph.getEdgeWeight(edge);
        }
        return total;
    }
}
