package pl.mczpk.gis.search.model;

import java.util.ArrayList;
import java.util.List;

import pl.mczpk.gis.graph.model.Edge;

public class GraphSearchResult {
	private List<Edge> edgesNotInTree = new ArrayList<Edge>();

	public List<Edge> getEdgesNotInTree() {
		return edgesNotInTree;
	}

	public void addEdgeNotInTree(Edge edgeNotInTree) {
		this.edgesNotInTree.add(edgeNotInTree);
	}
}
