package com.cfyj.algorithm.sort.base;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinHeapSort {

  public static void main(String[] args) {

    int arr[] = new int[150];
    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(arr.length );
    }
    log.info("before:{}", arr);
    minHeap(arr);
    log.info("after:{}", arr);
  }

  public static void minHeap(int arr[]) {
    for (int i = (arr.length / 2) - 1; i >= 0; i--) {
      adjustHeap(arr, i, arr.length);
    }

    for (int j = arr.length - 1; j > 0; j--) {
      swap(arr, 0, j);
      adjustHeap(arr, 0, j);
    }
  }

  private static void swap(int[] arr, int i, int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
  }

  private static void adjustHeap(int[] arr, int i, int length) {
    int tmp = arr[i];
    for (int k = 2 * i + 1; k < length; k = k * 2 + 1) {
      if (k + 1 < length && arr[k] > arr[k + 1]) { //唯一与大顶堆有区别的地方，是选取较小的值和父节点比较
        k++;
      }

      if (  tmp > arr[k]) {
        arr[i] = arr[k];
        i = k;
      } else {
        break;
      }
    }
    arr[i] = tmp;
  }

}
