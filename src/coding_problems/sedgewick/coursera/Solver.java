package coding_problems.sedgewick.coursera;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private MinPQ<Node> queue;
    private MinPQ<Node> alternatingQueue;
    private List<Board> steps;
    private boolean solvable;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        queue = new MinPQ<>();
        alternatingQueue = new MinPQ<>();
        queue.insert(new Node(initial));
        alternatingQueue.insert(new Node(initial.twin()));
        solve();
        queue = null;
        alternatingQueue = null;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private void solve() {
        while (!queue.isEmpty()) {
            Node node = queue.delMin();
            Node alternatingNode = alternatingQueue.delMin();

            if (node.getBoard().isGoal()) {
                buildSolution(node);
                solvable = true;
                return;
            }

            if (alternatingNode.getBoard().isGoal()) {
                solvable = false;
                return;
            }

            node.getBoard().neighbors().forEach(x -> {
                if (node.getPrevious() == null || !node.getPrevious().getBoard().equals(x)) {
                    queue.insert(new Node(x, node));
                }
            });
            alternatingNode.getBoard().neighbors().forEach(x -> {
                if (alternatingNode.getPrevious() == null || !alternatingNode.getPrevious().getBoard().equals(x)) {
                    alternatingQueue.insert(new Node(x, alternatingNode));
                }
            });
        }
    }

    private void buildSolution(Node node) {
        steps = new ArrayList<>(moves() + 1);
        while (node != null) {
            steps.add(0, node.getBoard());
            node = node.getPrevious();
        }
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (!solvable) return -1;
        return steps.size() - 1;
    }

    public Iterable<Board> solution() {
        return steps;
    }
}

class Node implements Comparable<Node> {
    private final Board board;
    private final Node previous;
    private final int len;
    private final int moves;

    Node(Board board, Node previous) {
        this.board = board;
        this.previous = previous;
        moves = previous.moves + 1;
        len = moves + board.manhattan();
    }

    public Node(Board board) {
        this.board = board;
        previous = null;
        moves = 1;
        len = moves + board.manhattan();
    }

    public Board getBoard() {
        return board;
    }

    public Node getPrevious() {
        return previous;
    }

    @Override
    public int compareTo(Node o) {
        return len - o.len;
    }
}