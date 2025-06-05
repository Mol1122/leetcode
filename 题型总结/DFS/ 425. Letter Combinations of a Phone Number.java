public class Solution {
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return results;
        }
        String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(digits, 0, digits.length(), "",  phone, results);
        return results;
    }
    
    private void dfs(String digits, int index, int length, String combination, 
                     String[] phone, List<String> results) {
        if (index == length) {
            results.add(combination);
            return;
        }
        int d = digits.charAt(index) - '0';
        for (char c : phone[d].toCharArray()) {
            dfs(digits, index + 1, length, combination + c, phone, results);
        }
    }
}

/* 算法：枚举型DFS, 模拟for循环
** 时间复杂度: O(4^n), space: O(n) */