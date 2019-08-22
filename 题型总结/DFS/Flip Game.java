/* You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list []. */

public class Solution {
  public List<String> generatePossibleNextMoves(String s) {
    List<String> results = new ArrayList<>();
    if (s == null || s.length() == 0) {
        return results;
    }
    char[] sc = s.toCharArray();
    for (int i = 0; i + 1 < sc.length; i++) {
        if (sc[i] == '+' && sc[i + 1] == '+') {
            sc[i] = '-';
            sc[i + 1] = '-';
            results.add(new String(sc));
            sc[i] = '+';
            sc[i + 1] = '+';
        }
    }
    return results;
  }
}
//time: O(n), space: O(n)  