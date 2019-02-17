public class SList {

  private SListNode head;
  private SListNode tail;
  private int size;

  public SList() {
    size = 0;
    head = null;
    tail = null;
  }
  
  public void insertFront(SListNode node) {
	  if (size == 0) {
		  head = node;
		  tail = node;
		  size++;
	  } else if (size == 1) {
		  head.setPrev(node);
		  node.setNext(head);
		  head = node;
		  size++;
	  } else {
		  head.setPrev(node);
		  node.setNext(head);
		  head= node;
		  size++;
	  }
  }
  
  public void removeFront() {
	  if (size > 1) {
		  head.getNext().setPrev(null);
		  head = head.getNext();
		  size--;
	  } else {
		  head = null;
		  tail = null;
		  size = 0;
	  }
  }
  
  public void removeEnd() {
	  if (size > 1) {
		  tail.getPrev().setNext(null);
		  tail = tail.getPrev();
		  size--;
	  } else {
		  head = null;
		  tail = null;
		  size = 0;
	  }
  }
  
  public int head() {
	  return head.getItem();
  }
  
  public int tail() {
	  return tail.getItem();
  }
  
  public int size() {
	  return size;
  }
}