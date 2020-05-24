package com.cfyj.algorithm.questions;

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
	解析:
		1.两个数组都已经排好序了.
		2.取两边最大值不断放置到A的末尾,直到数组B遍历结束
		
 */
public class SortedMergeArray2 {
	
	public static void main(String[] args) {
		int [] A = {1,2,3,0,0,0};
		int m = 3;
		int [] B  = {2,5,6} ; 
		int n = 3 ; 
		merge(A, m, B, n);	
		System.out.println(Arrays.toString(A));
	}
	
	
	public static void merge(int[] A, int m, int[] B, int n) {
		if(m==0) {
			A[m] = B[m];
		}
		int tmp ; 
		while(n>0 && m>0 ) { 
			if(A[m-1] > B[n-1]) {
				A[m+n-1]  =  A[m-1];
				A[m-1] = 0 ;
			}else {
				A[m+n-1]  = B[--n];
			}
//			A[m+n-1] = A[m-1] > B[n-1] ? A[--m] : B[--n] ; 		
		}
		
		
    }
	

	
}
