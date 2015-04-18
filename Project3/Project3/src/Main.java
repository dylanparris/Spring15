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
		System.out.println("Enter Command: ('0' to exit)");
		String input = s.next();
		
		if(input.equals("quit")){
			s.close();
			System.exit(0);
		} else if(input.equals("print")){
			String line = g.printGraph();
			System.out.println(line);
		} else if(input.contains("addedge")){
			System.out.println("Add Edge request detected");
			//TODO
		} else if(input.contains("deleteedge")){
			System.out.println("delete edge request detected");
			//TODO
		} else if(input.contains("path")){
			System.out.println("path request detected");
			//TODO
		} else if(input.equals("reachable")){
			System.out.println("reachable request detected");
			//TODO
		}
		
		menu();
	}

}
