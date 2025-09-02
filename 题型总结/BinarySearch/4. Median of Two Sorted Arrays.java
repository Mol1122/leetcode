/*
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5. 
 */


//Method 1
 class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return 0.0;
        }
        int n = nums1.length + nums2.length;
        if (n % 2 == 0) {
            return (findKth(nums1, 0, nums2, 0, n / 2) + findKth(nums1, 0, nums2, 0, n / 2  + 1)) / 2.0;
        }
        return findKth(nums1, 0, nums2, 0, (n + 1) / 2) / 1.0;
    }
    
    private int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }
        if (k == 1) {
            return A[A_start] < B[B_start] ? A[A_start] : B[B_start];
        }
        int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;
        
        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }
}
//time: O(logn), space: O(n)

//Method 2
public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0.0;
        }
        int n = A.length + B.length;
        if (n % 2 == 0) {
            return (findKth(A, B, n / 2) + findKth(A, B, n / 2 + 1)) / 2.0;
        }
        return findKth(A, B, n / 2 + 1) / 1.0;
    }
    
    private int findKth(int[] A, int[] B, int k) {
        if (A.length == 0) {
            return B[k - 1];
        }
        if (B.length == 0) {
            return A[k - 1];
        }
        int start = Math.min(A[0], B[0]);
        int end = Math.max(A[A.length - 1], B[B.length - 1]);
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (smallerOrEqual(A, mid) + smallerOrEqual(B, mid) < k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (smallerOrEqual(A, start) + smallerOrEqual(B, start) >= k) {
            return start;
        }
        return end;
    }
    
    private int smallerOrEqual(int[] arr, int number) {
        int start = 0, end = arr.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= number) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (arr[start] > number) {
            return start;
        }
        if (arr[end] > number) {
            return end;
        }
        return arr.length;
    }
}