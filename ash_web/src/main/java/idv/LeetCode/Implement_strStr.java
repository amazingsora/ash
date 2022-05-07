package idv.LeetCode;

public class Implement_strStr {
	public int strStr(String haystack, String needle) {
		if ("".equals(needle))
			return 0;
		if ("".equals(haystack))
			return -1;

		int loc = 0;
		int x = 0;
		int searchWindex = 0;
		boolean flag = false;
		for (int i = 0; i < haystack.length(); i++) {
			for(int j = 0; j < needle.length(); j++) {
				char w = haystack.charAt(i+j);
				char checkW = needle.charAt(j);
				
				if(checkW == w) {
					
					searchWindex++;
				}else {
					searchWindex = 0;
					loc = -1;
				}

			}

		}
		if (flag) {
			return loc;
		} else {
			return -1;

		}

	}

	public static void main(String[] args) {
		String s = "mississippi";
		System.out.println(new Implement_strStr().strStr(s, "issip"));
	}
}
