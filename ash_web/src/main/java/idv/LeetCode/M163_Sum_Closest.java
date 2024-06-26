package idv.LeetCode;

import java.util.Arrays;

import org.apache.commons.math3.stat.descriptive.summary.Sum;

public class M163_Sum_Closest {
	public int threeSumClosest(int[] nums, int target) {
		int output = 0;
		Arrays.sort(nums);
		int maxvalue = Integer.MAX_VALUE;

		for(int i = 0 ;i<nums.length-2;i++) {
			int iv = nums[i];
			int r = nums.length-1;
			int l = i+1;
			while(r>l) {
				int rv = nums[r];
				int lv = nums[l];
				int sumvalue = iv+rv+lv;		
				if(sumvalue ==target) {
					return target;
				}
				int d = Math.abs(sumvalue-target);
				
				if(d<maxvalue) {
					maxvalue = d;
					output = sumvalue;
				}
				if(sumvalue>target) {
					r--;
				}else {
					l++;
				}
			}
		}	
        return output;
    }
	
	
	public static void main(String[] args) {
		M163_Sum_Closest x = new M163_Sum_Closest();
		
		//int arr[] = {-1,2,1,-4};
		int arr[] = {1,1,1,0};		
		Arrays.sort(arr);
		System.out.println(arr);
    	System.out.println(x.threeSumClosest(arr,-100));
	}
}

