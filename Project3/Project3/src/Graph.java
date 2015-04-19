
import java.util.*;



public class Graph{
	
	LinkedList<Vertex> vertices;
	
	public Graph(){
		vertices = new LinkedList<Vertex>();
	}
	
	public void addVertex(String newVertexName){
		if(!vertices.contains(new Vertex(newVertexName))){
			Vertex newVertex = new Vertex(newVertexName);
			vertices.add(newVertex);
		}
	}
	
	/**
	 * Adds new edge to graph. Creates vertices if they don't already exist.
	 * if edge already exists updates weight.
	 * @param source - source vertex of edge.
	 * @param destination - destination vertex of edge.
	 * @param weight - weight of edge.
	 */
	public void addEdge(String source, String destination, float weight){
		
		if(vertices.contains(new Vertex(source)) && vertices.contains(new Vertex(destination))){
			vertices.get(vertices.indexOf(new Vertex(source))).addEdge(destination, weight);
		} else if (!vertices.contains(new Vertex(source))){
			Vertex temp = new Vertex(source);
			vertices.add(temp);
			addEdge(source, destination, weight);
		} else if (!vertices.contains(new Vertex(destination))) {
			Vertex temp = new Vertex(destination);
			vertices.add(temp);
			addEdge(source, destination, weight);
		}
	}
	
	public void deleteEdge(String source, String destination){
		if(vertices.contains(new Vertex(source)) && vertices.contains(new Vertex(destination))){
			Vertex v = vertices.get(vertices.indexOf(new Vertex(source)));
			Edge e = v.edges.get(v.edges.indexOf(new Edge(source, destination, (float) 0)));
			v.deleteEdge(e);
		}
	}
	
	public String printGraph(){
		vertices.sort(null);
		String output = "";
		for(int i = 0; i < vertices.size(); i++){
			output = output + vertices.get(i).toString();
		}
		return output;
		
	}
}
