package coding_problems.grammarly.word_parser;

import java.util.List;

public interface Solver {
    List<String> solve(Trie root, String str);
}
