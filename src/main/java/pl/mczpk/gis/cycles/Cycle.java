package pl.mczpk.gis.cycles;

import java.util.ArrayList;
import java.util.List;

import pl.mczpk.gis.graph.model.Node;

public class Cycle {
	private List<Node> nodes = new ArrayList<Node>();

	public List<Node> getNodes() {
		return nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}
}
