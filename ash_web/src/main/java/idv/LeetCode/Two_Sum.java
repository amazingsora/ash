package idv.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_Sum {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer,Integer>map = new HashMap<>();
		int result[]= new int[2];
		for(int i = 0;i<nums.length;i++) {
			if(map.containsKey(target-nums[i])) {
				result[0]=map.get(target-nums[i]);
				result[1]=i;
			}
			
			map.put(nums[i], i);

		}
		return result;
	}
	public static void main(String[] args) {
		int p[] = {2,7,11,15};
		Two_Sum s = new Two_Sum();
		System.out.println(Arrays.toString(s.twoSum(p, 9)));
	}
}
