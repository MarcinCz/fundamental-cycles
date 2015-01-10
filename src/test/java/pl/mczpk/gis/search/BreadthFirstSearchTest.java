package pl.mczpk.gis.search;

import org.junit.Test;

import pl.mczpk.gis.IO.GraphReader;
import pl.mczpk.gis.graph.Graph;


public class BreadthFirstSearchTest extends GraphSearchTestBase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		testee = new BreadthFirstSearch();
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
}
