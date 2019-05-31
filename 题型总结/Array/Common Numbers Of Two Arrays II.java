/* Find all numbers that appear in both of two unsorted arrays.

Assumptions

Both of the two arrays are not null.
In any of the two arrays, there could be duplicate numbers.
Examples

A = {1, 2, 3, 2}, B = {3, 4, 2, 2, 2}, return [2, 2, 3] (there are both two 2s in A and B) */

public class Solution {
  public List<Integer> common(List<Integer> a, List<Integer> b) {
    List<Integer> results = new ArrayList<>();
    if (a == null || b == null || a.size() == 0 || b.size() == 0) {
        return results;
    }
    if (a.size() > b.size()) {
        return common(b, a);
    }
    Map<Integer, Integer> num2count = new HashMap<>();
    for (int num : a) {
        num2count.put(num, num2count.getOrDefault(num, 0) + 1);
    }
    for (int num : b) {
        if (num2count.containsKey(num)) {
            results.add(num);
            num2count.put(num, num2count.get(num) - 1);
            if (num2count.get(num) == 0) {
                num2count.remove(num);
            }
        }
    }
    Collections.sort(results);
    return results;
  }
}
//time: O(n + m + mlogm), space: O(m)