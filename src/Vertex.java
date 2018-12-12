
/**
 * Class for graph vertex
 * 
 * @author jitesh golatkar
 *
 */
public class Vertex {
	private char index;
	private int weight;
	private Vertex predecessor;

	public Vertex(char index) {
		this.index = index;
		
		predecessor = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (index != other.index)
			return false;

		return true;
	}

	public char getIndex() {
		return index;
	}

	public void setIndex(char index) {
		this.index = index;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

}
