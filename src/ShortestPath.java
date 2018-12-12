import java.util.Comparator;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Class ShortestPath: includes implementation of methods to find shortest path
 * using Dijkstra Algorithm.
 * 
 * 
 * @author jitesh golatkar
 *
 */
public class ShortestPath {

	private NavigableSet<Vertex> priorityQueue;

	/**
	 * method to initialize all vertices of a graph
	 * 
	 * @param graph
	 */
	public void initializeSingleSource(Graph graph) {

		for (Vertex v : graph.getGraph().keySet()) {
			if (v.equals(graph.getSource())) {
				v.setWeight(0);
			} else {
				v.setWeight(Integer.MAX_VALUE);
			}
			v.setPredecessor(null);

		}

	}

	/**
	 * method to implement relaxation of weights of vertices
	 * 
	 * @param v
	 * @param path
	 */
	public void relax(Vertex u, Vertex v, Integer w) {
		if (v.getWeight() > (u.getWeight() + w)) {
			v.setWeight(u.getWeight() + w);
			// reorder priority queue (here it is TreeSet )
			priorityQueue.remove(v);
			priorityQueue.add(v);
			v.setPredecessor(u);
		}
	}

	/**
	 * method to calculate all shortest paths in a graph
	 * 
	 * @param graph
	 */
	public void findShortestPath(Graph graph) {
		initializeSingleSource(graph);
		Set<Vertex> visitedSet = new HashSet<>();

		/*
		 * Initialize min-priority queue with graph vertices.
		 * 
		 * Min-Heap priority queue functionality is implemented using TreeSet data
		 * structure. To update reflect updated weight of Vertex and reordering TreeSet,
		 * vertex is removed and added again.Time complexity of additional step is O(log
		 * n). This is still asymptotically similar to reordering min-heap.
		 * 
		 */
		priorityQueue = new TreeSet<>(new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				if (o1.getWeight() < o2.getWeight())
					return -1;
				else if (o1.getWeight() > o2.getWeight()) {
					return 1;
				} else {
					if (o1.getIndex() != o2.getIndex())
						return 1;
					else
						return 0;
				}
			}
		});

		for (Vertex v : graph.getKeySet()) {
			priorityQueue.add(v);
		}

		while (!priorityQueue.isEmpty()) {

			Vertex u = priorityQueue.pollFirst();
			for (Vertex v : graph.getGraph().get(u)) {
				relax(u, v, graph.getEdgeWeight(u.getIndex(), v.getIndex()));
			}
			visitedSet.add(u);

		}

	}

	/**
	 * This method prints each path from source and its corresponding shortest path
	 * cost.
	 * 
	 * @param graph
	 */
	public void printShortestPaths(Graph graph) {
		Stack<Vertex> path = null;
		Integer totalWeight = null;
		for (Vertex v : graph.getKeySet()) {
			totalWeight = 0;
			path = new Stack<>();
			while (v.getPredecessor() != null) {
				path.push(v);
				v = v.getPredecessor();
			}

			System.out.print(v.getIndex());
			while (!path.isEmpty()) {
				totalWeight = path.peek().getWeight();
				System.out.print(" --> " + path.pop().getIndex());
			}
			System.out.print(" [Cost: " + totalWeight + "]\n");
		}

	}

}
