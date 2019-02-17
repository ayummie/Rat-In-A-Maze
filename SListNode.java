public class SListNode {
	
	private int item;
	private SListNode next;
	private SListNode prev;
	
	public SListNode(int newItem) {
		item = newItem;
		next = null;
		prev = null;
	}
	
	public int getItem() {
		return item;
	}
	
	public void setItem(int newItem) {
		item = newItem;
	}
	
	public SListNode getNext() {
		return next;
	}
	
	public void setNext(SListNode n) {
		next = n;
	}
	
	public SListNode getPrev() {
		return prev;
	}
	
	public void setPrev(SListNode n) {
		prev = n;
	}
}