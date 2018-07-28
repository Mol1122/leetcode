public class Solution {
    /**
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        List<String> results = new ArrayList<>();
        if (str == null) {
            return results;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        dfs(chars, new boolean[chars.length], "", results);
        return results;
    }
    
    private void dfs(char[] chars, boolean[] visited, String permutation, List<String> results) {
        if (chars.length == permutation.length()) {
            results.add(permutation);
            return;
        }
        
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                continue;
            }
            permutation += chars[i];
            visited[i] = true;
            dfs(chars, visited, permutation, results);
            visited[i] = false;
            permutation = permutation.substring(0, permutation.length() - 1);
        }
    }
}