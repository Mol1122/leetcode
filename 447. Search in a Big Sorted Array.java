public class Solution {
    /*
     * @param reader: An instance of ArrayReader.
     * @param target: An integer
     * @return: An integer which is the first index of target.
     */
    public int searchBigSortedArray(ArrayReader reader, int target) {
        int index = 1; 
        while (reader.get(index - 1) < target) { //难点！！
            index = index * 2;
        }
        int left = 0, right = index - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (reader.get(left) == target) {
            return left;
        }
        if (reader.get(right) == target) {
            return right;
        }
        return -1;
    }
}

/* 算法：binary search反过来就是不断乘以2，一次找到任何一个比target打的index作为right
** 难点：reader.get的时候，是index - 1
** 时间复杂度：O(logn) */