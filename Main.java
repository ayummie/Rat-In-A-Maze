import java.io.*;
import java.util.Scanner;
import java.util.Scanner;

public class Main {
	public static void main(String[] file) throws IOException {

		System.out.println("Choose one: \n1 Open maze file \n2 Create new maze file");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		Maze m = null;
		
		if (choice == 1) {
			m = new Maze(file);
			
		} else if (choice == 2) {
			m = new Maze();
		}
		m.printMaze();
		m.BFS();
		m.DFS();
	}
}
