package idv.LeetCode;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
*/
public class Longest_Common_Prefix {

	public String longestCommonPrefix(String[] strs) {
		StringBuffer result = new StringBuffer();
		String tarWords = "";
		List<String> set = new LinkedList<String>();
		String a = strs[0];
		int maxlength = 0;
		int index = 0;
		int setindex = 0;
		int firstindex = 0;
		if (a.length() > 0 && strs.length>1) {
			for (int i = 0; i < a.length(); i++) {
				String word = a.substring(i, i + 1);
				boolean flag = true;
				for (int j = 1; j < strs.length; j++) {
					if (!(strs[j].indexOf(word) > -1)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					if (!tarWords.equals("")) {
						tarWords += word;

					} else {
						setindex++;

						tarWords = word;

					}
				} else {
					if (maxlength < tarWords.length()) {
						maxlength = tarWords.length();
						index = setindex - 1;
						if(index<=firstindex) {
							firstindex = index;
						}
						
					}
					set.add(tarWords);
					tarWords = "";
				}
				if(tarWords.equals(a)) {
					return tarWords;

				}
			}
		}
		else if(strs.length==1) {
			return strs[0];

		}
		if(set.size()>0) {
			return set.get(firstindex);

		}
		return "";
	}

	public static void main(String[] args) {
		String[] strs = { "flower", "flow", "flight" };
		String[] str2 = { "dog", "racecar", "car" };
		String[] str3 = { "" };
		String[] str4 = { "a" };
		String[] str5 = {"flower","flower","flower","flower"};
		String[] str6 = {"flower","fkow"};

		System.out.println(new Longest_Common_Prefix().longestCommonPrefix(strs));
	}
}
