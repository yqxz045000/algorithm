package com.cfyj.algorithm.lru;

public class LRUTest {

	public static void main(String[] args) {
		testMyLRUList1();
		
	}
	
	//基于LinkedHashMap实现，测试结果正确，数据正常设置，并且顺序正确
	private static void testMyLRUList2() {
		MyLRUList2<String,String> lru = new MyLRUList2<String,String>(3);
		lru.set("1", "1");
		lru.set("2", "2");
		lru.set("1", "1");
		lru.set("3", "3");
		lru.set("4", "4");
		lru.set("5", "5");
		lru.set("6", "6");
		
		lru.get("3");
		lru.get("4");
		lru.get("6");
		lru.get("5");
		lru.get("4");
	}
	
	
	//自定义实现，测试结果正确，数据正常设置，并且顺序正确
	private static void testMyLRUList1() {
		MLRUList<String,String> lru = new MLRUList<String,String>(3);
		lru.set("1", "1");
		lru.set("2", "2");
		lru.set("1", "1");
		lru.set("3", "3");
		lru.set("4", "4");
		lru.set("5", "5");
		lru.set("6", "6");
		
		lru.get("3");
		lru.get("4");
		lru.get("6");
		lru.get("5");
		lru.get("4");
	}
}
