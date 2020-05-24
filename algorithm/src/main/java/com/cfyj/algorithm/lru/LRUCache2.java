package com.cfyj.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * LinkedHashMap： 
  实现： 
    LinkedHashMap继承自HashMap，重写了操作Node的方法，内部除使用HashMap维护K-V映射外，还会维护一条双向链表，记录元素插入顺序，每次修改元素时
  都会维护链表。LinkedHashMap提供accessOrder参数，默认为false，代表： 按照访问顺序更改链表位置，头部为最近最少访问元素，尾部为最近最多访问元素，
  可以说天然实现了LRU特性--get、getOrDefault、put（值被替换时也会更改顺序）等。
  
  使用LinkedHashMap实现LRU: 
    LinkedHashMap提供removeEldestEntry， 代表是否移除最老元素，默认为false，为true则代表移除头部元素。
    1.初始化LinkedHashMap时，将accessOrder 置为true代表对元素维护其访问顺序，此时链表变为一条记录操作顺序的双向链表，头部为最少访问，尾部为最多访问
    2.覆盖LinkedHashMap.removeEldestEntry 方法，判断当size>=capacity时返回true，即代表移除最老元素，否则返回false，
    elements = new LinkedHashMap(capacity,0.75f,true){
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            if(this.size() > capacity){
                return true;
            }
            return false;
        }
    };
 * @author cf
 * @create 2020年5月14日
 * @param <K>
 * @param <V>
 */
@Slf4j
public class LRUCache2<K, V> {

  private LinkedHashMap<K, V> elements;

  private int capacity;

  private static final int DEFAULT_CAPACITY = 10;

  LRUCache2() {
    this(DEFAULT_CAPACITY);
  }

  LRUCache2(int capacity) {
    elements = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        if (this.size() > capacity) { // 这里不能使用=，因为当使用put时，元素新增了刚好到达临界值，而这里使用=会将元素扔出去
          return true;
        } else {
          return false;
        }

      }

    };
  }

  public V put(K k, V v) {
    return elements.put(k, v);
  }

  public V get(K k) {
    return elements.get(k);
  }

  @Override
  public String toString() {
    return "LRUCache2 [elements=" + elements;
  }

  public static void main(String[] args) {
    LRUCache2<Integer, Integer> cache = new LRUCache2(2 /* 缓存容量 */ );
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1); // 返回 1
    cache.put(3, 3); // 该操作会使得密钥 2 作废】
    log.info("values1:{}", cache);
    System.out.println(cache.get(2)); // 返回 -1 (未找到)
    cache.put(4, 4); // 该操作会使得密钥 1 作废
    log.info("values2:{}", cache);
    System.out.println(cache.get(1)); // 返回 -1 (未找到)
    log.info("values3:{}", cache);
    cache.get(3); // 返回 3
    log.info("values4:{}", cache);
    cache.get(4); // 返回 4
    log.info("values5:{}", cache);
  }

}
