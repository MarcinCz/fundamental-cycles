package pl.mczpk.gis.search;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Test;

import pl.mczpk.gis.IO.GraphReader;
import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.RandomGraphFactory;
import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.util.GraphUtils;

public abstract class GraphSearchTestBase extends TestCase {
	
	protected GraphSearch testee;
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void testEdgesNotInTreeCount_RandomGraph() {
		for(int i = 0; i < 10; ++i) {
			Graph graph = RandomGraphFactory.getInstance().getGraph(100, 150);
			
			GraphSearchResult searchResult = testee.searchGraph(graph, graph.getRandomNode());
			logger.trace(String.format("Random graph search took %s ms", searchResult.getExecutionTime()));
			
			assertEquals(GraphUtils.getCyclomaticNumberForGraph(graph), searchResult.getEdgesNotInTree().size());
		}
	}
	
	@Test
	public void testParentConnection_RandomGraph() {
		for(int i = 0; i < 10; ++i) {
			Graph graph = RandomGraphFactory.getInstance().getGraph(100, 150);
			Node startingNode = graph.getRandomNode();
			
			GraphSearchResult searchResult = testee.searchGraph(graph, startingNode);
			logger.trace(String.format("Random graph search took %s ms", searchResult.getExecutionTime()));
			
			for(Edge edgeNotInTree: searchResult.getEdgesNotInTree()) {
				assertLastParentForNode(edgeNotInTree.getFirstNode(), startingNode);
				assertLastParentForNode(edgeNotInTree.getSecondNode(), startingNode);
			}
		}
	}
	
	@Test
	public void shouldFindAllCycles(){
		Graph graph = GraphReader.readGraphFromFile("src/test/resources/triangle.graph");
		assertEquals(1, testee.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree().size());
		
		graph = GraphReader.readGraphFromFile("src/test/resources/rectangle.graph");
		assertEquals(1, testee.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree().size());
		
		graph = GraphReader.readGraphFromFile("src/test/resources/rectangle_with_diagonals.graph");
		assertEquals(3, testee.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree().size());
		
		graph = GraphReader.readGraphFromFile("src/test/resources/pentagon.graph");
		assertEquals(2, testee.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree().size());
		
		graph = GraphReader.readGraphFromFile("src/test/resources/pentagon2.graph");
		assertEquals(4, testee.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree().size());
		
		graph = GraphReader.readGraphFromFile("src/test/resources/hexagon.graph");
		assertEquals(4, testee.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree().size());
		
		graph = GraphReader.readGraphFromFile("src/test/resources/hexagon2.graph");
		assertEquals(4, testee.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree().size());
	}
	
	public void assertLastParentForNode(Node node, Node lastParent) {
		while(node.getParent() != lastParent) {
			node = node.getParent();
		}
	}
}
