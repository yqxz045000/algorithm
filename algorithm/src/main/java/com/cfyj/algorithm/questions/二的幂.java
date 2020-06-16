package com.cfyj.algorithm.questions;

/**
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

示例 1:

输入: 1
输出: true
解释: 20 = 1
示例 2:

输入: 16
输出: true
解释: 24 = 16
示例 3:

输入: 218
输出: false

解析:
    2的幂次的特点: 
        1.能不断被2整除,直到等于1; 
        2. 2的幂次2进制体现形式为   00010000,只有1个1,

实现:
    因为2的二进制体现为。0001000,只有1个为1,那么移除X最低位的1判断是否等于0即可,有一个特殊值为0

 * @author chenfeng
 *
 */
public class 二的幂 {

	
    public static boolean isPowerOfTwo(int n) {
    	if(n<=0) {//因为可能位负数
    		return false; 
    	}
    	
    	if((n &(n-1))==0) {//清除最低位的1,因为对于2幂次而言,他的二进制只有1个1
    		return true ;     		
    	}
    	return false ; 
    }
    
    public static void main(String[] args) {
		int n = 10 ; 
		System.out.println(isPowerOfTwo(n));
	}
    
    
}
