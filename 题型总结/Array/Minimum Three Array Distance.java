/* Given three sorted integer arrays, pick one element from each of them, what is the min value of |x - y| + |y - z| + |z - x|.

Assumptions:

The given three arrays are not null or empty.
Examples:

a = {1, 2, 3}

b = {4, 5}

c = {3, 4}

The minimum value is |3 - 4| + |4 - 4| + |4 - 3| = 2 */

public class Solution {
  public int minDistance(int[] a, int[] b, int[] c) {
    int res_i = 0, res_j = 0, res_k = 0;
    int i = 0, j = 0, k = 0;
    int diff = Integer.MAX_VALUE;

    while (i < a.length && j < b.length && k < c.length) {
      int min = Math.min(a[i], Math.min(b[j], c[k]));
      int max = Math.max(a[i], Math.max(b[j], c[k]));
      if (max - min < diff) {
        res_i = i;
        res_j = j;
        res_k = k;
        diff = max - min;
      }
      if (diff == 0) {
        break;
      }
      if (a[i] == min) {
        i++;
      } else if (b[j] == min) {
        j++;
      } else {
        k++;
      }
    }
    return Math.abs(a[res_i] - b[res_j]) + Math.abs(b[res_j] - c[res_k]) + Math.abs(c[res_k] - a[res_i]);
  }
}
//time: O(n), space: O(1)