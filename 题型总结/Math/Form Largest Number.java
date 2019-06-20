/* Given an array of numbers in string type, sort them in a way that the concatenation of them yields the largest value. 
Return the largest number in string type.

Assumptions:

The given array is not null.
Each of the strings in the array is not null and they are all numbers.
Examples:

{“54”, “546”, “648”, “88”}, the arrangement “8864854654” gives the largest value.  */

public class Solution {
  public String largestNumber(String[] nums) {
    String[] strs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      strs[i] = nums[i] + "";
    }
    Arrays.sort(strs, new Comparator<String>(){
      public int compare(String s1, String s2) {
        return (s2 + s1).compareTo(s1 + s2);
      }
    });
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strs.length; i++) {
      sb.append(strs[i]);
    }
    String result = sb.toString();
    int index = 0;
    while (index < result.length() && result.charAt(index) == '0') {
      index++;
    }
    if (index == result.length()) {
      return "0";
    }
    return result.substring(index);
  }
}
//time: O(nlogn), space: O(n)