package uk.co.argon.argon.datastructures.heap;

public interface Heap<E extends Comparable<E>> {
	public void add(E e);
	public E poll();
	public E remove(int index);
	public E remove(Object o);
	public int size();
	public boolean isEmpty();
	public boolean contains(Object o);
	public void clear();
	public void print();
}
