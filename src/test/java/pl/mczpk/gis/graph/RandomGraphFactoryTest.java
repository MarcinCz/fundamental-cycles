package pl.mczpk.gis.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.mczpk.gis.graph.model.Node;

public class RandomGraphFactoryTest {
	
	private RandomGraphFactory testee = RandomGraphFactory.getInstance();
	
	@Test
	public void testGeneratedFullGraph() {
		Graph generatedGraph = testee.getGraph(10, 1.0f);
		
		assertEquals(10, generatedGraph.getNodesCount());
		for(Node node: generatedGraph.getNodes()) {
			assertEquals(9, generatedGraph.getAdjecencyListForNode(node).getNodes().size());
			assertFalse(generatedGraph.getAdjecencyListForNode(node).getNodes().contains(node));
		}
	}
	
	@Test
	public void testGeneratedGraphWithoutEdges() {
		Graph generatedGraph = testee.getGraph(10, 0.0f);
		
		assertEquals(10, generatedGraph.getNodesCount());
		for(Node node: generatedGraph.getNodes()) {
			assertEquals(0, generatedGraph.getAdjecencyListForNode(node).getNodes().size());	
		}
	}
	
	@Test
	public void testGeneratedGraphWithoutNodes() {
		Graph generatedGraph = testee.getGraph(0, 0.0f);
		
		assertEquals(0, generatedGraph.getNodesCount());
	}
	
	@Test
	public void testGeneratedGraphWithRandomEdges() {
		Graph generatedGraph = testee.getGraph(10, 0.5f);
		
		assertEquals(10, generatedGraph.getNodesCount());
		for(Node node: generatedGraph.getNodes()) {
			for(Node adjecentNode: generatedGraph.getAdjecencyListForNode(node).getNodes()) {
				assertTrue(generatedGraph.getAdjecencyListForNode(adjecentNode).getNodes().contains(node));
			}
		}
	}
}