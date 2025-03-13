/**
 * public class FlipTool {
 *   static public void flip(int[] arr, int i) {
 *      ...
 *   }
 * }
 */
public class Solution {
    /**
     * @param array: an integer array
     * @return: nothing
     */
    public void pancakeSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int n = array.length;
        while (n > 0) {
            for (int i = 0; i < n; i++) {
                if (array[0] < array[i]) {
                    FlipTool.flip(array, i);
                }
            }
            FlipTool.flip(array, n - 1);
            n--;
        }
    }
}

/* 算法：烙饼排序：每次从0遍历到n-1，找出最大值，flip到最前面，最后flip，这样最大的就跑到最后的，n-1进行下一次遍历
** 时间复杂度: O(n^2) */