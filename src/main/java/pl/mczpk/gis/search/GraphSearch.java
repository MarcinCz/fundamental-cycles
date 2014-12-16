package pl.mczpk.gis.search;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.search.model.GraphSearchResult;

public interface GraphSearch {

	public GraphSearchResult searchGraph(Graph graph, Node startingNode);
	
}
