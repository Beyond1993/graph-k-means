import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class Cluster {
    private Set<Vertex> points;
    private List<Integer> indice;
    private Vertex centre;
    private int id;

    public Cluster(int id) {
        this.id = id;
        this.points = new HashSet<Vertex>();
        this.indice = new ArrayList<Integer>();
        this.centre = null;
    }

    public Set<Vertex> getGraphNodes() {
        return points;
    }

    public List<Integer> getGraphNodesAsInteger() {
        return indice;
    }

    public void addGraphNode(Vertex point) {
        points.add(point);
        indice.add(point.getIndex());
    }

    public Vertex getCentreid() {
        return centre;
    }

    public void setCentre(Vertex centre) {
        this.centre = centre;
    }

    public int getId() {
        return id;
    }

    public void plotCluster() {
        System.out.println("[Cluster: " + id + "]");
        System.out.println("[Centre: " + centre + "]");
        System.out.print("[Vertexs: ");

        for (Vertex p : points) {
            System.out.print(p + ",");
        }

        System.out.println("]\n");
    }
}
