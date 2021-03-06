
import java.util.*;



/**
 * @author Dylan
 * graph class for project 3
 */
public class Graph{

	LinkedList<Vertex> vertices;

	public Graph(){
		vertices = new LinkedList<Vertex>();
	}

	/**
	 * @param newVertexName name of the new vertex;
	 */
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

	/**
	 * @param source
	 * @param destination
	 */
	public void deleteEdge(String source, String destination){
		if(vertices.contains(new Vertex(source)) && vertices.contains(new Vertex(destination))){
			Vertex v = vertices.get(vertices.indexOf(new Vertex(source)));
			Edge e = v.edges.get(v.edges.indexOf(new Edge(source, destination, 0)));
			v.deleteEdge(e);
		}
	}

	/**
	 * @return returns string representation of graph
	 */
	public String printGraph(){
		vertices.sort(null);
		String output = "";
		for(int i = 0; i < vertices.size(); i++){
			output = output + vertices.get(i).name + "\n";
			vertices.get(i).edges.sort(new Comparator<Edge>(){
				@Override
				public int compare(Edge e1, Edge e2){
					return e1.destination.compareTo(e2.destination);
				}
			});
			for(int j = 0; j < vertices.get(i).edges.size(); j++){
				output += "\t";
				output += vertices.get(i).edges.get(j).destination + " ";
				output += vertices.get(i).edges.get(j).weight + "\n";
			}
		}
		return output;

	}
}
