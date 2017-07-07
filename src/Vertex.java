import java.util.ArrayList;
/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class Vertex {

    public ArrayList < Edge > edgeList = null;

    private String label = "";

    private int index;

	private int cluster;

    public Vertex(String label, int index) {
        this.label = label;
        this.index = index;
        this.cluster = 0;
        if (edgeList == null) {
            edgeList = new ArrayList < Edge > ();
        }
    }

    public void addEdgeList(Edge edge) {
        edgeList.add(edge);
    }

    public String getLabel() {
        return label;
    }

    public int getIndex() {
        return index;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
    @Override
    public String toString() {
        return this.label;
    }
}