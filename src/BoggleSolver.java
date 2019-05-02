import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        return wordsOnBoard(board);
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

    private Set<String> wordsOnBoard(BoggleBoard board) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                result.addAll(wordsOnBoardFromPosition(board, Position.of(i, j)));
            }
        }

        return result;
    }

    private Set<String> wordsOnBoardFromPosition(BoggleBoard board, Position position) {
        return wordsOnBoardFromPosition(board, position, dictionary.getRoot(), Collections.singleton(position));
    }

    private Set<String> wordsOnBoardFromPosition(BoggleBoard board, Position position, Node rootNode, Set<Position> previousLetters) {
        if (rootNode.isEmpty()) return Collections.emptySet();
        Node node = rootNode.get(getLetter(board, position));

        Set<String> result = new HashSet<>();
        if (node.isWord()) {
            if (node.getWord().length() > 2) {
                result.add(node.getWord());
            }
        }

        for (Position pos : nextDirections(board, position, previousLetters)) {
            result.addAll(wordsOnBoardFromPosition(board, pos, node, copyAndAdd(previousLetters, pos)));
        }

        return result;
    }

    private Set<Position> copyAndAdd(Set<Position> previousLetters, Position nextPosition) {
        Set<Position> result = new HashSet<>(previousLetters);
        result.add(nextPosition);
        return result;
    }

    private List<Position> nextDirections(BoggleBoard board, Position position, Set<Position> previousLetters) {
        List<Position> result = new ArrayList<>();
        // left
        boolean hasLeft = position.j > 0;
        if (hasLeft) {
            Position nextPosition = nextPossiblePosition(position, 0, -1);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
        }
        // right
        boolean hasRight = position.j < board.cols() - 1;
        if (hasRight) {
            Position nextPosition = nextPossiblePosition(position, 0, 1);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
        }
        // top
        boolean hasTop = position.i > 0;
        if (hasTop) {
            Position nextPosition = nextPossiblePosition(position, -1, 0);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
        }
        // bottom
        boolean hasBottom = position.i < board.rows() - 1;
        if (hasBottom) {
            Position nextPosition = nextPossiblePosition(position, 1, 0);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
        }
        // top-left
        if (hasTop && hasLeft) {
            Position nextPosition = nextPossiblePosition(position, -1, -1);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
        }
        // top-right
        if (hasTop && hasRight) {
            Position nextPosition = nextPossiblePosition(position, -1, 1);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
        }
        // bottom-left
        if (hasBottom && hasLeft) {
            Position nextPosition = nextPossiblePosition(position, 1, -1);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
        }
        // bottom-right
        if (hasBottom && hasRight) {
            Position nextPosition = nextPossiblePosition(position, 1, 1);
            if (!previousLetters.contains(nextPosition)) {
                result.add(nextPosition);
            }
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
    private static final Node EMPTY_NODE = new Node(false, Collections.emptyMap()) {
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
    private final Map<Character, Node> nodes;
    private boolean word;
    private String wordText;

    Node(boolean word, Map<Character, Node> nodes) {
        this(word, nodes, null);
    }

    Node(boolean word, Map<Character, Node> nodes, String wordText) {
        this.nodes = nodes;
        this.word = word;
        this.wordText = wordText;
    }

    public static Node empty() {
        return new Node(false, new HashMap<>(26));
    }

    public void add(String word, int d) {
        if (word.length() == d) {
            this.word = true;
            this.wordText = word;
            return;
        }

        nodes.compute(word.charAt(d), (key, node) -> {
            if (node == null) {
                node = empty();
            }
            node.add(word, d + 1);
            return node;
        });
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
            return nodes.getOrDefault(ch, EMPTY_NODE)
                        .get('U');
        }
        return nodes.getOrDefault(ch, EMPTY_NODE);
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public boolean contains(String word, int d) {
        if (word.length() == d) {
            return true;
        }
        return get(word.charAt(d)).contains(word, d + 1);
    }
}

class Position {
    private static final Map<Integer, Map<Integer, Position>> CACHE = new HashMap<>();

    public final int i;
    public final int j;

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
        return Objects.hash(i, j);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return i == position.i &&
                j == position.j;
    }
}
