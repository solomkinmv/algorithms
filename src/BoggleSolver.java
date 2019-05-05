import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoggleSolver {

    private final Trie dictionary = new Trie();

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        for (String word : dictionary) {
            this.dictionary.add(word);
        }
    }

    public static void main(String[] args) {
        String dictionaryFilename = "resources/coding_problems/sedgewick/coursera/course2/week4_substring_search/boggle/dictionary-algs4.txt";
//        String dictionaryFilename = "resources/coding_problems/sedgewick/coursera/course2/week4_substring_search/boggle/dictionary-zingarelli2005.txt";
        In in = new In(dictionaryFilename);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        String boardFilename = "resources/coding_problems/sedgewick/coursera/course2/week4_substring_search/boggle/board4x4.txt";
//        String boardFilename = "resources/coding_problems/sedgewick/coursera/course2/week4_substring_search/boggle/board-points26539.txt";
//        String boardFilename = "resources/coding_problems/sedgewick/coursera/course2/week4_substring_search/boggle/board-q.txt";
//        String boardFilename = "resources/coding_problems/sedgewick/coursera/course2/week4_substring_search/boggle/board-points1.txt";
        BoggleBoard board = new BoggleBoard(boardFilename);
        int score = 0;

        List<String> list = new ArrayList<>();
        for (String word : solver.getAllValidWords(board)) {
            list.add(word);
        }
        Collections.sort(list);
        for (String word : list) {
            int wordScore = solver.scoreOf(word);
            StdOut.println(word + " = " + wordScore);
            score += wordScore;
        }
        StdOut.println("Score = " + score);
        System.out.println(solver.scoreOf("QUITE"));
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        precomputePositions(board);

        return new NodeToWordIterable(wordsOnBoard(board));
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        if (!dictionary.contains(word)) return 0;
        int length = word.length();

        if (length < 3) return 0;
        if (length < 5) return 1;
        if (length < 6) return 2;
        if (length < 7) return 3;
        if (length < 8) return 5;
        return 11;
    }

    private Set<Node> wordsOnBoard(BoggleBoard board) {
        Set<Node> result = new HashSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                wordsOnBoardFromPosition(board, Position.of(i, j), result);
            }
        }

        return result;
    }

    private void wordsOnBoardFromPosition(BoggleBoard board, Position position, Set<Node> result) {
        wordsOnBoardFromPosition(board, position, dictionary.getRoot(), new HashSet<>(), result);
    }

    private void wordsOnBoardFromPosition(BoggleBoard board, Position position, Node rootNode, Set<Position> previousLetters, Set<Node> result) {
        if (rootNode.isEmpty()) return;
        if (previousLetters.contains(position)) return;

        previousLetters.add(position);

        Node node = rootNode.get(getLetter(board, position));

        if (node.isWord()) {
            if (node.getWord().length() > 2) {
                result.add(node);
            }
        }

        for (Position pos : position.getNeighbors()) {
            wordsOnBoardFromPosition(board, pos, node, previousLetters, result);
        }

        previousLetters.remove(position);
    }

    private void precomputePositions(BoggleBoard board) {
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                Position position = Position.of(i, j);
                position.setNeighbors(nextDirections(board, position));
            }
        }
    }

    private List<Position> nextDirections(BoggleBoard board, Position position) {
        List<Position> result = new ArrayList<>();
        // left
        boolean hasLeft = position.j > 0;
        if (hasLeft) {
            result.add(nextPossiblePosition(position, 0, -1));
        }
        // right
        boolean hasRight = position.j < board.cols() - 1;
        if (hasRight) {
            result.add(nextPossiblePosition(position, 0, 1));
        }
        // top
        boolean hasTop = position.i > 0;
        if (hasTop) {
            result.add(nextPossiblePosition(position, -1, 0));
        }
        // bottom
        boolean hasBottom = position.i < board.rows() - 1;
        if (hasBottom) {
            result.add(nextPossiblePosition(position, 1, 0));
        }
        // top-left
        if (hasTop && hasLeft) {
            result.add(nextPossiblePosition(position, -1, -1));
        }
        // top-right
        if (hasTop && hasRight) {
            result.add(nextPossiblePosition(position, -1, 1));
        }
        // bottom-left
        if (hasBottom && hasLeft) {
            result.add(nextPossiblePosition(position, 1, -1));
        }
        // bottom-right
        if (hasBottom && hasRight) {
            result.add(nextPossiblePosition(position, 1, 1));
        }

        return result;
    }

    private Position nextPossiblePosition(Position position, int iShift, int jShift) {
        return Position.of(position.i + iShift, position.j + jShift);
    }

    private char getLetter(BoggleBoard board, Position position) {
        return getLetter(board, position, 0, 0);
    }

    private char getLetter(BoggleBoard board, Position position, int iShift, int jShift) {
        return board.getLetter(position.i + iShift, position.j + jShift);
    }

    private static class NodeToWordIterable implements Iterable<String> {

        private final Iterable<Node> nodeIterable;

        NodeToWordIterable(Iterable<Node> nodeIterable) {
            this.nodeIterable = nodeIterable;
        }

        @Override
        public Iterator<String> iterator() {
            return new NodeToWordIterator(nodeIterable.iterator());
        }
    }

    private static class NodeToWordIterator implements Iterator<String> {

        private final Iterator<Node> nodeIterator;

        NodeToWordIterator(Iterator<Node> nodeIterator) {
            this.nodeIterator = nodeIterator;
        }

        @Override
        public boolean hasNext() {
            return nodeIterator.hasNext();
        }

        @Override
        public String next() {
            return nodeIterator.next().getWord();
        }
    }

}

