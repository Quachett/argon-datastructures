package uk.co.argon.argon.datastructures.heap;

import java.util.Collection;

public class MinHeapImpl<E extends Comparable<E>> extends AbstractHeapImpl<E> {

	public MinHeapImpl() {
	}
	
	public MinHeapImpl(Collection<E> c) {
		super(c);
	}
	
	@Override
	protected void heapifyUp(int index) {
		while(index > 0) {
			calculate(index);
			if(index > 0 && shouldSwap(index, p)) {
				swap(p, index);
				index = p;
			}
			else
				break;
		}
	}

	@Override
	protected void heapifyDown(int index) {
		while(lc < next && rc < next) {
			calculate(index);
			
			if(lc < next && rc < next) {
				int c = (heap.get(lc).compareTo(heap.get(rc)) <= 0)? lc: rc;
				
				if(!shouldSwap(index, c)) {
					swap(index, c);
					index = c;
				}
				else
					break;
			}
		}
	}
	
	private void swap(int p, int c) {
		E e = heap.get(p);

		updateIndexTracker(heap.get(c), c, p);
		heap.set(p, heap.get(c));
		
		updateIndexTracker(e, p, c);
		heap.set(c, e);
	}

	private boolean shouldSwap(int from, int to) {
		return heap.get(from).compareTo(heap.get(to)) <= 0;
	}
}
