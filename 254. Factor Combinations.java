class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<>(), 2, n);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> temp, int start, int n) {
        int upper = (int)Math.sqrt(n);
        for (int i = start; i <= n; i++) {
            int factor = -1;
            if (n%i == 0) {
                factor = n / i;
            }
            if (factor != -1 && factor >= i) {
                temp.add(i);
                temp.add(factor);
                result.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
                dfs(result, temp, i, factor);
                temp.remove(temp.size() - 1);
            }
        }
    }
}

/* 算法：dfs的combination类, n的upperbound是(int)sqrt(n) */