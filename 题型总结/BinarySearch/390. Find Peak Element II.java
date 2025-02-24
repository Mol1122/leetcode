/* Given an integer matrix A which has the following features :

The numbers in adjacent positions are different.
The matrix has n rows and m columns.
For all i < n, A[i][0] < A[i][1] && A[i][m - 2] > A[i][m - 1].
For all j < m, A[0][j] < A[1][j] && A[n - 2][j] > A[n - 1][j]
We define a position [i, j] is a peak if:

  A[i][j] > A[i + 1][j] && A[i][j] > A[i - 1][j] && 
  A[i][j] > A[i][j + 1] && A[i][j] > A[i][j - 1]
Find a peak element in this matrix. Return the index of the peak.

Example
Example 1:

Input: 
    [
      [1, 2, 3, 6,  5],
      [16,41,23,22, 6],
      [15,17,24,21, 7],
      [14,18,19,20,10],
      [13,14,11,10, 9]
    ]
Output: [1,1]
Explanation: [2,2] is also acceptable. The element at [1,1] is 41, greater than every element adjacent to it.
Example 2:

Input: 
    [
      [1, 5, 3],
      [4,10, 9],
      [2, 8, 7]
    ]
Output: [1,1]
Explanation: There is only one peek.
Challenge
Solve it in O(n+m) time.

If you come up with an algorithm that you thought it is O(nlogm) or O(mlogn), can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?

Notice
Guarantee that there is at least one peak, and if there are multiple peaks, return any one of them. */

public class Solution {
    public List<Integer> findPeakII(int[][] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        int n = nums.length;
        int m = nums[0].length;
        
        return find(nums, 1, n - 2, 1, m - 2);
    }
    
    private List<Integer> find(int[][] nums, int x1, int x2, int y1, int y2) {
        int mid1 = x1 + (x2 - x1) / 2;
        int mid2 = y1 + (y2 - y1) / 2;
        int x = mid1;
        int y = mid2;
        int max = nums[mid1][mid2];
        
        //在横纵中线找最大
        for (int i = y1; i <= y2; i++) {
            if (nums[mid1][i] > max) {
                max = nums[mid1][i];
                x = mid1;
                y = i;
            }
        }
        for (int i = x1; i <= x2; i++) {
            if (nums[i][mid2] > max) {
                max = nums[i][mid2];
                x = i;
                y = mid2;
            }
        }
        boolean isPeak = true;
        if (nums[x - 1][y] > nums[x][y]) {
            isPeak = false;
            x -= 1;
        } else if (nums[x + 1][y] > nums[x][y]) {
            isPeak = false;
            x += 1;
        } else if (nums[x][y - 1] > nums[x][y]) {
            isPeak = false;
             y -= 1;
        } else if (nums[x][y + 1] > nums[x][y]) {
            isPeak = false;
            y += 1;
        }
        if (isPeak) {
            List<Integer> list = Arrays.asList(x, y);
            return list;
        }
        if (x >= x1 && x < mid1 && y >= y1 && y < mid2) {
            return find(nums, x1, mid1 - 1, y1, mid2 - 1);
        }
        if (x >= x1 && x < mid1 && y > mid2 && y <= y2) {
            return find(nums, x1, mid1 - 1, mid2 + 1, y2);
        }
        if (x > mid1 && x <= x2 && y >= y1 && y < mid2) {
            return find(nums, mid1 + 1, x2, y1, mid2 - 1);
        }
        if (x > mid1 && x <= x2 && y > mid2 && y <= y2) {
            return find(nums, mid1 + 1, x2, mid2 + 1, y2);
        }
        List<Integer> list = Arrays.asList(-1, -1);
        return list;
    } 
}
//time: O(n + m), space: O(1). 因为棋盘最多走完n或者最多走完m

public class Solution {
    /**
     * @param a: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }

        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int col = findMaxIndex(nums, mid);

            if (mid - 1 >= 0 && nums[mid - 1][col] > nums[mid][col]) {
                end = mid;
            } else if (mid + 1 < nums.length && nums[mid + 1][col] > nums[mid][col]) {
                start = mid;
            } else {
                return Arrays.asList(mid, col);
            }
        }
        return Arrays.asList(-1, -1); 
    }

    private int findMaxIndex(int[][] nums, int row) {
        int col = 0, max = nums[row][0];

        for (int j = 0; j < nums[row].length; j++) {
            if (nums[row][j] > max) {
                col = j;
                max = nums[row][j];
            }
        }
        return col;
    }
}
//time: O(mlogn), space: O(1)