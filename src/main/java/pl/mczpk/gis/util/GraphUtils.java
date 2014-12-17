package pl.mczpk.gis.util;

import pl.mczpk.gis.graph.Graph;

public class GraphUtils {

	public static int getCyclomaticNumberForGraph(Graph graph) {
		return graph.getEdgesCount() - graph.getNodesCount() + 1;
	}

	public static int getEdgesCountInFullGraph(int nodesCount) {
		return (nodesCount * (nodesCount - 1) / 2);
	}

	public static int getEdgesCountInTree(int nodesCount) {
		return nodesCount - 1;
	}
}
