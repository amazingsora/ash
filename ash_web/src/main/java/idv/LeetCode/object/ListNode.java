package idv.LeetCode.object;

import org.apache.commons.lang3.StringUtils;

public class ListNode {
	public int val;
	public ListNode next;
	public ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}
	
}
