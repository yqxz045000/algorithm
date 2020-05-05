package com.cfyj.algorithm.sort.base;

import java.util.Arrays;

/**
 * 大顶堆
 * @author chenfeng
 *
 */
public class MaxHeapSort {

	public static void main(String[] args) {
		int[] arr = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
//		int[] arr = { 4,6,8,5,9 };

		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	/**
	 * 大顶堆排序:
	 * 1.根据堆非叶子节点排序构建大顶堆
	 * 2.将大顶堆与末尾元素交换--得出第一个最大值
	 * 3.重新对堆排序:从堆顶开始找最大值,放置到堆顶,然后继续交换到末尾
	 * @param arr
	 */
	public static void heapSort(int arr[] ) { 
		//1.构建大顶堆
		for (int i = arr.length/2 -1; i >=0 ; i--) {//arr.length/2 -1  找第一个非叶子节点,从右到左构建大顶堆
			adjustHeap(arr,i,arr.length); //构建本次非叶子节点的大顶堆
		}
		
		//交换最大堆位置,将最大堆交换到末尾
		for (int j = arr.length -1; j >0; j--) {
			swap(arr,0,j);//将大顶堆的堆顶元素交换到数组末尾
			adjustHeap(arr, 0, j); //重新对堆排序:从堆顶开始找最大值,放置到堆顶
		}
		
	}
	
	/**
	 * 将大顶堆的堆顶元素交换到数组末尾
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i]^ arr[j]; 
		arr[j] = arr[i]^ arr[j]; 
		arr[i]= arr[i]^ arr[j]; 
	}

	/**
	 * 比较其非叶子节点的左右节点,先选出最大的子节点,然后再和非叶子节点比较,大于则交换位置,然后继续将非叶子节点和其孙子比较,直到非叶子节点大于最大孙子或无孙子
	 * @param arr 数组
	 * @param i  非叶子节点位置
	 * @param length  需要构建大顶堆的数组长度,需要排序已经构建过的元素
	 */
	private static void adjustHeap(int[] arr, int i, int length) {
		int tmp = arr[i]; //取出非叶子节点
		for (int k = 2*i+1 ; k < length ; k = 2*k+1) { //从非叶子节点的左节点开始,向下遍历,直到没有子节点为止
			if (k+1 < length && arr[k] < arr[k+1]) { //只要是非叶子节点,其左节点一定存在,但右节点不一定存在,所以需要判断 k+1 < length
				k++;//选出其子节点中最大的子节点,当右节点大于左节点时则索引指向右节点
			}
			
			if (arr[k] > tmp) { //当子节点大于非叶子节点时,则交换
				arr[i] = arr[k];
				i=k; //标记非子节点应该交换的位置.
			}else {//当非叶子节点大于最大子节点则终止
				break;
			}	
		}
		arr[i] = tmp ; 	//将非子节点交换到对应的子节点位置,可能是子节点也可能是孙节点
	}
}
