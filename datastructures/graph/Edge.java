package datastructures.graph;

public class Edge<N> implements Comparable<Edge<N>> {

    private N nodeU;
    private N nodeV;
    private Double weight;

    public Edge(N nodeU, N nodeV, double weight) {
        this.nodeU = nodeU;
        this.nodeV = nodeV;
        this.weight = weight;
    }

    public Edge(N nodeU, N nodeV) {
        this(nodeU, nodeV, 0.0);
    }

    public N getNodeU() {
        return nodeU;
    }

    public N getNodeV() {
        return nodeV;
    }

    public Double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge<N> otherEdge) {
        return this.weight.compareTo(otherEdge.weight);
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", nodeU.toString(), nodeV.toString());
    }
}
