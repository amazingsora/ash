package org.ash_javaTest;

import java.util.Arrays;

public class Sort {
	public static void main(String[] args) {
		int arr[] = {15,2,3,7,8,9};

		Sort.selectSort(arr);
		System.out.println(Arrays.toString(arr));

		
	}
	
	private static void selectSort (int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			int min  = arr[i];
			int index = i;
			for (int j = i+1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					index = j;
				}
			}
			
			int temp = arr[i];
			arr[i]= arr[index];
			arr[index]=temp;
			
			
			System.out.println(Arrays.toString(arr));
		}
		
		
		
		
		
	}
	
	
}
