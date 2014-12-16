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

	public int getNodesCount() {
		return adjecencyListsMap.size();
	}

	public void addEdge(Edge edge) {
		if(adjecencyListsMap.get(edge.getFirstNode()) == null || adjecencyListsMap.get(edge.getSecondNode()) == null) {
			throw new IllegalStateException("Cannot add edge for nodes which are not in the graph");
		}
		
		adjecencyListsMap.get(edge.getFirstNode()).addNode(edge.getSecondNode());
		adjecencyListsMap.get(edge.getSecondNode()).addNode(edge.getFirstNode());
		++edgesCount;
	}
	
	public void addNode(Node node) {
		adjecencyListsMap.put(node, new AdjecencyList());
	}
	
	public AdjecencyList getAdjecencyListForNode(Node node) {
		return adjecencyListsMap.get(node);
	}
	
	public int getEdgesCount() {
		return edgesCount;
	}
	
	public Set<Node> getNodes() {
		return adjecencyListsMap.keySet();
	}
	
	public Node getRandomNode() {
		int nodeNumber = new Random().nextInt(getNodesCount());
		return new ArrayList<Node>(getNodes()).get(nodeNumber);
	}
}
