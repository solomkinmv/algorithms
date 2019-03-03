package coding_problems.sedgewick.coursera.course2.week1_graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

    private final WordNet wordnet;

    public Outcast(WordNet wordnet) {         // constructor takes a WordNet object
        this.wordnet = wordnet;
    }

    public static void main(String[] args) {  // see test client below
        String synsetPath = "resources/coding_problems/sedgewick/coursera/course2/week1_graphs/synsets.txt";
        String hypernymsPath = "resources/coding_problems/sedgewick/coursera/course2/week1_graphs/hypernyms.txt";
        WordNet wordnet = new WordNet(synsetPath, hypernymsPath);
        Outcast outcast = new Outcast(wordnet);

        String outcastPath = "resources/coding_problems/sedgewick/coursera/course2/week1_graphs/outcast8.txt";

        In in = new In(outcastPath);
        String[] nouns = in.readAllStrings();
        StdOut.println(outcastPath + ": " + outcast.outcast(nouns));
    }

    public String outcast(String[] nouns) {   // given an array of WordNet nouns, return an outcast
        String outcast = null;
        int maxDist = 0;
        for (String noun : nouns) {
            int dist = sumDistances(noun, nouns);
//            System.out.println(noun + "\t" + dist);
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
