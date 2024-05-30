package uk.co.argon.argon.datastructures.queue;

import uk.co.argon.argon.datastructures.linkedlist.LinkedList;
import uk.co.argon.argon.datastructures.linkedlist.SinglyLinkedList;

public class Queue<E> {
	private LinkedList<E> queue = new SinglyLinkedList<>();

	public Queue() {
	}

	public Queue(E e) {
		if(e != null)
			offer(e);
	}
	
	public int size() {
		return queue.size();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	public E peek() {
		return queue.peek();
	}
	
	public E poll() {
		return queue.poll();
	}
	
	public void offer(E e) {
		queue.addLast(e);
	}
	
	public void clear() {
		queue.clear();
	}
	
	public void print() {
		queue.print();
	}
}
