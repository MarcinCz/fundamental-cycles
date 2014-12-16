package pl.mczpk.gis.search;

import static org.junit.Assert.*;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.search.model.GraphSearchResult;

public class GraphSearchTestBase {

	protected void verifyEdgesNotInTreeCount(Graph graph, GraphSearchResult searchResult) {
		int nodesCount = graph.getNodesCount();
		int edgesCount = graph.getEdgesCount();
		int edgesNotInTreeCount = searchResult.getEdgesNotInTree().size();
		
		assertEquals(edgesCount - nodesCount + 1, edgesNotInTreeCount);
	}
}
