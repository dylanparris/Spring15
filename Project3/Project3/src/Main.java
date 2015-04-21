import java.io.FileNotFoundException;
import java.io.FileReader;
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
			g.shortestPath(command[2], command[1]);
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

	
	
}
