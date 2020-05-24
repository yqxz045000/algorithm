package com.cfyj.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

import com.cfyj.algorithm.lru.old.LRU;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现LRU列表： 使用HashMap保存元素，维护双向链表保存元素访问时序顺序。
 * 实现策略: 维护一条双向链表来维护LRU链表, Map维护k-v关系
 * 目前需要优化的点（还未做准确性测试和性能测试）： 1.将headKey、和tailKey 设置为entry类型，这样便于使用和查看链情况：完成
 * 2.代码太臃肿，查看是否具有优化的空间
 * 
 * 
 * 
 * @author lius
 *
 */
@Slf4j
public class LRUCache3<K, V> implements LRU<K, V> {

	private int length_max = 16;

	private int length = 0;

	private Map<K, Entry<K, V>> map;

	private Entry<K, V> headKey;

	private Entry<K, V> tailKey;

	/**
	 * 获取元素，并把最新访问的元素放到表头
	 */
	@Override
	public V get(K k) {
		/**
		 * 1.判断是否存在该元素 2.不存在则直接返回 3.存在则先获取元素 4.删除该元素在顺序链表中的位置 5.将该元素移到表头
		 */

		if (this.map.containsKey(k)) {
			moveToHead(k);
			return this.map.get(k).getV();
		} else {
			return null;
		}
	}

	private void moveToHead(K k) {
		/**
		 * 1.判断列表长度是否等于1 2.等于则不变动 3.大于则将该元素前后节点变为关联节点 4.将该元素设置为表头
		 */
		if (this.length == 1) {
			return;
		} else if (this.headKey.getK().equals(k)) {
			// 当前元素为表头，无需移动
			return;
		} else {
			// 1.1表中间：前后移动
			// 1.2表末尾：只移动前向,指定前向为表末尾
			// 2.将前向的前向索引改为访问元素的位置
			// 3.指定为最前向，清空前向索引
			Entry<K, V> entry = this.map.get(k);
			if (this.tailKey.getK().equals(k)) {
				entry.getBeforeEntry().setAfterEntry(null);
				this.tailKey = entry.getBeforeEntry();
			} else {
				entry.getBeforeEntry().setAfterEntry(entry.getAfterEntry());
				entry.getAfterEntry().setBeforeEntry(entry.getBeforeEntry());
			}
			this.headKey.setBeforeEntry(entry);
			entry.setAfterEntry(this.headKey);
			this.headKey = entry;
			entry.setBeforeEntry(null);
		}
	}

	/**
	 * 新增元素，会把新增的元素放到表尾部
	 */
	@Override
	public void set(K k, V v) {
		/**
		 * 1.先判断列表长度是否已满 2.已满则清除链表 3.判断元素是否存在，存在则更新key 4.不存在增新增key，设置链表顺序，设置表头，表尾元素
		 * 5.长度自增
		 * 
		 */

		if (isMax()) {
			clear();
		}

		if (this.map.containsKey(k)) {		
			this.map.get(k).setV(v);
			moveToHead(k);
		} else {
			// 不存在
			addEntry(k, v);
		}
	}

	/**
	 * 新增元素
	 * 
	 * @param k
	 * @param v
	 */
	private void addEntry(K k, V v) {
		if (this.length == 0) {
			this.map.put(k, new Entry(k, v, null, null));
			// 第一个元素首尾相等
			this.headKey = this.map.get(k);
			this.tailKey = this.headKey;
		} else {			
			/**
			 * 设置为新头部元素
			 */
			Entry<K, V> headEntry = this.headKey;
			headEntry.setBeforeEntry(new Entry(k, v, null, headEntry));
			this.map.put(k, headEntry.getBeforeEntry());
			this.headKey = headEntry.getBeforeEntry();
		}
		this.length += 1;
	}

	private void clear() {
		/**
		 * 1.如果长度等于1，则删除表头 2.如果长度大于1，则删除表尾部 3.长度自减
		 */
		if (!isMax()) {
			return;
		}
		if (this.length == 1) {
			this.map.remove(this.headKey.getK());
			this.headKey = null;
			this.tailKey = null;

		} else {
			this.tailKey = this.tailKey.getBeforeEntry();
			this.map.remove(this.tailKey.getAfterEntry().getK());
			this.tailKey.setAfterEntry(null);
		}

		this.length -= 1;
	}

	private boolean isMax() {
		if (this.length_max == this.length) {
			return true;
		}
		return false;
	}

	LRUCache3() {
		this.map = new HashMap((int) (this.length_max * 1.35));
	}

	LRUCache3(int max_l) {
		if (max_l <= 0) {
			throw new RuntimeException("列表长度应大于0");
		}
		this.length_max = max_l;
		this.map = new HashMap((int) (this.length_max * 1.35));
	}
	
	@Override
	public String toString() {
		String str ="";
		if(this.headKey!=null) {
			StringBuilder sb = new StringBuilder();
			Entry<K, V>  e =this.headKey;
			while(e !=null ) {
				if( e !=this.headKey) {
					sb.append(",");
				}
				sb.append(e.getK().toString()).append("=").append(e.getV().toString());
				e =e.getAfterEntry();
			}
			str = sb.toString();
		}
		return str ;
	}
	
	public static void main(String[] args) {
		LRUCache3<Integer, Integer> cache = new LRUCache3(2 /* 缓存容量 */ );
		cache.set(1, 1);
		cache.set(2, 2);
		cache.get(1); // 返回 1
		cache.set(3, 3); // 该操作会使得密钥 2 作废】
		log.info("values1:{}", cache);
		System.out.println(cache.get(2)); // 返回 -1 (未找到)
		cache.set(4, 4); // 该操作会使得密钥 1 作废
		log.info("values2:{}", cache);
		System.out.println(cache.get(1)); // 返回 -1 (未找到)
		log.info("values3:{}", cache);
		cache.get(3); // 返回 3
		log.info("values4:{}", cache);
		cache.get(4); // 返回 4
		log.info("values5:{}", cache);
	}
	
}

class Entry<K, V> {

	private K k;
	private V v;
	private Entry<K, V> beforeEntry;
	private Entry<K, V> afterEntry;

	public Entry(K k, V v, Entry<K, V> beforeEntry, Entry<K, V> afterEntry) {
		super();
		this.k = k;
		this.v = v;
		this.beforeEntry = beforeEntry;
		this.afterEntry = afterEntry;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	public V getV() {
		return v;
	}

	public void setV(V v) {
		this.v = v;
	}

	public Entry<K, V> getBeforeEntry() {
		return beforeEntry;
	}

	public void setBeforeEntry(Entry<K, V> beforeEntry) {
		this.beforeEntry = beforeEntry;
	}

	public Entry<K, V> getAfterEntry() {
		return afterEntry;
	}

	public void setAfterEntry(Entry<K, V> afterEntry) {
		this.afterEntry = afterEntry;
	}

}
