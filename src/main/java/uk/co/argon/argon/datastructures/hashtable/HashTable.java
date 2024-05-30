package uk.co.argon.argon.datastructures.hashtable;

import java.util.List;

public interface HashTable<K, V> extends Iterable<K> {
	
	public int size();
	public boolean isEmpty();
	public void clear();
	public boolean containsKey(K k);
	public V put(K k, V v);
	public V get(K k);
	public V remove(K k);
	public V replace(K k, V v);
	public Entry<K, V> getEntry(K k);
	public List<K> getKeys();
	public List<V> getValues();
}
