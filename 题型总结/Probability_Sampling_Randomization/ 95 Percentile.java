public class Solution {
  public int percentile95(List<Integer> lengths) {
      //the length of possible longest url is 4096
      int[] count = new int[4097];
      for (int len : lengths) {
          count[len]++;
      }
      int sum = 0;
      int len = 4097;
      while (sum <= 0.05 * lengths.size()) {
          sum += count[--len];
      }
      return len;
  }
}
