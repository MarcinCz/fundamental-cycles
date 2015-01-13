package pl.mczpk.gis.search;

import java.util.LinkedList;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.graph.model.NodeState;

public class BreadthFirstSearch extends AbstractGraphSearch {

	private LinkedList<Node> queue;
	
	@Override
	protected void doSearchGraph(Graph graph, Node startingNode) {
		markAllNodesAsNotVisited(graph);
		prepareStartingNode(startingNode);

		queue = new LinkedList<Node>();
		queue.add(startingNode);

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			visitAdjecentNodes(graph, node);
		}
	}

	private void visitAdjecentNodes(Graph graph, Node node) {
		for (Node adjecentNode : graph.getAdjecencyListForNode(node).getNodes()) {
			visitNode(adjecentNode, node);
		}
	}

	private void visitNode(Node node, Node parentNode) {
		if(parentNode.getParent().equals(node)) {
			return;
		}
		
		if (node.getState().equals(NodeState.NOT_VISITIED)) {
			markNodeAsVisitedFromParentNode(node, parentNode);
			queue.add(node);
		} else {
			tryToAddEdgeNotInTree(node, parentNode);
		}
	}
}
