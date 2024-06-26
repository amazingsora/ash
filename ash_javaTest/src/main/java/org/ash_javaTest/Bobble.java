package org.ash_javaTest;

import java.util.Arrays;

public class Bobble {
	public static void main(String[] args) {
		int arr[] = {15,2,3,7,8,9};
		
		System.out.println(Arrays.toString(arr));
		int count = 0;
		while(count < arr.length){
			for(int i = 0;i<arr.length;i++) {
				
				if (i+1 == arr.length) {
					break;
				}
				int j =i+1;
				if(arr[i]>arr[j]) {
					int temp = arr[j];
					arr[j] = arr[i];
					arr[i] = temp;
				}
			}
			System.out.println("c"+count);
			
			count ++;		
		}	
		
		System.out.println(Arrays.toString(arr));

	}

	

}
