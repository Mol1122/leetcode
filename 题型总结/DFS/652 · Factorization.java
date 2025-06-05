/* Description
A non-negative numbers can be regarded as product of its factors.
Write a function that takes an integer n and return all possible combinations of its factors.

LintCode - Online Judge Solution

Candidate Written Test Screening, Team Competency Assessment, Programming Teaching Exercises, Online Exam Grading

WeChat for information（ID chenleo0002）


Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combination.
Example
Example1

Input: 8
Output: [[2,2,2],[2,4]]
Explanation:
8 = 2 x 2 x 2 = 2 x 4
Example2

Input: 1
Output: [] */

public class Solution {
    /**
     * @param n: An integer
     * @return: a list of combination
     *          we will sort your return value in output
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();

        dfs(2, n, new ArrayList<>(), results);
        return results;
    }

    private void dfs(int start, int remain, List<Integer> temp, List<List<Integer>> results) {
        if (remain == 1) {
            if (temp.size() != 1) {
                results.add(new ArrayList<>(temp));
            }
            return;
        }
        for (int i = start; i <= remain; i++) {
            if (i > remain / i) { //如果n = 32, 第一个数选了2， 那么第二个数就不能选8， 因为如果选了8就是[2， 8， 2], 就会跟[2, 2, 8]形成重复。只有升序才能保证不重复
                break;
            }
            if (remain % i == 0) {
                temp.add(i);
                dfs(i, remain / i, temp, results);
                temp.remove(temp.size() - 1);
            }
        }
        temp.add(remain);//因为22行剪枝剪多了，当i = remain的时候，应该是可以加进去的，但是remain/remain = 1, remain > 1, 在22行就被剪枝掉了. eg. 32 = 2*2*8, 到最后一层的时候i = 8, remain = 8, 这时候i > remain/remain 会被break掉，那就会在这个pick up
        dfs(remain, 1, temp, results);
        temp.remove(temp.size() - 1);
    }
}