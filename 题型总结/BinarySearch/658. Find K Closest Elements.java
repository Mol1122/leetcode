class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //中心开花算法，时间复杂度：O(logn + k) 
//         List<Integer> results = new ArrayList<>();
//         if (arr == null || arr.length == 0) {
//             return results;
//         }
//         int left = getLeftClosest(arr, x);
//         int right = left + 1;
        
//         for (int i = 0; i < k; i++) {
//             if (isLeftCloser(arr, left, right, x)) {
//                 results.add(arr[left--]);
//             } else {
//                 results.add(arr[right++]);
//             }
//         }
//         Collections.sort(results);
//         return results;
    
    }
    
    private boolean isLeftCloser(int[] arr, int left, int right, int x) {
        if (left < 0) {
            return false;
        }
        if (right >= arr.length) {
            return true;
        }
        return Math.abs(arr[left] - x) <= Math.abs(arr[right] - x);
    }
    
    private int getLeftClosest(int[] arr, int target) {
        int start = 0, end = arr.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target < arr[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (arr[end] <= target) {
            return end;
        }
        if (arr[start] <= target) {
            return start;
        }
        return -1;
    }
}


/* 中心开花算法：找到最后一个数< target,然后再用两个指针分别向两边走去找k个数值最近的值
** 时间复杂度：O(logn + k) 
** 转换成两个sorted array找第k小的题 -> [1, 2, 3,|| 8, 9], target = 4, k = 3
**                       leftArray={3, 2, 1}         rightArray={4, 5}
**                                 <--L                R-->
** 时间复杂度：O(logn + logk) = O(logn): logn ->找到分界点， logk ->找到第k小的数(因为是A里找第k/2个数，B里找k/2个数，然后每次去掉小的那k/2个数，每次去掉k的一半，所以是logk)
*/

