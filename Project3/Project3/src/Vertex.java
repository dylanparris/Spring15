import java.util.LinkedList;


public class Vertex implements Comparable<Vertex>{
	
	String name;
	LinkedList<Edge> edges;
	
	public Vertex(String newVertexName){
		name = newVertexName;
		edges = new LinkedList<Edge>();
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
	public void deleteEdge(String destination){
		if(!edges.contains(destination)){
			edges.remove(destination);
		}
	}
	
	public int compareTo(Vertex otherVertex){
		return this.name.compareTo(otherVertex.name);
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
		
		return name + "\n" + edges.toString() + "\n";
	}
}