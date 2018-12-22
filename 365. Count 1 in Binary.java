public class Solution {
    /*
     * @param num: An integer
     * @return: An integer
     */
    public int countOnes(int num) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }
};