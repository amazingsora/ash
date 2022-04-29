package org.ash_javaTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections4.map.HashedMap;

public class calender {
	public static void main(String[] args) {
		List<String> a = new LinkedList<String>();
		a.add("ssss");
		a.add("aaass");
		a.add("aaaes");

		for(String s:a) {
			System.out.println("SSS===>"+formatString(s,8)+"EEEE");
			
			
		}

	}
	public static String formatString(String oriStr,int mun) {
		String patten = "%1$-"+mun+"s";
		String str = String.format(patten, oriStr);
		return str;
	}
}
