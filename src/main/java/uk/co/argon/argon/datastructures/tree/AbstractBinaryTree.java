package uk.co.argon.argon.datastructures.tree;

import java.util.Iterator;

import uk.co.argon.argon.datastructures.queue.Queue;

public abstract class AbstractBinaryTree<T extends Comparable<T>> implements Tree<T> {
	
	protected Node root = null;
	protected int nodeCount = 0;
	
	class Node {
		T data;
		Node left, right;
		
		Node(T data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	@Override
	public void clear() {
		root = clear(root);
		nodeCount = 0;
	}
	
	private Node clear(Node node) {
		if(node != null) {
			node.left = clear(node.left);
			node.right = clear(node.right);
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean remove(T t) {
		return delete(t);
	}

	@Override
	public int size() {
		return nodeCount;
	}
	
	@Override
	public int height() {
		return height(root);
	}

	public int height(Node node) {
		if(node == null)
			return 0;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	@Override
	public void printInOrder() {
		System.out.print("[");
		printInOrder(root);
		System.out.println("]");
	}
	
	public void printInOrder(Node node) {
		if(node == null)
			return;
		printInOrder(node.left);
		print(node.data);
		printInOrder(node.right);
	}

	@Override
	public void printPreOrder() {
		System.out.print("[");
		printPreOrder(root);
		System.out.println("]");
	}

	private void printPreOrder(Node node) {
		if(node == null)
			return;
		print(node.data);
		printPreOrder(node.left);
		printPreOrder(node.right);
	}

	@Override
	public void printPostOrder() {
		System.out.print("[");
		printPostOrder(root);
		System.out.println("]");
	}

	private void printPostOrder(Node node) {
		if(node == null)
			return;
		printPostOrder(node.left);
		printPostOrder(node.right);
		print(node.data);
	}

	@Override
	public void printLevelOrder() {
		Queue<Node> queue = new Queue<>(root);
		System.out.print("[");
		printLevelOrder(queue);
		System.out.println("]");
	}

	public void printLevelOrder(Queue<Node> queue) {
		if(queue.isEmpty())
			return;
		
		Node node = queue.poll();
		print(node.data);
		
		if(node.left != null)
			queue.offer(node.left);
		
		if(node.right != null)
			queue.offer(node.right);
		
		printLevelOrder(queue);
	}
	
	private void print(T data) {
		System.out.print(data + " ");
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
