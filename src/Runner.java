import java.io.File;

/**
 * Class to Run project
 * 
 * @author jitesh golatkar
 *
 */
public class Runner {

	public static void main(String args[]) {

		KruskalMST k = new KruskalMST();

		String currentPath = System.getProperty("user.dir");
		int fileCount = 0;
		File current = null;
		for (String file : new File(currentPath).list()) {
			if (file.startsWith("input") && file.endsWith(".txt")) {
				fileCount++;
				current = new File(file);
				Graph graph = new Graph();
				GraphBuilder.populateGraph(current, graph);
				System.out.println("\nGraph " + fileCount + "\n");
				graph.printGraph();
				System.out.println("------------------------------");
				System.out.println("\tShortest Paths\n");
				ShortestPath sp = new ShortestPath();
				sp.findShortestPath(graph);
				sp.printShortestPaths(graph);
				System.out.println("------------------------------");
				System.out.println();
			} else {
				/* Print Minimum Spanning Tree Edges and Total Cost */
				if (file.startsWith("mst") && file.endsWith(".txt")) {
					current = new File(file);
					Graph minspanGraph = new Graph();
					GraphBuilder.populateGraph(current, minspanGraph);
					System.out.println("Minimum Spanning Tree: Kruskal Algorithm\n");
					System.out.println("Given Graph:");
					minspanGraph.printGraph();
					System.out.println("Minimum Spanning Tree Edges & Total Cost");
					int totalCost = 0;
					for (Edge e : k.mst(minspanGraph)) {
						totalCost += e.getWeight();
						System.out
								.println(e.getSource().getIndex() + " " + e.getDest().getIndex() + " " + e.getWeight());

					}
					System.out.println("Total Cost: " + totalCost);
				}

			}

		}
	}

}
