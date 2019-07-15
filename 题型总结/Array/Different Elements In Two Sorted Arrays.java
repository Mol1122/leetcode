/* Given two sorted arrays a and b containing only integers, return two list of elements: the elements only in a but not in b, and the elements only in b but not in a.

Do it in one pass.

Assumptions:

The two given arrays are not null.
Examples:

a = {1, 2, 2, 3, 4, 5}

b = {2, 2, 2, 4, 4, 6}

The returned two lists are:

[

  [1, 3, 5],

  [2, 4, 6]  // there are two 2s in a, so there is one 2 in b not in a

] */

public class Solution {
  public int[][] diff(int[] a, int[] b) {
    if (a == null || b == null) {
      return new int[0][0];
    }
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    int i = 0, j = 0;

    while (i < a.length && j < b.length) {
      if (a[i] < b[j]) {
        list1.add(a[i]);
        i++;
      } else if (a[i] > b[j]) {
        list2.add(b[j]);
        j++;
      } else {
        i++;
        j++;
      }
    }
    while (i < a.length) {
      list1.add(a[i++]);
    }
    while (j < b.length) {
      list2.add(b[j++]);
    }
    int[][] results = new int[2][];
    results[0] = new int[list1.size()];
    for (j = 0; j < list1.size(); j++) {
      results[0][j] = list1.get(j);
    }
    results[1] = new int[list2.size()];
    for (j = 0; j < list2.size(); j++) {
      results[1][j] = list2.get(j);
    }
    return results;
  }
}
//time: O(n), space: O(n + m)