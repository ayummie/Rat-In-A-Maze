import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Maze {

	private Room[] allRooms;
	private int width;
	private int size;
	Scanner input;
	
	public Maze() {
		mazeRandom();
	}
	

	public Maze(String[] file) throws IOException {
		mazeFile(file);
	}

	public void mazeFile(String[] file) throws IOException {
		File file1 = new File("maze.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
			input = new Scanner(file1);
			width = input.nextInt();
			size = width * width;
			allRooms = new Room[size];
			for (int i = 0; i < size; i++) {
				allRooms[i] = new Room(i);
				for (int j = 0; j < 4; size++) {
					allRooms[i].setDoor(j, input.nextInt());
				}
			}
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}
    
	public void mazeRandom() {
		input = new Scanner(System.in);
		Random rand = new Random();
		System.out.println("\nPlease enter the size of your two-dimensional square maze: ");
		width = input.nextInt();
		size = width * width;
		allRooms = new Room[size];
		for (int i = 0; i < size; i++) {
			allRooms[i] = new Room(-1);
		}
		allRooms[0].setDoor(0,0);
		allRooms[size-1].setDoor(1,0);
		while (find(0) != find(size-1)) {
			int firstRoom = rand.nextInt(size);
			int[] partner = findPartner(firstRoom);
			int secondRoom = rand.nextInt(4);
			while (partner[secondRoom] < 0) {
				secondRoom = rand.nextInt(4);
			}
			secondRoom = partner[secondRoom];
			if (find(firstRoom) != find(secondRoom)) {
				connectRooms(firstRoom, secondRoom);
				createUnion(find(firstRoom), find(secondRoom));
			}
		}
		for (int i = 0; i < size; i++) {
			allRooms[i].setRoom(i);
		}
	}
	
	public int find(int num) {
		int index = allRooms[num].getRoom();
		if (index < 0) {
			return num;
		} else {
			return find(index);
		}
	}
	
	public void createUnion(int first, int second) {
		int a = allRooms[first].getRoom();
		int b = allRooms[second].getRoom();
		if (a < b) {
			allRooms[first].setRoom(a + b);
			allRooms[second].setRoom(first);
		} else {
			allRooms[first].setRoom(second);
			allRooms[second].setRoom(a + b);
		}
	}
	
	public void connectRooms(int i , int j) {
		int[] partner = findPartner(i);
		if (partner[0] == j) {
			allRooms[i].setDoor(0,0);
			allRooms[j].setDoor(1,0);
		} else if (partner[1] == j) {
			allRooms[i].setDoor(1,0);
			allRooms[j].setDoor(0,0);
		} else if (partner[2] == j) {
			allRooms[i].setDoor(2,0);
			allRooms[j].setDoor(3,0);
		} else {
			allRooms[i].setDoor(3,0);
			allRooms[j].setDoor(2,0);
		}

	}
	
	public int[] findPartner(int roomNumber) {
		int[] partner = new int[4];
		for (int i = 0; i < 4; i++) {
			partner[i] = -1;
		}
		if (roomNumber >= width) {
			partner[0] = roomNumber - width;
		}
		if (roomNumber < size - width) {
			partner[1] = roomNumber + width;
		}
		if (roomNumber % width != width - 1) {
			partner[2] = roomNumber + 1;
		}
		if (roomNumber % width != 0) {
			partner[3] = roomNumber - 1;
		}
		return partner;
	}
	
	public boolean pathExists(int i, int j, int k) {
		int[] partner = findPartner(i);
		if (j == partner[k]) {
			if (allRooms[i].getDoor(k) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public String findReverse(Room r) {
		String path = "";
		while(r.getParent() != null) {
			path += Integer.toString(r.getRoom()) + " ";
			r = r.getParent();
		}
		path += "0";
		for (int i = 0; i < size; i++) {
			allRooms[i].setParent(null);
		}
		return path;
	}
	
	public void printPath(String path) {
		String[] rooms = path.split(" ");
		System.out.print("\n");
		for (int i = 0; i < size; i++) {
			boolean inPath = false;
			for (int j = 0; j < rooms.length; j++) {
				if (i == Integer.parseInt(rooms[j])) {
					inPath = true;
				}
			}
			if (inPath == true) {
				System.out.print("X ");
			} else {
				System.out.print("  ");
			}
			if (i % width == width - 1) {
				System.out.print("\n");
			}
		}
	}
	
	public void printMaze() {
		System.out.println("\nInitializing Maze:");
		System.out.print("   ");
		for (int top = 1; top < width; top++) {
			System.out.print("___");
		}
		System.out.print("\n");
		for (int row = 0; row < width; row++) {
			for (int room = 0; room < width; room++) {
				if (allRooms[room + row*width].getDoor(3) == 1) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}
				if (allRooms[room + row*width].getDoor(1) == 1) {
					System.out.print("__");
				} else {
					System.out.print("  ");
				}
			}
			System.out.print("|\n");
		}
	}
	
	public void BFS() {
		Queue q = new Queue();
		q.add(0);
		allRooms[0].setVisited(true);
		String roomsVisited = "0";
		while(!q.isEmpty()) {
			int removedNode = q.remove();
			int [] partner = findPartner(removedNode);
			for (int i = 0; i < 4; i++) {
				int room = partner[i];
				if (room >= 0) {
					if (pathExists(removedNode, room, i) && !allRooms[room].wasVisited()) {
						roomsVisited += Integer.toString(i) + " ";
						q.add(room);
						allRooms[room].setVisited(true);
						allRooms[room].setParent(allRooms[removedNode]);
						if (room == size - 1) {
							System.out.println("\nRooms visited by BFS: " + roomsVisited);
							String path = findReverse(allRooms[size - 1]);
							System.out.println("This is the path (in reverse): " + path);
							printPath(path);
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < size; i++) {
			allRooms[i].setVisited(false);
		}
	}
	
	public void DFS() {
		Stack s = new Stack();
		s.push(0);
		allRooms[0].setVisited(true);
		String roomsVisited = "";
		while(!s.isEmpty()) {
			int popped = s.pop();
			roomsVisited += Integer.toString(popped) + " ";
			int[] partner = findPartner(popped);
			if (popped == size - 1) {
				System.out.println("\nRooms visited by DFS: " + roomsVisited);
				String path = findReverse(allRooms[size - 1]);
				System.out.println("This is the path (in reverse: " + path);
				printPath(path);
				break;
			} else {
				for (int i = 0; i < 4; i++) {
					int room = partner[i];
					if (room >= 0 && pathExists(popped, room, i) && !allRooms[room].wasVisited()) {
						s.push(room);
						allRooms[room].setVisited(true);
						allRooms[room].setParent(allRooms[popped]);
					}
				}
			}
		}
		for (int i = 0; i < size; i++) {
			allRooms[i].setVisited(false);
		}
	}
}
