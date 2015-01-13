package pl.mczpk.gis.search;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.graph.model.NodeState;

public abstract class AbstractGraphSearch implements GraphSearch {

	protected final static int ROOT_NODE_LEVEL = 0;
	protected GraphSearchResult searchResult;

	public GraphSearchResult searchGraph(Graph graph, Node startingNode) {
		long startTime = System.currentTimeMillis();
		searchResult = new GraphSearchResult();
		
		doSearchGraph(graph, startingNode);
		
		long endTime = System.currentTimeMillis();
		searchResult.setExecutionTime(endTime - startTime);
		return searchResult;
	}
	
	protected abstract void doSearchGraph(Graph graph, Node startingNode);
	
	protected void prepareStartingNode(Node startingNode) {
		startingNode.setState(NodeState.VISITED);
		startingNode.setLevel(ROOT_NODE_LEVEL);
		startingNode.setParent(startingNode);
	}

	protected void markAllNodesAsNotVisited(Graph graph) {
		for (Node node : graph.getNodes()) {
			node.setState(NodeState.NOT_VISITIED);
		}
	}

	protected void tryToAddEdgeNotInTree(Node firstNode, Node secondNode) {
		if (firstNode.getId().compareTo(secondNode.getId()) > 0) {
			searchResult.addEdgeNotInTree(new Edge(firstNode, secondNode));
		}
	}

	protected void markNodeAsVisitedFromParentNode(Node node, Node parentNode) {
		node.setState(NodeState.VISITED);
		node.setLevel(parentNode.getLevel() + 1);
		node.setParent(parentNode);
	}

}
