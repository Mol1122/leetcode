/* Implement a load balancer for web servers. It provide the following functionality:

Add a new server to the cluster => add(server_id).
Remove a bad server from the cluster => remove(server_id).
Pick a server in the cluster randomly with equal probability => pick().
At beginning, the cluster is empty. When pick() is called you need to randomly return a server_id in the cluster.

Example
Example 1:

Input:
  add(1)
  add(2)
  add(3)
  pick()
  pick()
  pick()
  pick()
  remove(1)
  pick()
  pick()
  pick()
Output:
  1
  2
  1
  3
  2
  3
  3
Explanation: The return value of pick() is random, it can be either 2 3 3 1 3 2 2 or other. */

public class LoadBalancer {
    List<Integer> nums;
    Map<Integer, Integer> num2Index;
    Random rand;
    
    public LoadBalancer() {
        nums = new ArrayList<>();
        num2Index = new HashMap<>();
        rand = new Random();
    }

    public void add(int server_id) {
        if (num2Index.containsKey(server_id)) {
            return;
        }
        num2Index.put(server_id, nums.size());
        nums.add(server_id);
    }

    public void remove(int server_id) {
        if (!num2Index.containsKey(server_id)) {
            return;
        }
        int index = num2Index.get(server_id);
        int last = nums.get(nums.size() - 1);
        
        nums.set(index, last);
        num2Index.put(last, index);
        
        nums.remove(nums.size() - 1);
        num2Index.remove(server_id);
    }

    public int pick() {
        return nums.get(rand.nextInt(nums.size()));
    }
}