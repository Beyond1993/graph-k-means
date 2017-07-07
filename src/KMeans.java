import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class KMeans {
    private int NUM_CLUSTERS = 2;
    private int NUM_POINTS = 5;
    private List<Vertex> points;
    private List<Cluster> clusters;
    private List<Vertex> centres;
    Graph graph = null;

    public KMeans(Graph graph) {
        this.graph = graph;
        this.points = new ArrayList<Vertex>();
        this.clusters = new ArrayList<Cluster>();
        this.centres = new ArrayList<Vertex>();
    }

    /**
     * Get the points from graph, and generate the random center id of cluster.
     */
    public void init() {
        this.points = graph.getGraphNodes();

        List<Integer> list = new ArrayList<Integer>();

        //int[] test = new int[]{0,4};
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster cluster = new Cluster(i);
            int centreId = new Random().nextInt(NUM_POINTS);

            while (list.contains(centreId)) {
                centreId = new Random().nextInt(NUM_POINTS);
            }

            list.add(centreId);

            Vertex p = points.get(centreId);
            this.centres.add(p);
            cluster.setCentre(p);
            clusters.add(cluster);
        }

        plotClusters();
    }

    /**
     * show the result to the console.
     */
    private void plotClusters() {
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster c = clusters.get(i);
            c.plotCluster();
        }
    }

    /**
     * calculate k-means.
     */
    public void calculate() {
        int iteration = 0;

        while (true) {
            List<Vertex> lastCentroids = getCentres();

            assignNode2Cluster();

            updateCentres();

            iteration++;

            List<Vertex> currentCentroids = getCentres();

            double distance = 0;

            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += graph.getMinDistance(lastCentroids.get(i).getIndex(),
                    currentCentroids.get(i).getIndex());
            }

            System.out.println("--------------------------------");
            System.out.println("Iteration: " + iteration);
            System.out.println("Centres distances: " + distance);
            plotClusters();

            if ((distance == 0) || (iteration > 1000)) {
                break;
            }
        }
    }

    private List<Vertex> getCentres() {
        return new ArrayList<Vertex>(centres);
    }

    /**
     * use Dijsktra algorithm to find the shortest distance and assign node to cluster.
     */
    private void assignNode2Cluster() {
        Integer min = Integer.MAX_VALUE;
        int cluster = 0;
        Integer distance = 0;

        for (Vertex point : points) {
            min = Integer.MAX_VALUE;

            for (int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster c = clusters.get(i);
                distance = graph.getMinDistance(point.getIndex(),
                        c.getCentreid().getIndex());

                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
            }

            point.setCluster(cluster);
            clusters.get(cluster).addGraphNode(point);
        }
    }

    /**
     * update centers
     */
    private void updateCentres() {
        for (Cluster cluster : clusters) {
            List<Integer> list = cluster.getGraphNodesAsInteger();
            int sumOfMST = Integer.MAX_VALUE;
            MinimumSpanningTree mst = new MinimumSpanningTree();

            for (Integer p : list) {
                int sum = mst.getMinSum(p, list, graph.getGraphEdge());

                if (sum < sumOfMST) {
                    cluster.setCentre(points.get(p));
                }
            }
        }
    }
}
