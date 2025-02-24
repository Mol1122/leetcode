/* 1901. Find a Peak Element II

A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.

Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].

You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.

You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.

 

Example 1:



Input: mat = [[1,4],[3,2]]
Output: [0,1]
Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
Example 2:



Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
Output: [1,1]
Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.*/

class Solution {
    public int[] findPeakGrid(int[][] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int start = 0, end = nums.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int col = findMaxIndex(nums, mid);

            if (mid - 1 >= 0 && nums[mid - 1][col] > nums[mid][col]) {
                end = mid - 1;
            } else if (mid + 1 < nums.length && nums[mid + 1][col] > nums[mid][col]) {
                start = mid + 1;
            } else {
                return new int[]{mid, col};
            }
        }
        return new int[]{-1, -1}; 
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
//这是典型的二维二分->一维二分