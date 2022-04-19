/* Given an array of n integer with duplicate number, and a moving window(size k), move the window at each iteration 
from the start of the array, find the maximum number inside the window at each moving.

Example
Example 1:

Input:
[1,2,7,7,8]
3
输出:
[7,7,8]

Explanation：
At first the window is at the start of the array like this `[|1, 2, 7| ,7, 8]` , return the maximum `7`;
then the window move one step forward.`[1, |2, 7 ,7|, 8]`, return the maximum `7`;
then the window move one step forward again.`[1, 2, |7, 7, 8|]`, return the maximum `8`;
Example 2:

Input:
[1,2,3,1,2,3]
5
Output:
[3,3]

Explanation:
At first, the state of the window is as follows: ` [,2,3,1,2,1 | , 3] `, a maximum of ` 3 `;
And then the window to the right one. ` [1, | 2,3,1,2,3 |] `, a maximum of ` 3 `; */

public class Solution {
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        TreeSet<Pair> set = new TreeSet<>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b) {
                if (a.val == b.val) {
                    return a.id - b.id;
                }
                return a.val - b.val;
            }
        });
        
        for (int i = 0; i < k - 1; i++) {
            set.add(new Pair(i, nums[i]));
        }
        for (int i = k - 1; i < nums.length; i++) {
            set.add(new Pair(i, nums[i]));
            results.add(set.last().val);
            set.remove(new Pair(i - k + 1, nums[i - k + 1]));
        }
        return results;
    }
}

class Pair {
    int id, val;

    public Pair(int id, int val) {
        this.id = id;
        this.val = val;
    }
}
//time: O(nlogk), space: O(k)