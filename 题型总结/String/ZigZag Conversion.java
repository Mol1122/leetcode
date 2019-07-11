/* The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of 3 rows like this:



P 
A 
H 
N
A P L S I I G
Y 
I 
R 



(you may want to display this pattern in a fixed font for better legibility).

And then read line by line: "PAHNAPLSIIGYIR". Write the code that will take a string and make this conversion given a number of rows. convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".     */


public class Solution {
  public String convert(String s, int numRows) {
    if (s == null || s.length() == 0) {
      return "";
    }
    if (numRows == 1 || s.length() <= numRows) {
      return s;
    }
    List<StringBuilder> rows = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      rows.add(new StringBuilder());
    }
    int currRow = 0;
    boolean goingDown = false;
    for (char c : s.toCharArray()) {
      rows.get(currRow).append(c);
      if (currRow == 0 || currRow == numRows - 1) {
        goingDown = !goingDown;
      }
      currRow += goingDown ? 1 : -1;
    }
    StringBuilder result = new StringBuilder();
    for (StringBuilder row : rows) {
      result.append(row);
    }
    return result.toString();
  }
}
//time: O(n), space: O(n)
