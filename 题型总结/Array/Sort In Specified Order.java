/* Given two integer arrays A1 and A2, sort A1 in such a way that the relative order among the elements will be same as those are in A2.

For the elements that are not in A2, append them in the right end of the A1 in an ascending order.

Assumptions:

A1 and A2 are both not null.
There are no duplicate elements in A2.
Examples:

A1 = {2, 1, 2, 5, 7, 1, 9, 3}, A2 = {2, 1, 3}, A1 is sorted to {2, 2, 1, 1, 3, 5, 7, 9} */

// HashMap
public class Solution {
  public int[] sortSpecial(int[] A1, int[] A2) {
    if (A1 == null || A1.length == 0) {
      return A1;
    }
    Map<Integer, Integer> num2count = new HashMap<>();
    for (int num : A1) {
      num2count.putIfAbsent(num, 0);
      num2count.put(num, num2count.get(num) + 1);
    }
    int[] result = new int[A1.length];
    int index = 0;
    for (int num : A2) {
      if (num2count.containsKey(num)) {
        for (int i = 0; i < num2count.get(num); i++) {
          result[index++] = num;
        }
        num2count.remove(num);
      }
    }
    for (int key : num2count.keySet()) {
      for (int i = 0; i < num2count.get(key); i++) {
        result[index++] = key;
      }
    }
    return result;
  }
}
//time: O(n + m), space: O(n)

// Comparator
public class Solution {
  public int[] sortSpecial(int[] A1, int[] A2) {
    if (A1 == null || A1.length == 0) {
            return A1;
        }
        Map<Integer, Integer> num2index = new HashMap<>();
        for (int i = 0; i < A2.length; i++) {
            num2index.put(A2[i], i);
        }
        Integer[] nums = new Integer[A1.length];
        for (int i = 0; i < A1.length; i++) {
            nums[i] = Integer.parseInt(A1[i] + "");
        }
        Arrays.sort(nums, new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                if (!num2index.containsKey(a) && !num2index.containsKey(b)) {
                    return a - b;
                }
                if (!num2index.containsKey(a)) {
                    return Integer.MAX_VALUE;
                } else if (!num2index.containsKey(b)) {
                    return Integer.MIN_VALUE;
                } else {
                    return num2index.get(a) - num2index.get(b);
                }
            }
        });
        for (int i = 0; i < nums.length; i++) {
            A1[i] = (int)nums[i];
        }
        return A1;
  }
}
//time: O(n + m + nlogn), space: O(n)
