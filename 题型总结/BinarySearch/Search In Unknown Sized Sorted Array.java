/*
Given a integer dictionary A of unknown size, where the numbers in the dictionary are sorted in ascending order, determine if a given target integer T is in the dictionary. Return the index of T in A, return -1 if T is not in A.

Assumptions

dictionary A is not null
dictionary.get(i) will return null(Java)/INT_MIN(C++)/None(Python) if index i is out of bounds
Examples

A = {1, 2, 5, 9, ......}, T = 5, return 2
A = {1, 2, 5, 9, 12, ......}, T = 7, return -1
*/

// You do not need to implement the Dictionary interface.
// You can use it directly, the implementation is provided when testing your solution.
public class Solution {
  public int search(Dictionary dict, int target) {
        if (dict == null) {
          return -1;
        }
        int start = 0, end = 1;
        while (dict.get(end) != null && dict.get(end) < target) {
            start = end;
            end = 2 * end;
        }
        return binarySearch(dict, target, start, end);
  }
  
  private int binarySearch(Dictionary dict, int target, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (dict.get(mid) == target) {
                return mid;
            } else if (target < dict.get(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (dict.get(start) != null && dict.get(start) == target) {
            return start;
        } else if (dict.get(end) != null && dict.get(end) == target) {
            return end;
        }
        return -1;
    }
}
