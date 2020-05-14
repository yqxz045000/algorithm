package com.cfyj.algorithm;

import java.util.Arrays;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		int[] array = new int[10];
		Random dRandom = new Random();
		for (int i = 0; i < array.length; i++) {
//			array[i] = dRandom.nextInt(array.length * 10);
			array[i] = i;
		}

		quicksort(array, 0, array.length - 1);
		System.out.println(Arrays.toString(array));
	}

	public static void quicksort(int arr[], int start, int end) {
		if (start < end) {
			int baseIndex = getIndex(arr, start, end);
			quicksort(arr, start, baseIndex);
			quicksort(arr, baseIndex + 1, end);
		}

	}

	private static int getIndex(int[] arr, int start, int end) {
		int tmp = arr[start];
		while (start < end) {

			while (start < end && tmp <= arr[end]) {
				end--;
			}
			arr[start] = arr[end];
			while (start < end && tmp >= arr[start]) {
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = tmp;
		return start;
	}
}
