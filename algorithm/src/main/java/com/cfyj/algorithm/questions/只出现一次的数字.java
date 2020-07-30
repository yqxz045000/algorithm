package com.cfyj.algorithm.questions;
/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
输入: [4,1,2,1,2]
输出: 4
解析:
	1.典型的存在性判断,存在性判断常见的策略如: 位图、散列表、布隆过滤器等,在这里可以选择位图或散列表方式去处理;
	2.由于有不使用额外空间的要求,并且其结构为其余元素均会出现两次,只有一个元素出现一次,那么唯一的区别为某个数只出现一次,
	那么可以采取位运算中 ^运算,  a^b^b =a ,只有某个数为出现一次,则对所有数组内所有元素做一次^运算,值即为只出现一次的数.

 * @author chenfeng
 *
 */
public class 只出现一次的数字 {

	
    //使用位运算, ^: 相同为0,不同为1,
    //^满足以下规则: 满足结合律和交换律  : a^ 0 =a a^b^b=a  b^a^b=a
    //对于出现两次的元素会抵消掉,对于只出现一次的元素会凸显出来
    //故迭代数组进行^ 运算后值即为 出现一次的元素
    public static int singleNumber(int[] nums) {
    	if(nums==null || nums.length==0) {
    		return 0;
    	}
    	
    	int num = 0 ;
    	for (int i = 0; i < nums.length; i++) {
    		num ^= nums[i];
		}

    	return  num ;
    }
	
	public static void main(String[] args) {
		int nums [] = {1,2,2,3,3,1,5,7};
		System.out.println(singleNumber(nums));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
