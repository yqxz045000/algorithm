package com.cfyj.algorithm.lru;

public interface LRU<K,V> {

	public void set(K k,V v);
	
	public V get(K key);
	
}
