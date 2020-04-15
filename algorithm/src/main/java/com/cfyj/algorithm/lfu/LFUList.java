package com.cfyj.algorithm.lfu;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * LFU 记录链表的热度值，根据热度值淘汰最低的元素. 方案1：只记录logc热度最低的key，当最低的元素logc值更改时遍历链表，选出最低的logc更新
 * 1.对哈希表排序：只排出最低的一个值即可，而不需要把所有的热度值都排序 2.记录热度值 3.淘汰最低值 4.热度递增 5.热度递减
 * 
 * 方案2：使用treemap的特性，当算法涉及到经常的排序，因为元素热度是随时变化的，所以不推荐使用
 * 
 * 
 * @author lius
 *
 */
public class LFUList<K, V> {

	private Map<K, LFUNode<V>> dataMap = new HashMap<K, LFUNode<V>>();
	private K tail_key = null;
	private int tail_logc = Integer.MAX_VALUE;
	private static final int INIT_LOGC = 5;
	private int max_length = 16;

	public LFUList() {
		// TODO Auto-generated constructor stub
	}

	public LFUList(int length) {
		max_length = length;
	}

	public synchronized V get(K key) {
		/**
		 * 1.判断元素是否存在 2.递增元素热度 3.如果是最后一个元素则遍历选出logc最少的key
		 */

		if (!dataMap.containsKey(key)) {
			return null;
		} else {
			incrLogc(key);
			return dataMap.get(key).getV();
		}
	}

	private void incrLogc(K key) {
		/**
		 * 1.当元素为尾部元素时，对key进行重排序，选出logc最小的key
		 */
		LFUNode<V> value = dataMap.get(key);
		if (incrLogcAlgorithm()) {
			value.setLogc(value.getLogc() + 1);
		}

		if (tail_key.equals(key)) {
			// 遍历元素，选出logc最小的key
			updateTailKey();
		}
	}

	private synchronized void updateTailKey() {

		if (dataMap != null && dataMap.size() > 0) {

			int logc = tail_logc;
			K key = tail_key;
			for (Map.Entry<K, LFUNode<V>> entry : dataMap.entrySet()) {
				if (entry.getValue().getLogc() < tail_logc) {
					logc = entry.getValue().getLogc();
					key = entry.getKey();
				}
			}
			tail_logc = logc;
			tail_key = key;
		}

	}

	public synchronized void set(K key, V value) {
		/**
		 * 1.判断值是否存在，存在则只更新Value 2.值不存在则新增值 3.更新logc值，判断小于tail_logc 4.更新tail_key
		 * 
		 */

		if (dataMap.containsKey(key)) {
			dataMap.get(key).setV(value);
			return;
		} else if (dataMap.size() == this.max_length) {
			removeTail();
		}

		dataMap.put(key, new LFUNode<V>(INIT_LOGC, value));
		if (isTail(key)) {
			this.tail_key = key;
			this.tail_logc = dataMap.get(key).getLogc();

		}
	}

	private void removeTail() {
		dataMap.remove(this.tail_key);
		this.tail_key = null;
		this.tail_logc = Integer.MAX_VALUE;
		updateTailKey();

	}

	private boolean isTail(K key) {
		if (dataMap.get(key).getLogc() < this.tail_logc) {
			return true;
		}
		return false;
	}

	/**
	 * 递增算法，决定是否递增
	 * 
	 * @return
	 */
	private boolean incrLogcAlgorithm() {
		return true;

	}

}

@Data
class LFUNode<V> {

	private int logc;

	private V v;

	public LFUNode(int logc, V v) {
		super();
		this.logc = logc;
		this.v = v;
	}

	public LFUNode() {
		super();
	}

}
