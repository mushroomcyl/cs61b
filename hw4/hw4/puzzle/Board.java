package hw4.puzzle;

import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    /** N is the size of titles*/
    private int N;
    private ArrayList<Integer> board;
    private int BLANK = 0;

    /** Constructs a board from an N-by-N array of tiles where
     tiles[i][j] = tile at row i, column j */
    public Board(int[][] tiles) {
        board = new ArrayList<>();
        N = tiles[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board.add(tiles[i][j]);
            }
        }
    }

    /** Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        if (!validate(i) || !validate(j)) {
            throw new IndexOutOfBoundsException("i or j is out of bounds.");
        }
        return board.get(i * N + j);
    }

    private boolean validate(int i) {
        return (i >= 0 && i < N);
    }

    /** Returns the board size N */
    public int size() {
        return N;
    }

    /** Returns the neighbors of the current board
     *  source: http://joshh.ug/neighbors.html
     *  it is from josh hug*/
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Hamming estimate: The number of tiles in the wrong position. */
    public int hamming() {
        int count = 0;
        for (int i = 0; i < board.size() - 1; i ++) {
            if (board.get(i) != i+1) {
                count++;
            }
        }
        return count;
    }

    /** Manhattan estimate: The sum of the Manhattan distance (sum of the vertical
     *  and horizontal distance) from the tiles to their goal positions. */
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N * N; i++) {
            int currNum = board.get(i);
            if (currNum == 0) {
                continue;
            }
            int[] currPos = convert2D(i);
            int[] goalPos = convert2D(currNum);
            count += calDist(currPos, goalPos);
        }
        return count;
    }

    /** convert a number to 2D index */
    private int[] convert2D(int n) {
        int[] index = new int[2];
        index[0] = n / N;
        index[1] = n % N;
        return index;
    }

    /** calculate the distance between current position to goal position */
    private int calDist(int[] curr, int[] goal) {
        int dist = 0;
        dist += Math.abs(curr[0] - goal[0]);
        dist += Math.abs(curr[1] - goal[1]);
        return dist;
    }

    /** Estimated distance to goal. This method should
     simply return the results of manhattan() when submitted to
     Gradescope. */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns true if this board's tile values are the same
     position as y's */
    public boolean equals(Object y) {
        if (y == this) {
            return true;
        }
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }

        Board ybd = (Board) y;

        if (this.N != ybd.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != ybd.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
