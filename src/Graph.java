import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class for creating graph
 * 
 * @author jitesh golatkar
 *
 */
public class Graph {

	private Map<Vertex, LinkedList<Vertex>> graphMap;
	private Set<Vertex> keySet;
	private int no_of_vertices;
	private int no_of_edges;
	private GraphType gtype;
	private Vertex source;
	private Map<String, Integer> allEdgeWeights;
	private List<Edge> allEdges;

	// unparameterized constructor
	public Graph() {
		this.graphMap = new HashMap<>();
		this.keySet = graphMap.keySet();
		this.allEdges = new ArrayList<>();
		this.allEdgeWeights = new HashMap<>();
	}

	public Map<String, Integer> getAllEdgeWeights() {
		return allEdgeWeights;
	}

	public void setAllEdgeWeights(Map<String, Integer> allEdgeWeights) {
		this.allEdgeWeights = allEdgeWeights;
	}

	public List<Edge> getAllEdges() {
		return allEdges;
	}

	public void setAllEdges(List<Edge> allEdges) {
		this.allEdges = allEdges;
	}

	public Set<Vertex> getKeySet() {
		return keySet;
	}

	public void setKeySet(Set<Vertex> keySet) {
		this.keySet = keySet;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Map<Vertex, LinkedList<Vertex>> getGraph() {
		return graphMap;
	}

	public void setGraph(Map<Vertex, LinkedList<Vertex>> graphMap) {
		this.graphMap = graphMap;
	}

	public int getNo_of_vertices() {
		return no_of_vertices;
	}

	public void setNo_of_vertices(int no_of_vertices) {
		this.no_of_vertices = no_of_vertices;
	}

	public int getNo_of_edges() {
		return no_of_edges;
	}

	public void setNo_of_edges(int no_of_edges) {
		this.no_of_edges = no_of_edges;
	}

	public GraphType getGtype() {
		return gtype;
	}

	public void setGtype(GraphType gtype) {
		this.gtype = gtype;
	}

	/**
	 * add new vertex into undirected graph representation
	 * 
	 * @param source
	 * @param dest
	 */
	public void insertIntoUndirectedGraph(Edge edge) {
		if (getGraph().get(edge.getSource()) == null) {
			this.getGraph().put(edge.getSource(), new LinkedList<>());
		}
		if (getGraph().get(edge.getDest()) == null) {
			this.getGraph().put(edge.getDest(), new LinkedList<>());
		}

		this.getGraph().get(edge.getSource()).add(edge.getDest());
		this.getGraph().get(edge.getDest()).add(edge.getSource());
		this.getAllEdges().add(edge);

		this.getAllEdgeWeights().put(
				Character.toString(edge.getSource().getIndex()) + Character.toString(edge.getDest().getIndex()),
				edge.getWeight());
		this.getAllEdgeWeights().put(
				Character.toString(edge.getDest().getIndex()) + Character.toString(edge.getSource().getIndex()),
				edge.getWeight());

	}

	/**
	 * add new vertex into directed graph representation
	 * 
	 * @param source
	 * @param dest
	 */

	public void insertIntoDirectedGraph(Edge edge) {
		if (this.getGraph().get(edge.getSource()) == null) {
			this.getGraph().put(edge.getSource(), new LinkedList<>());
		}
		if (this.getGraph().get(edge.getDest()) == null) {
			this.getGraph().put(edge.getDest(), new LinkedList<>());
		}
		this.getGraph().get(edge.getSource()).add(edge.getDest());
		this.getAllEdges().add(edge);
		this.getAllEdgeWeights().put(
				Character.toString(edge.getSource().getIndex()) + Character.toString(edge.getDest().getIndex()),
				edge.getWeight());

	}

	/**
	 * displays graph representation
	 */

	public void printGraph() {

		for (Vertex v : graphMap.keySet()) {
			System.out.print(v.getIndex() + " --> ");
			for (Vertex adj : graphMap.get(v)) {
				System.out.print(adj.getIndex() + " ");
			}
			System.out.println();
		}

	}

	public Integer getEdgeWeight(Character source, Character dest) {
		return getAllEdgeWeights().get(Character.toString(source) + Character.toString(dest));
	}

}
