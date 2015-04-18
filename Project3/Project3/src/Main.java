
public class Main {

	public static void main(String[] args) {
		Graph g = new Graph();
		
		g.addVertex("test");
		g.addEdge("test", "this", (float)5);
		g.addVertex("beta");
		g.addEdge("beta", "alpha", (float) 4);
		g.addVertex("alpha");
		
		System.out.println(g.printGraph());

	}

}
