import java.util.ArrayList;
import java.util.List;


/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class Graph {
    private List<Vertex> nodes = null;
    private List<Edge> edges = null;
    private int NUM_VERTEXS = 0;

    public Graph(String graphContent) {
        String[] lines = graphContent.split("\n");
        initGraph(5);

        Vertex sourceNode;
        Vertex distNode;
        int weight = 0;

        for (int i = 0; i < lines.length; i++) {
            String[] nodesInfo = lines[i].split(",");
            sourceNode = nodes.get(Integer.parseInt(nodesInfo[1]));
            distNode = nodes.get(Integer.parseInt(nodesInfo[2]));
            nodes.add(sourceNode);

            weight = Integer.parseInt(nodesInfo[3]);

            Edge edge01 = new Edge(sourceNode, distNode, weight, i);
            Edge edge02 = new Edge(distNode, sourceNode, weight, i);
            sourceNode.addEdgeList(edge01);
            distNode.addEdgeList(edge02);
            edges.add(edge01);
            edges.add(edge02);
        }
    }

    public void initGraph(int n) {
        if (nodes == null) {
            nodes = new ArrayList<Vertex>();
        }

        if (edges == null) {
            edges = new ArrayList<Edge>();
        }

        Vertex node = null;

        for (int i = 0; i < n; i++) {
            node = new Vertex(String.valueOf(i), i);
            nodes.add(node);
        }

        this.NUM_VERTEXS = n;
    }

    public int getNumOfVertexs() {
        return this.NUM_VERTEXS;
    }

    public List<Vertex> getGraphNodes() {
        return nodes;
    }

    public List<Edge> getGraphEdge() {
        return edges;
    }

    public int getMinDistance(int s, int d) {
        return getMinDistance(nodes.get(s), nodes.get(d));
    }

    public int getMinDistance(Vertex o1, Vertex o2) {
        Dijkstra dijkstra = new Dijkstra(nodes, edges);
        dijkstra.execute(o1);

        return dijkstra.getShortestDistance(o2);
    }

    public void DFSearch() {
        DFSearch(nodes.get(0), new ArrayList<Vertex>());
    }

    private void DFSearch(Vertex node, List<Vertex> visited) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        System.out.println("节点：" + node.getLabel());

        for (int i = 0; i < node.edgeList.size(); i++) {
            DFSearch(node.edgeList.get(i).getNodeDestination(), visited);
        }
    }
}
