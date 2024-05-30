package uk.co.argon.argon.datastructures.linkedlist;

public interface LinkedList<E> extends Iterable<E> {

	public void add(E e);
	public void add(int index, E e);
	public void addLast(E e);
	public void clear();
	public boolean contains(Object o);
	public E get(int index);
	public E getFirst();
	public E getLast();
	public boolean isEmpty();
	public int indexOf(Object o);
	public int lastIndexOf(Object o);
	public E peek();
	public E peekLast();
	public E poll();
	public E pollLast();
	public E pop();
	public E push(E e);
	public E remove();
	public E remove(int index);
	public E remove(Object o);
	public E removeLast();
	public int size();
	public String print();
	public void printReverse();
}
