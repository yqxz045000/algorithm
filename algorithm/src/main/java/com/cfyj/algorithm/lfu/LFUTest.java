package com.cfyj.algorithm.lfu;

public class LFUTest {

	
	public static void main(String[] args) {
		LFUList<String,String> lfu = new LFUList<String,String>(3);
		lfu.set("1", "1");
		lfu.set("2", "2");
		lfu.set("3", "3");
		lfu.set("4", "4");
		lfu.set("5", "3");
		lfu.set("6", "4");
		
		
		lfu.get("1");
		lfu.get("2");
		lfu.get("3");

		
	}
}
