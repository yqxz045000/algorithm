package com.cfyj.algorithm.questions.双指针;

import java.util.Arrays;

/**
 * 合并排序数组: 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *  初始化 A 和 B 的元素数量分别为 m 和 n。 示例: 输入: A = [1,2,3,0,0,0], m = 3 B = [2,5,6], n = 3
 * 输出: [1,2,2,3,5,6] 说明: A.length == n + m
 * 
 * 解析: 两个数组都有序,最好的方式就是利用其本身有序性去排列,避免已经有序的元素仍然需要排列,一般的做法就是合并两个数组排序,
 * 实现: 1.利用已有元素顺序排序,即双指针策略,以较长数组A为底,迭代数组A,找数组B最小元素在A中的位置,若小于某个位置的元素,
 * 则替换目标位置,并将A的元素放置到B上,然后继续找B.迭代完成后将B中剩余元素放置到A后
 * @author chenfeng
 *	未完成
 */
public class 合并排序数组 {

	
	public static void merge(int[] A, int m, int[] B, int n) {
		if(B ==null || B.length==0) {
			return ; 
		}else if(A==null || A.length ==0 ) {
			System.arraycopy(B, 0, A, m, n);
		}
		
		int BPointer = 0 ;		
		int APointer = 0 ;	
		for (int i = 0; i < m; i++) {
			if( B[BPointer] <= A[i]) {
				if(i==0) {
					System.arraycopy(A, i, A, i+1, m-i);					
				}else {
					System.arraycopy(A, i, A, i+1, m-i+1);					
				}
//				A[m+i-1] = A[i] ;  //将A的大元素放到后面去
				A[i] = B[BPointer++];//将B的小元素放到当前位置	
				APointer++; //A新增的元素,后续将B剩余元素放置到A的这个节点上
			}		
		}
		//此时B中小于A的元素都放入到了A中,A中大于B的元素迁移到了 n的位置,剩下要将B大于A的元素迁移到A的后面
		if(BPointer< n) {//意味着B的元素还没有迁移完成,将其放置到A的后面,A当前的位置为 自身元素+B中插入的元素 =m+APointer
			for (int j = BPointer; j < n; j++) {// 迭代B从0开始,因为可能存在部分插入的情况
				if(B[j]>0) {
					A[m+APointer++] = B[j];					
				}
			}	
		}	
    }
	
	
	public static void main(String[] args) {
//		int [] A = {1,3,5,0,0,0,0};
		int [] A = {4,5,6,0,0,0};
		int m = 3;
//		int [] B  = {2,4,8,9} ;
		int [] B  = {1,2,3} ;
		int n = 3; 
		merge(A, m, B, n);	
		System.out.println(Arrays.toString(A));
	}
	
	
	
	
	
	
	
}
