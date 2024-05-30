package uk.co.argon.argon.datastructures.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AbstractHeapImpl<E extends Comparable<E>> implements Heap<E> {
	protected List<E> heap = new ArrayList<>();
	protected Map<E, List<Integer>> indexTracker = new HashMap<>();
	protected int next = 0;
	protected int lc = 0;
	protected int rc = 0;
	protected int p = 0;
	protected int size = 0;
	
	public AbstractHeapImpl() {
	}
	
	public AbstractHeapImpl(Collection<E> c) {
		c.forEach(e -> add(e));
	}
	
	@Override
	public void add(E e) {
		heap.add(next, e);
		
		if(indexTracker.containsKey(e))
			indexTracker.get(e).add(next);
		else
			indexTracker.put(e, new ArrayList<>(Arrays.asList(next)));
		
		if(next > 0)
			heapifyUp(next);
		update(true);
	}
	
	@Override
	public E poll() {
		return remove(0);
	}
	
	@Override
	public E remove(int index) {
		if(isEmpty() || index>=size())
			throw new IndexOutOfBoundsException("Provided index: " + index + " is out of bounds for size: " + size());
		
		E e = heap.get(index);
		swap(e, index);
		update(false);
		calculate(index);
		if(p<index && heap.get(p).compareTo(heap.get(index)) > 0)
			heapifyUp(index);
		else
			heapifyDown(index);
		return e;
	}
	
	@Override
	public E remove(Object o) {
		if(isEmpty() || !contains(o))
			throw new NoSuchElementException("Object: " + o.toString() + " not found");
		
		int index = indexTracker.get(o).get(0);
		E e = remove(index);
		
		return e;
	}
	
	@Override
	public boolean contains(Object o) {
		return indexTracker.containsKey(o);
	}
	
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	
	@Override
	public int size() {
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
		p = (i - 1)/2;
	}
	
	protected abstract void heapifyUp(int index);
	
	protected abstract void heapifyDown(int index);
	
	protected void updateIndexTracker(E e, int i, int j) {
		indexTracker.get(e).remove((Object)i);
		indexTracker.get(e).add(j);
	}
	
	protected void indexTrackerRemove(E e, int i) {
		indexTracker.get(e).remove((Object)i);
		if(indexTracker.get(e).size() == 0)
			indexTracker.remove(e);
	}
	
	private void swap(E e, int index) {
		indexTracker.get(heap.get(index)).remove((Object)index);
		
		heap.set(index, heap.get(next-1));
		updateIndexTracker(heap.get(index), next-1, index);
		
		heap.remove(next-1);
		indexTrackerRemove(e, index);
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
	}
}
