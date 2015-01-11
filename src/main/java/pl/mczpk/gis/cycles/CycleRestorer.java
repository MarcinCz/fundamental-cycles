package pl.mczpk.gis.cycles;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;

public class CycleRestorer {

	private final Logger logger = Logger.getLogger(this.getClass());

	public List<Cycle> restoreCycles(List<Edge> edges) {
		List<Cycle> cycles = new ArrayList<Cycle>();
		
		for(Edge edge: edges) {
			cycles.add(restoreCycle(edge)); 
		}
		return cycles;
	}

	private Cycle restoreCycle(Edge edge) {
		Cycle cycle = new Cycle();
		Node firstNode = edge.getFirstNode();
		Node secondNode = edge.getSecondNode();
		
		cycle.addNodeAtFront(firstNode);
		cycle.addNodeAtEnd(secondNode);
		
		while(firstNode.getLevel() > secondNode.getLevel()) {
			firstNode = firstNode.getParent();
			cycle.addNodeAtFront(firstNode);
		}
		
		while(secondNode.getLevel() > firstNode.getLevel()) {
			secondNode = secondNode.getParent();
			cycle.addNodeAtEnd(secondNode);
		}
		
		if(firstNode.equals(secondNode)) {
			//one of the nodes in the edge was cycle root node
			cycle.removeNodeAtFront();
		} else {
			//still need to find cycle root node
			while(secondNode.getParent() != firstNode.getParent()) {
				firstNode = firstNode.getParent();
				cycle.addNodeAtFront(firstNode);
				secondNode = secondNode.getParent();
				cycle.addNodeAtEnd(secondNode);
			}
			
			cycle.addNodeAtFront(firstNode.getParent());
		}
		
		logger.trace("Restored cycle: " + cycle);
		return cycle;
	}
	
}
