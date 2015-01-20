package pl.mczpk.gis.cycles;

import java.util.ArrayList;
import java.util.List;

import pl.mczpk.gis.graph.model.Node;

public class Cycle {
	private List<Node> nodes;

	
	public Cycle() {
		this.nodes = new ArrayList<Node>();
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void addNodeAtEnd(Node node) {
		nodes.add(node);
	}
	
	public void addNodeAtFront(Node node) {
		nodes.add(nodes.size(), node);
	}
	
	public Node getNodeAtFront() {
		return nodes.get(0);
	}
	
	public void removeNodeAtFront() {
		nodes.remove(0);
	}
	
	@Override
	public String toString() {
		return nodes.toString();
	}
}
