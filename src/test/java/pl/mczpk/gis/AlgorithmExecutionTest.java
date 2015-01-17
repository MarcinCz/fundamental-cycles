package pl.mczpk.gis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import pl.mczpk.gis.cycles.Cycle;
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
	private final static int TEST_ITERATIONS = 200;
	
	/**
	 * Liczba wierzchołków w generowanych grafach
	 */
	private final static int NODES_IN_GENERATED_GRAPH = 10000;
	private final static int ITERATION_STEP = 20;

	private Random random = new Random();
//	private int minEdges = GraphUtils.getEdgesCountInTree(NODES_IN_GENERATED_GRAPH);
//	private int maxEdges = GraphUtils.getEdgesCountInFullGraph(NODES_IN_GENERATED_GRAPH);
	private CycleRestorer cr = new CycleRestorer();
	
	private interface AlgorithmExecutionTime {
		public double getSearchExecutionTime();
		public double getFullExecutionTime();
		public double getCycleLength();
	}
	
	/**
	 * Test uruchamia algorytm wyszukiwania cykli fundamentalnych dla wyszukiwania wszerze i w głąb.
	 * Dla podanej liczby wierzchołków generowanie są grafy spójne z losową liczbą krawędzi.
	 * Algorytm jest uruchamiany określoną liczbę razy po czym wypisuje średnie czasy wykonania algorytmu w milisekundach.
	 */
	@Test
	public void testExecutionTimes() {
		List<Double[]> results = new ArrayList<Double[]>();
		
		for (int iter = ITERATION_STEP; iter <= NODES_IN_GENERATED_GRAPH; iter += ITERATION_STEP)
		{
		long bfsSearchTimeSum = 0;
		long bfsFullTimeSum = 0;
		double bfsCycleLength = 0;
		long dfsSearchTimeSum = 0;
		long dfsFullTimeSum = 0;
		double dfsCycleLength = 0;
		
		int minEdges = GraphUtils.getEdgesCountInTree(iter);
		int maxEdges = GraphUtils.getEdgesCountInFullGraph(iter);
		
		for(int i = 0; i < TEST_ITERATIONS; i++) {
			int edgesToGenerate = minEdges + random.nextInt(maxEdges - minEdges) + 1;
			Graph graph = RandomGraphFactory.getInstance().getGraph(iter, edgesToGenerate);
			
			AlgorithmExecutionTime bfsExecutionTime = testForGraphSearch(new BreadthFirstSearch(), graph);
			bfsSearchTimeSum += bfsExecutionTime.getSearchExecutionTime();
			bfsFullTimeSum += bfsExecutionTime.getFullExecutionTime();
			bfsCycleLength += bfsExecutionTime.getCycleLength();
			
//			AlgorithmExecutionTime dfsExecutionTime = testForGraphSearch(new DeepFirstSearch(), graph);
//			dfsSearchTimeSum += dfsExecutionTime.getSearchExecutionTime();
//			dfsFullTimeSum += dfsExecutionTime.getFullExecutionTime();
//			dfsCycleLength += dfsExecutionTime.getCycleLength();
			
//			System.out.println("Iteration " + (i + 1)  + " finished");
		}
		
		Double[] res = {(double) iter, (double) bfsSearchTimeSum / TEST_ITERATIONS, (double) bfsFullTimeSum / TEST_ITERATIONS, bfsCycleLength / TEST_ITERATIONS, (double) dfsSearchTimeSum / TEST_ITERATIONS, (double) dfsFullTimeSum / TEST_ITERATIONS, dfsCycleLength / TEST_ITERATIONS};
		results.add(res);
		System.out.println("Iteration " + iter  + " finished");
//		System.out.println("BFS mean search execution time: " + (double) bfsSearchTimeSum / TEST_ITERATIONS);
//		System.out.println("BFS mean full algorithm execution time: " + (double) bfsFullTimeSum / TEST_ITERATIONS);
//		System.out.println("DFS mean search execution time: " + (double) dfsSearchTimeSum / TEST_ITERATIONS);
//		System.out.println("DFS mean full algorithm execution time: " + (double) dfsFullTimeSum / TEST_ITERATIONS);
		}
		
		for (Double[] res : results){
			for(int i = 0; i < res.length; ++i)
				System.out.print(res[i] + "\t");
			System.out.print("\n");
		}
		
	}
	
	private AlgorithmExecutionTime testForGraphSearch(GraphSearch search, Graph generatedGraph) {

		GraphSearchResult searchResult = search.searchGraph(generatedGraph, generatedGraph.getRandomNode());
		long startTime = System.currentTimeMillis();
		List<Cycle> cycleList = cr.restoreCycles(searchResult.getEdgesNotInTree());
		long crTime = System.currentTimeMillis() - startTime;
		
		Long fullCyclesLength = 0L;
		for (Cycle cycle : cycleList)
			fullCyclesLength += cycle.getNodes().size();
		
		final long searchTime = searchResult.getExecutionTime();
		final long fullTime = searchResult.getExecutionTime() + crTime;
		final double avgCycleLength = fullCyclesLength/cycleList.size();
		
		return new AlgorithmExecutionTime() {
			
			@Override
			public double getSearchExecutionTime() {
				return searchTime;
			}
			
			@Override
			public double getFullExecutionTime() {
				return fullTime;
			}

			@Override
			public double getCycleLength() {
				return avgCycleLength;
			}
		};
	}

}
