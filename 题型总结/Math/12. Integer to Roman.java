/* Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000 */

public class Solution {
  public String intToRoman(int n) {
    String M[] = {"", "M", "MM", "MMM"}; //thoudsand
    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; //hundred
    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; //ten
    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; //one

    return M[(n / 1000) % 10] + C[(n / 100) % 10] + X[(n / 10) % 10] + I[(n % 10)];
  }
}
//time: O(1), space: O(1)
