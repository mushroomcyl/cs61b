/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */

/**
 * LSD radix sort is good for fixed-length string.
 * MSD radix sort is good for variable-length strings.
 *
 * Here is a great tool for seeing how Radix sort works visually.
 * https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
 *
 */
import java.util.Arrays;

public class RadixSort {
    private static int R = 256;

    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0 , sorted, 0, asciis.length);

        //get the max length of string in asciis
        int max = 0;
        for (String asc : asciis) {
            max = max > asc.length() ? max : asc.length();
        }

        //sort
        int digit = 0;
        while (digit < max) {
            sorted = sortHelperLSD(sorted, digit);
            digit += 1;
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        // sort one digit of the asciis

        int[] counts = new int[R];
        String[] sorted = new String[asciis.length];

        //count
        for (String a : asciis) {
            int pos = (int) a.charAt(a.length() - index - 1);
            counts[pos] += 1;
        }

        //adjust counts' numbers as start indices
        //use the same counts array to record start indices
        int prev = 0;
        for (int i = 0; i < R; i++) {
            if (counts[i] > 0) {
                counts[i] += prev;
                prev = counts[i];
            }
        }

        //reorder
        for (int numString = asciis.length - 1; numString >= 0; numString--) {
            // int number of character at particular position
            int num = (int) asciis[numString].charAt(asciis[numString].length() - index - 1);
            counts[num] -= 1;
            int pos = counts[num];
            sorted[pos] = asciis[numString];
        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
