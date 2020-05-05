package com.cfyj.algorithm.sort.base;

import java.math.BigDecimal;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * 比较各排序算法的时间
 * @author chenfeng
 *
 */
@Slf4j
public class SortCompare {

	
	public static void main(String[] args) {
		int lengthsize = 5000 ; 
		int num = 10000;
//		compareQuicksort(lengthsize, num);
		compareBubblesort(lengthsize, num);
//		compareMaxHeapsort(lengthsize, num);
		/**
		 *快排执行次数:1000,数组长度:5000,总耗时:450,平均耗时:0.45ms
		 *冒泡执行次数:1000,数组长度:5000,总耗时:33878,平均耗时:33.878
		 *堆执行次数:1000,数组长度:5000,总耗时:380,平均耗时:0.38
		 *
		 *快排执行次数:100000,数组长度:5000,总耗时:43963,平均耗时:0.43963
		 *冒泡执行次数:10000,数组长度:5000,总耗时:337695,平均耗时:33.7695
		 *堆执行次数:100000,数组长度:5000,总耗时:37736,平均耗时:0.37736
		 */
	}
	
	public static void compareQuicksort(int lengthsize,int num) {
		int[] arr = new int[lengthsize]; 
		Random dRandom = new Random();
		long start = System.currentTimeMillis();
		for (int j = 0; j < num; j++) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = dRandom.nextInt(arr.length*10);
			}			
			QuickSort.quickSort(arr, 0, arr.length-1);
			
		}
		long time = System.currentTimeMillis() -start ;
		
		log.info("快排执行次数:{},数组长度:{},总耗时:{},平均耗时:{}" ,num,lengthsize,time,new BigDecimal(time).divide(new BigDecimal(num)));
	}
	
	public static void compareBubblesort(int lengthsize,int num) {
		int[] arr = new int[lengthsize]; 
		Random dRandom = new Random();
		long start = System.currentTimeMillis();
		for (int j = 0; j < num; j++) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = dRandom.nextInt(arr.length*10);
			}			
			BubbleSort.bubbleSort(arr);
			
		}
		long time = System.currentTimeMillis() -start ;
		log.info("冒泡执行次数:{},数组长度:{},总耗时:{},平均耗时:{}" ,num,lengthsize,time,new BigDecimal(time).divide(new BigDecimal(num)) );
	}
	
	
	public static void compareMaxHeapsort(int lengthsize,int num) {
		int[] arr = new int[lengthsize]; 
		Random dRandom = new Random();
		long start = System.currentTimeMillis();
		for (int j = 0; j < num; j++) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = dRandom.nextInt(arr.length*10);
			}			
			MaxHeapSort.heapSort(arr);
			
		}
		long time = System.currentTimeMillis() -start ;
		log.info("堆执行次数:{},数组长度:{},总耗时:{},平均耗时:{}" ,num,lengthsize,time,new BigDecimal(time).divide(new BigDecimal(num)) );
	}
	
	
}
