package com.cfyj.algorithm.questions;

import java.util.HashSet;
import java.util.Map;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
	示例 1：
	输入: s = "leetcode"
	输出: false 
	示例 2：
	输入: s = "abc"
	输出: true
	限制：
	0 <= len(s) <= 100
	如果你不使用额外的数据结构，会很加分。
	
	思路: 其实是存在性检测,而这里要做的就是一边存储一边检测存储的值是否存在,存在则返回false,否则直到末尾返回true-代表唯一
		1.暴力解法: 使用散列表存储每个字符,当添加时若元素存在则说明字符不唯一
		结果: 双百
		2.位图--利用位图的唯一特性,每个元素占一位,时间复杂度为O(1),空间复杂度为O(1).
			题中说了总长度为100,并且ASCII码表个数为128个,所以用两个整型来存储char,
			当char大于64则存储在hight,小于64则存储到low中,然后存储前先检测元素存在性,存在则返回false
	涉及到的位运算:
		|:一个为1则为1
		&:全为1则为1
		^: 相同为0,不相同为1.
			
			
 * @author chenfeng
 */
public class IsUniqueStr {
	
	public static void main(String[] args) {
		String astr = "l2etcod";
		System.out.println(isUnique2(astr));
		
	}
	
	public static boolean isUnique(String astr) {
		char[] charArray = astr.toCharArray();
		HashSet<Character> set = new HashSet<>();
		for (int i = 0; i < charArray.length; i++) {
			if(!set.add(charArray[i])) {
				return false ; 
			}
		}
		
		return true ;
    }
	
	public static boolean isUnique2(String astr) {
		char[] charArray = astr.toCharArray();
		long height = 0 ; 
		long low = 0 ;
		for (char ch: charArray) {
			
			if(ch<64) {
				long l2 = 1L << ch ; 
				if((low | l2 )== low ) {
					return false ; 
				}
				low |= l2 ; 
			}
			
			if(ch>64) {
				long l1 = 1L << ch-64 ; 
				if((height | l1 )== height ) {
					return false ; 
				}
				height |= l1 ; 
			}
		}
		return true ; 
    }
	
}
