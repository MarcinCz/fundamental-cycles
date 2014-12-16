package pl.mczpk.gis.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.mczpk.gis.graph.model.AdjecencyList;
import pl.mczpk.gis.graph.model.Node;

public class RandomGraphFactory {

	private Random randomGenerator = new Random();
	private final static RandomGraphFactory INSTANCE = new RandomGraphFactory();
	
	private RandomGraphFactory() {
	}
	
	public static RandomGraphFactory getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Creates new random graph
	 * @param nodesToGenerate
	 * 						generated graph will have this number of nodes
	 * @param edgeProbability
	 * 						edge probability, must be between 0.0f and 1.0f
	 */
	public Graph getGraph(int nodesToGenerate, float edgeProbability) {
		Graph graph = new Graph();
		List<Node> nodes = new ArrayList<Node>();
		Map<Node, AdjecencyList> adjecencyListsMap = new HashMap<Node, AdjecencyList>();
		
		for (int nodeNumber = 0; nodeNumber < nodesToGenerate; ++nodeNumber) {
			Node node = new Node(String.valueOf(nodeNumber));
			nodes.add(node);
			adjecencyListsMap.put(node, new AdjecencyList());
		}
		
		for(int nodeNumber = 0; nodeNumber < nodesToGenerate; ++nodeNumber) {
			for(int adjecentNodeNumber = nodeNumber + 1; adjecentNodeNumber < nodesToGenerate; ++adjecentNodeNumber) {
				if(randomGenerator.nextFloat() <= edgeProbability) {
					Node node = nodes.get(nodeNumber);
					Node adjecentNode = nodes.get(adjecentNodeNumber);
					adjecencyListsMap.get(node).addNode(adjecentNode);
					adjecencyListsMap.get(adjecentNode).addNode(node);
				}
			}
		}
		
		for(Node node: adjecencyListsMap.keySet()) {
			graph.setAdjecencyListForNode(node, adjecencyListsMap.get(node));
		}
		
		return graph;
	}
}
