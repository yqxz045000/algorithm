package com.cfyj.algorithm.lru.old;

import java.util.LinkedHashMap;

/**
 * 基于LinkedHashMap可以实现LRU算法，维护了一条双向链表，记录了表头、表尾部元素，按照LRU算法特点，可以反向利用这条链表，新进入的元素在表的尾部，当访问新的元素时将其位置添加到链表的尾部，当存储空间不足时移除
 * 表头部元素---其实这个不是最优解,对于linkedHashMap而言本身就提供了实现LRU的策略.
 * 注: 此种方式为过期思想,因为对于LinkedHashMap本身而言提供来LRU的实现方式
 * @author lius
 *
 */
public class MyLRUList2<K, V> implements LRU<K, V>{
	
	private LinkedHashMap<K, V> map ;
	
	private int lengthMax = 16;
	
	private int length = 0;
	
	MyLRUList2(){
		map = new LinkedHashMap<>((int) (this.lengthMax*1.35));
	}
	
	
	MyLRUList2(int length_max){
		this.lengthMax = length_max ;
		map = new LinkedHashMap<>((int) (this.lengthMax*1.35));
	}

	@Override
	public V get(K key) {
		
		/**
		 * 不存在元素则直接返回
		 * 存在元素则先获取然后，
		 * 删除元素，
		 * 重新设置元素，这样就把数据设置到了表尾部
		 */
		V v = null;
		if(map.containsKey(key)) {
			V value = map.get(key);
			map.remove(key);
			map.put(key, value);
		}
		
		return v;
	}
	
	@Override
	public void set(K key, V value) {
		/**
		 * 1.判断是否已满，已满则移除表头元素
		 * 2.判断元素是否已存在，则移除元素然后重新设置值
		 * 3.不存在则新增元素
		 */
		
		if(isMax()) {
			clearHead();
		}
		if(map.containsKey(key)) {
			map.remove(key);
			map.put(key, value);
		}else {
			map.put(key, value);
			this.length+=1;			
		}
	}


	private void clearHead() {		
		if(isMax() ) {
			map.remove(getHeadKey());
		}
		length-=1;
	}
	
	
	public K getHeadKey() {
		return map.keySet().iterator().next();
	}
	
	private boolean isMax() {
		if(this.length>=this.lengthMax) {
			return true;
		}
		return false;
	}
	
}
