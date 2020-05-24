package com.cfyj.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于LinkedHashMap本身实现.
 * 
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
  
  
 * @author chenfeng
 *
 */
@Slf4j
public class LRUCache1 {

    private int capacity ;

    private LinkedHashMap<Integer,Integer> elements ; 

    public LRUCache1(int capacity) {
        this.capacity = capacity;
        elements = new LinkedHashMap<Integer,Integer> (capacity,0.75f,true){
             @Override
	      protected boolean removeEldestEntry(Map.Entry<Integer,Integer>  eldest){
               if(  this.size() > capacity){
                  return true ;   
               } 
               return false ; 
            }
        };

     
    }
    
    public int get(int key) {
        Integer integer =  elements.get(key);
        return integer!=null ? integer : -1;
    }
    
    public void put(int key, int value) {
        elements.put(key,value);
    }
    
    
    public static void main(String[] args) {
    	LRUCache1 cache = new LRUCache1( 2 /* 缓存容量 */ );

    	cache.put(1, 1);
    	cache.put(2, 2);
    	log.info("1:{}",cache.elements);
    	cache.get(1);       // 返回  1
    	cache.put(3, 3);    // 该操作会使得密钥 2 作废
    	cache.get(2);       // 返回 -1 (未找到)
    	cache.put(4, 4);    // 该操作会使得密钥 1 作废
    	cache.get(1);       // 返回 -1 (未找到)
    	cache.get(3);       // 返回  3
    	cache.get(4);       // 返回  4

	}
    
	
}
