package com.cfyj.algorithm.questions;

/**
 * 
 * 
 * 
 * 
 * 
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * 
 * 解析： 元素行和列有序排列，但会存在不同元素，所以从局部看是局部有序，但整体看却不是有序，那么就需要先将元素构建为一个有序数组，然后再求TopK，
 * topK的解法有几种，若元素有序则可以使用数组定位获取topK，还有一种即通过堆排序来获取TopK。 关键点：
 * 因为元素已经具有了一定顺序，那能否按照一定规则将元素构建为一个有序数组，
 * 
 * 实现： 1.暴力解法就是将元素平铺映射到数组上，然后求TopK，如通过堆排序获取TopK。
 * 
 * 
 * 
 * 
 * 
 * @author cf
 * @version 创建时间 2020年6月6日
 */
public class 有序矩阵中第K小的元素 {

	public static int kthSmallest2(int[][] matrix, int k) {
		/**
		 * 
		 * 1.
		 * 2.
		 * 3.
		 * 
		 * 
		 */
		
		
		
		
		return 0;
	}

	public  static int kthSmallest(int[][] matrix, int k) {
		int row = matrix.length;
		int col = matrix[0].length;
		int left = matrix[0][0];
		int right = matrix[row - 1][col - 1];
		while (left < right) {
			// 每次循环都保证第K小的数在start~end之间，当start==end，第k小的数就是start
			int mid = (left + right) / 2;
			// 找二维矩阵中<=mid的元素总个数
			int count = findNotBiggerThanMid(matrix, mid, row, col);
			if (count < k) {
				// 第k小的数在右半部分，且不包含mid
				left = mid + 1;
			} else {
				// 第k小的数在左半部分，可能包含mid
				right = mid;
			}
		}
		return right;
	}

	private  static int findNotBiggerThanMid(int[][] matrix, int mid, int row, int col) {
		// 以列为单位找，找到每一列最后一个<=mid的数即知道每一列有多少个数<=mid
		int i = row - 1;
		int j = 0;
		int count = 0;
		while (i >= 0 && j < col) {
			if (matrix[i][j] <= mid) {
				// 第j列有i+1个元素<=mid
				count += i + 1;
				j++;
			} else {
				// 第j列目前的数大于mid，需要继续在当前列往上找
				i--;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
		int nums = 8;
		kthSmallest(matrix, nums);
	}

}
