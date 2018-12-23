//array of treenodes, determine if all the nodes belongs to a tree and have all nodes of that tree
    public static boolean validTree(TreeNode[] nodes) {
        if (nodes == null || nodes.length == 0) {
            return true;
        }
        Map<TreeNode, Integer> indegree = new HashMap<>();
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();

        for (TreeNode node : nodes) {
            graph.put(node, new ArrayList<>());
            indegree.put(node, 0);
        }
        for (TreeNode node : nodes) {
            if (node.left != null) {
                graph.get(node).add(node.left);
                indegree.put(node.left, indegree.get(node.left) + 1);
            }
            if (node.right != null) {
                graph.get(node).add(node.right);
                indegree.put(node.right, indegree.get(node.right) + 1);
            }
        }
        int count = 0;
        TreeNode root = null;
        for (TreeNode key : indegree.keySet()) { //check if it's forest
            if (indegree.get(key) == 0) {
                count++;
                root = key;
                if (count > 1) {
                    return false;
                }
            }
        }
        Set<TreeNode> visited = new HashSet<>();
        visited.add(root);
        //check circle, and check if it's forest(a tree + single point)
        boolean result = treeDFS(visited, root);
        if (!result) {
            return false;
        }
        return graph.keySet().size() == visited.size();
    }

    private static boolean treeDFS(Set<TreeNode> visited, TreeNode root) {
        if (root.left != null) {
            if (visited.contains(root.left)) {
                return false;
            }
            visited.add(root.left);
            treeDFS(visited, root.left);
        }
        if (root.right != null) {
            if (visited.contains(root.right)) {
                return false;
            }
            visited.add(root.right);
            treeDFS(visited, root.right);
        }
        return true;
    }