package com.cfyj.algorithm.questions.存在性检测和去重;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和求坐标:
 * 
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
	示例:
	给定 nums = [2, 7, 11, 15], target = 9
	因为 nums[0] + nums[1] = 2 + 7 = 9
	所以返回 [0, 1]
	
	原因: 使用散列表时问题变成了求一个元素在Map中是否存在,这个元素为 target-nums[i],所以通过散列表方式来维护元素所在位置,但是需要维护双层Map,因为可能存在元素重复的情况,双层Map可以避免元素被覆盖
	
	实现: 散列表思想,找两个数,其实根据一个key和target去检测另一个值是否存在,存在则返回.
	需要维护双层map,为了避免存在相同值时导致元素被覆盖,但若也存在相同值时则需要丢弃元素
	
	执行用时 3 ms, 在所有 Java 提交中击败了81.96% 的用户
	内存消耗 :39.9 MB, 在所有 Java 提交中击败了5.06% 的用户

 * @author chenfeng
 *
 */
public class 两数之和求坐标2 {

	public static int[] twoSum(int[] num, int target) {
		//因为可能存在相同元素,则维护双层map解决重复元素问题
		Map<Integer, Integer> map = new HashMap();
		Map<Integer, Integer> map2 = new HashMap();
		int tmp = 0;
		for (int i = 0; i < num.length; i++) {
			if(map.containsKey(num[i])) {
				map2.put(num[i], i);			
			}else  {
				map.put(num[i], i);
			}
			
			tmp = target - num[i];
			if ( map.get(tmp)!=null && map.get(tmp)!=i  ) {
				int[] index = { map.get(tmp), i };
				return index;
			}else if(map2.get(tmp)!=null && map2.get(tmp)!=i) {
				int[] index = { map2.get(tmp), i };
				return index;
			}
		}

		return null;
	}
	
	public static void main(String[] args) {
		int [] nums = {3,3} ; 
		int target = 6 ; 
		int [] index = twoSum(nums, target);
		System.out.println(Arrays.toString(index));
	}
	
	
	
}
