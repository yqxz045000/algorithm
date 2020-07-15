package com.cfyj.algorithm.questions.排序或求TopK;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/smallest-k-lcci/
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

示例：

输入： arr = [1,3,5,7,2,4,6,8], k = 4
输出： [1,2,3,4]
提示：

0 <= len(arr) <= 100000
0 <= k <= min(100000, len(arr))


 * 
 * @author chenfeng
 *
 */
public class 最小K个数 {
	
	public static void main(String[] args) {
		int arr [] = {1,2,3,4,5,6,7,8};
		int k = 3; 
		int [] arrB = smallestK(arr, k);
		
		System.out.println(Arrays.toString(arrB));
	}
	
    public static int[] smallestK(int[] arr, int k) {
    	return findKthinimum(arr, k);
    }
    
    
    
	public static int[] findKthinimum(int[] nums, int k) {
		if(k==0 || nums==null ||nums.length==0 ) {
			return new int [0];
		}else if(k >= nums.length) {//k>length,说明需要全部
			return nums ; 
		}
		
		for (int i = (nums.length / 2) - 1; i >= 0; i--) {
			adjust(nums, i, nums.length);
		}
		
		for (int j = nums.length - 1; (j >= nums.length - k) && j>=0  ; j--) {
			swap(nums, 0, j);
			adjust(nums, 0, j);
		}
		
		//K < length
		return Arrays.copyOfRange(nums, nums.length-k , nums.length);

	}
	
	//最小堆
	public static void adjust(int[] arr, int i, int length) {
		int tmp = arr[i];
		
		for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
			if ((k + 1) < length && arr[k] > arr[k + 1]) {//找最大的子节点
				k++;
			}

			if (  k < length && tmp > arr[k]) {
				arr[i] = arr[k];
				i = k;
			}else {
				break; //找到后即可退出
			}
		}
		arr[i] = tmp;
	}

	public static void swap(int arr[], int i, int j) {
		if (i==j) {//当位置相等的元素交换时会导致值变为0,所以需要加这个限制
			return ; 
		}
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];	
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
