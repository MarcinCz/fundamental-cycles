package pl.mczpk.gis.search;

import org.junit.Test;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.RandomGraphFactory;
import pl.mczpk.gis.search.model.GraphSearchResult;

public class BreadthFirstSearchTest extends GraphSearchTestBase {

	private GraphSearch testee = new BreadthFirstSearch();
	
	@Test
	public void testRandomGraph() {
		for(int i = 0; i < 10; ++i) {
			Graph graph = RandomGraphFactory.getInstance().getGraph(100, 150);
			
			GraphSearchResult searchResult = testee.searchGraph(graph, graph.getRandomNode());
			
			verifyEdgesNotInTreeCount(graph, searchResult);
		}
	}
}
