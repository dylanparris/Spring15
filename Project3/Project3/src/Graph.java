
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
	
	public void shortestPath(String start, String end){
		int startIndex;
		int endIndex;
		if(vertices.contains(new Vertex(start))){
			startIndex = vertices.indexOf(new Vertex(start));
		} else {
			System.out.println(start + " vertex not found in graph");
			return;
		}
		if(vertices.contains(new Vertex(end))){
			endIndex = vertices.indexOf(new Vertex(end));
		} else {
			System.out.println(end + " vertex not found in graph");
			return;
		}
		shortestPath(vertices.get(startIndex), vertices.get(endIndex));
	}
	
	public static void shortestPath(Vertex start, Vertex end){
		PriorityQueue<Edge> minheap=new PriorityQueue<Edge>(1, new Comparator<Edge>(){
			public int compare(Edge e1, Edge e2){
				if(e1.weight > e2.weight){
					return 1;
				} else if (e1.weight == e2.weight){
					return 0;
				} else {
					return -1;
				}
			}
		});
	    //add elements
	    minheap.addAll(start.edges);
	    System.out.println("Minheap---------------------");
	    System.out.println(minheap.toString());//  		toString(minheap.toArray()));
	    for (Iterator<Edge> iterator = minheap.iterator(); iterator.hasNext();) {
	        //System.out.println("Min : "+ minheap.element());
	        //System.out.println("Removing " + minheap.element());
	        
	        minheap.remove();
	       // System.out.println(minheap.toString());
	    }
	}
}
