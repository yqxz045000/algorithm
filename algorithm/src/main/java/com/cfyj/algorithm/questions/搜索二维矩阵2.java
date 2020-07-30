package com.cfyj.algorithm.questions;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 
 * 每行的元素从左到右升序排列。 每列的元素从上到下升序排列。 示例:
 * 
 * 现有矩阵 matrix 如下：
 * 
 * [ 
 * [1, 4, 7, 11, 15], 
 * [2, 5, 8, 12, 19], 
 * [3, 6, 9, 16, 22], 
 * [10, 13, 14, 17,24], 
 * [18, 21, 23, 26, 30] ] 
 * 给定 target = 5，返回 true。 给定 target = 20，返回 false。
 * 
 * 解析: 
 * 	暴力法: 平铺计算存在性.
 *  取巧法1: 只有元素大于二维数组的起始节点和小于二维数组的尾部节点才可能在其内,先比较是否存在与二维数组内,存在则二分法
 * 检查元素是否存在.
 * 	取巧法2: 因为取巧法1需要遍历多次小的值,那能否直接过滤到这些小值呢? 从元素结构上看, 右上角的元素向左变小,向下变大,
 * 那么比较方式为 若target大于当前值则向下,因为此行的最大值已经比target小了,所以target一定不存在,若target小于当前
 * 值,那么意味着当前列位置一定都大于目标值,可以向右换一列比较.
 * 
 * 
 * @author chenfeng
 *
 */
public class 搜索二维矩阵2 {
	
	//为了避免重复的比较,则可以从右上角开始比较,从结果上看 右上角元素向下递增,向右递减,那么每次都至少淘汰一行或一列数据,
	//避免重复区间范围内的值比较,当大于当前值,则代表当前行全小于目标值,不可能存在,则向下加1, 即比较行向下移动
	//小于当前值,代表当前列都大于目标值,则向右减-1,即比较行向右移动
	public static boolean searchMatrix2(int[][] matrix, int target) {
		boolean flag = false;
		if (matrix == null || matrix.length == 0|| matrix[0].length==0) {
			return flag;
		}
		
		int col =matrix[0].length-1 ;//列
		int row =0; //行
		
		while( row<matrix.length && col>=0  ) {
			
			if(target > matrix[row][col]) { //大于右上角,则代表当前行全小于目标值,不可能存在,则向下加1, 即比较行向下移动
				row++;
				continue;
			}else if (target < matrix[row][col]) {//小于右上角,代表当前列都大于目标值,则向右减-1,即比较行向右移动
				col--;
			}else {
				flag = true ; 
				break;
			}	
		}
		return flag;
	}


	//取巧法1
	public static boolean searchMatrix1(int[][] matrix, int target) {
		boolean flag = false;
		if (matrix == null || matrix.length == 0) {
			return flag;
		}

		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != null && matrix[i].length > 0 && target >= matrix[i][0]
					&& target <= matrix[i][matrix[i].length - 1]) {
				flag = binarySearch(matrix[i], target);
				if (flag) {
					return flag;
				}
			}
		}
		return flag;
	}

	public static boolean binarySearch(int[] arr, int target) {
		int start = 0;
		int end = arr.length;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (arr[mid] > target) {
				end = mid - 1;
			} else if (arr[mid] < target) {
				start = mid + 1;
			} else {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 } };
		int target = 13;
		System.out.println(searchMatrix2(matrix, target));
	}

}
