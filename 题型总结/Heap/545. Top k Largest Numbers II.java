/* Description
Implement a data structure, provide two interfaces:

add(number). Add a new number in the data structure.
topk(). Return the top k largest numbers in this data structure. k is given when we create the data structure.
LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


Example
Example1

Input: 
s = new Solution(3);
s.add(3)
s.add(10)
s.topk()
s.add(1000)
s.add(-99)
s.topk()
s.add(4)
s.topk()
s.add(100)
s.topk()
        
Output: 
[10, 3]
[1000, 10, 3]
[1000, 10, 4]
[1000, 100, 10]

Explanation:
s = new Solution(3);
>> create a new data structure, and k = 3.
s.add(3)
s.add(10)
s.topk()
>> return [10, 3]
s.add(1000)
s.add(-99)
s.topk()
>> return [1000, 10, 3]
s.add(4)
s.topk()
>> return [1000, 10, 4]
s.add(100)
s.topk()
>> return [1000, 100, 10]
Example2

Input: 
s = new Solution(1);
s.add(3)
s.add(10)
s.topk()
s.topk()

Output: 
[10]
[10]

Explanation:
s = new Solution(1);
>> create a new data structure, and k = 1.
s.add(3)
s.add(10)
s.topk()
>> return [10]
s.topk()
>> return [10]
 */

//Method 1
public class Solution {
    Queue<Integer> pq; 
    int size;
    public Solution(int k) {
        pq = new PriorityQueue<>();
        size = k;
    }

    
    public void add(int num) {
        if (pq.size() < size) {
            pq.offer(num);
            return;
        }
        if (num > pq.peek()) {
            pq.poll();
            pq.offer(num);
        }
    }

    public List<Integer> topk() {
        Iterator it = pq.iterator();
        List<Integer> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add((Integer)it.next());
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
}

/* 算法：quick select: add O(1), topK: O(n)
         minheap: add O(logk) topK: O(klogk)
   难点：考点不在于做对，而是在于分析k大的时候用quick select更好
         topK一定要用到iterator因为是在线算法，不能每次都poll() */

//Method 2
public class Solution {
    Queue<Integer> minheap;
    int k;

    public Solution(int k) {
        minheap = new PriorityQueue<>();
        this.k = k;
    }

    
    public void add(int num) {
        minheap.offer(num);
        if (minheap.size() > k) {
            minheap.poll();
        }
    }

    
    public List<Integer> topk() {
        List<Integer> results = new ArrayList<>();
        
        while (!minheap.isEmpty()) {
            results.add(minheap.poll());
        }
        for (int num : results) {
            add(num);
        }
        Collections.reverse(results);
        return results;
    }
}