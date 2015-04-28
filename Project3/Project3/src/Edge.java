/**
 * @author Dylan
 * Edge class for project 3
 */
public class Edge implements Comparable<Edge>{

	String source;
	String destination;
	float weight;

	/**
	 * @param start - head of edge
	 * @param end - tail of edge
	 * @param distance - weight of edge
	 */
	public Edge(String start, String end, float distance){
		source = start;
		destination = end;
		weight = distance;
	}

	/**
	 * @return weight of edge
	 */
	public float getWeight(){
		return weight;
	}

	/**
	 * @param newWeight new weight to be set
	 */
	public void setWeight(float newWeight){
		weight = newWeight;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Edge otherEdge) {
		if(this.weight > otherEdge.weight){
			return 1;
		} else if (this.weight == otherEdge.weight){
			return 0;
		} else {
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof Edge){
			Edge e = (Edge) o;
			return e.destination.equals(this.destination);
		}
		return false;	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return destination.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override 
	public String toString(){
		return "   " + destination + " " + weight + "\n";
	}

}
