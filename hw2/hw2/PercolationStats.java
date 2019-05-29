package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int[] Count;

    /**
     * perform T independent experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T is smaller than 0.");
        }

        int count = 0;

        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int rand_row = StdRandom.uniform(N);
                int rand_col = StdRandom.uniform(N);
                try {
                    p.open(rand_row, rand_col);
                    count++;
                } catch (Exception e) {
                    continue;
                }
            }
            Count[i] = count;
        }
    }

    /**
     * sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(Count);
    }

    /**
     * sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(Count);
    }

    /**
     * low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev() / Count.length);
    }

    /**
     * high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev() / Count.length);
    }
}
