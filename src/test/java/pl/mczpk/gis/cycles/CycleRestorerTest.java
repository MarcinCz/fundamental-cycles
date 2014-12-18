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
	
	@Test
	public void testForOneCycle() {
		List<Edge> edges = getEdges();
		
		List<Cycle> cycles = testee.restoreCycles(edges);
		
		assertEquals(1, cycles.size());
		assertCycleIsEqualToGivenNodes(cycles.get(0), n1, n2, n3, n6, n5, n4);
	}

	public List<Edge> getEdges() {
		
		setParentLine(n1, n2, n3);
		setParentLine(n1, n4, n5, n6);
		
		return Arrays.asList(new Edge(n3, n6));
	}
	
	public void setParentLine(Node... nodes) {
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
