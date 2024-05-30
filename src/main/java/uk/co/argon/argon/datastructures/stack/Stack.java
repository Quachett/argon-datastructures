package uk.co.argon.argon.datastructures.stack;

import uk.co.argon.argon.datastructures.linkedlist.LinkedList;
import uk.co.argon.argon.datastructures.linkedlist.SinglyLinkedList;

public class Stack<E> {
	private LinkedList<E> stack = new SinglyLinkedList<>();

	public Stack(E e) {
		if(e != null)
			push(e);
	}
	
	public int size() {
		return stack.size();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public E push(E e) {
		return stack.push(e);
	}
	
	public E pop() {
		return stack.pop();
	}
	
	public E peek() {
		return stack.peek();
	}
	
	public void clear() {
		stack.clear();
	}
	
	public void print() {
		stack.print();
	}
}
