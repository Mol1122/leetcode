/* Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. Find the coordinates of the points that is k-th closest to <0,0,0>.

We are using euclidean distance here.

Assumptions

The three given arrays are not null or empty
K >= 1 and K <= a.length * b.length * c.length
Return

a size 3 integer list, the first element should be from the first array, the second element should be from the second array and the third should be from the third array
Examples

A = {1, 3, 5}, B = {2, 4}, C = {3, 6}

The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)

The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9) */

public class Solution {
  public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
    List<Integer> results = new ArrayList<>();
    if (a == null || b == null || c == null) {
        return results;
    }
    Queue<List<Integer>> minheap = new PriorityQueue<>(2 * k, 
        new Comparator<List<Integer>>() {
        public int compare(List<Integer> list1, List<Integer> list2) {
            long dist1 = getDistance(list1, a, b, c);
            long dist2 = getDistance(list2, a, b, c);
            if (dist1 == dist2) {
                return 0;
            }
            return dist1 < dist2 ? -1 : 1;
        }
    });
    Set<List<Integer>> visited = new HashSet<>();
    List<Integer> curr = Arrays.asList(0, 0, 0);
    minheap.offer(curr);
    visited.add(curr);

    int[] dx = {1, 0, 0};
    int[] dy = {0, 1, 0};
    int[] dz = {0, 0, 1};
    for (int i = 0; i < k; i++) {
        curr = minheap.poll();
        for (int j = 0; j < 3; j++) {
            List<Integer> next = Arrays.asList(curr.get(0) + dx[j], 
                                               curr.get(1) + dy[j],
                                               curr.get(2) + dz[j]);
            if (next.get(0) < a.length && next.get(1) < b.length && 
            next.get(2) < c.length && !visited.contains(next)) {
                minheap.offer(next);
                visited.add(next);
            }
        }
    }
    curr.set(0, a[curr.get(0)]);
    curr.set(1, b[curr.get(1)]);
    curr.set(2, c[curr.get(2)]);
    return curr;
  }

  public long getDistance(List<Integer> list, int[] a, int[] b, int[] c) {
      long dist = 0;
      dist += a[list.get(0)] * a[list.get(0)];
      dist += b[list.get(1)] * b[list.get(1)];
      dist += c[list.get(2)] * c[list.get(2)];
      return dist;
  }
}
//time: O(klogk), space: O(k)