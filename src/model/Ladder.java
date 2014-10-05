package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ladder {

	private static final int MAX_ROW_LENGTH = 10;

	private List<List<Node>> ladder;
	private int columnLength;
	private int rowLength;

	public void init(int columnLength) {

		this.columnLength = columnLength;
		this.rowLength = new Random().nextInt(MAX_ROW_LENGTH) + 5;
		this.ladder = new ArrayList<>();

		for (int i = 0; i < rowLength; i++) {
			ladder.add(makeRow(i));
		}

	}

	public List<Node> run(int colNum) {

		Node start = ladder.get(0).get(colNum);
		Node nextNode = start.next();
		List<Node> path = new ArrayList<>();

		path.add(start);
		while (nextNode.getRow() < rowLength - 1) {
			nextNode = getNode(nextNode.next());
			path.add(nextNode);
		}

		return path;
	}

	private Node getNode(Node node) {

		Node no = null;
		try {
			no = ladder.get(node.getRow()).get(node.getCol());
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return no;
	}

	private List<Node> makeRow(int rowNum) {

		List<Node> row = new ArrayList<>();

		for (int i = 0; i < columnLength; i++) {
			row.add(new Node(rowNum, i));
		}

		for (int i = 0; i < columnLength - 1; i++) {
			row.set(i, setBridge(row.get(i), row.get(i + 1)));
		}

		return row;
	}

	private Node setBridge(Node node, Node nextNode) {

		if (node.hasBridge()) {
			return node;
		}

		Random random = new Random();

		int bridge = random.nextInt(2);
		System.out.println(bridge);

		if (bridge == 1) {
			node.setBridge(nextNode);
			nextNode.setBridge(node);
		}

		return node;

	}

	private String printRow(Node node, Node nextNode) {

		String str = "";

		if (node.getRow() != nextNode.getRow()
				&& node.getCol() == nextNode.getCol()) {
			str += printNth("\t", node.getCol());
		}

		if (node.hasRightBridge()) {
			str += printNth("*", 9);
		} else {
			str += "*";
		}

		if (node.getRow() != nextNode.getRow()) {
			return str += "\n";
		}

		System.out.println(str);
		return str;
	}

	private String printNth(String str, int n) {
		String result = "";
		for (int i = 0; i < n; i++) {
			result += str;
		}
		return result;
	}

	public String printLadder() {

		StringBuilder sb = new StringBuilder();

		for (List<Node> row : ladder) {
			for (Node node : row) {
				if (node.hasRightBridge()) {
					sb.append("|========");
				} else if (node.hasBridge()) {
					sb.append("=======|\t");
				} else {
					sb.append("ㅣ\t");
				}
			}
			sb.append("\n");
		}

		return sb.toString();

	}

	public String printPath(List<Node> path) {
		StringBuilder sb = new StringBuilder();

		int index = 0;
		Node current = path.get(index);

		for (List<Node> row : ladder) {
			for (Node node : row) {
				if (node.equals(current)) {
					if (node.hasRightBridge()) {
						sb.append("********");
					} else {
						sb.append("*\t");
					}
					if (index + 1 < path.size()) {
						current = path.get(++index);
					}
					continue;
				} else {
					if (node.hasRightBridge()) {
						sb.append("|=======");
					} else {
						sb.append("|\t");
					}
				}
			}
			sb.append("\n");
		}

		for (Node node : path) {
			sb.append(printRow(node, node.next()));
		}

		return sb.toString();

	}
}
