/* Find the Kth smallest number s such that s = 3 ^ x * 5 ^ y * 7 ^ z, x > 0 and y > 0 and z > 0, x, y, z are all integers.

Assumptions

K >= 1
Examples

the smallest is 3 * 5 * 7 = 105
the 2nd smallest is 3 ^ 2 * 5 * 7 = 315
the 3rd smallest is 3 * 5 ^ 2 * 7 = 525
the 5th smallest is 3 ^ 3 * 5 * 7 = 945
 */
 
public class Solution {
  public long kth(int k) {
    Queue<Long> minheap = new PriorityQueue<>(k);
    Set<Long> set = new HashSet<>();

    minheap.offer(3 * 5 * 7L);
    set.add(3 * 5 * 7L);
    for (int i = 0; i < k - 1; i++) {
        long num = minheap.poll();
        if (!set.contains(num * 3)) {
            minheap.offer(num * 3);
            set.add(num * 3);
        }
        if (!set.contains(num * 5)) {
            minheap.offer(num * 5);
            set.add(num * 5);
        }
        if (!set.contains(num * 7)) {
            minheap.offer(num * 7);
            set.add(num * 7);
        }
    }
    return minheap.poll();
  }
}
//time: O(klogk), space: O(k)