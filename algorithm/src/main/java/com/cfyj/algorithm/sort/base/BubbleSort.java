package com.cfyj.algorithm.sort.base;

import java.util.Arrays;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡排序:https://github.com/hustcc/JS-Sorting-Algorithm/blob/master/1.bubbleSort.md
 * @author chenfeng
 *描述: 	两两比较,当元素不符合顺序时则交换位置.
 *时间复杂度 O(N^2 /2) = O(N^2)
 *步骤: 
 *	双层循环,外层控制比较次数,内层控制遍历位置
 *	外层从1开始,所以次数为i=length-1,因为最后一个元素无需比较,内层控制遍历位置,次数为length- i,意思是已经比较过的无需再次比较
 */
@Slf4j
public class BubbleSort {

	public static void main(String[] args) {
		int[] array = new int[10];
		Random dRandom = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = dRandom.nextInt(array.length*1000);
//			array[i] =array.length-i;
		}
		
		bubbleSort(array);
	}
	
	
	public static int[] bubbleSort(int[] oldarray) {
		boolean flag ;
		int temp ; 
		int num =0; 
		int[] array = Arrays.copyOf(oldarray, oldarray.length);
		for (int i = 1; i < array.length; i++) { //因为是n和n+1比较,最多只需要比较n-1轮,所以从1开始
			flag = true;//当某一次已经排好序,意味着可以提前结束
			for (int j = 0; j < array.length-i; j++) {	//-i目的是,最后位置元素已经和前面元素比较过了,无需再次去比较			
				if(array[j]>array[j+1]) {
//					temp = array[j+1];
//					array[j+1] = array[j] ;
//					array[j] = temp;
					array[j] = array[j] ^ array[j+1] ; 
					array[j+1] = array[j] ^ array[j+1] ; 
					array[j] = array[j] ^ array[j+1] ; 
					
					
					flag=false;
				}
				++num;
			}
			if(flag) {
				break;
			}
		}
		
//		log.info("原数组:{},新数组:{}",oldarray,array);
//		log.info("数组长度:{},迭代次数:{}",array.length,num);
		return array;
	}
	
	
}
