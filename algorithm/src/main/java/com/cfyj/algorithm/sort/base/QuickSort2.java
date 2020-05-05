package com.cfyj.algorithm.sort.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * 转载:https://blog.csdn.net/Leon_cx/article/details/81487451
 * 排序的思想就是先选择一个基数，通过一趟排序将数据分割成两个部分，比基数小的放在基数前面，比基数大的放在基数后面。再对
 * 这两个部分采用同样的排序规则进行排序。
 * 
 * 解法分析 这种解法紧跟快速排序的定义，将小于、等于、大于基数的元素提取出来，最后再将他们组装起来，很容易理解。
 * 但是这种解法会创建很多list，空间复杂度高，所以衍生出第二种解法。
 * 
 * @author chenfeng
 *
 */
@Slf4j
public class QuickSort2 {

	public static void main(String[] args) {
		test();

	}

	public static void test() { // 基本测试
//		int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };

		Random dRandom = new Random();
		List<Integer> aList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			aList.add(dRandom.nextInt(10 * 10));
		}
		List<Integer> oldList = new ArrayList<>();
		Collections.copy(aList, oldList);
		quickSort(aList);

		log.info("原数组:{},新数组:{}", oldList, aList);
	}

	/**
	 * @Title: quickSort
	 * @Description: 快速排序，使用list实现
	 * @param: items
	 */
	public static void quickSort(List<Integer> items) {
		// 用于存放小于基数的元素
		List<Integer> smaller = new ArrayList<Integer>();
		// 用于存放等于基数的元素
		List<Integer> same = new ArrayList<Integer>();
		// 用于存放大于基数的元素
		List<Integer> larger = new ArrayList<Integer>();

		// 选择中间元素作为基数（选择任意元素作为基数都可以）
		Integer choosenItem = items.get(items.size() / 2);
		// 遍历所有元素，根据元素和基数的大小关系，将元素添加到三个list中
		for (Integer i : items) {
			if (i < choosenItem) {
				smaller.add(i);
			} else if (i == choosenItem) {
				same.add(i);
			} else {
				larger.add(i);
			}
		}

		if (smaller.size() > 1) {
			// 递归排序小于基数的元素
			quickSort(smaller);
		}
		if (larger.size() > 1) {
			// 递归排序大于基数的元素
			quickSort(larger);
		}

		// 清除list中原来的元素
		items.clear();
		// 按照大小顺序把元素添加到list中
		items.addAll(smaller);
		items.addAll(same);
		items.addAll(larger);
	}

}
