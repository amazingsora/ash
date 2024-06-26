package idv.LeetCode;

public class E11_Roman_to_Integer {
	//final String roman[] = {"M","D","C","L","X","V","I"};
		final String roman[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		final String roman2[] = { "CM", "CD", "XC","XL", "IX", "IV" };
		final int value2[] = {-200, -200,-20, -20, -2, -2};

		final int value[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		public int romanToInt(String s) {
	        int v = 0;
//	        boolean flag2str =true; 
//	        String last2sub = s.substring(s.length()-1,s.length());
//	        if(s.length()>1) {
//	        	last2sub =s.substring(s.length()-2,s.length()) ;
//
//	        }
//	        
//	        if(last2sub.length()>1) {
//		        String a = last2sub.substring(0,1);
//		        String b = last2sub.substring(1);
//		        if(a.equals(b)) {
//		         flag2str = false;
//		        }
//	        }
//	        
//	        int dvalue = 1;
//	        int index = 0;
//	        boolean flag = false;
//	        if(flag2str) {
//	        	for(int i = 0; i<roman.length;i++) {
//		        	if(roman[i].equals(last2sub)) {
//		        		flag = true;
//		        		index = i;
//		        		dvalue=2;
//		        	}
//		        }
//	        }
//	        
//	        if(flag == false) {
//		        String lastStr =s.substring(s.length()-1,s.length()) ;
//		        for(int i = 0; i<roman.length;i++) {
//		        	if(roman[i].equals(lastStr)) {
//		        		flag = true;
//		        		index = i;
//		        	}
//		        
//		        }
//	        }
//	        if(flag) {
//	        	v=value[index];
//	        }
//	        if(s.length()-dvalue>0) {
//	        	v=v+romanToInt(s.substring(0,s.length()-dvalue));
//	        }
			//final String roman[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	        char crp[] = s.toCharArray();
        	for(int i = 0; i<roman2.length;i++) {
        		if(s.indexOf(roman2[i])>-1) {
        			v+=value2[i];
        			
        		}
        	}
        	for(int i = 0; i<crp.length;i++) {
        		switch (String.valueOf(crp[i])) {
				case "I": {
        			v+=1;
					break;
				}
				case "V": {
        			v+=5;
					break;
				}
				case "X": {
        			v+=10;
					break;
				}
				case "L": {
        			v+=50;
					break;
				}
				case "C": {
        			v+=100;
					break;
				}
				case "D": {
        			v+=500;
					break;
				}
				case "M": {
        			v+=1000;
					break;
				}
				default:
				}
	        }
	        return v;
	    }
		public static void main(String[] args) {
			E11_Roman_to_Integer x = new E11_Roman_to_Integer();
	    	System.out.println(x.romanToInt("IV"));
		}
}
