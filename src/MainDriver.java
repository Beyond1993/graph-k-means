/**
 * @author wayne
 * @version : 1.0
 * @date: May/16/2017
 */
public class MainDriver {

	public static void main(String[] args) {
		String graphContent = FileUtil.read("graph.txt", null);
		Graph graph = new Graph(graphContent);
		KMeans kmeans = new KMeans(graph);
		kmeans.init();
    	kmeans.calculate();
   	}
}
