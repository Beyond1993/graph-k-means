import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class Dijkstra {
    private final List<Edge> edges;
    private Set<Vertex> visitedNodes;
    private Set<Vertex> unVisitedNodes;
    private Map<Vertex, Integer> distance;

    public Dijkstra(List<Vertex> nodes, List<Edge> edges) {
        this.edges = new ArrayList<Edge>(edges);
    }

    public void execute(Vertex source) {
        visitedNodes = new HashSet<Vertex>();
        unVisitedNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        distance.put(source, 0);
        unVisitedNodes.add(source);

        while (unVisitedNodes.size() > 0) {
            Vertex node = getMinimum(unVisitedNodes);
            visitedNodes.add(node);
            unVisitedNodes.remove(node);
            updateMinimalDistances(node);
        }
    }

    private void updateMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);

        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > (getShortestDistance(node) +
                    getDistance(node, target))) {
                distance.put(target,
                    getShortestDistance(node) + getDistance(node, target));
                unVisitedNodes.add(target);
            }
        }
    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getNodeSource().equals(node) &&
                    edge.getNodeDestination().equals(target)) {
                return edge.getWeight();
            }
        }

        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();

        for (Edge edge : edges) {
            if (edge.getNodeSource().equals(node) &&
                    !isSettled(edge.getNodeDestination())) {
                neighbors.add(edge.getNodeDestination());
            }
        }

        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;

        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }

        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return visitedNodes.contains(vertex);
    }

    public int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);

        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }
}
