package idv.LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Valid_Parentheses{
	public static void main(String[] args) {
		String str = "[{()}]{{";
		System.out.println(new v20().isValid(str));
	}

}

class v20 {
	List<String> lw = new LinkedList<String>();
	List<String> rw = new LinkedList<String>();
	Map<String, String> map = new HashMap<>();

	public boolean isValid(String s) {
		initList();
		if (null == s || s.length() == 0) {
			return false;
		}
		if (!(s.length() % 2 == 0)) {
			return false;

		}
		// 檢核長度
		if (rw.contains(String.valueOf(s.charAt(0)))) {
			return false;
		}
		// 檢核
		List<String> tarW = new LinkedList<String>();
		int count =1;
		String firstW = String.valueOf(s.charAt(0));
		String lastW = String.valueOf(s.charAt(s.length()-1));
		if(lw.contains(lastW)) {
			return false;
			
		}
		tarW.add(firstW);
		System.out.printf("第%s數 為: %s \n", 1,tarW.get(0));
		for (int i = 1; i < s.length(); i++) {
			String w = String.valueOf(s.charAt(i));
			System.out.printf("第%s數 為: %s \n", i+1,w);
			
			if(tarW.size()>0&&w.equals(map.get(tarW.get(tarW.size()-1)))) {
				tarW.remove(tarW.size()-1);
				count--;
				
				
			}
			else if(lw.contains(w)) {
				tarW.add(w);
				
				count++;
		
			}else {
				return false;

			}

		}
		if(count!=0) {
			return false;

		}
		return true;

	}

	private void initList() {
		if (lw.size() == 0) {
			lw.add("(");
			lw.add("[");
			lw.add("{");
		}
		if (rw.size() == 0) {
			rw.add(")");
			rw.add("]");
			rw.add("}");
		}
		if (map.size() == 0) {
			map.put("(", ")");
			map.put("[", "]");
			map.put("{", "}");

		}

	}

}