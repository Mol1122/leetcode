/* You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.

The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.

Given two integers x and y that represents the location of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

You must write an algorithm with less than O(mn) runtime complexity Example 1:


Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
Output: 6
Example 2:

Input: image = [["1"]], x = 0, y = 0
Output: 1                                   */

class Solution {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int left = findLeft(image, 0, y);
        int right = findRight(image, y, image[0].length - 1);
        int top = findTop(image, 0, x);
        int bottom = findBottom(image, x, image.length - 1);

        return (right - left + 1) * (bottom - top + 1);
    }

    private boolean isRowEmpty(char[][] image, int row) {
        for (int j = 0; j < image[0].length; j++) {
            if (image[row][j] == '1') {
                return false;
            }
        }
        return true;
    }

    private int findTop(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isRowEmpty(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isRowEmpty(image, start)) {
            return end;
        }
        return start;
    }

    private int findBottom(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isRowEmpty(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isRowEmpty(image, end)) {
            return start;
        }
        return end;
    }

    private boolean isColumnEmpty(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return false;
            }
        }
        return true;
    }

    private int findLeft(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isColumnEmpty(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isColumnEmpty(image, start)) {
            return end;
        }
        return start;
    }

    private int findRight(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isColumnEmpty(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isColumnEmpty(image, end)) {
            return start;
        }
        return end;
    }
}