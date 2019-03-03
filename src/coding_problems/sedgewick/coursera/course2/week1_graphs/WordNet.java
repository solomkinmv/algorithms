package coding_problems.sedgewick.coursera.course2.week1_graphs;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {

    private final Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
    private final Map<Integer, String> idSynset = new HashMap<>();
    private final Map<String, Integer> synsetId = new HashMap<>();
//    private final Digraph digraph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        validateNull(synsets);
        validateNull(hypernyms);

        initSynsets(synsets);
        initHypernyms(hypernyms);
    }

    // do unit testing of this class
    public static void main(String[] args) {
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return synsetId.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        validateNull(word);
        return synsetId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        validateNull(nounA);
        validateNull(nounB);

        validateNoun(nounA);
        validateNoun(nounB);

        return -1;
    }

    // a synset (second field of synsets.txt) that is the ancestor ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        validateNull(nounA);
        validateNull(nounB);

        validateNoun(nounA);
        validateNoun(nounB);

        return null;
    }

    private void initHypernyms(String hypernyms) {
        for (String line : lines(hypernyms)) {
            String[] split = line.split(",");
            Integer hyponim = Integer.valueOf(split[0]);
            for (int i = 1; i < split.length; i++) {
                addEdge(hyponim, Integer.valueOf(split[i]));
            }
        }
    }

    private void addEdge(Integer from, Integer to) {
        adjacencyList.computeIfAbsent(from, key -> new HashSet<>())
                     .add(to);
    }

    private void initSynsets(String synsets) {
        for (String line : lines(synsets)) {
            String[] split = line.split(",");
            Integer id = Integer.valueOf(split[0]);
            String noun = split[1];
            synsetId.put(noun, id);
            idSynset.put(id, noun);
        }
    }

    private void validateNoun(String word) {
        if (!isNoun(word)) throw new IllegalArgumentException();
    }

    private void validateNull(Object object) {
        if (object == null) throw new IllegalArgumentException();
    }

    private String[] lines(String filename) {
        In in = new In(filename);
        String[] result = in.readAllLines();
        in.close();
        return result;
    }
}
