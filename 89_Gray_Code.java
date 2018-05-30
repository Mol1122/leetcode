class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        if (n <= 1) {
            for (int i = 0; i <= n; i++) {
                result.add(i);
            }
            return result;
        }
        result = grayCode(n - 1);
        List<Integer> rev = reverse(result);
        int x = 1 << (n - 1); //shit 1 by n - 1 positions
        for (int i = 0; i < rev.size(); i++) {
            rev.set(i, rev.get(i) + x);
        }
        result.addAll(rev);
        return result;
    }
    
    private List<Integer> reverse(List<Integer> result) {
        if (result == null) {
            return null;
        }
        List<Integer> rev = new ArrayList<Integer>();
        for (int i = result.size() - 1; i >= 0; i--) {
            rev.add(result.get(i));
        }
        return rev;
    }
}
/* 语法：rev.set(position, new_value)
 * 算法：个位数0, 1开始不变，第三个数相当于第二个数+(1 shift by n-1 positions), 第四个数相当于第一个数+(1 shift by n-1 positions)
 * reverse + (1 shift by n - 1 positions)的作用在于保证跟前一个数只相差一个bit difference 
 * eg: 0--00
 *     1--01
 *     3--11
 *     2--10
 *
 *     0--000
 *     1--001
 *     3--011
 *     2--010
 *     6--110
 *     7--111
 *     5--101
 *     4--100*/