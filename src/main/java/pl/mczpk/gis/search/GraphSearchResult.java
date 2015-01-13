package pl.mczpk.gis.search;

import java.util.ArrayList;
import java.util.List;

import pl.mczpk.gis.graph.model.Edge;

public class GraphSearchResult {
	private List<Edge> edgesNotInTree = new ArrayList<Edge>();
	private long executionTime;
	
	public List<Edge> getEdgesNotInTree() {
		return edgesNotInTree;
	}

	void addEdgeNotInTree(Edge edgeNotInTree) {
		this.edgesNotInTree.add(edgeNotInTree);
	}
	
	/**
	 * Returns execution time in milliseconds
	 * @return
	 */
	public long getExecutionTime() {
		return executionTime;
	}
	
	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
}
