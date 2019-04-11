/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        } 
        TreeMap<Integer, PriorityQueue<Pair>> map = new TreeMap<>(); //用来存return value
        Queue<Pair> queue = new LinkedList<>(); //用来遍历整棵树
        queue.offer(new Pair(root, 0, 0));
        
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            //map.putIfAbsent(p.x, new ArrayList<>());
            map.putIfAbsent(p.x, new PriorityQueue<>(new Comparator<Pair>() {
                public int compare(Pair a, Pair b) {
                    if (a.x == b.x && a.y == b.y) {
                        return a.node.val - b.node.val;
                    }
                    return b.y - a.y;
                }
            }));
      
            map.get(p.x).offer(p);
            if (p.node.left != null) {
                queue.offer(new Pair(p.node.left, p.x - 1, p.y - 1));
            }
            if (p.node.right != null) {
                queue.offer(new Pair(p.node.right, p.x + 1, p.y - 1));
            }
        }
        for(PriorityQueue<Pair> level : map.values()) {
            List<Integer> temp = new ArrayList<>();
            //Collections.sort(level);
            while (!level.isEmpty()) {
                temp.add(level.poll().node.val);
            }
            results.add(temp);
        }
        // for(List<Pair> level : map.values()) {
        //     List<Integer> temp = new ArrayList<>();
        //     Collections.sort(level);
        //     for(Pair p : level) {
        //         temp.add(p.node.val);
        //     }
        //     results.add(temp);
        // }
        return results;
    }
}

class Pair implements Comparable<Pair> {
    TreeNode node;
    int x, y;
    
    public Pair(TreeNode node, int x, int y) {
        this.node = node;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Pair b) {
        if(this.x == b.x && this.y == b.y)
            return this.node.val - b.node.val;
        return b.y - this.y;
    }
}

/* 时间复杂度：O(nlogn)
** 空间复杂度：O() */