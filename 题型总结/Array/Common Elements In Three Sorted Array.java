/* Find all common elements in 3 sorted arrays.

Assumptions

The 3 given sorted arrays are not null
There could be duplicate elements in each of the arrays
Examples

A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]

 */
public class Solution {
  public List<Integer> common(int[] a, int[] b, int[] c) {
    List<Integer> results = new ArrayList<>();
    if (a == null || b == null || c == null || 
        a.length == 0 || b.length == 0 || c.length == 0) {
        return results;
    }
    int p1 = 0, p2 = 0, p3 = 0;
    while (p1 < a.length && p2 < b.length && p3 < c.length) {
        if (a[p1] == b[p2] && b[p2] == c[p3]) {
            results.add(a[p1]);
            p1++;
            p2++;
            p3++;
        } else if (a[p1] <= b[p2] && a[p1] <= c[p3]) { //注意，一定是小于等于
            p1++;
        } else if (b[p2] <= a[p1] && b[p2] <= c[p3]) {
            p2++;
        } else {
            p3++;
        }
    }
    return results;
  }
}
//time: O(n + m + k), space: O(1)
