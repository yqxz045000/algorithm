package com.cfyj.algorithm.sort.base;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * 参考:https://blog.csdn.net/nrsc272420199/article/details/82587933?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1
 * 描述: 找一个基准节点,根据基准节点进行排序,大于分区节点的值在右边,小于分区节点的值在左边,然后递归执行此策略,最终拿到排序结果
 * 核心: 找到基准数据的在数组中的中间位置,大于在其前面的值,小于在其后面的值
 * 
 * @author chenfeng
 *
 */
@Slf4j
public class QuickSort {

	public static void main(String[] args) {
		statisticsTest();
		
	}
	
	public static int  test() { //基本测试
//		int[] arr = { 49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22 };
		int[] arr = new int[10]; 
		Random dRandom = new Random();
		for (int i = 0; i < arr.length; i++) {
			arr[i] = dRandom.nextInt(arr.length*10);
		}
		int[] oldarray = Arrays.copyOf(arr, arr.length);
		quickSort(arr, 0, arr.length - 1);
		
		log.info("原数组:{},新数组:{}",oldarray,arr);
		log.info("数组长度:{},迭代次数:{}",arr.length,num);
		return num ; 
	}
	
	public static void statisticsTest() {
		int sum = 0; 
		int size = 10000;
		for (int i = 0; i < size; i++) {
			sum += test() ; 
			num= 0 ;
		}
		log.info("基准测试次数:{},平均迭代次数:{}" , size,sum/size);
		/**
		 * 100-10-738 : 数组长度100,测试次数10,平均迭代次数738
		 * 100-741
		 * 1000-743
		 * 10000-744
		 * 100000-744
		 * ----
		 * 10-10-33 注:冒泡为45
		 * 100-30
		 * 1000-31
		 * 10000-31
		 * 
		 * 
		 */
	}
	
	
	static int num = 0; 
	/**
	 * 
	 * @param arr
	 * @param start init: start = 0 ;
	 * @param end  init: end = length-1;
	 * @return
	 */
	public static int [] quickSort(int [] arr , int start , int end) {
		
		if(start < end) {
			int baseIndex = getBaseIndex(arr,start,end);//获取基准节点的索引
			quickSort(arr, start, baseIndex);//对基准节点前面的元素排序
			quickSort(arr, baseIndex+1, end);//对基准节点后面的元素排序.
		}
		return arr;
	}
	
	/**
	 * 获取基准值的索引
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	private static int getBaseIndex(int[] arr, int start, int end) {
		int tmp = arr[start] ; //升序时以最左节点作为基准节点值,降序反之
		while (start < end) { //起始大于等于结束时意味着左右已循环交换完毕,当前节点已经处于基准索引位置
			
			while (start < end && tmp<= arr[end] ) { //先倒叙右遍历,当右边节点大于基准时则 索引递减
				end--;
				num++;
			}
			arr[start] = arr[end]; //倒叙右遍历,右边节点小于基准值时则需要交换,意味着大于基准值的节点都在右边
			
			while (start < end && tmp >= arr[start]) {//然后正序左遍历,当左侧节点小于基准时,索引递增
				start++;
				num++;
			}
			arr[end] = arr[start]; //正序左遍历,当左侧节点大于基准时需要交换,意味着左边的都小于基准值
		}
		arr[start] = tmp;	
		return start;
	}
	
	
}
