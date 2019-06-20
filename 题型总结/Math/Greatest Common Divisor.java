/* Find the greatest common factor of two positive integers.

Examples:

a = 12, b = 18, the greatest common factor is 6, since 12 = 6 * 2, 18 = 6 * 3.
a = 5, b = 16, the greatest common factor is 1. */

public class Solution {
  public int gcf(int big, int small) {
    if (big < small) {
      return gcf(small, big);
    }
    if (small != 0) {
      return gcf(small, big % small);
    } else {
      return big;
    }
  }
}
//time: O(n), space: O(n)