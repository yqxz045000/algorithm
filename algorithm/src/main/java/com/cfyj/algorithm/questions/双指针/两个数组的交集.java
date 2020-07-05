package com.cfyj.algorithm.questions.双指针;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。 示例 1: 输入: nums1 = [1,2,2,1], nums2 = [2,2] 输出: [2] 示例
 * 2: 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4] 输出: [9,4] 说明: 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * 
 * 解析: 1.无序数组交集： 双指针策略
  	若两个元素有序时，则可以通过双指针的方式去检测集合元素在另一个集合元素中的存在性，如集合A 和集合B，先对AB进行排序，
  	排序后用两个指针 i j 去标识AB的起始节点，若A[i] > B[j],在移动b的指针，直到B[j] =A[i] orB[j] > A[i] ，这是再
  	移动A的指针，当元素B[j] =A[i] 时，就找到了目标的交集
  
  	2.存在性检测思想,在一个结果集中查找另一个结果集是否存在,并且还需要对结果去重,有几种策略: set去重检测存在性、位图去重检测存在性等,
 * 而位图需要维护多个较大的byte数组,故推荐采用set实现.
 * 
 * 1.位图
 * 2.set
 * 实现: 位图方式
 * 
 * @author chenfeng
 *
 */
public class 两个数组的交集 {

	public static int[] intersection(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null) {
			return null;
		}

		// 取一个最小长度的size作为结果集长度
		int tmp = nums1.length < nums2.length ? nums1.length : nums2.length;

		int result[] = new int[tmp];
		int k = 0;
		byte[] data = new byte [Integer.MAX_VALUE/2]; // 存储数据的容器,对于int数组来说所有值都能存储在int内,所以只需要一个int即可存储所有元素
		byte[] data2 = new byte [Integer.MAX_VALUE/2]; 
		for (int i = 0; i < nums1.length; i++) { // 将数据存储在容器内
			data[nums1[i]>>3] |= 1 <<  (7 & nums1[i]);
		}

		for (int j = 0; j < nums2.length; j++) {
			if ((data[nums1[j]>>3] | 1 <<  (7 & nums1[j])) == data[nums1[j]>>3] && (data2[nums1[j]>>3] | 1 <<  (7 & nums1[j])) != data2[nums1[j]>>3]) { // 添加元素容器未发生改变,说明值已经存在了
				data2[nums1[j]>>3] |= 1 <<  (7 & nums1[j]);
				result[k++] = nums2[j];// 这里仍然可能会存在重复的情况
			}
		}
		return Arrays.copyOf(result, k); // 因为临时容器可能会大于结果集,所以需要做切割
	}

	public static int[] intersection2(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null) {
			return null;
		}
		
		int tmp = nums1.length < nums2.length ? nums1.length : nums2.length;
		Set<Integer> tmpSet = new HashSet(tmp);
		Set<Integer> set = new HashSet(tmp);
		int[] result = new int [tmp] ;
		int k = 0 ; 
		for (int i = 0; i < nums1.length; i++) {
			set.add(nums1[i]);
		}
		
		for (int j = 0; j < nums2.length; j++) {
			if(set.contains(nums2[j])) {
				if(tmpSet.add(nums2[j])) {
					result[k++] = nums2[j]; 
				}
			}
		}
		 
		return Arrays.copyOf(result, k);
	}
	
	public static void main(String[] args) {
		int nums1 [] = {1,2,3,4,5,6,7,8,9,11};
		int[] nums2 = {9,8,7,6,5,4,3,2,1,10};
		System.out.println(Arrays.toString(intersection2(nums1, nums2)));
	}

}
