
public class Edge implements Comparable<Edge>{
	
	String source;
	String destination;
	float weight;
	
	public Edge(String start, String end, float distance){
		source = start;
		destination = end;
		weight = distance;
	}
	
	public float getWeight(){
		return weight;
	}
	
	public void setWeight(float newWeight){
		weight = newWeight;
	}

	public int compareTo(Edge otherEdge) {
		return this.destination.compareTo(otherEdge.destination);
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Edge){
			Edge e = (Edge) o;
			return e.destination.equals(this.destination);
		}
		return false;	
	}
	
	@Override
	public int hashCode() {
	    return destination.hashCode();
	}
	
	@Override
	public String toString(){
				
		return "   " + destination + " " + weight + "\n";
	}
}
