public interface Deque<T> {
    /** Adds x to the front of the list. */
    public void addFirst(T x);

    /** Adds x to the end of the list. */
    public void addLast(T x);

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty();

    /** Returns the number of items in the deque. */
    public int size();

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque();

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst();

    /** Removes and returns the item at the back of the deque. */
    public T removeLast();

    /** Gets the item at the given index. */
    public T get(int index);
}
