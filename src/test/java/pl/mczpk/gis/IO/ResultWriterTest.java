package pl.mczpk.gis.IO;

import java.util.List;

import org.junit.Test;

import pl.mczpk.gis.cycles.Cycle;
import pl.mczpk.gis.cycles.CycleRestorer;
import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;
import pl.mczpk.gis.search.BreadthFirstSearch;

public class ResultWriterTest {

	@Test
	public void shouldWriteGraphToFile(){
		Node a = new Node("1");
		Node b = new Node("2");
		Node c = new Node("3");
		
		Graph graph = new Graph();
		
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		
		graph.addEdge(new Edge(a, b));
		graph.addEdge(new Edge(b, c));
		graph.addEdge(new Edge(a, c));
		
		ResultWriter.writeGraphToFile(graph, "src/test/resources/triangle_write_test.graph");
	}
	
	@Test
	public void shouldWriteCyclesToFile(){
		Node a = new Node("1");
		Node b = new Node("2");
		Node c = new Node("3");
		Node d = new Node("4");
		
		Graph graph = new Graph();
		
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		
		graph.addEdge(new Edge(a, b));
		graph.addEdge(new Edge(b, c));
		graph.addEdge(new Edge(a, c));
		graph.addEdge(new Edge(b, d));
		graph.addEdge(new Edge(c, d));
		
		CycleRestorer cr = new CycleRestorer();
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		
		List<Cycle> cycles = cr.restoreCycles(bfs.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree());
		
		ResultWriter.writeCyclesToFile(cycles , "src/test/resources/triangle_write_test2.graph", true);
	}
}
