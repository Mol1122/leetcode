/* Given a string of numbers and operators, return all possible results from computing all the different possible 
ways to group numbers and operators in Ascending order. The valid operators are +, - and *.

Example 1

Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]

Example 2

Input: "2\*3-4\*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10] */

public class Solution {
  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> results = new ArrayList<>();
    if (input == null || input.length() == 0) {
        return results;
    }
    for (int i = 0; i < input.length(); i++) {
        if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
            List<Integer> part1 = diffWaysToCompute(input.substring(0, i));
            List<Integer> part2 = diffWaysToCompute(input.substring(i + 1));

            for (int num1 : part1) {
                for (int num2 : part2) {
                    int temp = 0;
                    switch (input.charAt(i)) {
                        case '+': temp = num1 + num2;
                            break;
                        case '-': temp = num1 - num2;
                            break;
                        case '*': temp = num1 * num2; 
                            break;
                    }
                    results.add(temp);
                }
            }
        }
    }
    if (results.size() == 0) {
        results.add(Integer.parseInt(input));
    }
    Collections.sort(results);
    return results;
  }
}
//time: O(n^n), space: O(n)