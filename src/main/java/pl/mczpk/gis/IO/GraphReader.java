package pl.mczpk.gis.IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.mczpk.gis.graph.Graph;
import pl.mczpk.gis.graph.model.Edge;
import pl.mczpk.gis.graph.model.Node;

public class GraphReader {

	static private class NodeDetails {
		private String node;
		private String[] adjacencyList;
		
		public NodeDetails(String node, String[] adjacencyList){
			setNode(node);
			setAdjacencyList(adjacencyList);
		}
		public String getNode() {
			return node;
		}
		public void setNode(String node) {
			this.node = node;
		}
		public String[] getAdjacencyList() {
			return adjacencyList;
		}
		public void setAdjacencyList(String[] adjacencyList) {
			this.adjacencyList = adjacencyList;
		}
		
	}
	
	static public Graph readGraphFromFile(String fileName){
		Graph graph = new Graph();

		List<NodeDetails> nodesList = new ArrayList<NodeDetails>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String currentLine;
			
			while ((currentLine = br.readLine()) != null) {
				String[] node = currentLine.split("\t");
				String[] adjacencyList = node[1].split(",");
				nodesList.add(new NodeDetails(node[0], adjacencyList));
			}
		} catch (FileNotFoundException e1) {
			throw new IllegalStateException("File does not exist");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(NodeDetails nodeDetails : nodesList){
			graph.addNode(new Node(nodeDetails.getNode()));
		}
		for(NodeDetails nodeDetails : nodesList){
			for(String adjacentNode : nodeDetails.getAdjacencyList()){
				graph.addEdge(new Edge(getNode(graph, nodeDetails.getNode()), getNode(graph, adjacentNode)));
			}
		}
		return graph;
	}
	
	static private Node getNode(Graph graph, String id){
		for (Node node : graph.getNodes()){
			if (node.getId().equals(id))
				return node;
		}
		return null;
	}
	
	
}
