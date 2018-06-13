class Solution {
    public boolean judgeSquareSum(int c) {
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i <= Math.sqrt(c); i++) {
            set.add(i * i);
            if (set.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }
}