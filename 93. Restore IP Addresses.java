class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> results = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return results;
        }
        dfs(s, 0, "", results, 0);
        return results;
    }
    
    private void dfs(String s, int startIndex, String ip, List<String> results, int count) {
        if (count == 4) {
            if (startIndex == s.length()) {
                String temp = ip.substring(0, ip.length() - 1);
                results.add(new String(temp));
            }
            return;
        }
        for (int i = 1; i <= 3 && startIndex + i - 1 < s.length(); i++) {
            String sub = s.substring(startIndex, startIndex + i);
            if (!isValid(sub)) {
                continue;
            }
            String suffix = s.substring(startIndex + i);
            dfs(s, startIndex + i, ip + sub + ".", results, count + 1); 
        }
    }
    
    private boolean isValid(String s) {
        if (s.charAt(0) == '0' && s.length() > 1) {
            return false;
        }
        int num = Integer.parseInt(s);
        return num >= 0 && num <= 255;
    }
}

/* 算法：partition string == combination,所以想到用dfs
** 难点：要把count == 4放到最外层，这样可以达到剪枝的效果 */