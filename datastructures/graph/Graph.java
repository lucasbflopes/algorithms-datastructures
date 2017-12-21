package datastructures.graph;

public interface Graph<N> extends BaseGraph<N> {

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
    public void addEdge(N nodeU, N nodeV);

    public void addEdge(Edge<N> edge);
}
