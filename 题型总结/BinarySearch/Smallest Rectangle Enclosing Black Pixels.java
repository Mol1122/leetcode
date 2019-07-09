/* An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,

Return 6. */
class Solution {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int n = image.length;
        int m = image[0].length;
        
        int left = findLeft(image, 0, y);
        int right = findRight(image, y, m - 1);
        int top = findTop(image, 0, x);
        int bottom = findButtom(image, x, n - 1);
        
        return (right - left + 1) * (bottom - top + 1);
    }
    private boolean isEmptyColumn(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return false;
            }
        }
        return true;
    }
    private boolean isEmptyRow(char[][] image, int row) {
        for (int j = 0; j < image[0].length; j++) {
            if (image[row][j] == '1') {
                return false;
            }
        }
        return true;
    }
    private int findLeft(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyColumn(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isEmptyColumn(image, start)) {
            return end;
        }
        return start;
    }
    private int findRight(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyColumn(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isEmptyColumn(image, end)) {
            return start;
        }
        return end;
    }
    private int findTop(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isEmptyRow(image, start)) {
            return end;
        }
        return start;
    }
    private int findButtom(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isEmptyRow(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isEmptyRow(image, end)) {
            return start;
        }
        return end;
    }
}

/* 算法：利用二分找到上下左右，还有再算出面积
** 难点：判断是start还是end的时候主要是看那个行或列是不是全为0 
** 时间复杂度： O(log n), space: O(1) */