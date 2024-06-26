package idv.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class M18_4Sum {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		boolean breakflag = false;
		
		for(int i = 0;i<nums.length;i++) {
			if(breakflag) {
				break;
			}
			int iv = nums[i];
			for(int j=i+1;j<nums.length;j++) {
				int jv = nums[j];
				int l = j+1;
				int r = nums.length-1;
				int max = Integer.MAX_VALUE;
				if(breakflag) {
					break;
				}
				while(l<r) {
					System.out.println("l-->"+l);
					System.out.println("r-->"+r);
					int rv = nums[r];
					int lv = nums[l];
					long sum = (long)iv+jv+rv+lv;
					System.out.println(sum);
					if(sum>Math.abs(max)) {
						break;
					}
					long lock = sum-target;
					if(lock==0) {
						System.out.println(lock);
						List<Integer> result = new ArrayList<Integer>();
						result.add(iv);
						result.add(jv);
						result.add(rv);
						result.add(lv);
						results.add(result);
						r--;
						if(rv==iv) {
							breakflag = true;
							break;
						}
						if(l==r) {
							r = nums.length-1;
							l ++;
						}
					}else {
						
						if(lock>0) {
							r--;
						}else {
							l++;
						}
					}
				}
			}
		}
        return results;
    }
	public List<List<Integer>> fourSumKai(int[] nums, int target) {	
		Arrays.sort(nums);
		boolean breakflag = false;		
		List<List<Integer>> results = new ArrayList<List<Integer>>();		
		for (int i = 0; i < nums.length; i++) {
			
			if(breakflag) {
				break;
			}
			for (int j = i+1; j < nums.length; j++) {
				
				long ij = (long) nums[i]+nums[j];
				if(ij>Integer.MAX_VALUE) {
					break;
				}
				int l = j+1; 
				int r =	nums.length-1;
				
				while (r>l) {
					int lr = nums[l]+nums[r];
					System.out.println(nums[i]);
					System.out.println(nums[j]);
					System.out.println(nums[l]);
					System.out.println(nums[r]);

					System.out.println("----------");
					if((lr+ij)==target) {
						List<Integer> result = new ArrayList<Integer>();
						result.add(nums[i]);
						result.add(nums[j]);
						result.add(nums[l]);
						result.add(nums[r]);
						r--;	

						if(results.contains(result)) {
							continue;
						}else {
							results.add(result);
						}
						
						
						if(nums[i] ==nums[r]) {
							breakflag = true;
							break;
						}
						
					}
					if((lr+ij)>target) {
						r--;
					}
					if((lr+ij)<target) {
						l++;
					}
				}
				if (breakflag) {
					break;
				}
			}
		}
        return results;

	}
	
	
	public static void main(String[] args) {
		M18_4Sum r = new M18_4Sum();

		
		int arr[] = {0,5,-8,-7,4,8,-4,3,9,7,8,10,3,-6,3,7,10,0};
			//{-2,-1,-1,1,1,2,2};
			//{1,0,-1,0,-2,2};
			//{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 ,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 ,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 ,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 ,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 ,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 ,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2 ,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};//-2 -1 0 0 1 2
		Arrays.sort(arr);

		System.out.println("arr" + Arrays.toString(arr));

				System.out.println("result===>"+r.fourSumKai(arr, -12));
	}
}
