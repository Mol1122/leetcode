/* Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,

123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred */

public class Solution {
  String[] belowTen = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
  String[] belowTwenty = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  String[] belowHundred = {"", "Ten ", "Twenty ", "Thirty ", "Forty ", "Fifty ", "Sixty ", "Seventy ", "Eighty ", "Ninety "};

  public String numberToWords(int num) {
    if (num == 0) {
      return "Zero";
    }
    return helper(num);
  }

  private String helper(int num) {
    String result = "";
    if (num < 10) {
      result += belowTen[num] + "";
    } else if (num < 20) {
      result += belowTwenty[num - 10] + "";
    } else if (num < 100) {
      result += belowHundred[num / 10] + helper(num % 10);
    } else if (num < 1000) {
      result += helper(num / 100) + " Hundred " + helper(num % 100);
    } else if (num < 1000000) {
      result += helper(num / 1000) + " Thousand " + helper(num % 1000);
    } else if (num < 1000000000) {
      result += helper(num / 1000000) + " Million " + helper(num % 1000000);
    } else {
      result += helper(num / 1000000000) + " Billion " + helper(num % 1000000000);
    }
    return result.trim();
  }
}
//time: O(n), space: O(n)