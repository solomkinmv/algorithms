package coding_problems.grammarly.word_parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Trie {

    private final char character;
    Map<Character, Trie> children = new HashMap<>();
    boolean word;

    Trie(char character) {
        this.character = character;
    }

    public boolean isWord() {
        return word;
    }

    @Override
    public String toString() {
        return "" + character;
    }

    Optional<Trie> getChild(char ch) {
        return Optional.ofNullable(children.get(ch));
    }
}
