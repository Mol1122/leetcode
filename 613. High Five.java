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
        Map<Integer, Double> result = new HashMap<>();
        Map<Integer, PriorityQueue<Integer>> idToScores = new HashMap<>();
        
        for (Record r : results) {
            idToScores.putIfAbsent(r.id, new PriorityQueue<Integer>());
            PriorityQueue<Integer> scores = idToScores.get(r.id);
            if (scores.size() < 5) {
                scores.offer(r.score);
            } else {
                if (scores.peek() < r.score) {
                    scores.poll();
                    scores.offer(r.score);
                }
            }
        }
        
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : idToScores.entrySet()) {
            int id = entry.getKey();
            PriorityQueue<Integer> scores = entry.getValue();
            double sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += scores.poll();
            }
            sum /= 5;
            result.put(id, sum);
        }
        return result;
    }
}