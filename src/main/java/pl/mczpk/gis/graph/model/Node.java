package pl.mczpk.gis.graph.model;


public class Node {

	private Node parent;
	private String id;
	private int level;
	private NodeState state = NodeState.NOT_VISITIED;

	public Node(String id) {
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}

	public Node getParent() {
		return parent;
	}

	public NodeState getState() {
		return state;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setState(NodeState state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return id;
	}
}
