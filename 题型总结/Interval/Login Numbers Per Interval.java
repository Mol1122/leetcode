/* Given a list of login sessions with start and end timestamps.

Get the list of intervals with number of users logged in.

Examples:

login sessions: [[1, 2], [0, 4], [5, 6]]

return [[0, 1, 1], [1, 2, 2], [2, 4, 1], [5, 6, 1]]

at (0, 1) there is 1 user logged in.

at (1, 2) there is 2 user logged in.

at (2, 4) there is 1 user logged in (one user logged out at 2).

at (5, 6) there is 1 user logged in. */

public class Solution {
  public int[][] sessionNum(int[][] logins) {
    if (logins == null || logins.length == 0) {
      return new int[0][0];
    }
    List<Pair> list = new ArrayList<>();
    for (int[] login : logins) {
      list.add(new Pair(login[0], 0));
      list.add(new Pair(login[1], 1));
    }
    Collections.sort(list, new Comparator<Pair>(){
      public int compare(Pair a, Pair b) {
        if (a.time == b.time) {
          return b.isStart - a.isStart;
        }
        return a.time - b.time;
      }
    });
    List<List<Integer>> results = new ArrayList<>();
    int count = 1;
    Pair last = list.get(0);

    for (int i = 1; i < list.size(); i++) {
      Pair curr = list.get(i);
      if (curr.isStart == 0) {
        if (count != 0) {
          results.add(Arrays.asList(last.time, curr.time, count));
          count++;
        } else {
          count++;
        }
      } else {
        results.add(Arrays.asList(last.time, curr.time, count));
        count--;
      }
      last = curr;
    }
    List<List<Integer>> results1 = mergeList(results);
    int[][] array = new int[results1.size()][3];
    int index = 0;
    for (List<Integer> item : results1) {
      array[index++] = new int[]{item.get(0), item.get(1), item.get(2)};
    }
    return array;
  }

  private List<List<Integer>> mergeList(List<List<Integer>> list) {
    List<List<Integer>> results = new ArrayList<>();

    for (List<Integer> item : list) {
      if (item.get(0).equals(item.get(1))) {
        continue;
      }
      if (item.get(0) == 0 && item.get(1) == 0 && item.get(2) == 0) {
        continue;
      }
      if (results.size() != 0 && results.get(results.size() - 1).get(1).equals(item.get(0)) && 
          results.get(results.size() - 1).get(2).equals(item.get(2))) {
        results.get(results.size() - 1).set(1, item.get(1));
        continue;
      }
      results.add(item);
    }

    return results;
  }
}

class Pair {
  int time, isStart;

  public Pair(int time, int isStart) {
    this.time = time;
    this.isStart = isStart;
  }
}
//time: O(nlogn), space: O(n)
