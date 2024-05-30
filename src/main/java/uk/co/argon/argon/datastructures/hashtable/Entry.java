package uk.co.argon.argon.datastructures.hashtable;

public class Entry<K, V> {
	private K key;
	private V value;
	private int hash;
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
		this.hash = key.hashCode();
	}
	
	public boolean equals(Entry<K, V> other) {
		return (hash != other.hash)? false: key.equals(other.key);
	}
	
	@Override
	public String toString() {
		return "[" + key + " => " + value + "]";
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.hash = key.hashCode();
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public int getHash() {
		return hash;
	}
}
