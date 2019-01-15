//409. Longest Palindrome
class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                count += 2;
                continue;
            }
            set.add(c);
        }
        if (set.size() > 0) {
            count += 1;
        }
        return count;
    }
}

//516. Longest Palindromic Subsequence
// eg. input: bbbab, output: 4
class Solution {
    public int longestPalindromeSubseq(String s) {    
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); //要么不包括i, 要么不包括j
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}

/* 算法：跟Longest Palindrome string的dp答案一样是区间版dp
** 时间复杂度：O(n^2) 
** 空间复杂度：O(n^2) */

//5. Longest Palindromic Substring
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        int start = 0, longest = 1;
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (isPalindrome[i][i + 1]) {
                start = i;
                longest = 2;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && 
                    s.charAt(i) == s.charAt(j);
                    
                if (isPalindrome[i][j] && j - i + 1 > longest) {
                    start = i;
                    longest = j - i + 1;
                }
            }
        }
        
        return s.substring(start, start + longest);
    }
}

/* 算法：区间类动态规划，isPalindrome[i][j]表示再区间i-j之间是否为Palindrome
** 时间复杂度：O(n^2) 
** 空间复杂度：O(n^2) */

//125. Valid Palindrome
//Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
        	return true;
        }
        s = s.toLowerCase();
        int front = 0;
        int end = s.length() - 1;
        
        while (front < end) {
        	while (front < s.length() && !isValidChar(s.charAt(front))) {
        		front++;
        	}
        	if (front == s.length()) {
        		return true;
        	}
        	while (end >= 0 && !isValidChar(s.charAt(end))) {
        		end--;
        	}
        	if (end < 0) {
        		return true;
        	}
        	if (s.charAt(front) != s.charAt(end)) {
        		return false;
        	} else {
        		front++;
        		end--;
        	}
        }
        return true;
    }
    
    private boolean isValidChar(char c) {
		return Character.isLetter(c) || Character.isDigit(c);
	}
}

//674. Longest Continuous Increasing Subsequence
//input: [1, 3, 5, 4, 7], output: 3
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int answer = 1;
        
        // from left to right
        int length = 1; // just A[0] itself
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }
        return answer;
    }
}

/* 算法：普通dp,从左往右和从右往左都遍历一边 */

//300. Longest Increasing Subsequence
class Solution {
    public int lengthOfLIS(int[] nums) {
        /* 算法：f[j] = 以nums[j]为结尾的最长上升子序列长度 
        ** 时间复杂度：O(n^2), 空间复杂度：O(n) */
        if (nums == null || nums.length == 0) {
            return 0;
        }        
        int n = nums.length;
        int[] f = new int[n];
        
        int max = 0;
        for (int j = 0; j < n; j++) {
            f[j] = 1;
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) {
                    f[j] = Math.max(f[j], f[i] + 1);
                }
            }
            max = Math.max(max, f[j]);
        }
        return max;
        
        
        
//         if (nums == null || nums.length == 0) {
//             return 0;
//         }
//         int[] minLast = new int[nums.length + 1];
//         minLast[0] = Integer.MIN_VALUE; //因为是算长度而不是index，就忽略0这个index,也是为了避免溢出
//         for (int i = 1; i < nums.length + 1; i++) {
//             minLast[i] = Integer.MAX_VALUE;
//         }
//         //find the first minLast >= nums[i]
//         for (int i = 0; i < nums.length; i++) {
//             int index = binarySearch(minLast, nums[i]);
//             minLast[index] = nums[i];
//         }
//         for (int i = nums.length; i >= 0; i--) {
//             if (minLast[i] != Integer.MAX_VALUE) {
//                 return i;
//             }
//         }
//         return 0;
//     }
    
//     private int binarySearch(int[] minLast, int num) {
//         int start = 0, end = minLast.length - 1;
//         while (start + 1 < end) {
//             int mid = start + (end - start) / 2;
//             if (minLast[mid] <= num) {
//                 start = mid;
//             } else {
//                 end = mid;
//             }
//         }
//         if (minLast[start] >= num) {
//             return start;
//         }
//         return end;
//     }
    }      
}
/* 算法：这个idea来源于，找start of the increasing subsequence, 10>9所以一定从9开始。然后对于每一个数都是想找左边的第一个比他大的数，然后替换掉它

minLast数组实际上记录上升的子序列，minLast = min是为了不越界。遍历每个数，在minLast里面找到第一个比他大的数，然后替换掉(只有替换掉才能保证上升趋势)
** 时间复杂度：用binary search的方法实现O(nlogn)的时间复杂度。  
** 空间复杂度：O(n) */

//128. Longest Consecutive Sequence
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int maxLength = 0;
        
        for (int num : set) {
            if (!set.contains(num-1)) {
                int currNum = num;
                int currLength = 1;
                
                while (set.contains(currNum+1)) {
                    currNum += 1;
                    currLength += 1;
                }
                maxLength = Math.max(maxLength, currLength);
            }
        }
        return maxLength;
    }
}

//398. Longest Continuous Increasing Subsequence II
/* Given a matrix:

[
  [1 ,2 ,3 ,4 ,5],
  [16,17,24,23,6],
  [15,18,25,22,7],
  [14,19,20,21,8],
  [13,12,11,10,9]
]
return 25 */
public class Solution {
    /**
     * @param matrix: A 2D-array of integers
     * @return: an integer
     */
    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] memo = new int[n][m];
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int len = dfs(matrix, i, j, memo);
                max = Math.max(max, len);
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int x, int y, int[][] memo) {
        int n = matrix.length;
        int m = matrix[0].length;
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int max = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] > matrix[x][y]) {
                int len = dfs(matrix, nx, ny, memo) + 1;
                max = Math.max(max, len);
            }
        }
        memo[x][y] = max;
        return max;
    }
}

    public String longestCommonSubstring(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        int n = s1.length();
        int m = s2.length();

        int[][] f = new int[n + 1][m + 1];

        int max = Integer.MIN_VALUE;
        int index1 = 0, index2 = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    if (max < f[i][j]) {
                        max = f[i][j];
                        index1 = i - 1;
                        index2 = j - 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (index1 >= 0 && index2 >= 0) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                break;
            }
            sb.append(s1.charAt(index1));
            index1--;
            index2--;
        }
        return sb.reverse().toString();
    }


