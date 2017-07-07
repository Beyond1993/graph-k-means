import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class MinimumSpanningTree {
	static class UnionFind {
		Set<String>	set;
		Map<String, String>	map;
		int	count;
		int	sum = 0;
		public UnionFind()
		{
			count = 0;
			sum	= 0;
			set	= new HashSet<>();
			map	= new HashMap<>();
		}


		public void MakeSet( String s ) {
			if (!set.contains( s ) ) {
				count++;
				set.add( s );
				map.put( s, s );
			}
		}


		public String Find( String s ) {
			if (!set.contains( s ) )
				return(null);
			if (s.equals( map.get( s ) ) )
				return(s);
			String root = this.Find(map.get(s));
			map.put( s, root );
			return(root);
		}


		public void Union( String s, String t, int weight ) {
			if ( !set.contains( s ) || !set.contains( t ) )
				return;
			if ( s.equals( t ) )
				return;
			sum += weight;
			count--;
			map.put( s, t );
		}
	}
	static class GraphEdgeComparator1 implements Comparator<Edge> {
		@Override
		public int compare( Edge a, Edge b )
		{
			return(a.getWeight() - b.getWeight() );
		}
	}
	public int getMST( List<Edge> rootEdges, List<Edge> otherEdges ) {
		Comparator<Edge>	comparator1	= new GraphEdgeComparator1();
		UnionFind		unionFind	= new UnionFind();
		Collections.sort( rootEdges, comparator1 );

		for ( Edge itr : rootEdges ) {
			unionFind.MakeSet( itr.getNodeSource().getLabel() );
			unionFind.MakeSet( itr.getNodeDestination().getLabel() );
		}

		for ( Edge itr : otherEdges ) {
			unionFind.MakeSet( itr.getNodeSource().getLabel() );
			unionFind.MakeSet( itr.getNodeDestination().getLabel() );
		}
		for ( Edge itr : rootEdges ) {
			String	s	= unionFind.Find( itr.getNodeSource().getLabel() );
			String	t	= unionFind.Find( itr.getNodeDestination().getLabel() );
			if ( !s.equals( t ) )
			{
				unionFind.Union( s, t, itr.getWeight() );
				if ( unionFind.count == 1 )
					break;
			}
		}
		for ( Edge itr : otherEdges ) {
			String	s	= unionFind.Find( itr.getNodeSource().getLabel() );
			String	t	= unionFind.Find( itr.getNodeDestination().getLabel() );
			if ( !s.equals( t ) )
			{
				unionFind.Union( s, t, itr.getWeight() );
				if ( unionFind.count == 1 )
					break;
			}
		}
		return(unionFind.sum);
	}


	/**
	 * Chose each node of cluster as root to create a minimum spanning tree and return the sum of path
	 * @param root root of minimum spanning tree.
	 * @param cluster all nodes in the cluster
	 * @param edges all edges of graph
	 * @return sum of minimum spanning tree.
	 */
	public int getMinSum( int root, List<Integer> cluster, List<Edge> edges ) {
		ArrayList<Edge> rootEdges	= new ArrayList<Edge>();
		List<Edge>	otherEdges	= new ArrayList<>();
		Set<Integer>	set		= new HashSet<Integer>();
		for ( Edge e : edges )
		{
			int	source		= e.getNodeSource().getIndex();
			int	destination	= e.getNodeDestination().getIndex();
			if ( cluster.contains( source ) && cluster.contains( destination ) )
			{
				set.add( e.getIndex() );
				if ( source == root || destination == root )
				{
					rootEdges.add( e );
				} else {
					otherEdges.add( e );
				}
			}
		}
		return(getMST( rootEdges, otherEdges ) );
	}
}