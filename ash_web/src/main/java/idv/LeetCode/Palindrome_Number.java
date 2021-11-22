package idv.LeetCode;

/**
 * 數字顛倒或正常皆為一樣
 * Given an integer x, return true if x is palindrome integer.
 * 
 * An integer is a palindrome when it reads the same backward as forward. For
 * example, 121 is palindrome while 123 is not.
 * 
 * Example 1:
 * Input: x = 121 Output: true 
 * 
 * Example 2:
 * Input: x = -121 Output: false Explanation: From left to right, it reads -121.
 * From right to left, it becomes 121-. Therefore it is not a palindrome.
 * 
 * Example 3:
 * Input: x = 10 Output: false Explanation: Reads 01 from right to left.
 * Therefore it is not a palindrome. 
 * 
 * Example 4:
 * Input: x = -101 Output: false
 */

public class Palindrome_Number {
	 public static boolean isPalindrome(int x) {
	        boolean flag = true;
	        String oriValue = String.valueOf(x);
	        char oriArr[] = oriValue.toCharArray();
	        int tar = oriArr.length-1;
	        for(int i =0;i<oriArr.length;i++) {
	        	System.out.println(String.valueOf(oriArr[i])+"___"+String.valueOf(oriArr[tar]));
	        	if(!String.valueOf(oriArr[i]).equals(String.valueOf(oriArr[tar]))) {
	        		flag =  false;
	        		break;
	        	}
	        	else {
	        		tar--;
	        	}
	        }
	        
	        return flag;
	    }
	 public static void main(String[] args) {
		System.out.println(isPalindrome(-1248));
	}
}
