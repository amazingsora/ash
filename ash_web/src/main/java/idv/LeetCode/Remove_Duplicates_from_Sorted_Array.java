package idv.LeetCode;

public class Remove_Duplicates_from_Sorted_Array {
	 public int removeDuplicates(int[] nums) {
		 if (nums.length<2) return nums.length;
		 int j =0;
		 for(int i = 0 ;i<nums.length;i++) {
			 if(nums[i]!=nums[j]) {
				 j++;
				 nums[j]=nums[i];
			 }
			 
		 }
		 return j+1;
	    }
	 public static void main(String[] args) {
		 int[] a = {2,1,2,1,2,1};
		 System.out.println(new Remove_Duplicates_from_Sorted_Array().removeDuplicates(a));
		
	}
}
