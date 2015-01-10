package pl.mczpk.gis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.mczpk.gis.IO.GraphReader;
import pl.mczpk.gis.IO.ResultWriter;
import pl.mczpk.gis.cycles.Cycle;
import pl.mczpk.gis.cycles.CycleRestorer;
import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.RandomGraphFactory;
import pl.mczpk.gis.search.BreadthFirstSearch;
import pl.mczpk.gis.search.DeepFirstSearch;
import pl.mczpk.gis.util.GraphUtils;

public class App 
{
	/*
	 * -f - flaga oznaczająca nazwę pliku wejściowego
	 * 
	 * Przykłady wywołań programu:
	 * prog -f my_graph.graph
	 * prog 10 15 0.5	gdzie 10(liczba wierzchołków), 15(liczba iteracji testu), 0.5(prawdopodobieństwo wystąpienia krawędzi)
	 */
	
    public static void main( String[] args ) throws IOException
    {
    	String flags = "";
    	String fileName = "";
    	List<String> ints = new ArrayList<String>(); //1-numberOfNodes, 2-numberOfIterations
    	String probabilityOfEdge = "";
    	
    	for (int i = 0; i < args.length; ++i){
    		if (args[i].matches("-[a-zA-Z]+"))
    				flags = args[i];
    		else if (args[i].matches(".+\\.graph"))
    			fileName = args[i];
    		else if (args[i].matches("\\d+"))
				ints.add(args[i]);
    		else if (args[i].matches("0\\.\\d+"))
				probabilityOfEdge = args[i];
    	}
    	
    	Graph graph;
    	CycleRestorer cr = new CycleRestorer();
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		DeepFirstSearch dfs = new DeepFirstSearch();
		
		if (!flags.contains("f")){
			if (ints.size() != 2)
				throw new IllegalStateException("Not enought arguments");
			if (ints.get(0).equals("") || ints.get(0).equals("0"))
    			throw new IllegalStateException("Number of nodes is incorrect");
			if (ints.get(1).equals("") || ints.get(1).equals("0"))
    			throw new IllegalStateException("Number of iterations is incorrect");
    		if (probabilityOfEdge.equals("") || probabilityOfEdge.equals(""))
    			throw new IllegalStateException("Probability of edge is incorrect");
    		
			 File dir = new File("results");
			 dir.mkdir();
			 
			for(int i = 0; i < Integer.parseInt(ints.get(1)); ++i ){
				fileName = "results/" + java.lang.System.currentTimeMillis();
				graph = RandomGraphFactory.getInstance().getGraph(Integer.parseInt(ints.get(0)), Float.parseFloat(probabilityOfEdge));
				
				List<Cycle> cyclesBreadth = cr.restoreCycles(bfs.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree());
				List<Cycle> cyclesDeep = cr.restoreCycles(dfs.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree());
				
				boolean result = GraphUtils.getCyclomaticNumberForGraph(graph) == cyclesBreadth.size() && GraphUtils.getCyclomaticNumberForGraph(graph) == cyclesDeep.size(); 
				ResultWriter.writeTestData(i, result, fileName);
				ResultWriter.writeGraphToFile(graph, fileName);
				
				ResultWriter.writeCyclesToFile(cyclesBreadth , fileName, true);
				ResultWriter.writeCyclesToFile(cyclesDeep , fileName, false);
			}
		}
		else {
			if (fileName.equals(""))
    			throw new IllegalStateException("File name is incorrect");
    		
    		graph = GraphReader.readGraphFromFile(fileName);
    		List<Cycle> cyclesBreadth = cr.restoreCycles(bfs.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree());
    		List<Cycle> cyclesDeep = cr.restoreCycles(dfs.searchGraph(graph, graph.getRandomNode()).getEdgesNotInTree());
    		
			ResultWriter.writeCyclesToFile(cyclesBreadth , fileName, true);
			ResultWriter.writeCyclesToFile(cyclesDeep , fileName, false);
		}
    	
    }
}
