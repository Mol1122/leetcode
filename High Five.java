/**
 * Definition for a Record
 * class Record {
 *     public int id, score;
 *     public Record(int id, int score){
 *         this.id = id;
 *         this.score = score;
 *     }
 * }
 */
public class Solution {
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
        Map<Integer, Double> ans = new HashMap<>();
        if (results == null || results.length == 0) {
            return ans;
        }
        Map<Integer, List<Integer>> id2Score = new HashMap<>();
        for (Record r : results) {
            id2Score.putIfAbsent(r.id, new ArrayList<>());
            id2Score.get(r.id).add(r.score);
        }
        for (Integer id : id2Score.keySet()) {
            List<Integer> scores = id2Score.get(id);
            Queue<Integer> minheap = new PriorityQueue<>(5);
            
            for (Integer score : scores) {
                if (minheap.size() < 5) {
                    minheap.offer(score);
                } else {
                    if (score > minheap.peek()) {
                        minheap.poll();
                        minheap.offer(score);
                    }
                }
                // minheap.offer(score);
                // if (minheap.size() > 5) {
                //     minheap.poll();
                // }
            }
            int sum = 0;
            while (!minheap.isEmpty()) {
                sum += minheap.poll();
            }
            ans.put(id, sum / 5.0);
        }
        return ans;
    }
}