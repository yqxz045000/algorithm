package com.cfyj.algorithm.sort.base;

/**
 * 二分查找思想:
 * 
 * 场景: 适合有序数据集中的目标值存在性或位置查找. 条件: 1.必须有序; 2.一组结果集; 3.判断目标值存在性或所处位置 核心:
 * 通过对中间节点升维方式,不断缩小目标值所处范围. 思想: 1.通过不断与中间元素比较缩小其 目标值可能存在的范围,当找到目标元素或范围内不存在则可得到结果
 * 2.对中间节点升维方式去判断,当目标值大于有序数据集中的中间节点,则缩小查找范围,继续与中间节点比较. 实现: 1.先取中间位置,
 * mid=(higt+low)/2 ,low= 数组范围最低位置; higt=数组范围最高位置
 * 2.目标值与mid比较,若大于mid,则调整low、和mid值,low= mid+1; mid=(higt+low)/2
 * 3.目标值与mid比较,若小于mid,则调整higt、和mid值,higt= mid-1; mid=(higt+low)/2
 * 4.目标值与mid比较,若等于mid则返回 5.不断压缩目标值所处范围,直到low>=mid时说明 元素不存在.
 * 
 * 
 * @author chenfeng
 *
 */
public class BinarySearch {

	/**
	 * 通过二分查找法返回元素在数组中的位置,若不存在则返回-1 条件: array必须为1个有序数据集
	 * 
	 * @param array
	 * @return
	 */
	public static int binarySearch(int[] array, int targetNum) {

		if (array != null && array.length > 0) {
			int low = 0;
			int high = array.length ;//不能-1
			int mid = (low + high) / 2;
			while (low <= high) { //因为会存在low=high=mid情况,即最左或最右值,需要进行一次比较
				if (targetNum == array[mid]) {
					return mid;
				} else if (targetNum > array[mid]) {  //若大于,则移动到mid的右区间
					low = mid + 1;
					mid = (low + high) / 2;
				} else {	//若小于,则移动到mid的左区间
					high = mid - 1;
					mid = (low + high) / 2;
				}
			}

		}

		return -1;
	}

	public static void main(String[] args) {
		int array[] = {1,2,3,4,5,6,11,12,13};
		System.out.println(binarySearch(array,1));
	}
}
