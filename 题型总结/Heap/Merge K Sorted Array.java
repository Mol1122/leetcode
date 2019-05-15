public class Solution {
  public int[] merge(int[][] nums) {
      if (nums == null || nums.length == 0) {
          return new int[0];
      }
      List<Integer> results = new ArrayList<>();
      Queue<Pair> minheap = new PriorityQueue<>(nums.length, 
                            new Comparator<Pair>() {
          public int compare(Pair a, Pair b) {
              if (nums[a.row][a.col] == nums[b.row][b.col]) {
                  return 0;
              } else if (nums[a.row][a.col] < nums[b.row][b.col]) {
                  return -1;
              } else {
                  return 1;
              }
          }
      });
      
      for (int i = 0; i < nums.length; i++) {
          if (nums[i].length > 0) {
              minheap.offer(new Pair(i, 0));
          }
      }
      while (!minheap.isEmpty()) {
          Pair p = minheap.poll();
          results.add(nums[p.row][p.col]);
          if (p.col + 1 < nums[p.row].length) {
              p.col++;
              minheap.offer(p);
          }
      }
      int[] array = new int[results.size()];
      for (int i = 0; i < results.size(); i++) {
          array[i] = results.get(i);
      }
      return array;
  }
}

class Pair {
    int row, col;
    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
//time: O(nlogk), space: O(k)
