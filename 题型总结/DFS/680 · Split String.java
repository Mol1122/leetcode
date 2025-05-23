/* Give a string, you can choose to split the string after one character or two adjacent characters, and make the string to be composed of only one character or two characters. Output all possible results.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


Example
Example1

Input: "123"
Output: [["1","2","3"],["12","3"],["1","23"]]
Example2

Input: "12345"
Output: [["1","23","45"],["12","3","45"],["12","34","5"],["1","2","3","45"],["1","2","34","5"],["1","23","4","5"],["12","3","4","5"],["1","2","3","4","5"]]
 */

 public class Solution {
    /**
     * @param s: a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        List<List<String>> results = new ArrayList<>();
        if (s == null) {
            return results;
        }
        dfs(s, 0, new ArrayList<>(), results);
        return results;
    }

    private void dfs(String s, int index, List<String> temp, List<List<String>> results) {
        if (index == s.length()) {
            results.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 1; i <= 2 && index + i <= s.length(); i++) {
            String sub = s.substring(index, index + i);
            temp.add(sub);
            dfs(s, index + i, temp, results);
            temp.remove(temp.size() - 1);
        }
    }
}
//time: O(2^n), space: O(n)