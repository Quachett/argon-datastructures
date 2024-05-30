package uk.co.argon.argon.datastructures.tree;

public class BinarySearchTree<T extends Comparable<T>> extends AbstractBinaryTree<T> {

	@Override
	public boolean contains(T t) {
		return contains(root, t);
	}
	
	private boolean contains(Node node, T t) {
		if(node == null)
			return false;
		
		if(node.data.compareTo(t) == 0)
			return true;
		
		if(t.compareTo(node.data) > 0)
			return contains(node.right, t);
		else
			return contains(node.left, t);
	}

	@Override
	public boolean insert(T t) {
		root = insert(root, t);
		nodeCount++;
		return true;
	}
	
	private Node insert(Node node, T t) {
		if(node == null)
			node = new Node(t, null, null);
		else {
			if(t.compareTo(node.data) < 0)
				node.left = insert(node.left, t);
			else
				node.right = insert(node.right, t);
		}
		
		return node;
	}

	@Override
	public boolean delete(T t) {
		root = delete(root, t);
		nodeCount--;
		return false;
	}
	
	private Node delete(Node node, T t) {
		if(node != null) {
			if(t.compareTo(node.data) == 0) {
				if(node.left == null)
					node = rightChild(node);
				else if(node.right == null)
					node = leftChild(node);
				else {
					node.data = findLeftDescendent(node.left);
					System.out.println("Delete: " + node.data);
					node.left = delete(node.left, node.data);
				}
			}
			else if(t.compareTo(node.data) < 0)
				node.left = delete(node.left, t);
			else
				node.right = delete(node.right, t);
		}
		return node;
	}
	
	private T findLeftDescendent(Node node) {
		if(node.right == null)
			return node.data;
		else
			return findLeftDescendent(node.right);
	}
	
	private Node leftChild(Node node) {
		Node leftChild = node.left;
		
		node = null;
		
		return leftChild;
	}

	private Node rightChild(Node node) {
		Node rightChild = node.right;
		
		node = null;
		
		return rightChild;
	}
}
