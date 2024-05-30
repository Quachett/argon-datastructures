package uk.co.argon.argon.datastructures.linkedlist;

public class DoublyLinkedList<E> extends AbstractLinkedList<E> {
	
	public DoublyLinkedList() {
		super();
	}
	
	public DoublyLinkedList(int capacity) {
		super(capacity);
	}

	@Override
	public void add(E e) {
		 if(super.capacity < size)
			 throw new IllegalArgumentException("Can not add new element. List capacity: " + super.capacity + " exceeded");
		 else {
				if(isEmpty())
					head = tail = new Node(e, null, tail);
				else
					head = head.prev = new Node(e, null, head);
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
			if(size()/index >= 2)
				add(head, index, 0, e, false);
			else
				add(tail, index, size()-1, e, true);
		}
		size++;
	}
	
	private void add(Node trav, int index, int indexCounter, E e, boolean rev) {
		if(indexCounter == index - 1) {
			trav.next.prev = new Node(e, trav, trav.next);
			trav.next = trav.next.prev;
			return;
		}
		if(rev)
			add(trav.prev, index, --indexCounter, e, rev);
		else
			add(trav.next, index, ++indexCounter, e, rev);
	}

	@Override
	public void addLast(E e) {
		if(isEmpty())
			add(e);
		else
			tail = tail.next = new Node(e, tail, null);
		size++;
	}

	@Override
	public E get(int index) {
		if(!isEmpty() && index >= 0 && index < size()) {
			if(size()/index >= 2)
				return get(head, index, 0, false);
			else
				return get(tail, index, size()-1, true);
		}
		throw new IndexOutOfBoundsException(index);
	}
	
	private E get(Node trav, int index, int indexCounter, boolean rev) {
		if(indexCounter == index)
			return trav.data;
		if(rev)
			return get(trav.prev, index, --indexCounter, rev);
		else
			return get(trav.next, index, ++indexCounter, rev);
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
			else if(index == size() - 1)
				this.removeLast();
			else if (size()/index >= 2)
				return remove(head, index, 0, false);
			else
				return remove(tail, index, size()-1, true);
		}
		throw new IndexOutOfBoundsException(index);
	}
	
	private E remove(Node trav, int index, int indexCounter, boolean rev) {
		if(indexCounter == index) {
			E e = trav.data;
			trav.prev.next = trav.next;
			trav.next.prev = trav.prev;
			trav = null;
			return e;
		}
		if(rev)
			return remove(trav.prev, index, --indexCounter, rev);
		else
			return remove(trav.next, index, ++indexCounter, rev);
	}

	@Override
	public E removeLast() {
		E e = tail.data;
		
		tail.prev.next = null;
		tail = tail.prev;
		
		return e;
	}
}
