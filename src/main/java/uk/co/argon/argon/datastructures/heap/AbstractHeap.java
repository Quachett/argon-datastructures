package uk.co.argon.argon.datastructures.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractHeap<E extends Comparable<E>> implements Heap<E> {
	protected List<E> heap = new ArrayList<>();
	protected boolean left = false;
	protected boolean found = false;
	//protected int i = -1;
	protected int next = 0;
	protected int lc = 0;
	protected int rc = 0;
	protected int pl = 0;
	protected int pr = 0;
	protected int size = 0;
	protected int indexCounter = -1;
	
	public AbstractHeap() {
	}
	
	public AbstractHeap(Collection<E> c) {
		heap.addAll(c);
		size = c.size();
		left = size%2 != 0;
		next = size;
	}
	
	@Override
	public void add(E e) {
		heap.add(next, e);
		if(next > 0) {
			calculate(next);
			if(left)
				bubbleUp(pl, next);
			else
				bubbleUp(pr, next);
		}
		update(true);
	}
	
	@Override
	public E poll() {
		return remove(0);
	}
	
	@Override
	public E remove(int index) {
		E e = heap.get(index);
		
		if(isEmpty() || index >= size() || index < 0)
			throw new IndexOutOfBoundsException(index);
		else if(size() == 1)
			heap.add(0,null);
		else {
			this.swap(index);
			update(false);
			calculate(index);
			if(lc < next && heap.get(lc) != null) {
				if(rc < next && (heap.get(rc) == null || 
						(heap.get(rc) != null &&
							heap.get(lc).compareTo(heap.get(rc)) <= 0)))
					bubbleDown(index, lc);
				else
					bubbleDown(index, rc);
			}
		}
		return e;
	}
	
	@Override
	public E remove(Object o) {
		E e = null;
		if(contains(o))
			e = remove(indexCounter);
		else
			throw new NoSuchElementException();
		return e;
	}
	
	@Override
	public boolean contains(Object o) {
		indexCounter = -1;
		if(!isEmpty()) {
			Iterator<E> itr = heap.iterator();
			while(itr.hasNext()) {
				indexCounter++;
				if(o.equals(itr.next())) {
					found = true;
					break;
				}
			}
		}
		return found;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public void clear() {
		heap.clear();
	}
	
	@Override
	public void print() {
		System.out.println(heap);
	}
	protected void calculate(int i) {
		lc = 2*i + 1;
		rc = 2*i + 2;
		pl = (i - 1)/2;
		pr = (i - 2)/2;
	}
	
	protected abstract void bubbleUp(int p, int c);
	
	protected abstract void bubbleDown(int p, int c);
	
	private void swap(int index) {
		heap.set(index, heap.get(next-1));
		heap.remove(next-1);
	}
	
	private void update(boolean add) {
		if(add) {
			next++;
			size++;
		}
		else {
			next--;
			size--;
		}
		left = !left;
	}
}
