/* Find all numbers that appear in both of the two unsorted arrays, return the common numbers in increasing order.

Assumptions

Both arrays are not null.
There are no duplicate numbers in each of the two arrays respectively.
Exmaples

A = {1, 2, 3}, B = {3, 1, 4}, return [1, 3]
A = {}, B = {3, 1, 4}, return [] */
public class Solution {
  public List<Integer> common(List<Integer> a, List<Integer> b) {
    List<Integer> results = new ArrayList<>();
    if (a == null || b == null || a.size() == 0 || b.size() == 0) {
        return results;
    }
    if (a.size() > b.size()) {
        return common(b, a);
    }
    Set<Integer> set = new HashSet<>();
    for (int num : a) {
        set.add(num);
    }
    for (int i = 0; i < b.size(); i++) {
        if (set.contains(b.get(i))) {
            results.add(b.get(i));
        }
    }
    Collections.sort(results);
    return results;
  }
}
//time: O(n + m + mlogm), space: O(m) assume m < n
