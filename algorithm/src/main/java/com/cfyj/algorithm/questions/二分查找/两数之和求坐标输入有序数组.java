package com.cfyj.algorithm.questions.二分查找;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

	函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。	
	说明:	
	返回的下标值（index1 和 index2）不是从零开始的。
	你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
	示例:
	
	输入: numbers = [2, 7, 11, 15], target = 9
	输出: [1,2]
	解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
	
	解析： 
	1.二分检索。 其实是根据某个值查找另一个值是否存在，因为数组为有序数组，那么就可以直接按照二分法思想去检索有序数组找出元素是否存在
	2.散列表。 根据一个值查另一个值，典型的映射关系，利用散列表思想检测映射关系存在性
	
 * @author cf
 * @version 创建时间 2020年6月6日
 */
public class 两数之和求坐标输入有序数组 {
	//二分检索
	public static int[] twoSum(int[] numbers, int target) {
		/**
		 * 解析： 其实是根据某个值查找另一个值是否存在，因为数组为有序数组，那么就可以直接按照二分法思想
		 * 去检索有序数组找出元素是否存在
		 * 1.遍历元素，找出需要查找的元素值 
		 * 2.根据元素值和检索的起始节点和结束节点的位置去检测元素是否存在，若不存在则直接返回--二分查找
		 */

		if (numbers == null || numbers.length == 0) {
			return null;
		}

		int tmp;
		int position;
		int[] result = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			tmp = target - numbers[i]; // 需要检索的元素
			position = findNums(numbers, i + 1, numbers.length - 1, tmp); // 二分查找检索元素是否存在
			if (position > 0) { // 当未找到元素时返回-1，
				result[0] = i + 1; // 因为要求返回的下标不从0开始，所以需要+1
				result[1] = position + 1; // 因为要求返回的下标不从0开始，所以需要+1
				return result;
			}

		}

		return null;
	}

	private static int findNums(int[] numbers, int start, int end, int target) {

		int mid = (start + end) / 2;

		while (start <= end) {
			if (numbers[mid] > target) { // 压缩到下边界
				end = mid - 1;
				mid = (start + end) / 2;
			} else if (numbers[mid] < target) { // 压缩到上边界
				start = mid + 1;
				mid = (start + end) / 2;
			} else {
				return mid;
			}
		}

		return -1;
	}
	
	//散列表
	public static int[] twoSum2(int[] numbers, int target) {
		/**
		 * 1.遍历元素，找出需要查找的另一个元素
		 */
		if (numbers == null || numbers.length == 0) {
			return null;
		}
		
		int tmp ; 
		int[] result = new int[2];
		Map<Integer, Integer> map = new HashMap();
		for (int i = 0; i < numbers.length; i++) {
			tmp = target - numbers[i];
			if( map.containsKey(tmp) ) { //因为要按坐标顺序输出，而在找出元素时，其实是根据后面的元素查找前面的元素，所以第一个元素为i，
				result[0] = map.get(tmp)  + 1  ; // 因为要求返回的下标不从0开始，所以需要+1
				result[1] =  i + 1 ; // 因为要求返回的下标不从0开始，所以需要+1
				return result; 
			}
			map.put(numbers[i], i);
		}	
		return null ; 
	}
	
	public static void main(String[] args) {
		int numbers[] = { 2, 7, 11, 15 };
		int target = 9;
		System.out.println( Arrays.toString(twoSum2(numbers, target)) );
	}

}
