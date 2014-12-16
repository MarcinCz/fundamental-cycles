package pl.mczpk.gis.graph.model;


public class Edge {
	private Node firstNode;
	private Node secondNode;
	
	public Edge(Node firstNode, Node secondNode) {
		this.firstNode = firstNode;
		this.secondNode = secondNode;
	}
	
	public Node getFirstNode() {
		return firstNode;
	}
	public void setFirstNode(Node firstNode) {
		this.firstNode = firstNode;
	}
	public Node getSecondNode() {
		return secondNode;
	}
	public void setSecondNode(Node secondNode) {
		this.secondNode = secondNode;
	}
}
