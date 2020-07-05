package com.cfyj.algorithm.questions.排序或求TopK;

import java.util.Arrays;

/**
 * 合并排序数组:
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
	初始化 A 和 B 的元素数量分别为 m 和 n。
	示例:
	输入:
	A = [1,2,3,0,0,0], m = 3
	B = [2,5,6],       n = 3
	输出: [1,2,2,3,5,6]
	说明:
	A.length == n + m
	实现: 1.暴力破解,先复制数组到一个数组中,然后采用堆排序
	结果:
	执行用时 :
1 ms
, 在所有 Java 提交中击败了
30.46%
的用户
内存消耗 :
39.6 MB
, 在所有 Java 提交中击败了
100.00%
的用户
	
 */
public class 合并排序数组 {
	
	public static void main(String[] args) {
		int [] A = {1,2,3,0,0,0};
		int m = 3;
		int [] B  = {2,5,6} ; 
		int n = 3 ; 
		merge(A, m, B, n);	
		System.out.println(Arrays.toString(A));
	}
	
	
	public static void merge(int[] A, int m, int[] B, int n) {
		System.arraycopy(B, 0, A, m, n);
		heapsort(A);
    }
	
	public static void heapsort(int nums []) {
		for (int i = nums.length/2 -1; i >=0 ; i--) {
			adjustHeap(nums,i,nums.length);
		}
		
		for (int j = nums.length-1 ; j >0; j--) {
			swap(nums , 0,j);
			adjustHeap(nums,0,j);
		}
		
		
	}

	private static void swap(int[] nums, int i, int j) {
		if(i==j) {
			return  ; 
		}
		nums[i] = nums[i] ^nums[j] ;
		nums[j] = nums[i] ^nums[j] ;
		nums[i] = nums[i] ^nums[j] ;
	}

	private static void adjustHeap(int[] nums, int i, int length) {
		int tmp = nums[i]; 
		for (int k = 2*i+1; k < length ; k= 2*k +1) {
			if((k+1)<length && nums[k]<nums[k+1]) {
				k++;
			}
			if(tmp < nums[k]) {
				nums[i] = nums[k];
				i = k; 
			}		
		}
		nums[i] = tmp ; 
	}
	
	
	
}
