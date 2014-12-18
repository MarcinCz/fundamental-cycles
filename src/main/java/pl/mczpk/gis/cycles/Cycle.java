package pl.mczpk.gis.cycles;

import java.util.LinkedList;
import java.util.List;

import pl.mczpk.gis.graph.model.Node;

public class Cycle {
	private LinkedList<Node> nodes = new LinkedList<Node>();

	public List<Node> getNodes() {
		return nodes;
	}

	public void addNodeAtEnd(Node node) {
		nodes.add(node);
	}
	
	public void addNodeAtFront(Node node) {
		nodes.push(node);
	}
	
	@Override
	public String toString() {
		return nodes.toString();
	}
}
