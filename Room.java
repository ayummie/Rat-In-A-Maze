
public class Room {
	private int[] doors;
	private Room parent;
	private int roomNumber;
	private boolean visited;
	
	public Room(int num) {
		roomNumber = num;
		doors = new int[4];
		for (int i = 0; i < 4; i++) {
			doors[i] = 1;
		}
		visited = false;
	}
	
	
	public int getDoor(int num) {
		return doors[num];
	}
	
	public void setDoor(int door, int num) {
		doors[door] = num;
	}
	
	public Room getParent() {
		return parent;
	}
	
	public void setParent(Room r) {
		parent = r;
	}
	
	public int getRoom() {
		return roomNumber;
	}
	
	public void setRoom(int num) {
		roomNumber = num;
	}
	
	public boolean wasVisited() {
		return visited;
	}
	
	public void setVisited(boolean b) {
		visited = b;
	}
}
