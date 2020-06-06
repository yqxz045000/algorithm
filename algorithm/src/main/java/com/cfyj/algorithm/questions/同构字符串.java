package com.cfyj.algorithm.questions;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. 同构字符串:https://leetcode-cn.com/problems/isomorphic-strings/
给定两个字符串 s 和 t，判断它们是否是同构的。
如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

示例 1:
输入: s = "egg", t = "add"
输出: true
示例 2:
输入: s = "foo", t = "bar"
输出: false
示例 3:
输入: s = "paper", t = "title"
输出: true
说明:
你可以假设 s 和 t 具有相同的长度。
 * @author chenfeng
 *
 */

/**
 * 解析:
    同构字符串其结构相同,只是元素类型不同,那么核心思想就是两边的结构一致,基于此有几种思想:
    1.元素替换法,直接将一方的元素替换为另一方,若替换后相同,则为同构,如:
    aabbcd 和 eeffgc 两个字符串,以第一个元素为基准,替换第二个元素,如e替换为a、f替换为b,g替换为c,c替换为d,若替换后和基准相同意味着为同构字符串.
   
    2.保留映射关系法. 其实是1的衍生,因为1需要将目标元素全替换一遍后比较,而方案2是采用映射法,将左右两个字符串的字符做一一对应,若未对应上则说非同构,如:
    aabbcd 和 eeffgc 两个字符串,映射关系为 a=e、b=f、c=g、d=c,插入映射关系时先检测是否存在值,若存在则检测映射关系是否一致,一致则跳过,不一致则说明出现了不同映射结构,说明非同构型, 这里需要注意的是 还需要反向遍历一次,如abc 和 dee这种, a=d、b=e、c=e,对于abc而言没有重复元素,所以可以映射上,但是对于以相反顺序映射时 dee和abc 为 d=a 、e=b、e=c,相同元素e被映射了两个不同元素,所以是非同构型.
   
    3.第三方转换. 仍然利用映射原理,其实是将两个元素转为第三种格式去处理,若结构相同,第三种格式的字符串就一致,举例如: 英文 和法语做比较看是否一致,那么可以将它们都转为美式英语,若一致则说明意思相同,在这里的意思是若一致其结构也就相同.

 * @author chenfeng
 *
 */
public class 同构字符串 {

	//因时间问题,采用解法2去处理,后续有时间希望通过解法3去解
	
    public static  boolean isIsomorphic(String s, String t) {
        
    	return isIsomorphicStr(s,t) && isIsomorphicStr(t,s); 
    }
    
	//一个元素被映射过多次,意味着其结构不同
    public static boolean isIsomorphicStr(String s, String t) {
    	if(s==null || s.length()==0 || t==null || t.length()==0) {
    		return true ; 
    	}
    	
        char[] charArray1 = s.toCharArray();
        char[] charArray2 = t.toCharArray();
        Map<Character, Character> map = new HashMap<Character, Character>();
    	for (int i = 0; i < charArray1.length; i++) {		
    		if(map.containsKey(charArray1[i])) {
    			if(!map.get(charArray1[i]).equals(charArray2[i])) {
    				return false ; 
    			}
    			continue;
    		}
    		map.put(charArray1[i], charArray2[i]);
		}
     	
    	return true ; 
    }
    
    public static void main(String[] args) {
    	String s = "egg";
    	String t = "ade" ; 
    	System.out.println(isIsomorphic(s, t));
    			
	}
	
}
