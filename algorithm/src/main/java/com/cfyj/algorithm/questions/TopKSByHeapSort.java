package com.cfyj.algorithm.questions;

import java.util.Arrays;

/**
 * 求topK个元素,基于堆排序求topK
 * 
 * @author chenfeng
 *
 */
public class TopKSByHeapSort {

	public static void main(String[] args) {
		int arr[] = { 3, 2, 1, 5, 6, 4 };
//		int arr[] = {2,1};
		int[] ks = findKthLargest(arr, 3);
		System.out.println(Arrays.toString(ks));
	}

	public static int[] findKthLargest(int[] nums, int k) {
		for (int i = (nums.length / 2) - 1; i >= 0; i--) {
			adjust(nums, i, nums.length);
		}
		swap(nums, 0, nums.length -1 );//提前交换
		
		for (int j = nums.length - 1; (j > nums.length - k) && j>=0  ; j--) {
			adjust(nums, 0, j);//先构建大顶堆,从堆顶开始向下找最大的
			swap(nums, 0, j-1);//交换到队尾-1为止
		}
		//可能会出现,top长度大于数组长度,返回第一个值
		if ( k > nums.length ) {
			return nums; 
		}else {
			return Arrays.copyOfRange(nums, nums.length - k, nums.length );//这里是绝对顺序,从1开始,所以尾部不需要减1			
		}
	}

	public static void adjust(int[] arr, int i, int length) {
		int tmp = arr[i];
		for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
			if ((k + 1) < length && arr[k] < arr[k + 1]) {//找最大的子节点
				k++;
			}

			if (tmp < arr[k]) {
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
