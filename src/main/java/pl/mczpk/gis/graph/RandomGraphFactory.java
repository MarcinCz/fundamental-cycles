package pl.mczpk.gis.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.util.GraphUtils;

public class RandomGraphFactory {

	private final Random randomGenerator = new Random();
	private final static RandomGraphFactory INSTANCE = new RandomGraphFactory();
	
	private final Logger logger = Logger.getLogger(this.getClass());

	private RandomGraphFactory() {
	}

	public static RandomGraphFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Creates new random connected graph
	 * 
	 * @param nodesToGenerate
	 *            generated graph will have this number of nodes
	 * @param edgesToGenerate
	 *            generated graph will have this number of edges
	 */
	public Graph getGraph(int nodesToGenerate, int edgesToGenerate) {
		verifyParameters(nodesToGenerate, edgesToGenerate);
		
		Graph graph = new Graph();
		
		addNodesToGraph(graph, nodesToGenerate);
		generateSpanningTree(graph);
		generateRandomEdgesNotInTree(graph, edgesToGenerate - GraphUtils.getEdgesCountInTree(nodesToGenerate));
		
		logger.debug("Random graph generated: \n" + graph);
		return graph;
	}
	

	private void verifyParameters(int nodesToGenerate, int edgesToGenerate) {
		if(edgesToGenerate > GraphUtils.getEdgesCountInFullGraph(nodesToGenerate) || edgesToGenerate < GraphUtils.getEdgesCountInTree(nodesToGenerate)) {
			throw new IllegalArgumentException("Can't create connected graph for " + nodesToGenerate + " nodes and " + edgesToGenerate + " edges.");
		}
	}
	
	private void addNodesToGraph(Graph graph, int nodesToGenerate) {
		for (int nodeNumber = 0; nodeNumber < nodesToGenerate; ++nodeNumber) {
			Node node = new Node(String.valueOf(nodeNumber));
			graph.addNode(node);
		}
	}
	
	private void generateSpanningTree(Graph graph) {
		List<Node> nodes = new ArrayList<Node>(graph.getNodes());
		List<Node> nodesToAdd = new ArrayList<Node>(nodes);
		List<Node> nodesAdded = new ArrayList<Node>();
		
		Node currentNode = getRandomNode(nodesToAdd);
		nodesToAdd.remove(currentNode);
		nodesAdded.add(currentNode);
		
		while(!nodesToAdd.isEmpty()) {
			Node adjecentNode = getRandomNode(nodes);
			if(!nodesAdded.contains(adjecentNode)) {
				graph.addEdge(new Edge(currentNode, adjecentNode));
				nodesToAdd.remove(adjecentNode);
				nodesAdded.add(adjecentNode);
			}
			currentNode = adjecentNode;
		}
	}
	
	private Node getRandomNode(List<Node> nodes) {
		int nodeNumber = randomGenerator.nextInt(nodes.size());
		return nodes.get(nodeNumber);
	}
	

	private void generateRandomEdgesNotInTree(Graph graph, int edgesToGenerate) {
		while(edgesToGenerate-- > 0) {
			
			Node firstNode;
			while(true) {
				firstNode = graph.getRandomNode();
				if(graph.getAdjecencyListSizeForNode(firstNode) < graph.getNodesCount() - 1) {
					break;
				}
			}
			
			Node secondNode;
			while(true) {
				secondNode = graph.getRandomNode();
				if(!firstNode.equals(secondNode) && !graph.areNodesAdjectent(firstNode, secondNode)) {
					graph.addEdge(new Edge(firstNode, secondNode));
					break;
				}
			}
		}
	}
}
