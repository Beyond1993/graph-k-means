/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class Edge {
    private Vertex nodeSource;
    private Vertex nodeDestination;
    private int weight;
    private int index;

    public Edge(Vertex nodeSource, Vertex nodeDestination, int weight, int index) {
        this.nodeSource = nodeSource;
        this.nodeDestination = nodeDestination;
        this.weight = weight;
        this.index = index;
    }

    public Vertex getNodeSource() {
        return nodeSource;
    }

    public Vertex getNodeDestination() {
        return nodeDestination;
    }

    public int getWeight() {
        return weight;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        return this.nodeSource.getLabel() + "-->" +
        this.getNodeDestination().getLabel() + " weight: " + this.getWeight() +
        " index : " + this.index;
    }
}
