package pl.mczpk.gis;

import java.util.List;

import org.junit.Test;

import pl.mczpk.gis.IO.GraphReader;
import pl.mczpk.gis.cycles.Cycle;
import pl.mczpk.gis.cycles.CycleRestorer;
import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.search.BreadthFirstSearch;
import pl.mczpk.gis.search.DeepFirstSearch;
import pl.mczpk.gis.search.model.GraphSearchResult;

public class AppTest 
{

	@Test
    public void testApp()
    {
       Graph graph = GraphReader.readGraphFromFile("src/test/resources/appTest.graph");
       DeepFirstSearch dfs = new DeepFirstSearch();
       BreadthFirstSearch bfs = new BreadthFirstSearch();
       
       CycleRestorer cr = new CycleRestorer();
       GraphSearchResult dfsResult = dfs.searchGraph(graph, graph.getNodeById("2"));
       List<Cycle> cyclesDeep = cr.restoreCycles(dfsResult.getEdgesNotInTree());
       GraphSearchResult bfsResult = bfs.searchGraph(graph, graph.getNodeById("2"));
       List<Cycle> cyclesBreadth = cr.restoreCycles(bfsResult.getEdgesNotInTree());
       
		       
       System.out.println("BFS result:");
       for(Cycle cycle: cyclesBreadth) {
    	   System.out.println(cycle);
       }
       
       System.out.println("DFS result:");
       for(Cycle cycle: cyclesDeep) {
    	   System.out.println(cycle);
       }
    }
}
