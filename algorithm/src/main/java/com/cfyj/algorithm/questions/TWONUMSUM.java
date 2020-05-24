package com.cfyj.algorithm.questions;

import java.util.Arrays;

/**
 * 两数之和求坐标:
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
	示例:
	给定 nums = [2, 7, 11, 15], target = 9
	因为 nums[0] + nums[1] = 2 + 7 = 9
	所以返回 [0, 1]
	
	实现: 冒泡思想,两数相加获取值
 * @author chenfeng
 *
 */
public class TWONUMSUM {

    public static int[] twoSum(int[] nums, int target) {
        
    	int tmp1 =0  ;
    	int tmp2 = 0 ;
    	boolean flag = false ; 
    	for (int i = 0; i < nums.length; i++) {
			if(nums[i]>target) { //TODO 当target存在负数时要除去此判断,因为target可能是小于当前值的
				continue;
			}
    		for (int j = i+1; j < nums.length; j++) {
    			if(nums[j]>target) { //TODO 当target存在负数时要除去此判断,因为target可能是小于当前值的
    				continue;
    			}
    			if(nums[i]+nums[j] == target) {
    				tmp1 = i ;
    				tmp2 = j;
    				flag = true ; 
    				break;
    			}
			}
			
		}
    	if(flag) {
    		int index [] = {tmp1,tmp2};
    		return index; 	    		
    	}else {
			return null ;
		}
    }
	
	public static void main(String[] args) {
		int [] nums = {2, 7, 11, 15} ; 
		int target = 26 ; 
		int [] index = twoSum(nums, target);
		System.out.println(Arrays.toString(index));
	}
	
	
	
}
