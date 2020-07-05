package com.cfyj.algorithm.questions.bit;
/**
 * 找出数组中重复的数字。
	在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字
	重复了几次。请找出数组中任意一个重复的数字。
	示例 1：
	输入：
	[2, 3, 1, 0, 2, 5, 3]
	输出：2 或 3
	限制：
	2 <= n <= 100000
 **/

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 解析: 典型的存在性检测,解决方法为位图、散列表数据结构,因为这里是int类型,所以更适合使用位图方式
 * 
 * @author chenfeng
 *
 */
public class 找出数组中重复的数字 {
	
	//散列表法
	public static int findRepeatNumber(int[] nums) {
		Set<Integer> set = new HashSet<>();
		if (nums != null && nums.length > 0) {

			for (int i = 0; i < nums.length; i++) {
				if (!set.add(nums[i])) {
					return nums[i];
				}
			}
		}
		return 0;
	}
	
	//位图
	public static int findRepeatNumber3(int[] nums) {
		if(nums==null || nums.length==0) {
			return -1 ;
		}
		
		byte[] bitMap = new byte [Integer.MAX_VALUE>>3+1];
		for (int i = 0; i < nums.length; i++) {
			if((bitMap[nums[i]>>3] | i<< (7 &nums[i]))==bitMap[nums[i]>>3]  ) {
				return nums[i];
			}
			bitMap[nums[i]>>3] |= i<< (7 &nums[i]);		
		}
		
		return -1 ; 	
	}
	
	public static void main(String[] args) {
		int nums[] = {26950,26950, 1, 0, 2, 5, 26950 };
		System.out.println(findRepeatNumber3(nums));
	}

}
