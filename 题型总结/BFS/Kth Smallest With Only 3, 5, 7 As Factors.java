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
      //time: O(n), space: O(n)
      Deque<Long> three = new ArrayDeque<>();
      Deque<Long> five = new ArrayDeque<>();
      Deque<Long> seven = new ArrayDeque<>();
      long seed = 3 * 5 * 7L;
      three.offerLast(seed * 3);
      five.offerLast(seed * 5);
      seven.offerLast(seed * 7);
      
      long num = seed;
      for (int i = 0; i < k - 1; i++) {
          if (three.peekFirst() < five.peekFirst() &&
              three.peekFirst() < seven.peekFirst()) {
                num = three.pollFirst();
                three.offerLast(num * 3);
                five.offerLast(num * 5);
                seven.offerLast(num * 7);
           } else if (five.peekFirst() < three.peekFirst() &&
                      five.peekFirst() < seven.peekFirst()) {
                num = five.pollFirst();
                five.offerLast(num * 5); //因为x*3已经在上一轮被generate过了
                seven.offerLast(num * 7); // x=3^a*5^b, x*3=3^(a+1)*5^b = 3^(a+1)*5^(b-1)*5, and 3^(a+1)*5^(b-1) < x
           } else {
                num = seven.pollFirst();
                seven.offerLast(num * 7);
           }
      }
      return num;

    //time: O(klogk), space: O(k)
   /* Queue<Long> minheap = new PriorityQueue<>();
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
    return minheap.poll();  */
     
    //time: O(n), space: O(1)
  /*  List<Long> results = new ArrayList<>();
    results.add(3 * 5 * 7L);
    int p3 = 1, p5 = 1, p7 = 1;

    for (int i = 0; i < k - 1; i++) {
        long lastNum = results.get(i - 1);
        while (results.get(p3) <= lastNum) {
            p3++;
        }
        while (results.get(p5) <= lastNum) {
            p5++;
        }
        while (results.get(p7) <= lastNum) {
            p7++;
        }
        results.add(Math.min(
                Math.min(results.get(p3) * 3, results.get(p5) * 5),
                results.get(p7) * 7));
    }
    return results.get(k - 1);  */
  }
  
}
