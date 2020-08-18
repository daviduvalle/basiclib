package du.cs.ds;

public class Tester {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("at");
		trie.insert("and");
		trie.insert("all");
		trie.insert("bat");
		trie.insert("can");
		trie.insert("cat");
		trie.insert("call");

		System.out.println(trie.search("call"));
		System.out.println(trie.search("cat"));
		System.out.println(trie.search("cast"));
	}

}
