import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main {
	
	public static Graph g;
	
	public static void main(String[] args) {
		
		if(args.length != 1){
			System.out.println("Usage Information: ");
			System.out.println("Main.java path/to/in1.txt");
			System.exit(-1);
		}
		
		g = new Graph();
		
		readFile(args[0]);
		
		menu();
		
		//System.out.println(g.printGraph());

	}
	
	public static void readFile(String fileName){
		Scanner in = null;
		try {
			 in = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
		
		String currentLine;
		String[] parts;
		while(in.hasNext()){
			currentLine = in.nextLine();
			parts = currentLine.split(" ");
			g.addEdge(parts[0], parts[1], Float.parseFloat(parts[2]));
			g.addEdge(parts[1], parts[0], Float.parseFloat(parts[2]));
		}
		in.close();
	}
	
	public static void menu(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Command: ('help' for avaliable commands)");
		String input = s.nextLine();
		
		if(input.equals("quit")){
			s.close();
			System.exit(0);
		} else if(input.equals("print")){
			System.out.println(g.printGraph());
		} else if(input.contains("addedge")){
			String[] command = input.split(" ");
			g.addEdge(command[2], command[1], Float.parseFloat(command[3]));
		} else if(input.contains("deleteedge")){
			String[] command = input.split(" ");
			g.deleteEdge(command[2], command[1]);
		} else if(input.contains("path")){
			String[] command = input.split(" ");
			System.out.println(dijkstra(g,command[1],command[2]));
		} else if(input.equals("reachable")){
			bfs(g);
		} else if(input.equals("help")){
			System.out.println("'addedge tailVertex headVertex transmitTime' 	- adds edge to graph");
			System.out.println("'deleteedge tailVertex headVertex' 		- removes edge to graph");
			System.out.println("'path fromVertex toVertex' 			- find shortest path between vertices");
			System.out.println("'print' 					- to print graph with its edges");
			System.out.println("'reachable' 					- prints all vertices and all vertices that are reachable from each");
			System.out.println("'quit' 						- Exits Program");
		} else {
			System.out.println("Not a valid command use 'help' to see valid commands");
		}
		
		menu();
		
	}

	public static String dijkstra(Graph graph, String start, String end){
		MinPQ<Vertex> minheapV=new MinPQ<Vertex>();
			
		MinPQ<Edge> minheapE = new MinPQ<Edge>();
		
		ArrayList<Vertex> path = new ArrayList<Vertex>(1);
		for(int i = 0; i < graph.vertices.size(); i++){
			
			graph.vertices.get(i).reset();
			if(graph.vertices.get(i).name.equals(start)){
				graph.vertices.get(i).setDistance(0);
			}
			minheapV.add(graph.vertices.get(i));
		}
				
		int index;
		for(int i = 0; i < graph.vertices.size() - 1; i++){ //for each vertex in the graph
			path.add(minheapV.remove());
			
			for(int k=0; k < path.get(path.size() - 1).edges.size(); k++){ 
				Edge currentEdge = path.get(path.size() - 1).edges.get(k);
				minheapE.add(currentEdge);
			}
			
			while(!minheapE.isEmpty()){
								
				Edge shortest = minheapE.remove();
				index = g.vertices.indexOf(new Vertex(shortest.destination));
				Vertex current = g.vertices.get(index);
				Vertex pred = g.vertices.get(g.vertices.indexOf(new Vertex(shortest.source)));
				float newWeight = pred.distance + shortest.weight;
				
				if(current.getDistance() > newWeight){
					
					current.setPred(shortest.source);					
					current.setDistance(newWeight);
				}
			}
		}
		String fullPath;
		if(path.contains(new Vertex(end))){
			Vertex current = path.get(path.indexOf(new Vertex(end)));
			fullPath = current.name + " " + current.distance;
			while(!current.predecessor.equals(start)){
				current = path.get(path.indexOf(new Vertex(current.predecessor)));
				fullPath = current.name + " " + fullPath;
			}
			fullPath = start + " " + fullPath;
		} else {
			fullPath = "No Path exsists";
		}
		return fullPath;
	}
	
	public static void bfs(Graph g){
		Queue<Vertex> queue = new LinkedList<Vertex>();
		ArrayList<Vertex> found;
		
		g.vertices.sort(new Comparator<Vertex>(){
    		public int compare(Vertex v1, Vertex v2){
    			return v1.name.compareTo(v2.name);
    	}});
		 
		 for(int root = 0; root < g.vertices.size(); root++){
			 found = new ArrayList<Vertex>();
			 queue.add(g.vertices.get(root));
			 found.add(g.vertices.get(root));
	 		
	        while(!queue.isEmpty())
	        {
	            Vertex r = queue.remove(); 
	            
	            for(Edge n: r.edges){
	            	Vertex currentVertex = g.vertices.get(g.vertices.indexOf(new Vertex(n.destination)));
	            	if(!found.contains(currentVertex)){
	                    queue.add(currentVertex);
	                    found.add(currentVertex);
	                }
	            }
	        }
	        
	        System.out.println(found.remove(0).name);
	        found.sort(new Comparator<Vertex>(){
	        		public int compare(Vertex v1, Vertex v2){
	        			return v1.name.compareTo(v2.name);
	        	}});
	        
	        for(int i = 0; i < found.size(); i++){
	        	System.out.println("\t" + found.get(i).name);
	        }
	 	}
	}
}
