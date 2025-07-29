/* Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

 

Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330" */

class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }
        Arrays.sort(strs, new Comparator<String>(){
            public int compare(String a, String b) {
                return (b + a).compareTo(a + b);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        int index = 0;
        while (index < sb.length() && sb.charAt(index) == '0') {
            index++;
        }
        if (index == sb.length()) {
            return "0";
        }
        return sb.toString().substring(index);
    }
}
//time: O(nlogn), space: O(n)