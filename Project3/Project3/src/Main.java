import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
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
		
		System.out.println(g.printGraph());

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
			String line = g.printGraph();
			System.out.println(line);
		} else if(input.contains("addedge")){
			String[] command = input.split(" ");
			System.out.println("Adding Edge from " + command[1] + " to " + command[2] + " with weight " + command[3]);
			g.addEdge(command[2], command[1], Float.parseFloat(command[3]));
		} else if(input.contains("deleteedge")){
			String[] command = input.split(" ");
			System.out.println("deleting Edge from " +command[1] + " to " + command[2]);
			g.deleteEdge(command[2], command[1]);
		} else if(input.contains("path")){
			System.out.println("path request detected");
			//TODO
			String[] command = input.split(" ");
			System.out.println("shortest path from " +command[1] + " to " + command[2]);
			//g.shortestPath(command[2], command[1]);
			dijkstra(g,command[1]);
		} else if(input.equals("reachable")){
			System.out.println("reachable request detected");
			//TODO
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

	public static void dijkstra(Graph graph, String start){
		PriorityQueue<Vertex> minheap=new PriorityQueue<Vertex>(1);
		ArrayList<Vertex> path = new ArrayList<Vertex>(10);
		ArrayList<Edge> availEdges = new ArrayList<Edge>(10);
		for(int i = 0; i < graph.vertices.size(); i++){
			
			graph.vertices.get(i).reset();
			if(graph.vertices.get(i).name.equals(start)){
				graph.vertices.get(i).setDistance(0);
			}
			minheap.add(graph.vertices.get(i));
			
			System.out.println("adding " + graph.vertices.get(i).name + " to min heap");
			System.out.println("with distance from " + start + ": " + graph.vertices.get(i).distance);
		}
		
		
		
		int index;
		for(int i = 0; i < graph.vertices.size() - 1; i++){ //for each vertex in the graph
			path.add(minheap.remove());
			for(int j = 0; j < path.size(); j++){ // for each vertex in the graph
				//System.out.println(path.get(j).name);
				for(int k=0; k < path.get(j).edges.size(); k++){ // for each edge in the path
					//if path doesn't contain what the edge is pointing to vertex already in path
					if(!path.contains(new Vertex(path.get(j).edges.get(k).destination))){
						availEdges.add(path.get(j).edges.get(k));
					}
				}
				availEdges.sort(new Comparator<Edge>(){
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
				if(availEdges.size() != 0){
					Edge shortest = availEdges.get(0);
					index = g.vertices.indexOf(new Vertex(shortest.destination));
					Vertex current = g.vertices.get(index);
					current.setPred(shortest.source);
					Vertex pred = g.vertices.get(g.vertices.indexOf(new Vertex(current.getPred())));
					System.out.println(current.name);
					System.out.println("predecessor: " + current.getPred());
					System.out.println("predecessor distance: " + pred.distance);
					System.out.println("shortest edge distance: " + shortest.weight);
					float newWeight = pred.distance + shortest.weight;
					System.out.println("Sum: " + newWeight);
					System.out.println();
					current.setDistance(newWeight);
					path.add(g.vertices.get(index));
					availEdges = new ArrayList<Edge>(10);
				}
			}
		}
		
		for(int i = 0; i < path.size(); i++){
			System.out.println(path.get(i).name + ": " + path.get(i).distance);
		}
		
	}
	
}
