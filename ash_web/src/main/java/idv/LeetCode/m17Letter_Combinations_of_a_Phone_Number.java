package idv.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class m17Letter_Combinations_of_a_Phone_Number {
    final String dictionary[]= {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
	
	public List<String> letterCombinations(String digits) {
    	List<String> list = new ArrayList<>();
    	if(digits.length()<1) {
    		return list;
    	}
    	String nextWord = "";

    	if(digits.length()>0) {
        	nextWord = digits.substring(1,digits.length());
    	}
    	
    	System.out.println("nextWord===>"+nextWord);
    	char digit[] = digits.toCharArray();
    	
    	
    	
    	char word[] = dictionary[Integer.valueOf(String.valueOf(digit[0]))].toCharArray();
    		for(int j = 0;j<word.length;j++) {
    			String a = String.valueOf(word[j]);
        		System.out.println("a=="+a);
        		System.out.println("nextWord==="+nextWord);
        		if(nextWord.length()<1) {
        			System.out.println("list.add(a)");
        			list.add(a);
            	}else {
            		List<String> listnext =  letterCombinations(nextWord);
            		for (String w: listnext) {
            			String nw = a+w;
            			list.add(nw);
            		}
            	}
    		}
		System.out.println(list.toString());

    	return list;
    }
	public static void main(String[] args) {
		String digits = "";
    	String nextWord = digits.substring(1,digits.length());
		System.out.println(nextWord);

		m17Letter_Combinations_of_a_Phone_Number a = new m17Letter_Combinations_of_a_Phone_Number();
		
		System.out.println(a.letterCombinations(digits).toString());
		
		
		
		
		
		
	}
}
