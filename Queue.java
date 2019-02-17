public class Queue extends SList {
	private SList list;

	public Queue() {
		this.list = new SList();
	}
	
	public void add(int roomNumber) {
		SListNode item = new SListNode(roomNumber);
		list.insertFront(item);
	}
	
	public int remove() {
		int room = list.tail();
		list.removeEnd();
		return room;
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}

}
