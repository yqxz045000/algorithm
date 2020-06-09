package com.cfyj.algorithm.questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class 最长不含重复字符的字符串 {

	public static void main(String[] args) {
		String str = "abcabcbb";
		int num = lengthOfLongestSubstring3(str);
		System.out.println(num);
	}

	private static int lengthOfLongestSubstring3(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int tmp = 0; //tmp可以理解为一个计算maxnum的起始指针,而map里存的是终止指针
		int maxNum = 0;
		Map<Character, Integer> map = new HashMap<>();
		char[] charArray = s.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (map.containsKey(charArray[i]) && map.get(charArray[i]) >=tmp) {//坚持的是否存在相同字符,因为可能会找到tmp之前的元素,所以需要大于tmp.
				 if( (i - tmp)>maxNum ) { //找出最大子串
					 maxNum = i - tmp ; 
				 }
				 tmp = map.get(charArray[i])+1;//因为要从第二个元素开始算,所以需要加1					 					
				 
			}
			map.put(charArray[i], i);
		}
		
		if((charArray.length - tmp)>maxNum) {
			maxNum = charArray.length- tmp; 
		}
		
		return maxNum;

	}

	
	

	
	
}
