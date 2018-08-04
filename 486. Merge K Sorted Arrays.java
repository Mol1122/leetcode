public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        } 
        return merge(arrays, 0, arrays.length - 1);
    }
    
    private int[] merge(int[][] arrays, int start, int end) {
        if (start >= end) {
            return arrays[start];
        }
        int middle = start + (end - start) / 2;
        int[] left = merge(arrays, start, middle);
        int[] right = merge(arrays, middle + 1, end);
        int[] result = new int[left.length + right.length];
        
        int l = 0, r = 0, index = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                result[index++] = left[l++];
            } else {
                result[index++] = right[r++];
            }
        }
        while (l < left.length) {
            result[index++] = left[l++];
        }
        while (r < right.length) {
            result[index++] = right[r++];
        }
        return result;
    }
}

/* 算法：利用merge sort的思想。因为每次分两半，每次merge需要O(n), 一共有O(logk)层
** 难点：array不同于list, array直接在merge后马上merge就好，不需要另外的方法去弄 */