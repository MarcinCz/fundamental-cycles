package pl.mczpk.gis.graph;

import static org.junit.Assert.*;

public class GraphFactoryTestBase {

	protected void assertEdgesCountForFullGraph(Graph graph) {
		int nodesCount = graph.getNodesCount();
		assertEquals((nodesCount * (nodesCount - 1)/2), graph.getEdgesCount());
	}
}
