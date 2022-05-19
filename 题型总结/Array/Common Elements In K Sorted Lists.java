/* Find all common elements in K sorted lists.



Assumptions

The input and its elements are not null, and support fast random access.

There could be duplicate elements in each of the lists.



Examples

Input = {{1, 2, 2, 3}, {2, 2, 3, 5}, {2, 2, 4}}, the common elements are {2, 2}. */

public class Solution {
  public List<Integer> commonElementsInKSortedArrays(List<List<Integer>> lists) {
    while (lists.size() > 1) {
      List<List<Integer>> new_list = new ArrayList<>();
      for (int i = 0; i + 1 < lists.size(); i += 2) {
        List<Integer> common = getCommon(lists.get(i), lists.get(i + 1));
        new_list.add(common);
      }
      if (lists.size() % 2 == 1) {
        new_list.add(lists.get(lists.size() - 1));
      }
      lists = new_list;
    }
    return lists.get(0);
  }

  private List<Integer> getCommon(List<Integer> list1, List<Integer> list2) {
    List<Integer> results = new ArrayList<>();
    int i = 0, j = 0;

    while (i < list1.size() && j < list2.size()) {
      if (list1.get(i) == list2.get(j)) {
        results.add(list1.get(i));
        i++;
        j++;
      } else if (list1.get(i) < list2.get(j)) {
        i++;
      } else {
        j++;
      }
    }
    return results;
  }
}
//time: O(nk), space: O(nk)