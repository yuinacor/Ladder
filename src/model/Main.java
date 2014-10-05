package model;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		Ladder ladder = new Ladder();

		ladder.init(5);

		System.out.println("start");
		List<Node> path = ladder.run(0);
		System.out.println(path.size());
		System.out.println(ladder.printPath(path));
		// System.out.println(ladder.printLadder());
		System.out.println("end");

	}

}
