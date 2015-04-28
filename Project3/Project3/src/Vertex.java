import java.util.LinkedList;


public class Vertex implements Comparable<Vertex>{
	
	String name;
	LinkedList<Edge> edges;
	float distance;
	String predecessor;
	static final float INFINITY = 2147483645; //Max for 32 bit int
	
	
	public Vertex(String newVertexName){
		name = newVertexName;
		edges = new LinkedList<Edge>();
		distance = INFINITY;
		predecessor = null;
	}
	
	/**
	 * Adds edge to vertex. if edge already exists updates weight.
	 * @param destination - destination of edge.
	 * @param weight - weight of edge.
	 */
	public void addEdge(String destination, float weight){
		
		if(!edges.contains(new Edge(this.name, destination, weight))){
			Edge newEdge = new Edge(this.name, destination, weight);
			edges.add(newEdge);
		} else {
			edges.get(edges.indexOf(new Edge(this.name, destination, weight))).setWeight(weight);
		}
	}
	
	/**
	 * removes edge from vertex.
	 * @param destination - destination of edge.
	 */
	public void deleteEdge(Edge edge){
		if(edges.contains(edge)){
			this.edges.remove(edge);
		}
	}
	
	public int compareTo(Vertex otherVertex){
		if(this.distance > otherVertex.distance){
			return 1;
		} else if (this.distance == otherVertex.distance){
			return 0;
		} else {
			return -1;
		}
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Vertex){
			Vertex e = (Vertex) o;
			return e.name.equals(this.name);
		}
		return false;	
	}
	
	@Override
	public int hashCode() {
	    return name.hashCode();
	}
	
	@Override
	public String toString(){
		/**edges.sort(new Comparator<Edge>(){
			public int compare(Edge e1, Edge e2){
				return e1.destination.compareTo(e2.destination);
			}});
		String output = "";
		for(int i = 0; i < edges.size(); i++){
			if(i == 0){
				output += this.name + "\n";
			}
			output = output + edges.get(i).toString();
		}
		return output;
		**/
		return this.name;
	}

	public void reset() {
		this.distance = INFINITY;
		this.predecessor = null;
	}
	
	public void setDistance(float dist){
		this.distance = dist;
	}
	
	public float getDistance(){
		return this.distance;
	}
	
	public void setPred(String s){
		this.predecessor = s;
	}
	
	public String getPred(){
		return this.predecessor;
	}
}