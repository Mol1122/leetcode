/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */

//Method 1
public class Solution {
  int ans = Integer.MIN_VALUE;
  int diff = Integer.MAX_VALUE;
  public int closest(TreeNode root, int target) {
      //iterative, time: O(height), space: O(1)
      int result = root.key;
      while (root != null) {
          if (root.key == target) {
              return root.key;
          } else {
              if (Math.abs(root.key - target) < Math.abs(result - target)) {
                  result = root.key;
              }
              if (target < root.key) {
                  root = root.left;
              } else {
                  root = root.right;
              }
          }
      }
      return result;
    
      //lowerBound, upperBound
      //time: O(h), space: O(h)
  /*    if (root == null) {
          return -1;
      }
      TreeNode lowerbound = getLowerBound(root, target);
      TreeNode upperbound = getUpperBound(root, target);
      if (lowerbound == null) {
          return upperbound.key;
      }
      if (upperbound == null) {
          return lowerbound.key;
      }
      return Math.abs(target - lowerbound.key) <= Math.abs(target - upperbound.key) ? lowerbound.key : upperbound.key;  */
      //in-order traversal: time: O(n), space:O(n)
   /*   if (root == null) {
          return -1;
      }
      helper(root, target);
      return ans;  */
  }
}

//Method 2
class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        TreeNode lowerBound = getLowerBound(root, target);
        TreeNode upperBound = getUpperBound(root, target);

        if (lowerBound == null) {
            return upperBound.val;
        }
        if (upperBound == null) {
            return lowerBound.val;
        }
        return Math.abs(target - lowerBound.val) <= Math.abs(upperBound.val - target) ? lowerBound.val : upperBound.val;
    }

    private TreeNode getUpperBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (target == root.val) {
            return root;
        }
        if (target >= root.val) {
            return getUpperBound(root.right, target);
        }
        TreeNode left = getUpperBound(root.left, target);
        return left == null ? root : left;
    }

    private TreeNode getLowerBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (target == root.val) {
            return root;
        }
        if (target < root.val) {
            return getLowerBound(root.left, target);
        }
        TreeNode right = getLowerBound(root.right, target);
        return right == null ? root : right;
    }
}

//Method 3
class Solution {
    public int closestValue(TreeNode root, double target) {
        double[] diff = {Double.MAX_VALUE};
        int[] result = {-1};
        traverse(root, target, diff, result);
        return result[0];
    }

    private void traverse(TreeNode root, double target, double[] diff, int[] result) {
        if (root == null) {
            return;
        }
        if (Math.abs(root.val - target) < diff[0]) {
            diff[0] = Math.abs(root.val - target);
            result[0] = root.val;
        }
        if (Math.abs(root.val - target) == diff[0] && result[0] > root.val) {
            result[0] = root.val;
        }
        traverse(root.left, target, diff, result);
        traverse(root.right, target, diff, result);
    }
}
//time: O(n), space: O(1)