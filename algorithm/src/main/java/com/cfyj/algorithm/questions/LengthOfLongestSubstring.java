package com.cfyj.algorithm.questions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		String str = "abcabcbb";
		int num = lengthOfLongestSubstring3(str);
		System.out.println(num);
	}

	private static int lengthOfLongestSubstring(String s) {
		/**
		 * 1.维护一个计数器 2.迭代元素，加入到Map中，若元素不存在则计数器加1，否则则将计数器置为零,在此之前需要将计数器存入到一个列表中
		 *
		 */
		if (s == null || s.length() == 0) {
			return 0;
		}
		TreeSet<Integer> numSet = new TreeSet();
		Set<Character> charSet = new HashSet<>();
		int num = 0;
		char[] charArray = s.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (charSet.contains(charArray[i])) {
				// 清空维护的列表，清空计数器，并以当前key为开头设置元素
				charSet.clear();
				numSet.add(num);
				num = 0;
			}
			charSet.add(charArray[i]);
			num += 1;
		}
		if (num != 0) {
			numSet.add(num);
		}
		return numSet.last();
	}

	private static int lengthOfLongestSubstring2(String s) {
		TreeSet<Integer> numSet = new TreeSet();
		if (s == null || s.length() == 0) {
			return 0;
		}

		char[] charArray = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>( (int)(s.length()*1.34));
		int tmp = 0;
		int pos = 0;
		for (int i = 0; i < charArray.length; i++) {
			if (map.containsKey(charArray[i])) {
				pos = map.get(charArray[i]);
				numSet.add(i - tmp);
				tmp = pos + 1;
				map.remove(charArray[i]);
			}
			map.put(charArray[i], i);
		}
		numSet.add(charArray.length - tmp);
		return numSet.last();

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
