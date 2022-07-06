package idv.LeetCode;
/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
 */

import idv.LeetCode.object.TreeNode;

// @lc code=start
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode() {} TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left
 * = left; this.right = right; } }
 */
public class ConvertSortedArraytoBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] nums) {
		return setNode(nums, 0, nums.length - 1);

	}

	public TreeNode setNode(int[] nums, int start, int end) {

		if (start == end) {
			return null;
		}
		int min = start + (end - start) / 2;

		TreeNode node = new TreeNode(nums[min]);
		node.setLeft(setNode(nums, (start + 1), end));
		node.setRight(setNode(nums, start, (end - 1)));

		return node;

	}

}
