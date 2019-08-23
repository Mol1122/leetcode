/* Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 */
class Solution {
    public int nthUglyNumber(int n) {
        Queue<Long> minheap = new PriorityQueue<>(n);
        Set<Long> set = new HashSet<>();
        
        minheap.offer(1L);
        set.add(1L);
        for (int i = 0; i < n - 1; i++) {
            long min = minheap.poll();
            if (!set.contains(min * 2)) {
                minheap.offer(min * 2);
                set.add(min * 2);
            }
            if (!set.contains(min * 3)) {
                minheap.offer(min * 3);
                set.add(min * 3);
            }
            if (!set.contains(min * 5)) {
                minheap.offer(min * 5);
                set.add(min  * 5);
            }
        }
        return minheap.poll().intValue();
    }
}
//算法： BFS2
//time: O(nlogn), space: O(n)

class Solution {
    public int nthUglyNumber(int n) {
        List<Integer> results = new ArrayList<>();
        results.add(1);
        int p2 = 0, p3 = 0, p5 = 0; //pointer to 2, 3, 5想象成赛道
        
        for (int i = 1; i < n; i++) {
            int lastNum = results.get(i - 1);
            while (results.get(p2) * 2 <= lastNum) {
                p2++;
            }
            while (results.get(p3) * 3 <= lastNum) {
                p3++;
            }
            while (results.get(p5) * 5 <= lastNum) {
                p5++;
            }
            results.add(Math.min(
                Math.min(results.get(p2) * 2, results.get(p3) * 3),
                results.get(p5) * 5
            ));
        }
        return results.get(n - 1);
    }
}

/* 算法：这个算法是最优解，但是是很难想到的
** 时间复杂度：O(n)
** 空间复杂度: O(1) */