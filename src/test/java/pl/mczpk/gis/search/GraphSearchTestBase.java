package pl.mczpk.gis.search;

import static org.junit.Assert.*;
import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.search.model.GraphSearchResult;
import pl.mczpk.gis.util.GraphUtils;

public class GraphSearchTestBase {

	protected void verifyEdgesNotInTreeCount(Graph graph, GraphSearchResult searchResult) {
		int edgesNotInTreeCount = searchResult.getEdgesNotInTree().size();
		
		assertEquals(GraphUtils.getCyclomaticNumberForGraph(graph), edgesNotInTreeCount);
	}
}
