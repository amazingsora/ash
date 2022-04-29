package idv.LeetCode;

public class Maximum_Subarray {
	 public int maxSubArray(int[] nums) {
	        return compare(nums, 0, nums.length-1);
	    }
	 private int compare(int[] nums,int l,int r) {
		if(l>r) return Integer.MIN_VALUE;
		if(l==r)return nums[l];
		int mid = l+(r-l)/2;
		return Math.max(crossMidNum(nums,l,r), Math.max(compare(nums,l,mid-1), compare(nums,mid+1,r)));
		 
		 
		 
	 }
	 private int crossMidNum(int[] nums,int l,int r) {
		 int mid = l + (r - l)/2;

		    int lmax = nums[mid], lg =  nums[mid];
		    for(int i = mid -1; i >= l; i--) {
		        lmax += nums[i];
		        lg = Math.max(lmax, lg);
		    }

		    int rmax = nums[mid], rg =  nums[mid];
		    for(int i = mid +1; i <= r; i++) {
		        rmax += nums[i];
		        rg = Math.max(rmax, rg);
		    }
			 System.out.println("l===>"+l+"---R==>"+r+"---result===>"+(lg + rg - nums[mid]));

		    return lg + rg - nums[mid];
	 }
	 public static void main(String[] args) {
		 Maximum_Subarray m = new Maximum_Subarray();
		 int x[] = {-1,3,5,-5,3,4};
		 System.out.println(m.maxSubArray(x));
	}
}
