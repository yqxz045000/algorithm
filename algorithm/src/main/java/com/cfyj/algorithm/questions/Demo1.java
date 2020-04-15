package com.cfyj.algorithm.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法题：给一堆硬币的array，返回所有的组合 有两种情况,一种是AB的方式,可以通过冒泡解决;一种是AXn的方式,通过多层组合解决.
 * 
 * @author chenfeng
 *
 */
public class Demo1 {

	public final static int array[] = { 1, 2, 3 };
	
	public static void main(String[] args) {
		System.out.println(bubbloSortCompose(array));
	}
	
	//AB形式
	public static List<String> bubbloSortCompose(int[] array) {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {//双向完全循环
			for (int j = 0; j < array.length ; j++) {
				if (i!=j) {//不能和自身比较
					result.add(array[i] + "" + array[j]);					
				}
			}
		}
		return result;
	}
	
	List<String> result = new ArrayList<>();
	//AXn形式
	public static void completeCompose(int[] array) {
		
		for (int i = 0; i < array.length; i++) {
			
		}
		
	}
	
	
	
}
