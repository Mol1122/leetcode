/**
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
] */

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Map<Integer, List<Integer>> hash = new HashMap<>();
        Queue<Integer> qCol = new LinkedList<>();
        Queue<TreeNode> qNode = new LinkedList<>();
        qCol.offer(0);
        qNode.offer(root);
        
        while (!qCol.isEmpty()) {
            int c = qCol.poll();
            TreeNode node = qNode.poll();
            hash.putIfAbsent(c, new ArrayList<>());
            hash.get(c).add(node.val);
            
            if (node.left != null) {
                qCol.add(c - 1);
                qNode.offer(node.left);
            }
            if (node.right != null) {
                qCol.offer(c + 1);
                qNode.offer(node.right);
            }
        }
        for (int i = Collections.min(hash.keySet()); i <= Collections.max(hash.keySet()); i++) {
            results.add(hash.get(i));
        }
        return results;
    } 
}

/* 算法：碰到这种 Tree 的不规则访问问题，用hash来对应。 用queue来保存访问顺序
** 语法难点： Collections.min(hash.set()) 找到hash里面最小值 
** 时间复杂度： O(n), psace: O(n) */