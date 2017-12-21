package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import datastructures.graph.Graph;

/**
 * Breadth First Search Algorithm to find the shortest path in an unweighted
 * graph
 * 
 * @author LucasBraganca
 */
public class BreadthFirstSearch<N> {

    Graph<N> graph;
    N origin;
    N destination;

    public BreadthFirstSearch(Graph<N> graph, N origin, N destination) {
        this.graph = graph;
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * 
     * Find the shortest path between two nodes in an unweighted graph.
     * 
     * @param graph
     *            unweighted graph containing the origin and destination nodes.
     * @param origin
     *            node from which the search of the shortest path will begin.
     * @param destination
     *            node corresponding to the destination.
     * @return a list of all nodes in the shortest path sorted from the
     *         destination to the origin. If no such path exists, return a list
     *         with only origin.
     */
    public List<N> findShortestPath() {
        Set<N> nodes = graph.getNodes();
        Map<N, Integer> distance = new HashMap<>();
        Map<N, N> predecessor = new HashMap<>();
        Set<N> nodesVisited = new HashSet<>();

        for (N node : nodes) {
            distance.put(node, Integer.MAX_VALUE);
            predecessor.put(node, null);
        }

        Queue<N> queue = new ArrayDeque<>();

        distance.put(origin, 0);
        queue.add(origin);
        nodesVisited.add(origin);

        while (!queue.isEmpty()) {
            N v = queue.remove();
            for (N neighbour : graph.getNeighborNodes(v)) {
                if (!nodesVisited.contains(neighbour)) {
                    distance.put(neighbour, distance.get(v) + 1);
                    predecessor.put(neighbour, v);
                    queue.add(neighbour);
                }
            }
            nodesVisited.add(v);
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

    /**
     * 
     * Find the shortest path length between two nodes in an unweighted graph.
     * 
     * @param graph
     *            unweighted graph containing the origin and destination nodes.
     * @param origin
     *            node from which the search of the shortest path will begin.
     * @param destination
     *            node corresponding to the destination.
     * @return the length of the shortest path between the nodes origin and
     *         destination. If no such path exists returns 0;
     */
    public int shortestPathLength() {
        return findShortestPath().size() - 1;
    }

}
