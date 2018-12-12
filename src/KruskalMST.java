import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Minimum Spanning Tree using Kruskal's algorithm
 * 
 * @author jitesh golatkar
 *
 */
public class KruskalMST {

	HashMap<Character, Vertex> disjointSets = new HashMap<>();

	public void makeSet(Vertex v) {
		v.setPredecessor(v);
		v.setWeight(0);
		disjointSets.put(v.getIndex(), v);
	}

	/**
	 * Make a union of two sets
	 * 
	 * @param u
	 * @param v
	 */
	public void union(Vertex u, Vertex v) {
		if (u.getPredecessor().getWeight() >= v.getPredecessor().getWeight()) {

			if (u.getPredecessor().getWeight() == v.getPredecessor().getWeight()) {
				u.getPredecessor().setWeight(u.getPredecessor().getWeight() + 1);
			}
			v.setPredecessor(u.getPredecessor());
		} else {
			u.setPredecessor(v.getPredecessor());
		}

	}

	/**
	 * This method returns the representative vertex of the set s belongs to
	 * 
	 * @param s
	 * @return
	 */
	public Vertex findSet(Vertex s) {
		if (s.getPredecessor() == s) {
			return s.getPredecessor();
		}
		s.setPredecessor(findSet(s.getPredecessor()));
		return s.getPredecessor();
	}

	/**
	 * calculate minimum spanning tree from given graph
	 * 
	 * @param graph
	 */
	public Set<Edge> mst(Graph graph) {
		Set<Edge> spanningTreeSet = new HashSet<>();
		for (Vertex v : graph.getGraph().keySet()) {
			makeSet(v);
		}

		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge e1, Edge e2) {
				if (e1.getWeight() < e2.getWeight())
					return -1;
				else if (e1.getWeight() > e2.getWeight())
					return 1;
				else
					return 0;
			}
		});

		for (Edge e : graph.getAllEdges()) {
			priorityQueue.add(e);
		}

		while (!priorityQueue.isEmpty()) {
			Edge e = priorityQueue.peek();
			Vertex u = disjointSets.get(e.getSource().getIndex());
			Vertex v = disjointSets.get(e.getDest().getIndex());
			Vertex parentSource = findSet(u);
			Vertex parentDest = findSet(v);
			if (parentSource != parentDest) {
				spanningTreeSet.add(e);
				union(parentSource, parentDest);
			}
			priorityQueue.poll();
		}
		return spanningTreeSet;

	}

}
