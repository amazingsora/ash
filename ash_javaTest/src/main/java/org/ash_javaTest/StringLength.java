package org.ash_javaTest;

import java.io.UnsupportedEncodingException;

public class StringLength {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String xxx = "野格炸彈";
		System.out.println("中文字長度 :"+xxx.length());
		System.out.println("中文字節長度 :"+xxx.getBytes("UTF-8").length);
		String eee = "ABCDE";
		System.out.println("英文長度 :"+eee.length());
		System.out.println("字節長度 :"+eee.getBytes("UTF-8").length);


	}

}
