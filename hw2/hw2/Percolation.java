package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    private int[][] grid;
    private boolean[][] status;
    private int numOfOpen;
    //virtual top and bottom
    private int top;
    private int bottom;
    private WeightedQuickUnionUF WQU;

    /** create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException(N + "is not smaller or equal to 0.");
        }
        //each element in grid is 0
        grid = new int[N][N];
        //each element in status is false
        status = new boolean[N][N];
        numOfOpen = 0;
        WQU = new WeightedQuickUnionUF(N * N + 2);
        top = N * N;
        bottom = N * N + 1;
    }

    // helper functions
    /** validate that p is a valid index */
    private void validate(int p) {
        int n = grid.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    /**
     * private helper method
     *
     * denote grid position in number(0 ~ N * N - 1).
     */
    private int grid_num(int row, int col) {
        int n = grid.length;
        return row * n + col;
    }

    /**
     * private helper method
     *
     * connect a component and it's neighbors
     */
    private void Connect(int row, int col) {
        int cur = grid_num(row, col);
        List<Integer> neighbors = new ArrayList<>();

        //first row of the grid
        if (row == 0) {
            WQU.union(top, cur);
        }

        //last row of the grid
        if (row == grid.length - 1) {
            WQU.union(bottom, cur);
        }

        //check current site's neighbors are opened or not
        //if opened, add them to neighbors
        if (row != 0 && isOpen(row - 1, col)) {
            neighbors.add(grid_num(row - 1, col));
        }
        if (row != grid.length - 1 && isOpen(row + 1, col)) {
            neighbors.add(grid_num(row + 1, col));
        }
        if (col != 0 && isOpen(row, col - 1)) {
            neighbors.add(grid_num(row, col - 1));
        }
        if (col != grid.length - 1 && isOpen(row, col + 1)) {
            neighbors.add(grid_num(row, col + 1));
        }

        //union the current site's all opened sites
        for (int i : neighbors) {
            WQU.union(cur, i);
        }
    }

    /** open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        validate(row);
        validate(col);

        if (!isOpen(row, col)) {
            grid[row][col] = 1;
            numOfOpen += 1;
            status[row][col] = true;
            Connect(row, col);
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return status[row][col];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        //how to deal with the edge case?
        //top connect to the bottom, the site only connect to bottom
        return WQU.connected(top, grid_num(row, col));
    }

    /** number of open sites */
    public int numberOfOpenSites(){
        return numOfOpen;
    }

    /** does the system percolate? */
    public boolean percolates(){
        return WQU.connected(top, bottom);
    }
    //public static void main(String[] args){}   // use for unit testing (not required)
}
