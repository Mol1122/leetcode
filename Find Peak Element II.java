public class Solution {
    /*
     * @param A: An integer matrix
     * @return: The index of the peak
     */
    public List<Integer> findPeakII(int[][] A) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }
        int n = A.length;
        int m = A[0].length;
        return find(A, 1, n - 2, 1, m - 2);
    }
    
    private List<Integer> find(int[][] A, int x1, int x2, int y1, int y2) {
        int mid1 = x1 + (x2 - x1) / 2;
        int mid2 = y1 + (y2 - y1) / 2;
        int x = mid1;
        int y = mid2;
        int max = A[mid1][mid2];
        
        //在中间行和中间列找最大值
        for (int i = y1; i <= y2; i++) {
            if (A[mid1][i] > max) {
                max = A[mid1][i];
                x = mid1;
                y = i;
            }
        }
        for (int i = x1; i <= x2; i++) {
            if (A[i][mid2] > max) {
                max = A[i][mid2];
                x = i;
                y = mid2;
            }
        }
        boolean isPeak = true;
        //判断四周有没有比当前大的，只要找到一个就不会是peak，就不用继续判断行或列了
        if (A[x - 1][y] > A[x][y]) { //只能是行
            isPeak = false;
            x -= 1;
        } else if (A[x + 1][y] > A[x][y]) { //只能是行
            isPeak = false;
            x += 1;
        } else if (A[x][y - 1] > A[x][y]) { //只能是列
            isPeak = false;
            y -= 1;
        } else if (A[x][y + 1] > A[x][y]) { //只能是列
            isPeak = false;
            y += 1;
        }
        
        if (isPeak) {
            List<Integer> result = new ArrayList<>();
            result.add(x);
            result.add(y);
            return result;
        }
        //判断向四个角的哪个方向移动
        if (x >= x1 && x < mid1 && y >= y1 && y < mid2) {
            return find(A, x1, mid1 - 1, y1, mid2 - 1);
        } 
        if (x >= x1 && x < mid1 && y > mid2 && y <= y2) {
            return find(A, x1, mid1 - 1, mid2 + 1, y2);
        } 
        if (x > mid1 && x <= x2 && y >= y1 && y < mid2) {
            return find(A, mid1 + 1, x2, y1, mid2 - 1);
        } 
        if (x > mid1 && x <= x2 && y > mid2 && y <= y2) {
            return find(A, mid1 + 1, x2, mid2 + 1, y2);
        }
        List<Integer> result = new ArrayList<>();
        result.add(-1);
        result.add(-1);
        return result;
    }
}

/* 算法：二维的二分，同时对行和列进行二分
** 时间复杂度：O(n + m) T(n) = O(n) + O(n/2) + T(n/2) 算来的
** 难点：39-50行往上下或左右挪一格相当于判断start + 1 < end. 这样接下来recursion的时候在mid1, mid2完全不需要取等，避免了死循环 */