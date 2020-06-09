package com.cfyj.algorithm.questions;

import javax.sound.midi.MidiChannel;

/**
 * 实现 int sqrt(int x) 函数。
 * 
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 示例 1:
 * 
 * 输入: 4 输出: 2 示例 2:
 * 
 * 输入: 8 输出: 2 说明: 8 的平方根是 2.82842...,   由于返回类型是整数，小数部分将被舍去。
 * 
 * 解析: 由题可知 y = x^2 , 假设y=4,x=2;那么其实是某个数x的平方不断逼近某个值,而x就是单调递增或递减去查找,是典型的二分查找思想
 * 
 * @author chenfeng
 *
 */
public class X的平方根 {

	public static int mySqrt(int x) {

		if (x == 0 || x == 1) {
			return x;
		}

		int left = 1;// 经过处理后x肯定大于1
		int right = x;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (x / mid == mid) {// 当值非常大值会导致mid*mid越界,所以采用除法
				return mid;
			} else if (x / mid < mid) {
				right = mid - 1;

			} else {
				left = mid + 1;
			}

		}
		return 0;
	}

	public static void main(String[] args) {
		int x = 5;
		System.out.println(mySqrt(x));
	}

}
