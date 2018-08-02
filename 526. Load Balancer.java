public class LoadBalancer {
    Map<Integer, Integer> num2index;
    List<Integer> nums;
    Random rand;
    public LoadBalancer() {
        num2index = new HashMap<>();
        nums = new ArrayList<>();
        rand = new Random();
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
        if (!num2index.containsKey(server_id)) {
            num2index.put(server_id, nums.size());
            nums.add(server_id);
        }
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
        if (num2index.containsKey(server_id)) {
            int index = num2index.get(server_id);
            int last = nums.get(nums.size() - 1);
            nums.set(index, last);
            num2index.put(last, index);
            nums.remove(nums.size() - 1);
            num2index.remove(server_id);
        }
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
        return nums.get(rand.nextInt(nums.size()));
    }
}