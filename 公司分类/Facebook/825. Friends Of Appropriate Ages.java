/* Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100. */

class Solution {
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length == 0) {
            return 0;
        }
        Map<Integer, Integer> age2count = new HashMap<>();
        for (int age : ages) {
            age2count.putIfAbsent(age, 0);
            age2count.put(age, age2count.get(age) + 1);
        }
        int count = 0;
        
        for (int i : age2count.keySet()) {
            for (int j : age2count.keySet()) {
                if (canRequest(i, j)) {
                    count += age2count.get(i) * (age2count.get(j) - (i == j ? 1 : 0)); //不能给自己send request
                }
            }
        }
        return count;
    }
    
    private boolean canRequest(int i, int j) {
        return !(j <= (0.5 * i + 7) || j > i || (j > 100 && i < 100));
    }
}
//time: O(n^2), sapce: O(n)