package lab11.graphs;

import java.util.Queue;
import java.util.ArrayDeque;
import java.lang.Integer;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private int INFINITY = Integer.MAX_VALUE;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here.
        //  Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new ArrayDeque<>();

        //initialize all the distance of vertex to infinity
        for (int i = 0; i < maze.V(); i++) {
            distTo[i] = INFINITY;
        }

        distTo[s] = 0;
        marked[s] = true;
        announce();

        queue.add(s);

        while (!queue.isEmpty()) {
            int v = queue.remove();

            if (v == t) {
                return;
            }

            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    announce();
                    distTo[w] = distTo[v] + 1;
                    marked[w] =true;
                    queue.add(w);
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

