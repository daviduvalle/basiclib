package du.cs.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implements a Trie tree for the English alphabet.
 * Insert and Search runtime complexity is:
 * O(W) where W is the length of the Word.
 * The runtime complexity of creating it is:
 * O(W*N) where N is the number of words
 *
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        // The root node is a special case that is
        // empty
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.getChildren()[index] == null) {
                // Create a new node
                TrieNode newNode = new TrieNode();
                node.getChildren()[index] = newNode;
                node = newNode;
            } else {
                // Go to the next node in the tree
                node = node.getChildren()[index];
            }
        }

        node.setWordEnd(true);
    }

    /**
     * Returns true if a word is in the trie
     * @param word a word
     * @return true if in the trie, false otherwise
     */
    public boolean search(String word) {
        TrieNode node = searchForNode(word);
        if (node == null) {
            return false;
        } else {
            if (node.isWordEnd()) {
                return true;
            }
        }
        return false;
    }

    public boolean prefixExists(String prefix) {
        TrieNode node = searchForNode(prefix);
        if (node != null)
            return true;
        return false;
    }

    public TrieNode searchForNode(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.getChildren()[index] != null) {
                node = node.getChildren()[index];
            } else {
                return null;
            }
        }

        if (node == root) {
            return null;
        }

        return node;
    }

    public List<String> getSuggestionsByPrefix(String prefix) {
        if (!prefixExists(prefix)) {
            return Collections.emptyList();
        }

        TrieNode root = searchForNode(prefix);
        List<String> suggestions = new ArrayList<>();
        findSuggestions(root, suggestions, prefix);

        return suggestions;
    }

    private void findSuggestions(TrieNode node, List<String> suggestions, String prefix) {
        if (node.isWordEnd()) {
            suggestions.add(prefix);
            return;
        } else {
            for (int i = 0; i < node.getChildren().length; i++) {
                if (node.getChildren()[i] != null) {
                    char x =(char)(i + 'a');
                    findSuggestions(node.getChildren()[i], suggestions, prefix + x);
                }
            }
        }
    }
}

/**
 * Represents a node in a trie tree
 * for the English alphabet (26 chars)
 */
class TrieNode {
    private TrieNode[] children;
    private boolean isWordEnd;

    public TrieNode() {
        this.children = new TrieNode[26];
    }

    public void setWordEnd(boolean isWordEnd) {
        this.isWordEnd = isWordEnd;
    }

    public boolean isWordEnd() {
        return this.isWordEnd;
    }

    public TrieNode[] getChildren() {
        return this.children;
    }
}