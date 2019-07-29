/* Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.

These books list in a row and each person can claim a continous range of books. For example, one copier can copy the books from i-th to j-th continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Return the shortest time that the slowest copier spends.

Example
Example 1:

Input: pages = [3, 2, 4], k = 2
Output: 5
Explanation: 
    First person spends 5 minutes to copy book 1 and book 2.
    Second person spends 4 minutes to copy book 3.
Example 2:

Input: pages = [3, 2, 4], k = 3
Output: 4
Explanation: Each person copies one of the books.
Challenge
O(nk) time */

public class Solution {
    public int copyBooks(int[] pages, int k) {
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int start = 0, end = 0;
        for (int page : pages) {
            start = Math.max(start, page);
            end += page;
        }
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (check(pages, mid, k)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (check(pages, start, k)) {
            return start;
        }
        return end;
    }
    
    private boolean check(int[] pages, int limit, int k) {
        int count = 0, left = 0;
        
        for (int p : pages) {
            if (p > limit) {
                return false;
            }
            if (p > left) {
                count++;
                left = limit;
            }
            left -= p;
        }
        return count <= k;
    }
}
//算法：二分答案，右边copy的书最多，用的人最少，满足要求，左边copy的书最少，用的人最多，不满足要求。找第一个满足要求的时间/page数
//time: O(log(len) * n), space: O(1)