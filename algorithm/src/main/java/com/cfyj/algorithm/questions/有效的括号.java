package com.cfyj.algorithm.questions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
/**
 * 有效的括号:
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

	有效字符串需满足：
	
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	注意空字符串可被认为是有效字符串。
如:
输入: "()"
输出: true

 * * 解析： 两两配对，当出现右结构时，栈顶一定是左结构，若不是则说明不符合括号匹配规则.
	* 实现：
	* 采用栈结构实现，迭代字符集，判断字符当为右结构时，判断栈顶是否为左结构，非左结构则不匹配，若遍历完成后栈不为空， 说明没有被完全匹配，也不满足条件
	* 临界点：
	*  1.当前栈中无元素，但第一次出现右结构，说明也不匹配
	*  2.当迭代完成后栈不为空，说明有未匹配的左结构
 * @author chenfeng
 *
 */
public class 有效的括号 {

	private static Map<Character, Character> cache = new HashMap();
	  static {
	    cache.put(')', '(');
	    cache.put(']', '[');
	    cache.put('}', '{');
	  }
	  public static boolean isValid(String s) {
	    if (s == null || s.length() == 0) {
	      return true;
	    }
	    char[] charArray = s.toCharArray();
	    Stack<Character> stack = new Stack<>();
	    for (int i = 0; i < charArray.length; i++) {
	      if (cache.containsKey(charArray[i])) { // 为右结构
	        if( stack.isEmpty()) { //当前栈中无元素，但第一次出现右结构，说明也不匹配
	          return false ;
	        } else if (   !stack.pop().equals(cache.get(charArray[i]))) { // 判断栈顶是否非左结构
	          return false;
	        }
	        continue; // 为左结构，消除匹配，继续迭代
	      }
	      stack.push(charArray[i]); // 为左结构则加入到栈中
	    }
	    if (stack.isEmpty()) {
	      return true;
	    }
	    return false;
	  }
	  
	  
	  
}
