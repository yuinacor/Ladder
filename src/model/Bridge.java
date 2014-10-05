package model;

public class Bridge {

	private static final int CONN_LENGTH = 2;

	private Node[] connected;

	public Node[] getConnected() {
		return connected;
	}

	public void setConected(Node node, Node nextNode) {
		if (connected == null) {
			connected = new Node[CONN_LENGTH];
		}

		connected[0] = node;
		connected[1] = nextNode;
	}

	public Node move(Node present) {

		Node next = null;

		for (Node node : connected) {
			if (node.equals(present)) {

			}
		}

	}

}
