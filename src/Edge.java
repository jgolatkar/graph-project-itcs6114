/**
 * class for graph edges
 * 
 * @author jitesh golatkar
 *
 */
public class Edge {
	private Integer weight;
	private Vertex source;
	private Vertex dest;

	/**
	 * empty constructor
	 */
	public Edge() {
	}

	public Edge(Vertex source, Vertex dest, Integer weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;

	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDest() {
		return dest;
	}

	public void setDest(Vertex dest) {
		this.dest = dest;
	}

}
