public class LinkedListDeque<T> {
    private class ListNode {
        private T item;
        private ListNode prev;
        private ListNode next;

        private ListNode(T i, ListNode p, ListNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /** The first item (if it exists) is at sentinel.next. */
    private ListNode sentinel;
    private int size;

    /** Creates an empty LinkedList. */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        ListNode temp = sentinel.next;
        sentinel.next = new ListNode(x, sentinel, temp);
        temp.prev = sentinel.next;
        size = size + 1;
    }

    /** Adds x to the end of the list. */
    public void addLast(T x) {
        ListNode temp = sentinel.prev;
        sentinel.prev = new ListNode(x, temp, sentinel);
        temp.next = sentinel.prev;
        size = size + 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        ListNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T first = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return first;
        }
    }

    /** Removes and returns the item at the back of the deque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T last = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return last;
        }
    }

    /** Gets the item at the given index. */
    public T get(int index) {
        if (size == 0 || index > size - 1) {
            return null;
        }
        ListNode p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** Gets the item at the given index, uses recursion. */
    public T getRecursive(int index) {
        if (size == 0 || index > size - 1) {
            return null;
        } else {
            return traverse(index, sentinel);
        }
    }

    private T traverse(int index, ListNode p) {
        if (index == 0) {
            return p.next.item;
        } else {
            return traverse(index - 1, p.next);
        }
    }
}
