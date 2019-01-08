public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int reversedLength;

    private static int RESIZE_FACTOR = 2;
    private static double USAGE_FACTOR = 0.25;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        reversedLength = 8; // array length
        nextFirst = 0;
        nextLast = 1;
    }

    /** Define some helper functions. */
    private boolean isFull() {
        return size == reversedLength;
    }

    private int plusOne(int index) {
        int nextIndex = index + 1;
        if (nextIndex >= reversedLength) {
            nextIndex %= reversedLength;
        }
        return nextIndex;
    }

    private int minusOne(int index) {
        int prevIndex = index - 1;
        if (prevIndex < 0) {
            prevIndex += reversedLength;
        }
        return prevIndex;
    }

    /** increase or decrease the size of array. */
    private void resize(int newLength, String flag) {
        T[] itemsNew = (T[]) new Object[newLength];
        if (flag.equals("increase")) {
            // The array is divided into two parts.
            int start = plusOne(nextFirst); //start position of the first part.
            int firstLength = size - start;
            System.arraycopy(items, start, itemsNew, 1, firstLength);
            int secondLength = nextLast;
            System.arraycopy(items, 0, itemsNew, firstLength + 1, secondLength);
        } else if (flag.equals("decrease")) {
            int start = plusOne(nextFirst);
            System.arraycopy(items, start, itemsNew, 1, size);
        }
        items = itemsNew;
        reversedLength = newLength;
        nextFirst = 0;
        nextLast = size + 1;
    }

    /** Adds x to the front of the list. */
    @Override
    public void addFirst(T x) {
        if (isFull()) {
            int newLength = reversedLength * RESIZE_FACTOR;
            resize(newLength, "increase");
        }
        items[nextFirst] = x;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    /** Adds x to the end of the list. */
    @Override
    public void addLast(T x) {
        if (isFull()) {
            int newLength = reversedLength * RESIZE_FACTOR;
            resize(newLength, "increase");
        }
        items[nextLast] = x;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    /** Returns true if deque is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Some helper functions for remove and get. */
    private void maintainUsageFactor() {
        if (reversedLength >= 16) {
            double usage = (double) size / reversedLength;
            if (usage < USAGE_FACTOR) {
                int newLength = reversedLength / RESIZE_FACTOR;
                resize(newLength, "decrease");
            }
        }
    }

    private int getTrueIndex(int index) {
        int trueIndex = (nextFirst + 1 + index) % reversedLength;
        return trueIndex;
    }

    /** Gets the item at the given index. */
    @Override
    public T get(int index) {
        if (index < size) {
            int trueIndex = getTrueIndex(index);
            return items[trueIndex];
        }
        return null;
    }

    private void setNull(int index) {
        int trueIndex = getTrueIndex(index);
        items[trueIndex] = null;
    }

    /** Removes and returns the item at the front of the deque. */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T first = get(0);
        setNull(0);
        nextFirst = plusOne(nextFirst);
        size -= 1;
        maintainUsageFactor();
        return first;
    }

    /** Removes and returns the item at the end of the deque. */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T last = get(size - 1);
        setNull(size - 1);
        nextLast = minusOne(nextLast);
        size -= 1;
        maintainUsageFactor();
        return last;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }
}
