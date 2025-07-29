/* Determine if the characters of a given string are all unique.

Assumptions

The only set of possible characters used in the string are 'a' - 'z', the 26 lower case letters.
The given string is not null.
Examples

the characters used in "abcd" are unique
the characters used in "aba" are not unique */

public class Solution {
  public boolean allUnique(String s) {
      if (s == null || s.length() == 0) {
          return true;
      }
      int[] bit_vector = new int[8];
      for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          int row = c / 32;
          int col = c % 32;
          int weight = (1 << col);
          if ((bit_vector[row] & weight) != 0) {
              return false;
          }
          bit_vector[row] |= weight;
      }
      return true;
  }
}
