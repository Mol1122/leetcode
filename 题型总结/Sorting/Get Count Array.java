/* Given an array A with all positive integers from [1...N]. How to get an array B such that B[i] represents how many elements A[j] (j > i) in array A that are smaller than A[i].

Assumptions:

The given array A is not null.
Examples:

A = { 4, 1, 3, 2 }, we should get B = { 3, 0, 1, 0 }.
Requirement:

time complexity = O(nlogn). */

public class Solution {
  public int[] countArray(int[] array) {
    if (array == null || array.length == 0) {
        return array;
    }
    int[] indexArray = initIndex(array);
    int[] countArray = new int[array.length];
    int[] helper = new int[array.length];

    mergeSort(array, indexArray, countArray, helper, 0, array.length - 1);
    return countArray;
  }

  private void mergeSort(int[] array, int[] indexArray, int[] countArray, int[] helper, int start, int end) {
    if (start >= end) {
        return;
    }
    int mid = start + (end - start) / 2;
    mergeSort(array, indexArray, countArray, helper, start, mid);
    mergeSort(array, indexArray, countArray, helper, mid + 1, end);
    merge(array, indexArray, countArray, helper, start, end);
  }

  private void merge(int[] array, int[] indexArray, int[] countArray, int[] helper, int start, int end) {
    copyArray(indexArray, helper, start, end);
    int middle = start + (end - start) / 2;
    int left = start;
    int right = middle + 1;
    int index = left;

    while (left <= middle) {
        if (right > end || array[helper[left]] < array[helper[right]]) {
            countArray[helper[left]] += (right - middle - 1); //易错
            indexArray[index++] = helper[left++];
        } else {
            indexArray[index++] = helper[right++];
        }
    }
  }

  private void copyArray(int[] indexArray, int[] helper, int start, int end) {
    for (int i = start; i <= end; i++) {
        helper[i] = indexArray[i];
    }
  }

  private int[] initIndex(int[] array) {
    int[] indexArray = new int[array.length];
    for (int i = 0; i < array.length; i++) {
        indexArray[i] = i;
    }
    return indexArray;
  }
}
//time: O(nlogn), space: O(n)