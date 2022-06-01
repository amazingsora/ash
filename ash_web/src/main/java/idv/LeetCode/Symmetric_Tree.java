package idv.LeetCode;

import idv.LeetCode.object.TreeNode;

public class Symmetric_Tree {
	
    public boolean isSymmetric(TreeNode root) {
    	TreeNode p = root.right;
    	TreeNode q = root.left;
    	 
    	return isSameTree(p,q);
    	
    }
	
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p != null && q != null) {
			if (p.val == q.val) {
				return isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
			} else
				return false;
		} else {
			return false;
		}
	}
}
