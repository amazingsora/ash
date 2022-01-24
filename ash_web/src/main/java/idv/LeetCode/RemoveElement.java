package idv.LeetCode;

public class RemoveElement {
	public int removeElement(int[] nums, int val) {
		int i = 0;
		for(int j = 0;j<nums.length;j++) {
			if(nums[j]!=val) {
                nums[i] = nums[j];

				i++;
			}
		
		}
		return i;
	}
	public static void main(String[] args) {
		 int[] a = {1};
		 System.out.println(new RemoveElement().removeElement(a,1));
		
	}
}
