package hw4.puzzle;

import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private ArrayList<WorldState> solution;

    /** Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists.*/
    public Solver(WorldState initial) {
        solution = new ArrayList<>();
        MinPQ<Node> pq = new MinPQ<>();

        pq.insert(new Node(initial, 0, null));

        //while the node is not the goal, recursion
        while (!pq.min().getWordState().isGoal()) {
            Node x = pq.delMin();
            for (WorldState neighbor : x.getWordState().neighbors()) {
                //not add the gradparent
                if (x.prev == null || !(neighbor.equals(x.getPrev().getWordState()))) {
                    pq.insert(new Node(neighbor, x.getMoveCount() + 1, x));
                }
            }
        }

        Node n = pq.min();
        while (n != null) {
            solution.add(0, n.getWordState());
            n = n.getPrev();
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState.*/
    public int moves() {
        return solution.size() - 1;
    }

    /** Returns a sequence of WorldStates from the initial WorldState
     to the solution.*/
    public Iterable<WorldState> solution() {
        return solution;
    }

    private class Node implements Comparable<Node> {
        private WorldState ws;
        private int moveCount;
        private Node prev;

        private Node(WorldState ws, int moveCount, Node prev) {
            this.ws = ws;
            this.moveCount = moveCount;
            this.prev = prev;
        }

        public WorldState getWordState() {
            return ws;
        }

        public int getMoveCount() {
            return moveCount;
        }

        public Node getPrev() {
            return prev;
        }

        @Override
        public int compareTo(Node node) {
            return ((this.moveCount + ws.estimatedDistanceToGoal()) -
                    (node.moveCount + node.ws.estimatedDistanceToGoal()));
        }
    }
}
