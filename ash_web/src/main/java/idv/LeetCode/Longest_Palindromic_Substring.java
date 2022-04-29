package idv.LeetCode;

import java.util.LinkedList;
import java.util.List;

public class Longest_Palindromic_Substring {
	public static void main(String[] args) {
		String str = "aaas";
		System.out.println(new Solution().longestPalindrome(str));
	}
}

/**
 * Example 1:
 * 
 * Input: s = "babad" Output: "bab" Explanation: "aba" is also a valid answer.
 * Example 2:
 * 
 * Input: s = "cbbd" Output: "bb"
 */
class Solution {
	public String longestPalindrome(String s) {
		int tarIndex = 0;
		int maxlen = 0;
		for(int i =0 ;i<s.length();i++) {
			
			int len = Math.max(getlen(s,i,i),getlen(s,i,i+1));
			if(len>maxlen) {
				 maxlen = len;
				 tarIndex = i-(len-1)/2;
				
			}
			 
			
		}
		System.out.println("maxlen ==="+maxlen);
		return  s.substring(tarIndex,tarIndex+maxlen);
		
		
	}
	private int getlen(String str,int l,int r) {
		while(l>=0&&r<str.length()&&str.charAt(l)==str.charAt(r)) {
			l--;
			r++;
			
		}
		return r-l-1;
		
	}


}