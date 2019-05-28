/* Write a program to find the nth super ugly number.

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

Note:
(1) 1 is a super ugly number for any given primes.
(2) The given numbers in primes are in ascending order.
(3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
(4) The nth super ugly number is guaranteed to fit in a 32-bit signed integer. */

public class Solution {
  public int nthSuperUglyNumber(int n, int[] primes) {
    Queue<Integer> minheap = new PriorityQueue<>(n);
    Set<Integer> set = new HashSet<>();
    minheap.offer(1);
    set.add(1);

    for (int i = 0; i < n - 1; i++) {
        int num = minheap.poll();
        for (int j = 0; j < primes.length; j++) {
            if (!set.contains(num * primes[j]) && 
            (long)num * primes[j] < Integer.MAX_VALUE) {
                minheap.offer(num * primes[j]);
                set.add(num * primes[j]);
            }
        }
    }
    return minheap.poll();
  }
}
//time: O(nklogn), space: O(nk)