public class ArrayDequeVersion1<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private float usage;

    //private static int RESIZE_FACTOR = 2;
    //private static double USAGE_FACTOR = 0.25;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        usage = 0;
        // set the two pointers randomly.
        nextFirst = 2;
        nextLast = nextFirst + 1;
    }

    /** Resize the array. */
    public void resize() {
        boolean reduce_size = (usage < 0.25 && items.length > 16);
        int target_size;
        int start = 0, end = 0;
        if (size == items.length) {
            target_size = items.length * 2;
            T[] new_items = (T[]) new Object[target_size];
            start = 0;
            end = items.length;
            System.arraycopy(items, start, new_items, 1, end - start);
            nextFirst = 0;
            nextLast = end - start + 1;
        }
        if (reduce_size) {
            target_size = items.length / 2;
            T[] new_items = (T[]) new Object[target_size];
            for (int i = 0; i < items.length - 1; i ++) {
                if (items[i] == null && items[i+1] != null) {
                    start = i + 1;

                }
                if (items[i] != null && items[i+1] == null) {
                    end = i;
                }
            }
            System.arraycopy(items, start, new_items, 1, end - start + 1);
            items = new_items;
            nextFirst = 0;
            nextLast = end - start + 2;
        }
    }

    /** Move the first pointer, add/remove. */
    public int movePointerFirst(boolean add) {
        if (add) {
            if (nextFirst != 0) {
                nextFirst -= 1;
            } else {
                nextFirst = items.length - 1;
            }
        } else {
            if (nextFirst != items.length - 1) {
                nextFirst += 1;
            } else {
                nextFirst = 0;
            }
        }
        return nextFirst;
    }

    /** Move the last pointer, add/remove. */
    public int movePointerLast(boolean add) {
        if (add) {
            if (nextLast != items.length - 1) {
                nextLast += 1;
            } else {
                nextLast = 0;
            }
        } else {
            if (nextLast != 0) {
                nextLast -= 1;
            } else {
                nextLast = items.length - 1;
            }
        }
        return nextLast;
    }

    /** Adds x to the front of the list. */
    public void addFirst(T x) {
        resize();
        items[nextFirst] = x;
        size += 1;
        usage = (float) size / (float) items.length;
        nextFirst = movePointerFirst(true);
    }


    /** Adds x to the end of the list. */
    public void addLast(T x) {
        resize();
        items[nextLast] = x;
        size += 1;
        usage = (float) size / (float) items.length;
        nextLast = movePointerLast(true);
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        for (T item : items) {
            System.out.print(item + " ");
        }
    }

    /** Removes and returns the item at the front of the deque. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int i = movePointerFirst(false);
        T remove = items[i];
        items[i] = null;
        size -= 1;
        usage = (float) size / (float) items.length;
        nextFirst = i;
        resize();
        return remove;
    }

    /** Removes and returns the item at the back of the deque. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int i = movePointerLast(false);
        T remove = items[i];
        items[i] = null;
        size -= 1;
        usage = (float) size / (float) items.length;
        nextLast = i;
        resize();
        return remove;
    }

    /** Gets the item at the given index. */
    public T get(int index) {
        if (index > items.length - 1) {
            return null;
        } else if (nextFirst + 1 + index < items.length) {
            return items[nextFirst + 1 + index];
        } else {
            return items[nextFirst + 1 + index - items.length];
        }
    }

}



