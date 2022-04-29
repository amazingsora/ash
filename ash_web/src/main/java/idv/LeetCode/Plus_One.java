package idv.LeetCode;

import java.util.Arrays;

public class Plus_One {
	public int[] plusOne(int[] digits) {
		System.out.println(digits.length == 1 && digits[0] == 0);
		if (digits.length == 1 && digits[0] == 0 ) {
			int[] res = new int[1];
			digits[0] = 1;
			return res;
		}
		for (int i = digits.length - 1; i >-1; i--) {
			System.out.println(i);
			if (digits[i] < 9) {
				digits[i] += 1;
				return digits;
			}
			digits[i] = 0;
		}
		int[] res = new int[digits.length + 1];
		res[0] = 1;
		return res;
	}

	public static void main(String[] args) {
		int nums[] = { 1 };
		Plus_One s = new Plus_One();
		System.out.println(Arrays.toString(s.plusOne(nums)));
	}

}
