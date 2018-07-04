public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        raninbowSort(colors, 0, colors.length - 1, 1, k);
    }
    
    private void raninbowSort(int[] colors, int start, int end, int colorLeft, int colorRight) {
        if (colorLeft == colorRight) {
            return;
        }
        if (start >= end) {
            return;
        }
        int left = start, right = end;
        int colorMid = colorLeft + (colorRight - colorLeft) / 2;
        
        while (left <= right) {
            while (left <= right && colors[left] <= colorMid) { //难点：<=，因为需要所有的数都是小于等于的，
                                                                 //如果是<那么等于的数就会被放到右边进行sort，就会离colorMid越来越远
                left++;
            }
            while (left <= right && colors[right] > colorMid) {
                right--;
            }
            if (left <= right) {
                int temp = colors[left];
                colors[left] = colors[right];
                colors[right] = temp;
                left++;
                right--;
            }
        }
        raninbowSort(colors, start, right, colorLeft, colorMid); //难点：colorMid,左边有的所有color的range就是[colorLeft, colorMid]
        raninbowSort(colors, left, end, colorMid + 1, colorRight); //难点: colorRight, 右边有的所有color de range是(colorMid, colorRight]
    }
}