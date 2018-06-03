class Solution {
    public int hammingDistance(int x, int y) {
        int distance = 0;
        while (x != 0 || y != 0) {
            if (x % 2 != y % 2) {
                distance++;
            }
            x /= 2;
            y /= 2;
        }
        return distance;
    }
}

/* x /= 2 相当于把bit向右移动了一位
** 难点：while里面要用||而不是&&*/