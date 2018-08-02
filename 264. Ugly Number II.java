class Solution {
    public int nthUglyNumber(int n) {
        List<Integer> results = new ArrayList<>();
        results.add(1);
        int p2 = 0, p3 = 0, p5 = 0; //pointer to 2, 3, 5想象成赛道
        
        for (int i = 1; i < n; i++) {
            int lastNum = results.get(i - 1);
            while (results.get(p2) * 2 <= lastNum) {
                p2++;
            }
            while (results.get(p3) * 3 <= lastNum) {
                p3++;
            }
            while (results.get(p5) * 5 <= lastNum) {
                p5++;
            }
            results.add(Math.min(
                Math.min(results.get(p2) * 2, results.get(p3) * 3),
                results.get(p5) * 5
            ));
        }
        return results.get(n - 1);
    }
}

/* 算法：这个算法是最优解，但是是很难想到的
** 时间复杂度：O(n)
** 空间复杂度: O(1) */