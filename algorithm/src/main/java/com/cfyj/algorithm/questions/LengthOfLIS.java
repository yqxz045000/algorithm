package com.cfyj.algorithm.questions;

/**
 * 
 * 最长上升子序列:https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。 示例: 输入: [10,9,2,3,7,101,18] 输出: 4 解释: 最长的上升子序列是
 * [2,3,7,101]，它的长度是 4。 说明: 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * 
 * 
 * 解析:
 * 
 * 实现:
 * 
 * @author chenfeng
 *
 */
public class LengthOfLIS {

	public static int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}else if(nums.length == 1) {
			return 1 ; 
		}
		
		int max = 0;//最大长度
		int tmp = 0;//计算max的起始指针
		for (int i = 0; i + 1 < nums.length; i++) {
			if (nums[i] > nums[i + 1]) {
				if (i - tmp >= max) {// =是因为需要更新tmp指针
					max = i +1 - tmp ;//从第二个节点开始算范围 
					tmp = i; //维护计算起始点的指针
				}
			}
		}

		return max;
	}
	
	
	public static void main(String[] args) {
		int nums [] = {10,9,2,5,3,7,101,18};
		System.out.println(lengthOfLIS(nums));
		
		
	}

}
