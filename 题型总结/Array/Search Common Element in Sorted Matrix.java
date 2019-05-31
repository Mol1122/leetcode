/* Given a 2D integer matrix, where every row is sorted in ascending order. How to find a common element in all rows. 
If there is no common element, then returns -1.

Example

matrix = { { 1, 2, 3, 4 }, { 4, 5, 6, 7 }, { 2, 3, 4, 8 } }

the common element is 4.
 */
 
public class Solution {
  public int search(int[][] matrix) {
    if (matrix == null || matrix.length <= 1) {
        return -1;
    }
    List<List<Integer>> lists = getLists(matrix);
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
    if (lists.get(0).size() == 0) {
        return -1;
    } else {
        return lists.get(0).get(0);
    }
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

  private List<List<Integer>> getLists(int[][] matrix) {
    List<List<Integer>> lists = new ArrayList<>();
    for (int i = 0; i < matrix.length; i++) {
        List<Integer> list = new ArrayList<>();
        for (int num : matrix[i]) {
            list.add(num);
        }
        lists.add(list);
    }
    return lists;
  }
}
//time: O(nk), space: O(n)
