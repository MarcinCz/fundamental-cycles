package pl.mczpk.gis.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import pl.mczpk.gis.graph.model.AdjecencyList;
import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;

public class Graph {
	private Map<Node, AdjecencyList> adjecencyListsMap = new HashMap<Node, AdjecencyList>();
	private int edgesCount = 0;

	public void addEdge(Edge edge) {
		if(adjecencyListsMap.get(edge.getFirstNode()) == null || adjecencyListsMap.get(edge.getSecondNode()) == null) {
			throw new IllegalStateException("Cannot add edge for nodes which are not in the graph");
		}
		if(areNodesAdjectent(edge.getFirstNode(), edge.getSecondNode()))
			return;
		
		adjecencyListsMap.get(edge.getFirstNode()).addNode(edge.getSecondNode());
		adjecencyListsMap.get(edge.getSecondNode()).addNode(edge.getFirstNode());
		++edgesCount;
	}

	/**
	 * Adds new node or does nothing if node is already added
	 * @param node
	 */
	public void addNode(Node node) {
		if(!adjecencyListsMap.containsKey(node)) {
			adjecencyListsMap.put(node, new AdjecencyList());
		}
	}
	
	public boolean areNodesAdjectent(Node firstNode, Node secondNode) {
		return getAdjecencyListForNode(firstNode).getNodes().contains(secondNode);
	}
	
	public AdjecencyList getAdjecencyListForNode(Node node) {
		return adjecencyListsMap.get(node);
	}
	
	public int getAdjecencyListSizeForNode(Node node) {
		return getAdjecencyListForNode(node).getNodes().size();
	}
	
	public int getEdgesCount() {
		return edgesCount;
	}
	
	public Set<Node> getNodes() {
		return adjecencyListsMap.keySet();
	}
	
	public int getNodesCount() {
		return adjecencyListsMap.size();
	}
	
	public Node getRandomNode() {
		int nodeNumber = new Random().nextInt(getNodesCount());
		return new ArrayList<Node>(getNodes()).get(nodeNumber);
	}
	
	@Override
	public String toString() {
		return adjecencyListsMap.toString();
	}
	
}
