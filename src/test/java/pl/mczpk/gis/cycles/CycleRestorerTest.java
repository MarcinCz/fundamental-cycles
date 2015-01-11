package pl.mczpk.gis.cycles;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;

public class CycleRestorerTest {

	CycleRestorer testee = new CycleRestorer();
	
	private final Node n1 = new Node("1");
	private final Node n2 = new Node("2");
	private final Node n3 = new Node("3");
	private final Node n4 = new Node("4");
	private final Node n5 = new Node("5");
	private final Node n6 = new Node("6");
	private final Node n7 = new Node("7");

	
	@Test
	public void testForOneCycleFromTwoLines() {
		List<Edge> edges = getEdgesTwoLines();
		
		List<Cycle> cycles = testee.restoreCycles(edges);
		
		assertEquals(1, cycles.size());
		System.out.println("Restored cycle " + cycles.get(0));
		assertCycleIsEqualToGivenNodes(cycles.get(0), n1, n2, n3, n6, n5, n4);
	}
	
	@Test
	public void testForOneCycleFromOneLine() {
		List<Edge> edges = getEdgesOneLine();
		
		List<Cycle> cycles = testee.restoreCycles(edges);
		
		assertEquals(1, cycles.size());
		System.out.println("Restored cycle " + cycles.get(0));
		assertCycleIsEqualToGivenNodes(cycles.get(0), n2, n3, n4, n1);
	}

	public List<Edge> getEdgesTwoLines() {
		
		setParentLine(n1, n2, n3);
		setParentLine(n1, n4, n5, n6);
		
		return Arrays.asList(new Edge(n3, n6));
	}
	
	public List<Edge> getEdgesOneLine() {
		
		setParentLine(n1, n2, n3, n4);
		
		return Arrays.asList(new Edge(n4, n1));
	}
	
	public void setParentLine(Node... nodes) {
		nodes[0].setParent(n7);
		for(int i = 1; i < nodes.length; i++) {
			nodes[i].setLevel(i);
			nodes[i].setParent(nodes[i - 1]);
		}
	}
	
	private void assertCycleIsEqualToGivenNodes(Cycle cycle, Node... nodes) {
		assertEquals(nodes.length, cycle.getNodes().size());
		
		for(int i = 0; i < nodes.length; i ++) {
			assertEquals(nodes[i], cycle.getNodes().get(i));
		}
	}
}
