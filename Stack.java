public class Stack extends SList {
	private SList list;
	
	public Stack() {
		this.list = new SList();
	}
	
	public int pop() {
		int room = list.head();
		list.removeFront();
		return room;
	}
	
	public void push(int roomNumber) {
		SListNode item = new SListNode(roomNumber);
		list.insertFront(item);
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
}
