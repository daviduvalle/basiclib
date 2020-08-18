package du.cs.ds;

/**
 * Implements a Trie tree for the English alphabet
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