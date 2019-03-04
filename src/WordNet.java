import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

public class WordNet {

    //    private final Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
    private final Map<Integer, String> idSynset = new HashMap<>();
    private final Map<String, Integer> wordToSynsetId = new HashMap<>();
    private final Digraph graph;

    public static void main(String[] args) {
        String synsetPath = "resources/coding_problems/sedgewick/coursera/course2/week1_graphs/synsets.txt";
        String hypernymsPath = "resources/coding_problems/sedgewick/coursera/course2/week1_graphs/hypernyms.txt";
        WordNet wordnet = new WordNet(synsetPath, hypernymsPath);

    }

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        validateNull(synsets);
        validateNull(hypernyms);

        int maxId = initSynsets(synsets);
        graph = initHypernyms(maxId, hypernyms);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return wordToSynsetId.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        validateNull(word);
        return wordToSynsetId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        validateNull(nounA);
        validateNull(nounB);

        validateNoun(nounA);
        validateNoun(nounB);

        if (nounA.equals(nounB)) return 0;

        SAP sap = new SAP(graph);
        int nounAId = wordToSynsetId.get(nounA);
        int nounBId = wordToSynsetId.get(nounB);
        return sap.length(nounAId, nounBId);
    }

    // a synset (second field of synsets.txt) that is the ancestor ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        validateNull(nounA);
        validateNull(nounB);

        validateNoun(nounA);
        validateNoun(nounB);

        SAP sap = new SAP(graph);
        int nounAId = wordToSynsetId.get(nounA);
        int nounBId = wordToSynsetId.get(nounB);
        int ancestorId = sap.ancestor(nounAId, nounBId);
        return idSynset.get(ancestorId);
    }

    private Digraph initHypernyms(int maxId, String hypernyms) {
        Digraph digraph = new Digraph(maxId + 1);

        for (String line : lines(hypernyms)) {
            String[] split = line.split(",");
            Integer hyponim = Integer.valueOf(split[0]);
            for (int i = 1; i < split.length; i++) {
                addEdge(digraph, hyponim, Integer.parseInt(split[i]));
            }
        }

        return digraph;
    }

    private void addEdge(Digraph graph, int hyponim, int hypernym) {
//        System.out.println(hypernym + " -> " + hypernym);
        graph.addEdge(hyponim, hypernym);
    }

    private int initSynsets(String synsets) {
        int maxId = 0;
        for (String line : lines(synsets)) {
            String[] split = line.split(",");
            Integer id = Integer.valueOf(split[0]);
            String synset = split[1];
            String[] nouns = synset.split(" ");
            for (String noun : nouns) {
                wordToSynsetId.put(noun, id);
            }
            idSynset.put(id, synset);
            maxId = Math.max(id, maxId);
        }

        return maxId;
    }

    private void validateNoun(String word) {
        if (!isNoun(word)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNull(Object object) {
        if (object == null) throw new IllegalArgumentException(object + " is null");
    }

    private String[] lines(String filename) {
        In in = new In(filename);
        String[] result = in.readAllLines();
        in.close();
        return result;
    }
}
