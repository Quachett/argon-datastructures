package uk.co.argon.argon.datastructures.linkedlist;

import java.util.EmptyStackException;
import java.util.Iterator;

import uk.co.argon.common.exceptions.HttpException;
import uk.co.argon.common.util.JsonParser;

public abstract class AbstractLinkedList<E> implements LinkedList<E> {
	
	private static final int DEFAULT_CAPACITY = 11;
	protected Node head, tail;
	protected int size = 0;
	private int index = -1;
	private int indexCounter = -1;
	protected int capacity = 0;
	
	public AbstractLinkedList() {
		this(DEFAULT_CAPACITY);
	}
	
	 public AbstractLinkedList(int capacity) {
		 this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
	}

	protected class Node {
		 protected E data;
		 protected Node next, prev;
			
		public Node(E data) {
			this.data = data;
		}

		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
		 
		public Node(E data, Node prev, Node next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	 }
	 
	 @Override
	 public void clear() {
		 Node trav = head;
		 while(trav != null) {
			 Node temp = trav.next;
			 trav.prev = trav.next = null;
			 trav.data = null;
			 trav = temp;
		 }
		 head = tail = trav = null;
		 size = 0;
	 }

	 @Override
	 public boolean contains(Object o) {
		 index = -1;
		 if(!isEmpty())
			 return contains(head, o);
		 return false;
	 }
	 
	 private boolean contains(Node trav, Object o) {
		 indexCounter++;
		 if(trav == null) {
			 resetIndexCounter();
			 return false;
		 }
		 if(o.equals(trav.data)) {
			 index = indexCounter;
			 resetIndexCounter();
			 return true;
		 }
		 return contains(trav.next, o);
	 }
	 
	private void resetIndexCounter() {
		indexCounter = -1;
	}

	@Override
	public E getFirst() {
		if(isEmpty())
			return null;
		return head.data;
	}

	@Override
	public E getLast() {
		if(isEmpty())
			return null;
		return tail.data;
	}

	@Override
	public boolean isEmpty() {
		if(size==0)
			return true;
		return false;
	}

	@Override
	public int indexOf(Object o) {
		if(contains(o))
			return index;
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		index = -1;
		lastIndexOf(head, o);
		resetIndexCounter();
		return index;
	}
	
	private void lastIndexOf(Node trav, Object o) {
		indexCounter++;
		if(trav == null)
			return;
		if(o.equals(trav.data))
			index = indexCounter;
		lastIndexOf(trav.next, o);
	}

	@Override
	public E peek() {
		if(isEmpty())
			throw new EmptyStackException();
		return this.getFirst();
	}

	@Override
	public E peekLast() {
		return this.getLast();
	}

	@Override
	public E poll() {
		return this.remove();
	}

	@Override
	public E pop() {
		if(isEmpty())
			throw new EmptyStackException();
		return this.remove();
	}

	@Override
	public E push(E e) {
		this.add(e);
		return e;
	}

	@Override
	public E remove() {
		if(isEmpty())
			throw new EmptyStackException();
		E e = head.data;
		
		if(head.next == null)
			head = tail = null;
		else {
			head.next.prev = null;
			head = head.next;
		}
		
		size--;
		
		return e;
	}

	@Override
	public E remove(Object o) {
		int index = indexOf(o);
		return (index != -1)? remove(index): null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		print(head, sb);
		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return print();
	}
	
	private void print(Node trav, StringBuilder sb) {
		if(trav == null)
			return;
		try {
			sb.append(JsonParser.getInstance().serialise(trav.data));
			if(trav.next != null)
				sb.append(", ");
		} catch (HttpException e) {
			e.printStackTrace();
		}
		print(trav.next, sb);
	}

	@Override
	public void printReverse() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		printReverse(head, sb);
		sb.append("]");
		System.out.println(sb.toString().replace(", ]", "]"));
	}
	
	private void printReverse(Node trav, StringBuilder sb) {
		if(trav == null)
			return;
		printReverse(trav.next, sb);
		try {
			sb.append(JsonParser.getInstance().serialise(trav.data)).append(", ");
		} catch (HttpException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			Node trav = head;
			@Override
			public boolean hasNext() {
				return trav != null;
			}

			@Override
			public E next() {
				E e = trav.data;
				trav = trav.next;
				return e;
			}
		};
	}
}
