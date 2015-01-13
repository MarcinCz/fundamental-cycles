package pl.mczpk.gis.search;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Node;

public interface GraphSearch {

	public GraphSearchResult searchGraph(Graph graph, Node startingNode);
	
}
