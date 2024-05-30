package uk.co.argon.argon.datastructures.tree;

public interface Tree<T extends Comparable<T>> extends Iterable<T> {
	public void clear();
	public boolean contains(T t);
	public boolean delete(T t);
	public boolean insert(T t);
	public boolean isEmpty();
	public boolean remove(T t);
	public int size();
	public int height();
	public void printInOrder();
	public void printPreOrder();
	public void printPostOrder();
	public void printLevelOrder();
}
