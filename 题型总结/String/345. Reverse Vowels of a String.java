/* Only reverse the vowels('a', 'e', 'i', 'o', 'u') in a given string, the other characters should not be moved or changed.

Assumptions:

The given string is not null, and only contains lower case letters.
Examples:

"abbegi" --> "ibbega" */

public class Solution {
  public String reverse(String s) {
    if (s == null || s.length() == 0) {
      return s;
    }
    String vowels = "aeiouAEIOU";
    char[] sc = s.toCharArray();
    int i = 0, j = sc.length - 1;

    while (i < j) {
      while (i < j && !vowels.contains(sc[i] + "")) {
        i++;
      }
      while (i < j && !vowels.contains(sc[j] + "")) {
        j--;
      }
      char temp = sc[i];
      sc[i] = sc[j];
      sc[j] = temp;
      i++; 
      j--;
    }
    return new String(sc);
  }
}
//time: O(n^2), space: O(1)