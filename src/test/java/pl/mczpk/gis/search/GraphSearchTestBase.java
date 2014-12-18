package pl.mczpk.gis.search;

import junit.framework.TestCase;

import org.junit.Test;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.RandomGraphFactory;
import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.search.model.GraphSearchResult;
import pl.mczpk.gis.util.GraphUtils;

public abstract class GraphSearchTestBase extends TestCase {
	
	protected GraphSearch testee;

	@Test
	public void testEdgesNotInTreeCount_RandomGraph() {
		for(int i = 0; i < 10; ++i) {
			Graph graph = RandomGraphFactory.getInstance().getGraph(100, 150);
			
			GraphSearchResult searchResult = testee.searchGraph(graph, graph.getRandomNode());
			
			assertEquals(GraphUtils.getCyclomaticNumberForGraph(graph), searchResult.getEdgesNotInTree().size());
		}
	}
	
	@Test
	public void testParentConnection_RandomGraph() {
		for(int i = 0; i < 10; ++i) {
			Graph graph = RandomGraphFactory.getInstance().getGraph(100, 150);
			Node startingNode = graph.getRandomNode();
			
			GraphSearchResult searchResult = testee.searchGraph(graph, startingNode);
			
			for(Edge edgeNotInTree: searchResult.getEdgesNotInTree()) {
				assertLastParentForNode(edgeNotInTree.getFirstNode(), startingNode);
				assertLastParentForNode(edgeNotInTree.getSecondNode(), startingNode);

			}
		}
	}
	
	public void assertLastParentForNode(Node node, Node lastParent) {
		while(node.getParent() != lastParent) {
			node = node.getParent();
		}
	}
}
