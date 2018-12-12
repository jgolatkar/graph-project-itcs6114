import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to build graph
 * 
 * @author jitesh golatkar
 *
 */
public class GraphBuilder {

	private static final String DELIMITER = " ";

	/**
	 * @param file
	 * @param graph
	 */

	public static void populateGraph(File file, Graph graph) {
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
			String line = bf.readLine();
			String[] grData = line.split(DELIMITER); // read first line
			graph.setNo_of_vertices(Integer.parseInt(grData[0])); // set no. of vertices
			graph.setNo_of_edges(Integer.parseInt(grData[1])); // set no. of edges
			graph.setGtype(GraphType.valueOf(grData[2])); // set Directed/Undirected
			boolean isSourceGiven = false;
			Vertex initialSource = null;
			Vertex source = null;
			Vertex dest = null;

			while ((line = bf.readLine()) != null) {
				grData = line.split(DELIMITER);
				if (grData.length > 1) {
					source = new Vertex(grData[0].charAt(0));
					dest = new Vertex(grData[1].charAt(0));
					if (graph.getKeySet().contains(source)) {
						for (Vertex v : graph.getKeySet()) {
							if (v.equals(source)) {
								source = v;
								break;
							}
						}
					}
					if (graph.getKeySet().contains(dest)) {
						for (Vertex v : graph.getKeySet()) {
							if (v.equals(dest)) {
								dest = v;
								break;
							}
						}
					}

					// save initial vertex as source of graph
					if (initialSource == null) {
						initialSource = source;
					}

					Edge edge = new Edge(source, dest, Integer.parseInt(grData[2]));
					if (graph.getGtype() == GraphType.valueOf("U")) {
						graph.insertIntoUndirectedGraph(edge);
					}
					if (graph.getGtype() == GraphType.valueOf("D")) {
						graph.insertIntoDirectedGraph(edge);
					}

				} else {
					isSourceGiven = true;
					graph.setSource(new Vertex(grData[0].charAt(0)));
				}

			}

			// set initial vertex as source if source is not given in file
			if (!isSourceGiven) {
				graph.setSource(initialSource);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
