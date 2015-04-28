import java.util.LinkedList;


/**
 * @author Dylan
 * Vertex class for project 3
 */
public class Vertex implements Comparable<Vertex>{

	String name;
	LinkedList<Edge> edges;
	float distance;
	String predecessor;
	static final float INFINITY = 2147483645; //Max for 32 bit int


	/**
	 * @param newVertexName name of new vertex
	 */
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
	 * @param edge - destination of edge.
	 */
	public void deleteEdge(Edge edge){
		if(edges.contains(edge)){
			this.edges.remove(edge);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Vertex otherVertex){
		if(this.distance > otherVertex.distance){
			return 1;
		} else if (this.distance == otherVertex.distance){
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
		if(o instanceof Vertex){
			Vertex e = (Vertex) o;
			return e.name.equals(this.name);
		}
		return false;	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.name;
	}

	/**
	 * resets distance from start and predecessor
	 */
	public void reset() {
		this.distance = INFINITY;
		this.predecessor = null;
	}

	/**
	 * @param dist sets new distance from start
	 */
	public void setDistance(float dist){
		this.distance = dist;
	}

	/**
	 * @return gets distance from start
	 */
	public float getDistance(){
		return this.distance;
	}

	/**
	 * @param sets predecessor
	 */
	public void setPred(String s){
		this.predecessor = s;
	}

	/**
	 * @return gets predecessor
	 */
	public String getPred(){
		return this.predecessor;
	}
}