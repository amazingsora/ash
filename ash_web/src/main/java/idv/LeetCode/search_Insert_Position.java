package idv.LeetCode;

public class search_Insert_Position {
    public static int searchInsert(int[] nums, int target) {
        int result = 0;
        for(int x:nums) {
        	if(x>=target) {
        	break;	
        	}
    		result++;

        }
        return result;
    }
    public static void main(String[] args) {
    	int []nums = {1,3,5,6}; 
    	int target = 7;
    	System.out.println(searchInsert(nums,target));
	}
}
