package coding_problems.grammarly.word_parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("Duplicates")
public class LongestTerminationSolver implements Solver {
    @Override
    public List<String> solve(Trie root, String str) {
        List<String> res = new LinkedList<>();
        Trie current = root;

        int prev = 0;
        int lastSuccess = prev;
        int i;
        for (i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            Optional<Trie> next = current.getChild(ch);
            if (next.isPresent()) {
                current = next.get();
            }
            if (next.isPresent() && next.get().isWord()) {
                lastSuccess = i + 1;
                continue;
            }
            if (!next.isPresent()) {
                if (lastSuccess > prev) {
                    res.add(str.substring(prev, lastSuccess));
                    prev = lastSuccess;
                    i = lastSuccess - 1;
                } else {
                    i = prev; // go back
                    prev++;
                }
                current = root;
                continue;
            }
        }
        if (lastSuccess > prev) {
            res.add(str.substring(prev, lastSuccess));
        }
        return res;
    }

}
