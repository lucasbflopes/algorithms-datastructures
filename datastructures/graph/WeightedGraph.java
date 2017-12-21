package datastructures.graph;

public interface WeightedGraph<N> extends BaseGraph<N> {

    /**
     * 
     * Add an edge from nodeU to nodeV to the graph. In an undirected graph, the
     * order whereby they are supplied to the method does not matter.
     * 
     * @param nodeU
     *            node representing the origin of the edge.
     * @param nodeV
     *            node representing the end of the edge.
     */
    public void addEdge(N nodeU, N nodeV, double weight);

    public void addEdge(Edge<N> edge);

    /**
     * Get the weight of the edge connecting nodeU and nodeV in the graph.
     * 
     * @param nodeU
     *            node in the graph representing the origin of the edge.
     * @param nodeV
     *            node in the graph representing the end of the edge.
     * @return weight of the edge. If the edge does not exist, returns 0
     */
    public double getEdgeWeight(N nodeU, N nodeV);

    public double getEdgeWeight(Edge<N> edge);
}
