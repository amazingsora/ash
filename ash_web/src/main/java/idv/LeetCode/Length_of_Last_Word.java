package idv.LeetCode;

public class Length_of_Last_Word {
	public int lengthOfLastWord(String s) {
		int result = 0;
		int max = 0;
		String strs[] = s.split(" ");
		if (strs.length == 1) {
			result = s.trim().length();
		} else {
			result = strs[strs.length-1].trim().length();

		}
		return result;

	}

	public static void main(String[] args) {
		Length_of_Last_Word s = new Length_of_Last_Word();
		String str = "Today is a nice day";

		System.out.println(s.lengthOfLastWord(str));
	}

}
