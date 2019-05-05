package coding_problems.grammarly.word_parser;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task. Given a dictionary and some string extract words.
 * There are different grade metrics, like words length, number of words, number of different words, etc..
 * <p>
 * This solution uses 2 different approaches to get different results.
 * LongestTerminationSolver — looks up for the longest words.
 * ShortTerminationSolver — looks up for greater number of words.
 */
public class Main {

    private static final TrieFactory factory = new TrieFactory();
    private static final Solver SHORT_TERMINATION_SOLVER = new ShortTerminationSolver();
    private static final Solver LONGEST_TERMINATION_SOLVER = new LongestTerminationSolver();
    private static final String RESOURCES_DIR = "resources/coding_problems/grammarly/word_parser/";

    public static void main(String[] args) {
        testLongest();
        testShortest();

        tryToSolve();
    }

    private static void tryToSolve() {
        Trie root = buildTrie();
        String line = readLine();

        solveWithShortTerminationSolver(root, line);
        solveWithLongestTerminationSolver(root, line);
    }

    private static void solveWithShortTerminationSolver(Trie root, String line) {
        List<String> result = SHORT_TERMINATION_SOLVER.solve(root, line);
        write(RESOURCES_DIR + "res_short", result);
    }

    private static void solveWithLongestTerminationSolver(Trie root, String line) {
        List<String> result = LONGEST_TERMINATION_SOLVER.solve(root, line);
        write(RESOURCES_DIR + "res_long", result);
    }

    @SneakyThrows
    private static List<String> read(String filename) {
        return Files.lines(Paths.get(filename))
                    .collect(Collectors.toList());
    }

    private static String readLine() {
        return read(RESOURCES_DIR + "str").get(0);
    }

    @SneakyThrows
    private static void write(String filename, List<String> results) {
        String line = String.join(" ", results);
        Files.write(Paths.get(filename), List.of(line));
    }

    private static Trie buildTrie() {
        Trie root = factory.build(read(RESOURCES_DIR + "dictionary"));
        return root;
    }

    private static void testShortest() {
        List<String> solution = SHORT_TERMINATION_SOLVER.solve(buildTrie(), "IAMATJAVACONFERENCE");

        System.out.println(solution);
    }

    private static void testLongest() {
        List<String> solution = LONGEST_TERMINATION_SOLVER.solve(buildTrie(), "IAMATJAVACONFERENCE");

        System.out.println(solution);
    }
}
