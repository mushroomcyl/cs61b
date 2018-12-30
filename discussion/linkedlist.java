public class SLList { 
	private class IntNode {
		public int item;
		public IntNode next;
		public IntNode(int item, IntNode next) {
			this.item = item; 
			this.next = next;
		}
	}
	
	private IntNode first;

	public void addFirst(int x) { 
		first = new IntNode(x, first);
	} 

	public void insert(int item, int position) {
		if (first == null || position == 0) {
			addFirst;
			return;
		}
		IntNode p = first;
		int i = 0;
		while (p.next != null && i < position) {
			p = p.next;
			i += 1;
		}
		IntNode newNode = new IntNode(item, p.next);
		p.next = newNode;
	}

	public void reverse() {
		//two pointers.
		IntNode frontOfReversed = null;
		IntNode nextNodeToAdd = first;
		while (nextNodeToAdd != null) {
			IntNode remainderOfOriginal = nextNodeToAdd.next;
			nextNodeToAdd.next = frontOfReversed;
			frontOfReversed = nextNodeToAdd;
			nextNodeToAdd = remainderOfOriginal;
		}
		first = frontOfReversed;
	}

	public void reverseRecursive() {
		first = reverseRecursiveHelper(first);
	}
	private IntNode reverseRecursiveHelper(IntNode front) {
		if (front == null || front.next == null){
			return front;
		}else{
			IntNode reversed = reverseRecursiveHelper(front.next);
			front.next.next = front;
			front.next = null;
			return reversed;
		}

	}
}