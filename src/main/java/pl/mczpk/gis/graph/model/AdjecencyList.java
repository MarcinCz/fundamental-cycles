package pl.mczpk.gis.graph.model;

import java.util.ArrayList;
import java.util.List;

public class AdjecencyList {
	private List<Node> nodes = new ArrayList<Node>();

	public List<Node> getNodes() {
		return nodes;
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}
}
