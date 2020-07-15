package com.cfyj.algorithm.questions.杂;

import java.util.LinkedHashMap;

/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短
 * ，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。

示例1:
 输入："aabcccccaaa"
 输出："a2b1c5a3"
示例2:
 输入："abbccd"
 输出："abbccd"
 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
提示：

字符串长度在[0, 50000]范围内。
	
	解析:
    核心是对连续字符串做压缩,有几种策略:
         1.数据压缩存储策略. 采用散列表存储,但要注意每次遇到不重复的元素后需要清空前面的数据,因为后面还可能会出现相同数据--不适用
         2.双指针思想. 维护起始节点指针和第一次不重复指针,计算两者之间的差集就是重复元素的长度,然后压缩存储,
         3.直接存储. 迭代字符集,若后一个元素和当前元素相同则计数,不同则拼接上次的重复count值,然后存储元素本身

 * @author chenfeng
 *
 */
public class 字符串压缩 {
	
	public static void main(String[] args) {
		String aString = "aabcccccaaa";
		aString = compressString(aString);
		System.out.println(aString);
	}
	
	public static String compressString(String S) {
		if (S == null || S.length() <= 2) {
            return S;
        }
        StringBuilder sb = new StringBuilder().append(S.charAt(0));
        int cnt = 1;
        for (int i = 1; i < S.length(); i++) {
            // 如果i与i-1相同，cnt累加
            if (S.charAt(i) == S.charAt(i - 1)) {
                cnt++;
            } else { 
                // 否则拼接上i-1的次数，从i开始重新计数
                sb.append(cnt).append(S.charAt(i));
                cnt = 1;
            }
        }
        return sb.append(cnt).length() < S.length()? sb.toString(): S;
    }

	
}
