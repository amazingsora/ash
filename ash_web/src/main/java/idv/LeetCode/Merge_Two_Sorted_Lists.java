package idv.LeetCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import idv.LeetCode.object.ListNode;

public class Merge_Two_Sorted_Lists {
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
		ListNode result = null;
		List<Integer> list = new LinkedList<Integer>();
		list = getList(list1, list);
		list = getList(list2, list);
		Collections.sort(list);
		System.out.println(list);
		if (list.size() > 0) {
			result = new ListNode();
			result.val = list.get(0);
			sortListNode(result, list, 0);

		}

		return result;
	}

	private List<Integer> getList(ListNode obj, List<Integer> list) {
		if (obj != null ) {
			list.add(obj.val);
			if (null != obj.next) {
				return getList(obj.next, list);
			}
		}

		return list;

	}

	private void sortListNode(ListNode result, List<Integer> list, int index) {
		index++;
		ListNode next = new ListNode();
		if (index < list.size()) {
			next.val = list.get(index);
			;
			result.next = next;
			sortListNode(next, list, index);
		}

	}

	public static void main(String[] args) {
		ListNode a = new ListNode();
		ListNode b = new ListNode();
		System.out.println(new Merge_Two_Sorted_Lists().mergeTwoLists(a,b));
	}
}
