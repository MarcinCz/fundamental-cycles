package pl.mczpk.gis.graph;

import static org.junit.Assert.*;
import pl.mczpk.gis.util.GraphUtils;

public class GraphFactoryTestBase {

	protected void assertEdgesCountForFullGraph(Graph graph) {
		int nodesCount = graph.getNodesCount();
		assertEquals(GraphUtils.getEdgesCountInFullGraph(nodesCount), graph.getEdgesCount());
	}
}
