package idv.LeetCode;

public class M12_Integer_to_Roman {
	//final String roman[] = {"M","D","C","L","X","V","I"};
	final String roman[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	final int value[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public String intToRoman(int num) {
    	StringBuffer bf = new  StringBuffer();
    	if(num>0) {
    		for(int i = 0 ;i<value.length;i++) {
    		 if(num>=value[i]) {
        			int qlue = num/value[i];
        			int rValue = num%value[i];
    				for(int j = 0;j<qlue;j++) {
                		bf.append(roman[i]);
                	}
    				if(rValue ==1) {
            			bf.append(roman[value.length-1]);
            		}else {
            			bf.append(intToRoman(rValue));
            		}
    				break;
        		}
        	}
    	}
		return bf.toString();
    }
    public static void main(String[] args) {
    	M12_Integer_to_Roman x = new M12_Integer_to_Roman();
    	System.out.println(x.intToRoman(6));
    }
}
