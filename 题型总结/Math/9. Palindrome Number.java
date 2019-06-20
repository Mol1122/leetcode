/* Determine whether an integer is a palindrome.

Assumptions

Could negative integers be palindromes? (ie, -1) No.

If you are thinking of converting the integer to string, note the restriction of using extra space.You could also try reversing an integer.
However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case? 
There is a more generic way of solving this problem. */

public class Solution {
  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    return x == reverse(x);
  }

  private int reverse(int x) {
    int result = 0;
    while (x != 0) {
      result = result * 10 + x % 10;
      x /= 10;
    }
    return result;
  }
}
//time: O(n), space: O(1)