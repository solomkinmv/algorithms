package coding_problems.sedgewick.coursera.course1;

import io.vavr.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final char[][] tiles;
    private final int n;
    private int manhattan = -1;
    private int hamming = -1;

    public Board(int[][] blocks) {
        if (blocks == null) throw new IllegalArgumentException();
        n = blocks.length;
        tiles = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = (char) blocks[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int[][] tiles = {
                {8, 1, 3},
                {4, 0, 2},
                {7, 6, 5}
        };

        Board board = new Board(tiles);

        assert board.hamming() == 5;
        assert board.manhattan() == 10;
    }

    public int dimension() {
        return tiles.length;
    }

    public int hamming() {
        if (hamming != -1) return hamming;
        int index = 1;
        int diffs = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    if (tiles[i][j] != index) {
                        diffs++;
                    }
                }
                index++;
            }
        }
        hamming = diffs;
        return hamming;
    }

    public int manhattan() {
        if (manhattan != -1) return manhattan;
        int index = 1;
        int diffs = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    if (tiles[i][j] != index) {
                        int expectedRow = (tiles[i][j] - 1) / n;
                        int expectedCol = (tiles[i][j] - 1) % n;
                        diffs += Math.abs(expectedRow - i) + Math.abs(expectedCol - j);
                    }
                }
                index++;
            }
        }

        manhattan = diffs;
        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public Board twin() {
        Tuple2<Integer, Integer> previousPair = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0) {
                    if (previousPair == null) {
                        previousPair = new Tuple2<>(i, j);
                    } else {
                        return swap(i, j, previousPair._1, previousPair._2);
                    }
                }
            }
        }
        throw new RuntimeException();
    }

    private int[][] cloneArray(char[][] src) {
        int[][] target = new int[n][src[0].length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                target[i][j] = src[i][j];
            }
        }
        return target;
    }

    public boolean equals(Object y) {
        if (!(y instanceof Board)) return false;
        Board other = (Board) y;

        return Arrays.deepEquals(tiles, other.tiles);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", (int) tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public Iterable<Board> neighbors() {
        List<Board> boards = new ArrayList<>();
        Tuple2<Integer, Integer> blankPoint = findBlank();
        int i = blankPoint._1, j = blankPoint._2;

        Board newBoard;
        newBoard = nullableSwap(i, j, i - 1, j);
        addBoardIfNotNull(boards, newBoard);
        newBoard = nullableSwap(i, j, i + 1, j);
        addBoardIfNotNull(boards, newBoard);
        newBoard = nullableSwap(i, j, i, j - 1);
        addBoardIfNotNull(boards, newBoard);
        newBoard = nullableSwap(i, j, i, j + 1);
        addBoardIfNotNull(boards, newBoard);

        return boards;
    }

    private void addBoardIfNotNull(List<Board> boards, Board newBoard) {
        if (newBoard != null) {
            boards.add(newBoard);
        }
    }

    private Tuple2<Integer, Integer> findBlank() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    return new Tuple2<>(i, j);
                }
            }
        }
        throw new RuntimeException();
    }

    private Board nullableSwap(int i1, int j1, int i2, int j2) {
        if (i2 < 0 || i2 >= n || j2 < 0 || j2 >= n) return null;
        return swap(i1, j1, i2, j2);
    }

    private Board swap(int i1, int j1, int i2, int j2) {
        int[][] copy = cloneArray(tiles);
        int tmp = copy[i1][j1];

        copy[i1][j1] = copy[i2][j2];
        copy[i2][j2] = tmp;
        return new Board(copy);
    }
}