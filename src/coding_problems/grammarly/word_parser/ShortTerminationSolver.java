package coding_problems.grammarly.word_parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ShortTerminationSolver implements Solver {
    public List<String> solve(Trie root, String str) {
        List<String> res = new LinkedList<>();
        Trie current = root;

        int prev = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            Optional<Trie> next = current.getChild(ch);
            if (next.isPresent()) {
                current = next.get();
            }
            if (next.isPresent() && next.get().isWord()) {
                res.add(str.substring(prev, i + 1));
                prev = i + 1;
                current = root;
                continue;
            }
            if (!next.isPresent()) {
                i = prev; // go back
                prev++;
                current = root;
                continue;
            }
        }
        return res;
    }

}
