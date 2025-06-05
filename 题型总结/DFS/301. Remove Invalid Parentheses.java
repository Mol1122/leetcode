/* Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ). 

Examples:

"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""] */


//Method 1
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> results = new ArrayList<>();
        if (s == null) {
            return results;
        }
        int[] count = getCount(s); //[left paren count  need to remove, right paren count need to remove]

        dfs(s, 0, count[0], count[1], results);
        return results;
    }

    private void dfs(String s, int startIndex, int leftCount, int rightCount, List<String> results) {
        if (leftCount == 0 && rightCount == 0) {
            if (isValid(s)) {
                results.add(new String(s));
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (i > startIndex && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            if (leftCount + rightCount > s.length() - i) {
                return;
            }
            if (s.charAt(i) == '(' && leftCount > 0) {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount - 1, rightCount, results);
            }
            if (s.charAt(i) == ')' && rightCount > 0) {
                dfs(s.substring(0, i) + s.substring(i + 1), i, leftCount, rightCount - 1, results);
            }
        }
    }

    private boolean isValid(String s) {
        int[] count = getCount(s);
        
        return count[0] == 0 && count[1] == 0;
    }

    private int[] getCount(String s) {
        int[] count = new int[2];
        char[] sc = s.toCharArray();

        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == '(') {
                count[0]++;
            } else if (sc[i] == ')') {
                if (count[0] > 0) {
                    count[0]--;
                } else {
                    count[1]++;
                }
            }
        }
        return count;
    }
}

/* 算法：组合类dfs. 找到需要删除多少个left和需要删除多少个right之后,就变成了在s里找需要删除的左，右的combination */
//time: O(n^n * n), space: O(n)

//Method 2
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> results = new HashSet<>();
        if (s == null) {
            return new ArrayList<>();
        }
        int[] count = getCount(s); //[left paren count  need to remove, right paren count need to remove]

        dfs(s, 0, count[0], count[1], results);
        return new ArrayList<>(results);
    }

    private void dfs(String s, int index, int leftCount, int rightCount, Set<String> results) {
        if (leftCount == 0 && rightCount == 0) {
            if (isValid(s)) {
                results.add(new String(s));
            }
            return;
        }
        if (index >= s.length()) {
            return;
        }
        if (leftCount + rightCount > s.length() - index) {
            return;
        }
        //remove
        if (s.charAt(index) == '(' && leftCount > 0) {
            dfs(s.substring(0, index) + s.substring(index + 1), index, leftCount - 1, rightCount, results);
        }
        if (s.charAt(index) == ')' && rightCount > 0) {
            dfs(s.substring(0, index) + s.substring(index + 1), index, leftCount, rightCount - 1, results);
        }

        //not remove
        dfs(s, index + 1, leftCount, rightCount, results);
    }

    private boolean isValid(String s) {
        int[] count = getCount(s);
        
        return count[0] == 0 && count[1] == 0;
    }

    private int[] getCount(String s) {
        int[] count = new int[2];
        char[] sc = s.toCharArray();

        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == '(') {
                count[0]++;
            } else if (sc[i] == ')') {
                if (count[0] > 0) {
                    count[0]--;
                } else {
                    count[1]++;
                }
            }
        }
        return count;
    }
}
//time: O(2^n * n), space: O(n)