package pl.mczpk.gis.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.util.GraphUtils;

public class RandomGraphFactoryTest extends GraphFactoryTestBase {
	
	private RandomGraphFactory testee = RandomGraphFactory.getInstance();
	
	@Test
	public void testGeneratedFullGraph() {
		int edgesCount = 1000;
		Graph generatedGraph = testee.getGraph(edgesCount, GraphUtils.getEdgesCountInFullGraph(edgesCount));
		
		assertEquals(edgesCount, generatedGraph.getNodesCount());
		assertEquals(GraphUtils.getEdgesCountInFullGraph(edgesCount), generatedGraph.getEdgesCount());

		for(Node node: generatedGraph.getNodes()) {
			assertEquals(edgesCount - 1, generatedGraph.getAdjecencyListForNode(node).getNodes().size());
			assertFalse(generatedGraph.areNodesAdjectent(node, node));
		}
	}
	
	@Test
	public void testGeneratedTree() {
		int edgesCount = 10;
		Graph generatedGraph = testee.getGraph(edgesCount, GraphUtils.getEdgesCountInTree(edgesCount));
		
		assertEquals(edgesCount, generatedGraph.getNodesCount());
		assertEquals(GraphUtils.getEdgesCountInTree(edgesCount), generatedGraph.getEdgesCount());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateForTooFewEdges() {
		testee.getGraph(10, GraphUtils.getEdgesCountInTree(10) - 1);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGenerateForTooManyEdges() {
		testee.getGraph(10, GraphUtils.getEdgesCountInFullGraph(10) + 1);
	}
	
	@Test
	public void testGeneratedGraphStructure() {
		Graph generatedGraph = testee.getGraph(10, 30);
		
		assertEquals(10, generatedGraph.getNodesCount());
		assertEquals(30, generatedGraph.getEdgesCount());
		
		for(Node node: generatedGraph.getNodes()) {
			for(Node adjecentNode: generatedGraph.getAdjecencyListForNode(node).getNodes()) {
				assertTrue(generatedGraph.areNodesAdjectent(node, adjecentNode));
			}
		}
	}
}
