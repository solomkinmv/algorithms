package coding_problems.grammarly.word_parser;

import java.util.List;

class TrieFactory {
    Trie build(List<String> dictionary) {
        Trie root = new Trie((char) 0);
        for (String word : dictionary) {
            appendWord(root, word);
        }
        return root;
    }

    private void appendWord(Trie trieRoot, String word) {
        appendWord(trieRoot, word.toCharArray(), 0);
    }

    private void appendWord(Trie trieRoot, char[] chars, int pos) {
        if (pos >= chars.length) {
            trieRoot.word = true;
            return;
        }

        Trie child = trieRoot.children.computeIfAbsent(chars[pos], Trie::new);
        appendWord(child, chars, pos + 1);
    }
}
