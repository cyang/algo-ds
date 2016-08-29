public class BinarySearchTree {
	static class Tree {
		Node root;
	}

	static class Node {
		Node parent, left, right;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void insertNode(Node root, Node insert) {
		if (root == null) {
			root = insert;
		} else {
			if (insert.data <= root.data) {
				if (root.left != null) {
					insertNode(root.left, insert);
				} else {
					// Found empty slot
					root.left = insert;
					insert.parent = root;
				}
			} else {
				if (root.right != null) {
					insertNode(root.right, insert);
				} else {
					// Found empty slot
					root.right = insert;
					insert.parent = root;
				}
			}
		}
	}

	public static Node findNode(Node root, int data) {
		if (root == null) {
			return null;
		}

		if (root.data == data) {
			return root;
		} else if (root.data > data) {
			if (root.left.data != data) {
				return findNode(root.left, data);
			} else {
				// found it!
				return root.left;
			}
		} else {
			if (root.right.data != data) {
				return findNode(root.right, data);
			} else {
				// found it!
				return root.right;
			}
		}
	}

	public static Node findMin(Node root) {
		if (root.left == null) {
			return root;
		}

		return findMin(root.left);
	}

	public static void transplant(Tree t, Node u, Node v) {
		// v replaces u as a subtree as a child of u's parent
		if (u.parent == null) {
			// Passing in tree because Java is pass-by-value of reference
			// The only way to update the address of a reference is via the
			// values, such that the modification is reflected outside of the
			// method
			t.root = v;
		} else if (u == u.parent.left) {
			// it is a left child
			u.parent.left = v;
		} else {
			// it is a right child
			u.parent.right = v;
		}

		if (v != null) {
			// update parent
			v.parent = u.parent;
		}

	}

	public static void deleteNode(Tree t, Node deleteNode) {
		if (deleteNode.left == null) {
			// no left children
			transplant(t, deleteNode, deleteNode.right);
		} else if (deleteNode.right == null) {
			// no right children
			transplant(t, deleteNode, deleteNode.left);
		} else {
			// both children
			Node min = findMin(deleteNode.right);

			if (deleteNode.right != min) {
				// min is not the immediate right child of u
				transplant(t, min, min.right);
				min.right = deleteNode.right;
				min.right.parent = min;
			}
			transplant(t, deleteNode, min);

			min.left = deleteNode.left;
			min.left.parent = min;

		}
	}

	public static void printInOrder(Node root) {
		if (root != null) {
			printInOrder(root.left);
			System.out.print(root.data + " ");
			printInOrder(root.right);
		}
	}

	public static void main(String[] args) {
		Tree tree = new Tree();
		Node root = new Node(2);
		tree.root = root;

		insertNode(root, new Node(1));
		insertNode(root, new Node(7));
		insertNode(root, new Node(5));
		insertNode(root, new Node(8));
		insertNode(root, new Node(3));
		insertNode(root, new Node(6));
		insertNode(root, new Node(4));

		printInOrder(tree.root);

		Node deleteNode = findNode(root, 2);

		System.out.println();
		deleteNode(tree, deleteNode);

		printInOrder(tree.root);
	}
}
