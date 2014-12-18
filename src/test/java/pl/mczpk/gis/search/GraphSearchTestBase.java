package pl.mczpk.gis.search;

import junit.framework.TestCase;

import org.junit.Test;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.RandomGraphFactory;
import pl.mczpk.gis.search.model.GraphSearchResult;
import pl.mczpk.gis.util.GraphUtils;

public abstract class GraphSearchTestBase extends TestCase {
	
	protected GraphSearch testee;

	@Test
	public void testRandomGraph() {
		for(int i = 0; i < 10; ++i) {
			Graph graph = RandomGraphFactory.getInstance().getGraph(100, 150);
			
			GraphSearchResult searchResult = testee.searchGraph(graph, graph.getRandomNode());
			
			verifyEdgesNotInTreeCount(graph, searchResult);
		}
	}
	
	protected void verifyEdgesNotInTreeCount(Graph graph, GraphSearchResult searchResult) {
		int edgesNotInTreeCount = searchResult.getEdgesNotInTree().size();
		
		assertEquals(GraphUtils.getCyclomaticNumberForGraph(graph), edgesNotInTreeCount);
	}
	
}
