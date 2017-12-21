package datastructures.graph;

import java.util.Set;

public interface BaseGraph<N> {

    /**
     * Get all the nodes in the graph.
     * 
     * @return a set of all the nodes in the graph in no particular order.
     */
    public Set<N> getNodes();

    /**
     * 
     * Get all the neighbor nodes of a given node in the graph.
     * 
     * @param node
     *            a node which might or might not be in the graph
     * @return the set of neighbors, i.e. adjacent nodes, of the provided node
     *         in the graph. If the node provided does not exist, return an
     *         empty set.
     */
    public Set<N> getNeighborNodes(N node);

    /**
     * Get the out degree of a node, i.e. the number of edge tails that starts
     * from the node.
     * 
     * @param node
     *            a node in the graph.
     * @return the out-degree of the node.
     */
    public int getOutDegree(N node);

    /**
     * Get the in degree of a node, i.e. the number of edge heads that ends in
     * the node.
     * 
     * @param node
     *            a node in the graph.
     * @return the in-degree of the node.
     */
    public int getInDegree(N node);

    public Set<Edge<N>> getEdges();
}
