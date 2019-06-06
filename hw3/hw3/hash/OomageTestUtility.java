package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] buckets = new int[M];
        for (Oomage oomage : oomages) {
            int bucket_num = (oomage.hashCode() & 0x7FFFFFFF) % M;
            buckets[bucket_num] += 1;

        }
        return (findSmallest(buckets) > (oomages.size() / 50) && findLargest(buckets) < (oomages.size() / 2.5));
    }

    private static int findLargest(int[] buckets) {
        int max = buckets[0];
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i] > max) {
                max = buckets[i];
            }
        }
        return max;
    }

    private static int findSmallest(int[] buckets) {
        int min = buckets[0];
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i] < min) {
                min = buckets[i];
            }
        }
        return min;
    }
}
