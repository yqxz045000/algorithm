package com.cfyj.algorithm.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LRUCache<K, V> {

  private LinkedList<K> lru;

  private Map<K, V> elements;

  private int capacity;

  private static final int DEFAULT_CAPACITY = 10;

  LRUCache() {
    this(DEFAULT_CAPACITY);
  }

  LRUCache(int capacity) {
    this.capacity = capacity;
    lru = new LinkedList<>();
    elements = new HashMap<>();
  }

  
  public void put(K k ,V v) {
    if(checkToMax()){
      removeTail();   
    }
    
    boolean flag = false  ; 
    V v1 = elements.put(k, v);   
    if(v1!=null) {
      flag = true ; 
    }
    addHead(k,flag);
  }
  
  
  public V get(K k) {
    V v = elements.get(k);
    if(v!=null) {
      addHead(k,true);       //元素存在则先删除再放置到头部
    }
    return v ; 
  }
  
  public V remove(K k) {
    
    if(elements.containsKey(k)) {
      V v = elements.remove(k);
      removeNode(k);
      return v ;
    }
    return null ; 
  }
  
  private void removeNode(K k) {
    lru.remove(k);
  }

  private void addHead(K k) {
    addHead(k,false);
  }
  private void addHead(K k , boolean exist) {
    
    if(exist) {
      removeNode(k);
    }else if(checkToMax()){
      removeTail();   
    }
    
    lru.addFirst(k);
  }
  
  private void removeTail() {
    K k = lru.pollLast();
    if(k!=null) {
      elements.remove(k);
    }
  }
  
  private boolean checkToMax() {
    if(lru.size()>= capacity) {
      return true ;
    }
    return false ; 
  }
  
  @Override
  public String toString() {
    return lru.toString();
  }
  
  
  public static void main(String[] args) {
    LRUCache<Integer,Integer> cache = new LRUCache( 2 /* 缓存容量 */ );

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // 返回  1
    cache.put(3, 3);    // 该操作会使得密钥 2 作废】
    log.info("values1:{}" , cache);
    System.out.println(cache.get(2));       // 返回 -1 (未找到)
    cache.put(4, 4);    // 该操作会使得密钥 1 作废
    log.info("values2:{}" , cache);
    System.out.println(cache.get(1));   // 返回 -1 (未找到)
    log.info("values3:{}" , cache);
    cache.get(3);       // 返回  3
    log.info("values4:{}" , cache);
    cache.get(4);       // 返回  4
    log.info("values5:{}" , cache);
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
