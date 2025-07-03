/* Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers, return the list of integers that are present in each array of nums sorted in ascending order.
 

Example 1:

Input: nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
Output: [3,4]
Explanation: 
The only integers present in each of nums[0] = [3,1,2,4,5], nums[1] = [1,2,3,4], and nums[2] = [3,4,5,6] are 3 and 4, so we return [3,4].
Example 2:

Input: nums = [[1,2,3],[4,5,6]]
Output: []
Explanation: 
There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
 

Constraints:

1 <= nums.length <= 1000
1 <= sum(nums[i].length) <= 1000
1 <= nums[i][j] <= 1000
All the values of nums[i] are unique. */

//Method 1
public class Solution {
  public int[] merge(int[][] nums) {
      if (nums == null || nums.length == 0) {
          return new int[0];
      }
      List<Integer> results = new ArrayList<>();
      Queue<Pair> minheap = new PriorityQueue<>(nums.length, 
                            new Comparator<Pair>() {
          public int compare(Pair a, Pair b) {
              if (nums[a.row][a.col] == nums[b.row][b.col]) {
                  return 0;
              } else if (nums[a.row][a.col] < nums[b.row][b.col]) {
                  return -1;
              } else {
                  return 1;
              }
          }
      });
      
      for (int i = 0; i < nums.length; i++) {
          if (nums[i].length > 0) {
              minheap.offer(new Pair(i, 0));
          }
      }
      while (!minheap.isEmpty()) {
          Pair p = minheap.poll();
          results.add(nums[p.row][p.col]);
          if (p.col + 1 < nums[p.row].length) {
              p.col++;
              minheap.offer(p);
          }
      }
      int[] array = new int[results.size()];
      for (int i = 0; i < results.size(); i++) {
          array[i] = results.get(i);
      }
      return array;
  }
}

class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
//time: O(nlogk), space: O(k)

//Method 2
class Solution {
    public List<Integer> intersection(int[][] nums) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        Map<Integer, Integer> num2count = new HashMap<>();
        for (int[] array : nums) {
            for (int num : array) {
                num2count.put(num, num2count.getOrDefault(num, 0) + 1);
            }
        }
        for (int key : num2count.keySet()) {
            if (num2count.get(key) == nums.length) {
                results.add(key);
            }
        }
        Collections.sort(results);
        return results;
    }
}
//time: O(kn + knlogkn), space: O(kn)