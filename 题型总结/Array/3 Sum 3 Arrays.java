public class Solution {
  public boolean exist(int[] a, int[] b, int[] c, int target) {
    if (a == null || b == null || c == null || a.length == 0 || b.length == 0 || c.length == 0) {
        return false;
    }
    Arrays.sort(a);
    Arrays.sort(b);
    Arrays.sort(c);
    for (int i = 0; i < a.length; i++) {
        Set<Integer> set = new HashSet<>();
        for (int num : b) {
            set.add(num);
        }
        for (int j = 0; j < c.length; j++) {
            if (set.contains(target - a[i] - c[j])) {
                return true;
            }
        }
    }
    return false;
  }
}
//time: O(n^2), space: O(n)
