package com.cfyj.algorithm.questions;

/**
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
 * 
 * 示例 1：
 * 
 * 输入：00000000000000000000000000001011 输出：3 解释：输入的二进制串
 * 00000000000000000000000000001011 中，共有三位为 '1'。
 * 
 * 示例 3：
 * 
 * 输入：11111111111111111111111111111101 输出：31 解释：输入的二进制串
 * 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * 
 * 
 * 解析: 求整数值二进制1的个数,有两种思路: 1.找有多少个0 ; 2.找有多少个1 1.找有多少个0:
 * 将整数值不断除以2,若无余数代表能被2整除,且有1个;当不能被2整除代表此位不为1,向右移动 2.找有多少个1: 使用规律: 清零最低位的1 x= x &
 * (x-1),不断清除最低位的1,且累加,当等于0时代表值已被清空
 * 
 * 规律: 清零最低位的1 x= x & (x-1) ,不断清零最低位1并且累加值,当值等于0时代表元素1已被清空
 * 
 * @author chenfeng
 *
 */
public class 位1的个数 {

	public static int hammingWeight(int n) {
		int num = 0;
		while (n != 0) {
			n = n & (n - 1);//去除最低位的1
			num += 1;//累加
		}

		return num;
	}
	
	public static int hammingWeight2(int n) {
		int num = 0;
		
		while (n != 0) {
			num+=1;
			n= n >> 1;
		}

		return num;
	}	
	public static void main(String[] args) {
//		int x = 10 ; 
//		System.out.println(hammingWeight2(x));
		
		int X = 10 ; 
		int y = X= X& -X ; 
		System.out.println(y);
	}
	
}
