public class Solution {
    /**
     * @param list: the information of studnet
     * @param k: 
     * @return: return a list
     */
    public List<List<String>> topKgpa(List<List<String>> list, int k) {
        List<List<String>> results = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return results;
        }
        Queue<List<String>> minheap = new PriorityQueue<>(k, new Comparator<List<String>>() {
            public int compare(List<String> a, List<String> b) {
                double gpaA = Double.parseDouble(a.get(1));
                double gpaB = Double.parseDouble(b.get(1));
                if (gpaA == gpaB) {
                    return Integer.parseInt(b.get(0)) - Integer.parseInt(a.get(0));
                }
                return a.get(1).compareTo(b.get(1));
            }
        });
        double gpaA = Double.parseDouble("1.25");
        double gpaB = Double.parseDouble("1.01");
        System.out.println("1.25".compareTo("1.01"));
                // if (gpaA == gpaB) {
        for (List<String> item : list) {
            // if (minheap.size() < k) {
            //     minheap.offer(item);
            // } else {
            //     if (Double.parseDouble(item.get(1)) > Double.parseDouble(minheap.peek().get(1))) {
            //         minheap.poll();
            //         minheap.offer(item);
            //     }
            // }
            minheap.offer(item);
            if (minheap.size() > k) {
                minheap.poll();
            }
        }
        k = minheap.size();
        while (!minheap.isEmpty()) {
            results.add(minheap.poll());
        }
        // Collections.sort(results, new Comparator<List<>>() {
        //     public int compare(List)
        // });
        Collections.sort(results, new Comparator<List<String>>() {
            public int compare(List<String> a, List<String> b) {
                return Integer.parseInt(a.get(0)) - Integer.parseInt(b.get(0));
            }
        });
        return results;
    }
}