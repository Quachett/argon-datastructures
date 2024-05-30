package uk.co.argon.argon.datastructures.heap;

import java.util.Collection;

public class MinHeap<E extends Comparable<E>> extends AbstractHeap<E> {
	
	public MinHeap() {
	}
	
	public MinHeap(Collection<E> c) {
		super(c);
	}
	
	@Override
	protected void bubbleUp(int p, int c) {
		while(p >= 0 && heap.get(c).compareTo(heap.get(p))<0) {
			swap(p, c);
			c = p;
			calculate(p);
			p = (pr == pl)? pr:pl;
		}
	}

	@Override
	protected void bubbleDown(int p, int c) {
		while(c<next && heap.get(p).compareTo(heap.get(c))>0) {
			swap(p, c);
			p = c;
			calculate(c);
			if(lc < next && heap.get(lc) != null) {
				if(rc < next && (heap.get(rc) == null || 
						(heap.get(rc) != null &&
							heap.get(lc).compareTo(heap.get(rc)) <= 0)))
					c = lc;
				else
					c = rc;
			}
			else
				break;
		}
	}
	
	private void swap(int p, int c) {
		E e = heap.get(p);
		heap.set(p, heap.get(c));
		heap.set(c, e);
	}

}
