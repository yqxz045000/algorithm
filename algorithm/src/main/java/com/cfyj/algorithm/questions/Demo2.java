package com.cfyj.algorithm.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 完全组合
 * 
 * @author chenfeng
 *
 */
public class Demo2 {

	public final static String array[] = { "A", "B", "C" };

	public static void main(String[] args) {
		
//			System.out.println("a 非的结果是：" + (8&0));
//			ThreadLocal tLocal = new ThreadLocal<>();
//			tLocal.set(value);
//		Thread.currentThread().
		
		ConcurrentHashMap map = new ConcurrentHashMap();
		map.put("a", "b");
		map.put("a", "b");
		map.size();
		
		
	}

	public void test1() {

		int num = array.length / 2 + (array.length % 2) != 0 ? 1 : 0;
		List<String> result = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			List<String> list = new ArrayList<>();
			list.add(array[i]);
			list.add(array[i + 1]);
			bubbloSortCompose(list);

		}

	}

	public static List<String> bubbloSortCompose(List<String> array) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < array.size(); i++) {// 双向完全循环
			for (int j = 0; j < array.size(); j++) {
				if (i != j) {// 不能和自身比较
					result.add(array.get(i) + array.get(j));
				}
			}
		}
		return result;
	}

}
