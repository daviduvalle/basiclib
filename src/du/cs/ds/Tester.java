package du.cs.ds;

public class Tester {

	public static void main(String[] args) {
	    System.out.println("Test");
	    BinarySearchTree<String> stringsTree = new BinarySearchTree<>();
	    stringsTree.insert("b");
	    stringsTree.insert("a");
	    stringsTree.insert("c");

	    DynamicArray<String> array = stringsTree.inorder(stringsTree.getRoot());
	}

}
