package model;

public class Node {

	private int row;
	private int col;
	private Node bridge;

	public Node() {

	}

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Node getBridge() {
		return bridge;
	}

	public void setBridge(Node bridge) {
		this.bridge = bridge;
	}

	public boolean hasBridge() {
		if (bridge != null) {
			return true;
		}
		return false;
	}

	public boolean hasRightBridge() {
		if (!hasBridge()) {
			return false;
		}

		if (bridge.getCol() > col) {
			return true;
		}
		return false;
	}

	public Node next() {

		if (bridge == null) {
			return new Node(row + 1, col);
		}

		return new Node(row + 1, bridge.col);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {

		String str = "(" + row + "," + col;

		if (hasRightBridge()) {
			str += "-" + bridge.row + "," + bridge.col;
		}

		return str += ")";

	}
}
