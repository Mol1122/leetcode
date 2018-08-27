/* mock interview 1.
BST:     6
		6  7
	5	5   7   count the most frequent number  */


//recurrsion 
int currVal = -1;
int currCount = 0;
int maxVal = -1;
int maxCount = 0;

public int mostFrequent(TreeNode root) {
	if (root == null) {
		return maxVal;
	}
	inorder(root);
	return maxVal;
}

private void inorder(TreeNode curr) {
	if (curr == null) {
		return;
	}
	inorder(curr.left);
	if (curr.val != currVal) {
		maxCount = Math.max(currCount, maxCount);
		if (maxCount == currCount) {
			maxVal = currVal;
		}
		currVal = curr.val;
		currCount = 0;

	}
	currCount++;
	inorder(curr.right);
}

//non-recurrsion
int currVal = -1;
int currCount = 0;
int maxVal = -1;
int maxCount = 0;

public int mostFrequent(TreeNode root) {
	if (root == null) {
		return maxVal;
	}
	Stack<TreeNode> stack = new Stack<>();
	while (root != null) {
		stack.push(root);
		root = root.left;
	}

	while (!stack.isEmpty()) {
		TreeNode node = stack.peek();
		if (node.val != currVal) {
			maxCount = Math.max(maxCount, currCount);
			if (maxCount == currCount) {
				maxVal = curr.val;
			}
			currVal = node.val;
			currCount = 0;
		}

		if (node.right == null) {
			node = stack.pop();
			while (!stack.isEmpty() && stack.peek().right == node) {
				node = stack.peek();
			}
		} else {
			node = node.right;
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
		}
	}
	return maxVal;
}