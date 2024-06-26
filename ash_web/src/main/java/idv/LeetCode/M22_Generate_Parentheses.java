package idv.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class M22_Generate_Parentheses {
	public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        
		genList(list, "", 0, 0, n);
                
        return list;
    }
	
	private void genList(List<String> list,String s, int lcount,int rcount,int n) {
		if(lcount==n&&rcount==n) {
			list.add(s);
			return;
		}
		if(n>lcount) {
			genList(list, s+"(", lcount+1, rcount, n);
		}
		
		if(lcount>rcount) {
			genList(list, s+")", lcount, rcount+1, n);
		}
	}
	
	public static void main(String[] args) {
		M22_Generate_Parentheses a = new M22_Generate_Parentheses();
		
		System.out.println(a.generateParenthesis(1));
		
		
		
		
		
	}
}
