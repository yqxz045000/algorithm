package com.cfyj.algorithm.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索旋转数组。https://leetcode-cn.com/problems/search-rotate-array-lcci/
 * 给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 * 示例1: 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5 输出:
 * 8（元素5在该数组中的索引） 示例2: 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14],
 * target = 11 输出：-1 （没有找到） 提示: arr 长度范围在[1, 1000000]之间
 * 
解析: 本质上是一个有序数组,但被多次旋转之后只存在局部有序,这里存在多种相同元素,所以不能使用散列表去映射关系查找,所以可以先找出数组中存在的有序区间,然后从每个有序区间中查找元素,基于有序的原则可以采取二分法去检索元素是否存在,先找到元素则先返回,那么也就返回了重复元素最小的索引.
这里需要注意: 存在临界节点的区间判断和查找有序区间时需要从前往后查找,因为要找出最小的元素.
 * 
 * 实现: 1.先遍历数组,将数组分为几个有序区间, 2.遍历有序区间通过二分法找目标值.
 * 临界点: 这里采用从后到前遍历元素,需要考虑数组的临界点,如index=0时后向 index-1无节点,
 * 	所以需要对index=0做特殊处理,如达到index=1时就提前判断
 * 
 * @author chenfeng
 *
 */
public class SearchArray {

	public static int search(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return -1;
		}

		List<String> list = new ArrayList<>();
		int end = arr.length - 1;
		// 确定有序区间的范围
		for (int i = arr.length - 1; i > 0; i--) {
			
			if (arr[i] <= arr[i - 1]) { //这里采取=是因为若元素值相等时,其实已经不满足有序了
				// 说明不满足顺序,这时需要存不满足顺序的起点到终点.
				list.add(i + "=" + end);
				end = i - 1;
			}
			
			if(i==1) {//当元素=1时即最后一次迭代,
				if(arr[i] <= arr[i - 1]) { //若不满足,则需要单独对0进行封装范围
					list.add(0 + "=" +0 );//因为末尾元素无法比较了
				}else {
					list.add(0 + "=" + end);//说明1和0仍满足顺序,所以直接设置范围为0-end		
				}
			}
		
		}
		// 通过二分法去找目标范围内是否存在元素.
		for (int j = list.size() - 1; j >= 0; j--) {
			String range = list.get(j);
			String[] split = range.split("=");
			int index = binarySearch(arr, new Integer(split[0]), new Integer(split[1]), target);
			if (index >= 0) {
				return index;
			}
		}

		return -1;
	}
	
	//二分查找
	public static int binarySearch(int[] arr, int start, int end, int target) {

		int mid = (start + end) / 2;
		while (start <= end) {

			if (target == arr[mid]) {
				return mid;
			} else if (target > arr[mid]) {
				start = mid+1; 
				mid = (start + end) / 2;
			} else {
				end = mid - 1; 
				mid = (start + end) / 2;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
//		int arr [] = {9,8,7,10,11,12,15,16,17} ; 
		int arr [] =  {5,5,5,1,2,3,4,5};
		int target = 5;
		System.out.println(search(arr, target));
	}
	
	
	
}
