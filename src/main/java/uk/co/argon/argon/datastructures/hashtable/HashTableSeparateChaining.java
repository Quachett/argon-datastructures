package uk.co.argon.argon.datastructures.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import uk.co.argon.argon.datastructures.linkedlist.LinkedList;
import uk.co.argon.argon.datastructures.linkedlist.SinglyLinkedList;

public class HashTableSeparateChaining<K, V> implements HashTable<K, V> {
	
	private static final int DEFAULT_CAPACITY = 15;
	private static final double DEFAULT_LOAD_FACTOR = 0.75;
	
	private double maxLoadFactor;
	private int capacity, threshold, size = 0;
	private LinkedList<Entry<K, V>> [] table;
	
	public HashTableSeparateChaining() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public HashTableSeparateChaining(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}

	@SuppressWarnings("unchecked")
	public HashTableSeparateChaining(int capacity, double maxLoadFactor) {
		if(capacity <= 0)
			throw new IllegalArgumentException("Illegal capacity: must be a positive integer");
		
		if(maxLoadFactor <= 0 || maxLoadFactor > 1 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
			throw new IllegalArgumentException("Illegal maxLoadFactor: must be a double between 0 and 1 inclusive");
		
		this.maxLoadFactor = maxLoadFactor;
		this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
		this.threshold = (int) (this.capacity * this.maxLoadFactor);
		this.table = new SinglyLinkedList[this.capacity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void clear() {
		Arrays.fill(this.table, null);
		this.size = 0;
	}

	@Override
	public boolean containsKey(K k) {
		return tableCheckIfContainsKey(normaliseIndex(k.hashCode()), k);
	}

	@Override
	public V put(K k, V v) {
		Entry<K, V> e = new Entry<>(k, v);
		return addEntryToTable(normaliseIndex(e.getHash()), e);
	}

	@Override
	public V replace(K k, V v) {
		V oldValue = v;
		if(!containsKey(k))
			oldValue = put(k, v);
		else{
			Entry<K, V> entry = getEntry(k);
			oldValue = entry.getValue();
			entry.setValue(v);
		}
		return oldValue;
	}

	@Override
	public V get(K k) {
		Entry<K, V> e = getEntry(k);
		if(e == null)
			return null;
		else
			return e.getValue();
	}

	@Override
	public V remove(K k) {
		Entry<K, V> e = getEntry(k);
		if(e == null)
			return null;
		else {
			LinkedList<Entry<K, V>> list = getEntryListFromTable(normaliseIndex(k.hashCode()));
			list.remove(e);
			--size;
			return e.getValue();
		}
	}

	@Override
	public Entry<K, V> getEntry(K k) {
		LinkedList<Entry<K, V>> list = getEntryListFromTable(normaliseIndex(k.hashCode()));
		if(list!=null) {
			for(Entry<K, V> e: list)
				if(e.getKey().equals(k))
					return e;
		}
		return null;
	}

	@Override
	public List<K> getKeys() {
		List<K> keys = null;
		if(size() > 0) {
			keys = new ArrayList<>();
			for(LinkedList<Entry<K, V>> list: table)
				if(list != null)
					getKeys(keys, list);
		}
		return keys;
	}

	@Override
	public List<V> getValues() {
		List<V> values = null;
		if(size() > 0) {
			values = new ArrayList<>();
			for(LinkedList<Entry<K, V>> list: table)
				if(list != null)
					getValues(values, list);
		}
		return values;
	}

	@Override
	public Iterator<K> iterator() {
		return new Iterator<K>() {
			int i = 0;
			List<K> keys = getKeys();
			@Override
			public boolean hasNext() {
				return i < size();
			}

			@Override
			public K next() {
				return keys.get(i++);
			}
		};
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(Entry<K, V> e: getEntrySet())
			sb.append(e.getKey()).append(" => ").append(e.getValue()).append(", ");
		sb.append("]");
		String str = sb.toString().replace(", ]", "]");
		return str;
	}

	private List<Entry<K, V>> getEntrySet() {
		List<Entry<K, V>> entryList = new ArrayList<>();
		for(LinkedList<Entry<K, V>> list: table) {
			if(list!=null)
				getEntryList(entryList, list);
		}
		return entryList;
	}
	
	private void getEntryList(List<Entry<K, V>> entryList, LinkedList<Entry<K, V>> list) {
		for(Entry<K, V> e: list)
			entryList.add(e);
	}
	
	private int normaliseIndex(int hashKey) {
		return (hashKey & Integer.MAX_VALUE) % this.capacity;
	}

	private void getKeys(List<K> keys, LinkedList<Entry<K, V>> list) {
		for(Entry<K, V> e: list)
			keys.add(e.getKey());
	}

	private void getValues(List<V> values, LinkedList<Entry<K, V>> list) {
		for(Entry<K, V> e: list)
			values.add(e.getValue());
	}
	
	private boolean tableCheckIfContainsKey(int index, K k) {
		LinkedList<Entry<K, V>> list = getEntryListFromTable(index);
		if(list!=null) {
			for(Entry<K, V> e: list)
				if(e.getKey().equals(k))
					return true;
		}
		
		return false;
	}
	
	private LinkedList<Entry<K, V>> getEntryListFromTable(int index) {
		return (isEmpty())? null: table[index];
	}
	
	private V addEntryToTable(int index, Entry<K, V> e) {
		LinkedList<Entry<K, V>> list = getEntryListFromTable(index);
		
		if(list == null)
			table[index] = list = new SinglyLinkedList<>();
		
		Entry<K, V> entry = getEntry(e.getKey());
		if(entry == null) {
			list.add(e);
			if(++size > this.threshold)
				resizeTable();
			return null;
		}
		else {
			V v = entry.getValue();
			entry.setValue(e.getValue());
			return v;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void resizeTable() {
		this.capacity *= 2;
		this.threshold = (int) (this.capacity * this.maxLoadFactor);
		
		LinkedList<Entry<K, V>>[] newTable = new LinkedList[this.capacity];
		int i = 0;
		for(LinkedList<Entry<K, V>> list: table) {
			if(list != null) {
				for(Entry<K, V> e: list) {
					int index = normaliseIndex(e.getHash());
					System.out.println("Old index: " + i + "\tNew index: " + index);
					LinkedList<Entry<K, V>> newList = newTable[index];
					if(newList == null)
						newTable[index] = newList = new SinglyLinkedList<>();
					
					newList.add(e);
				}
				table[i].clear();
				table[i] = null;
			}
			i++;
		}
		table = newTable;
	}

}
