package uk.co.argon.argon.datastructures.linkedlist;

import java.util.Collection;

public class SinglyLinkedList<E> extends AbstractLinkedList<E> {
	
	public SinglyLinkedList() {
		super();
	}
	
	public SinglyLinkedList(E e) {
		super();
		this.add(e);
	}
	
	public SinglyLinkedList(Collection<E> c) {
		super();
		if(c.size() > super.capacity)
			throw new IllegalArgumentException("Collection can not be added, List capacity too small");
		c.forEach(e -> this.add(e));
	}
	
	public SinglyLinkedList(int capacity, E e) {
		super(capacity);
		this.add(e);
	}
	
	public SinglyLinkedList(int capacity, Collection<E> c) {
		super(capacity);
		if(c.size() > super.capacity)
			throw new IllegalArgumentException("Collection can not be added, List capacity too small");
		c.forEach(e -> this.add(e));
	}
	
	public SinglyLinkedList(int capacity) {
		super(capacity);
	}

	 public void add(E e) {
		 if(super.capacity < size)
			 throw new IllegalArgumentException("Can not add new element. List capacity: " + super.capacity + " exceeded");
		 else {
			 if(isEmpty())
				 head = tail = new Node(e, tail);
			 else {
				 head = new Node(e, head);
			 }
			 size++;
		 }
	 }

	@Override
	public void add(int index, E e) {
		if(index >= size() || index <= 0 )
			throw new IndexOutOfBoundsException(index);
		if(isEmpty() || size() == 1)
			this.add(e);
		else {
			add(head, index, 0, e);
			size++;
		}
	}
	
	private void add(Node trav, int index, int indexCounter, E e) {
		if(indexCounter == index-1) {
			trav.next = new Node(e, trav.next);
			return;
		}
		add(trav.next, index, ++indexCounter, e);
	}

	@Override
	public void addLast(E e) {
		if(isEmpty())
			 add(e);
		else {
			tail = tail.next = new Node(e, tail.next);
			size++;
		}
	}

	@Override
	public E get(int index) {
		if(!isEmpty() && index >= 0 && index < size())
			return get(head, index, 0);
		throw new IndexOutOfBoundsException(index);
	}
	
	private E get(Node trav, int index, int indexCounter) {
		if(indexCounter == index)
			return trav.data;
		return get(trav.next, index, ++indexCounter);
	}

	@Override
	public E pollLast() {
		return this.removeLast();
	}

	@Override
	public E remove(int index) {
		if(!isEmpty() && index >= 0 && index < size()) {
			if(index == 0)
				return super.remove();
			return remove(head, index, 0);
		}
		throw new IndexOutOfBoundsException(index);
	}
	
	private E remove(Node trav, int index, int indexCounter) {
		if(indexCounter == (index - 1)) {
			E e = trav.next.data;
			trav.next = trav.next.next;
			if(trav.next == null) {
				if(size-1 == 1) 
					head = trav;
				tail = trav.next;
			}
			size--;
			return e;
		}
		return remove(trav.next, index, ++indexCounter); 
	}

	@Override
	public E removeLast() {
		return remove(size()-1);
	}
}
