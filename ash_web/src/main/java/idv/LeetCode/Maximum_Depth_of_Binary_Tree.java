package idv.LeetCode;

import idv.LeetCode.object.TreeNode;

public class Maximum_Depth_of_Binary_Tree {
	public int maxDepth(TreeNode root) {
		return root == null? 0:1+Math.max(maxDepth(root.right), maxDepth(root.left));

	}
}
