package org.ash_javaTest;

import java.util.ArrayList;
import java.util.List;

public class TestClass implements TestInterface{

	@Override
	public void test() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test(String a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test(String a, int b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test(List<?> a) {
		for(Object obj:a) {
			if(obj instanceof String) {
				System.out.println("這是字串: "+obj);

			}

			else if(obj instanceof Integer) {
			}

		}

		
	}
	public static void main(String[] args) {
		List <String> list = new ArrayList<>();
		System.out.println(799%800);
	}

}
