package pl.mczpk.gis.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pl.mczpk.gis.graph.model.AdjecencyList;
import pl.mczpk.gis.graph.model.Node;

public class Graph {
	private Map<Node, AdjecencyList> adjecencyListsMap = new HashMap<Node, AdjecencyList>();

	public int getNodesCount() {
		return adjecencyListsMap.size();
	}

	public void setAdjecencyListForNode(Node node, AdjecencyList adjecencyList) {
		this.adjecencyListsMap.put(node, adjecencyList);
	}
	
	public AdjecencyList getAdjecencyListForNode(Node node) {
		return adjecencyListsMap.get(node);
	}
	
	public Set<Node> getNodes() {
		return adjecencyListsMap.keySet();
	}
}
