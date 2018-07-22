public class Solution {
    /*
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param target: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int targer) {
        // List<List<Integer>> results = new ArrayList<>();
        // if (A == null || A.length == 0 || k < 1) {
        //     return results;
        // }
        // dfs(A, k, targer, 0, new ArrayList<>(), results);
        // return results;
        List<List<Integer>> results = new ArrayList<>();
        if (A == null || A.length == 0 || k <= 0) {
            return results;
        }
        helper(A, k, targer, 0, new ArrayList<>(), results);
        return results;
    }
    
    private void helper(int[] A, int k, int target, int index, 
                     List<Integer> subset, List<List<Integer>> results) {
        
        if (k == 0 && target == 0) {
            results.add(new ArrayList<>(subset));
            return;
        }
        if (k == 0 || index >= A.length) {
            return;
        }
        subset.add(A[index]);
        helper(A, k - 1, target - A[index], index + 1, subset, results);
        
        subset.remove(subset.size() - 1);
        helper(A, k, target, index + 1, subset, results);
    }
    
    // private void dfs(int[] A, int k, int target, int startIndex, List<Integer> subset, List<List<Integer>> results) {
    //     if (k == 0 && target == 0) {
    //         results.add(new ArrayList<>(subset));
    //         return;
    //     }
    //     if (k == 0) {
    //         return;
    //     }
        
    //     for (int i = startIndex; i < A.length; i++) {
    //         if (A[i] > target) {
    //             break;
    //         }
    //         subset.add(A[i]);
    //         dfs(A, k - 1, target - A[i], i + 1, subset, results);
    //         subset.remove(subset.size() - 1);
    //     }
    // }
}