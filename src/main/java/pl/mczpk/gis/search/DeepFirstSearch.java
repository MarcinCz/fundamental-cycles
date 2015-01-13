package pl.mczpk.gis.search;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.graph.model.NodeState;

public class DeepFirstSearch extends AbstractGraphSearch {

	private int searchLevel;
	private Graph graph;
	
	@Override
	protected void doSearchGraph(Graph graph, Node startingNode) {
		this.graph = graph;
		
		markAllNodesAsNotVisited(graph);
		prepareStartingNode(startingNode);
		
		for(Node nodeToVisit: graph.getAdjecencyListForNode(startingNode).getNodes()) {
			if(nodeToVisit.getState().equals(NodeState.NOT_VISITIED)) {
				visitNode(nodeToVisit, startingNode, searchLevel);
			} else {
				tryToAddEdgeNotInTree(nodeToVisit, startingNode);
			}
		}
	}

	private void visitNode(Node node, Node parentNode, int searchLevel) {
		++searchLevel;
		markNodeAsVisitedFromParentNode(node, parentNode);
		for(Node nodeToVisit: graph.getAdjecencyListForNode(node).getNodes()) {
			if(node.getParent().equals(nodeToVisit)) {
				continue;
			}
			
			if(nodeToVisit.getState().equals(NodeState.NOT_VISITIED)) {
				visitNode(nodeToVisit, node, searchLevel);
			} else {
				tryToAddEdgeNotInTree(nodeToVisit, node);
			}
		}
	}
}