class Trie {
    private final Node root = Node.empty();

    public void add(String word) {
        root.add(word, 0);
    }

    public Node get(char ch) {
        return root.get(ch);
    }

    public Node getRoot() {
        return root;
    }

    public boolean contains(String word) {
        return root.contains(word, 0);
    }
}

class Node {
    private static final Node EMPTY_NODE = new Node(false, null) {
        @Override
        public void add(String word, int d) {
            throw new UnsupportedOperationException("Immutable empty node");
        }

        @Override
        public boolean isWord() {
            return false;
        }

        @Override
        public Node get(char ch) {
            return this;
        }

        @Override
        public boolean contains(String word, int d) {
            return false;
        }
    };
    private final Node[] nodes;
    private boolean word;
    private String wordText;
    private boolean empty = true;

    Node(boolean word, Node[] nodes) {
        this(word, nodes, null);
    }

    Node(boolean word, Node[] nodes, String wordText) {
        this.nodes = nodes;
        this.word = word;
        this.wordText = wordText;
    }

    public static Node empty() {
        return new Node(false, new Node[26]);
    }

    public void add(String word, int d) {
        if (word.length() == d) {
            this.word = true;
            this.wordText = word;
            return;
        }

        int index = charToIndex(word.charAt(d));
        Node node = nodes[index];
        if (node == null) {
            node = empty();
            nodes[index] = node;
        }
        node.add(word, d + 1);
        empty = false;
    }

    public boolean isWord() {
        return word;
    }

    public String getWord() {
        if (!isWord()) {
            throw new IllegalStateException("Can't get word from non-word node");
        }
        return wordText;
    }

    public Node get(char ch) {
        if (ch == 'Q') {
            return getOrDefault(ch)
                    .get('U');
        }
        return getOrDefault(ch);
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean contains(String word, int d) {
        if (word.length() == d) {
            return isWord();
        }
        return getOrDefault(word.charAt(d))
                .contains(word, d + 1);
    }

    private Node getOrDefault(char ch) {
        int index = charToIndex(ch);
        Node node = nodes[index];
        if (node == null) {
            return EMPTY_NODE;
        }
        return node;
    }

    private int charToIndex(char ch) {
        return ch - 'A';
    }
}

class Position {
    private static final Map<Integer, Map<Integer, Position>> CACHE = new HashMap<>();

    public final int i;
    public final int j;
    private List<Position> neighbors;

    private Position(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public static Position of(int i, int j) {
        return CACHE.computeIfAbsent(i, key -> new HashMap<>())
                    .computeIfAbsent(j, key -> new Position(i, j));
    }

    @Override
    public int hashCode() {
        return 7 * i + 13 * j;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    public List<Position> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Position> neighbors) {
        this.neighbors = neighbors;
    }
}
