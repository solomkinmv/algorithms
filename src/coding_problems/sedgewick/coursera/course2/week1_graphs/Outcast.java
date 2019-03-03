package coding_problems.sedgewick.coursera.course2.week1_graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {         // constructor takes a WordNet object
        this.wordnet = wordnet;
    }

    public static void main(String[] args) {  // see test client below
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }

    public String outcast(String[] nouns) {   // given an array of WordNet nouns, return an outcast
        String outcast = null;
        int maxDist = 0;
        for (String noun : nouns) {
            int dist = sumDistances(noun, nouns);
            if (dist > maxDist) {
                outcast = noun;
                maxDist = dist;
            }
        }
        return outcast;
    }

    private int sumDistances(String targetNoun, String[] nouns) {
        int result = 0;
        for (String noun : nouns) {
            result += wordnet.distance(targetNoun, noun);
        }
        return result;
    }

}
