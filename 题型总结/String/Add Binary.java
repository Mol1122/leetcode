/* Given two binary strings, return their sum (also a binary string).

Input: a = “11”

           b = “1”

Output: “100” */

public class Solution {
  public String addBinary(String a, String b) {
    if (a == null || b == null) {
      return "";
    }
    String ans = "";
    int carry = 0;
    for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
      int sum = carry;
      sum += i >= 0 ? a.charAt(i) - '0' : 0;
      sum += j >= 0 ? b.charAt(j) - '0' : 0;
      ans = (sum % 2) + ans;
      carry = sum / 2;
    }
    if (carry != 0) {
      ans = carry + ans;
    }
    return ans;
  }
}
//time: O(n), space: O(1)