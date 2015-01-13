package pl.mczpk.gis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import pl.mczpk.gis.cycles.CycleRestorer;
import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.RandomGraphFactory;
import pl.mczpk.gis.search.BreadthFirstSearch;
import pl.mczpk.gis.search.DeepFirstSearch;
import pl.mczpk.gis.search.GraphSearch;
import pl.mczpk.gis.search.GraphSearchResult;
import pl.mczpk.gis.util.GraphUtils;

public class AlgorithmExecutionTest {

	/**
	 * Liczba iteracji testu
	 */
	private final static int TEST_ITERATIONS = 100;
	
	/**
	 * Liczba wierzchołków w generowanych grafach
	 */
	private final static int NODES_IN_GENERATED_GRAPH = 500;

	private Random random = new Random();
	private int minEdges = GraphUtils.getEdgesCountInTree(NODES_IN_GENERATED_GRAPH);
	private int maxEdges = GraphUtils.getEdgesCountInFullGraph(NODES_IN_GENERATED_GRAPH);
	private CycleRestorer cr = new CycleRestorer();
	
	private interface AlgorithmExecutionTime {
		public double getSearchExecutionTime();
		public double getFullExecutionTime();
	}
	
	/**
	 * Test uruchamia algorytm wyszukiwania cykli fundamentalnych dla wyszukiwania wszerze i w głąb.
	 * Dla podanej liczby wierzchołków generowanie są grafy spójne z losową liczbą krawędzi.
	 * Algorytm jest uruchamiany określoną liczbę razy po czym wypisuje średnie czasy wykonania algorytmu w milisekundach.
	 */
	@Test
	public void testExecutionTimes() {
		List<Graph> generatedGraphs = new ArrayList<Graph>();
		
		for(int i = 0; i < TEST_ITERATIONS; i++) {
			int edgesToGenerate = minEdges + random.nextInt(maxEdges - minEdges) + 1;
			generatedGraphs.add(RandomGraphFactory.getInstance().getGraph(NODES_IN_GENERATED_GRAPH, edgesToGenerate));
		}
		AlgorithmExecutionTime bfsExecutionTime = testForGraphSearch(new BreadthFirstSearch(), generatedGraphs);
		AlgorithmExecutionTime dfsExecutionTime = testForGraphSearch(new DeepFirstSearch(), generatedGraphs);
		
		System.out.println("BFS mean search execution time: " + bfsExecutionTime.getSearchExecutionTime());
		System.out.println("BFS mean full algorithm execution time: " + bfsExecutionTime.getFullExecutionTime());
		System.out.println("DFS mean search execution time: " + dfsExecutionTime.getSearchExecutionTime());
		System.out.println("DFS mean full algorithm execution time: " + dfsExecutionTime.getFullExecutionTime());
	}
	
	private AlgorithmExecutionTime testForGraphSearch(GraphSearch search, List<Graph> generatedGraphs) {
		long searchTimeSum = 0; 
		long fullTimeSum = 0; 

		long startTime;
		long crTime;
		for(int i=0;i<generatedGraphs.size(); ++i) {
			GraphSearchResult searchResult = search.searchGraph(generatedGraphs.get(i), generatedGraphs.get(i).getRandomNode());
			startTime = System.currentTimeMillis();
			cr.restoreCycles(searchResult.getEdgesNotInTree());
			crTime = System.currentTimeMillis() - startTime;
			
			searchTimeSum += searchResult.getExecutionTime();
			fullTimeSum += searchResult.getExecutionTime() + crTime;

			System.out.println("Iteration " + i + " done");
		}
		
		final double searchTime = (double) searchTimeSum / TEST_ITERATIONS;
		final double fullTime = (double) fullTimeSum / TEST_ITERATIONS;
		
		return new AlgorithmExecutionTime() {
			
			@Override
			public double getSearchExecutionTime() {
				return searchTime;
			}
			
			@Override
			public double getFullExecutionTime() {
				return fullTime;
			}
		};
	}

}
