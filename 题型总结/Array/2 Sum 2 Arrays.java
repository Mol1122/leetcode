public class Solution {
  public boolean existSum(int[] a, int[] b, int target) {
    if (a == null || b == null || a.length == 0 || b.length == 0) {
        return false;
    }
    Set<Integer> set = new HashSet<>();
    for (int num : a) {
        set.add(num);
    }
    for (int num : b) {
        if (set.contains(target - num)) {
            return true;
        }
    }
    return false;
  }
}
//time: O(n), space: O(n)
