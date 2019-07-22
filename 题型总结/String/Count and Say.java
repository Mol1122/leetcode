/* Given a sequence of number: 1, 11, 21, 1211, 111221, …

The rule of generating the number in the sequence is as follow:

1 is "one 1" so 11.

11 is "two 1s" so 21.

21 is "one 2 followed by one 1" so 1211.

Find the nth number in this sequence.

Assumptions:

n starts from 1, the first number is "1", the second number is "11"
n is smaller than 30 */

public class Solution {
  public String countAndSay(int n) {
    if (n <= 0) {
        return null;
    }
    String oldString = "1";
    while (--n > 0) {
        StringBuilder sb = new StringBuilder();
        char [] oldChars = oldString.toCharArray();

        for (int i = 0; i < oldChars.length; i++) {
            int count = 1;
            while ((i+1) < oldChars.length && oldChars[i] == oldChars[i+1]) {
                count++;
                i++;
            }
            sb.append(String.valueOf(count) + String.valueOf(oldChars[i]));
        }
        oldString = sb.toString();
    }
    return oldString;
  }
}
//time: O(n), space: O(1)