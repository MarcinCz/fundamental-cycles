package pl.mczpk.gis.IO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import pl.mczpk.gis.cycles.Cycle;
import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Node;

public class ResultWriter {

	static public void writeGraphToFile(Graph graph, String fileName){
		String message = "Adjacency lists of nodes in created graph:\n";
		if (fileName == null || fileName.trim().length() == 0)
			fileName = "graph.result";
		else
			fileName += ".result";
		
		for(Node node : graph.getNodes()){
			message += node.toString() + "\t";
			
			String adjacencyList = "";
			for(Node node2 : graph.getAdjecencyListForNode(node).getNodes()){
				adjacencyList += node2.toString() + ",";
			}
			message += adjacencyList.substring(0, adjacencyList.length() - 1);
			message += "\n";
		}
		message += "\n";
		
		try {
			Writer writer = new FileWriter(fileName);
			
			writer.write(message);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println(message);
	}
	
	static public void writeCyclesToFile(List<Cycle> cycles, String fileName, boolean isBreadthSearch){
		String message = "";
		if (fileName == null || fileName.trim().length() == 0)
			fileName = "graph.result";
		else
			fileName += ".result";
		
		if(isBreadthSearch)
			message += "All fundamental cycles found using Breadth First Search:\n";
		else
			message += "All fundamental cycles found using Deep First Search:\n";
		
		for(Cycle cycle : cycles){
			message += cycle.toString() + "\n";
		}
		message += "\n";
		
		try {
			Writer writer = new FileWriter(fileName, true);
			writer.write(message);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(message);
	}
	
	static public void writeTestData(int iteration, boolean result, String fileName){
		String message = "Iteration " + iteration + " - test result was ";
		message += result ? "positive" : "negative";
		message += "\n";
		
		if (fileName == null || fileName.trim().length() == 0)
			fileName = "graph.result";
		else
			fileName += ".result";

		message += "\n";
		
		try {
			Writer writer = new FileWriter(fileName, true);
			writer.write(message);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(message);
	}
}
